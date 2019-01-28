package hr.foi.morder;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import hr.foi.morder.scannerlib.CodeGenerateFragment;

public class DostavaActivity extends AppCompatActivity implements CodeGenerateFragment.OnFragmentInteractionListener {

    ImageView imageView;
    Button button;
    FragmentManager manager;
    CodeGenerateFragment fragment;
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dostava);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.potvrdi);

        lista = new ArrayList<>();
        lista.add("probno");
        lista.add("probno2");

        manager = getSupportFragmentManager();
        fragment = (CodeGenerateFragment)manager.findFragmentById(R.id.fragLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.generateCode(imageView, lista.toString().trim());
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
