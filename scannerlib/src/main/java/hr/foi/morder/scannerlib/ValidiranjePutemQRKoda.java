package hr.foi.morder.scannerlib;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission_group.CAMERA;

/**
 * A placeholder fragment containing a simple view.
 */
public class ValidiranjePutemQRKoda extends Fragment implements MetodaValidacijeDostave, ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private String sifra;
    Context thiscontext;

    public ValidiranjePutemQRKoda() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_scanner_start, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        thiscontext = getContext();

        scannerView = new ZXingScannerView(thiscontext);
        getActivity().setContentView(scannerView);
        Intent intent = getActivity().getIntent();
        sifra = intent.getStringExtra("Ocekivana vrijednost");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkPermission()) {
                Toast.makeText(thiscontext, "Permission is granted!", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void validirajNarudzbuDostave(final String result)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(ValidiranjePutemQRKoda.this);
            }
        });
        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                startActivity(intent);
            }
        });

        builder.setMessage(result);
        if(result.equals(String.valueOf(sifra))){
            builder.setMessage("Narudžba uspješno dostavljenja");
        }
        else {
            builder.setMessage("Kriva adresa");
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void handleResult(Result result)
    {
        final String scanResult = result.getText();
        this.validirajNarudzbuDostave(scanResult);
    }


    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(thiscontext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    /*
     * Asks phone/tablet user to allow camera scanning.
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
    }

    /*
     * Starts camera scanner if it is allowed.
     */
    public void onRequestPermissionsResult(int requestCode, String permission[], int grantResults[]) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(thiscontext, "Permission granted!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(thiscontext, "Permission is denied!", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                displayAlertMessage("You need to allow access for both permissions!",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkPermission())
            {
                if (scannerView == null)
                {
                    scannerView = new ZXingScannerView(thiscontext);
                    getActivity().setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else {
                requestPermission();
            }
        }
    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(thiscontext).setMessage(message).setPositiveButton("OK", listener).setNegativeButton("Cancel", null).create().show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public Fragment getFragment()
    {
        return this;
    }
}