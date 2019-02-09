package hr.foi.morder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class KosaricaDostavaActivity extends AppCompatActivity {
    Button naruci;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kosarica);
        FragmentManager fm = getSupportFragmentManager();
        final KosaricaFragment fragment = (KosaricaFragment)fm.findFragmentById(R.id.fragmentKosarica);


        naruci = findViewById(R.id.buttonNaruci);
        naruci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.dovrsiNarudzbuDostave();
            }
        });
    }
}