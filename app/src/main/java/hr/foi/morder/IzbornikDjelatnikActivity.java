package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import hr.foi.morder.scannerlib.ScannerStart;

public class IzbornikDjelatnikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik_djelatnik);
    }

    public void OnClickViewOrders(View view) {
        Intent i = new Intent(this, PrikazStolovaActivity.class);
        startActivity(i);
    }

    public void OnClickSignOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OnClickStartQR(View view){
        Intent intent=new Intent(this, ScannerStart.class);
        startActivity(intent);
    }
}