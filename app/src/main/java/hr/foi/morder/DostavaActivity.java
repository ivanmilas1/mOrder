package hr.foi.morder;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import hr.foi.morder.scannerlib.CodeGenerateFragment;

public class DostavaActivity extends AppCompatActivity implements CodeGenerateFragment.OnFragmentInteractionListener {

    FragmentManager manager;
    CodeGenerateFragment fragment;
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetaljiNarudzbeActivity detalji = new DetaljiNarudzbeActivity();
        setContentView(R.layout.activity_dostava);

        lista = new ArrayList<>();
        lista.add("probno");
        lista.add("probno2");

        manager = getSupportFragmentManager();
        fragment = (CodeGenerateFragment)manager.findFragmentById(R.id.fragLayout);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void GenerirajKod(View view){
        fragment.generateCode(lista);
    }

}
