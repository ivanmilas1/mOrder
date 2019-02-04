package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OdabirKorisnikaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.odabir_korisnika);
    }

    public void naruciDostavu(View view) {
        Intent intent = new Intent(this, NarucivanjeDostavaActivity.class);
        startActivity(intent);
    }
    public void dohvatiQrKod(View view) {
        Intent intent = new Intent(this, DostavaActivity.class);
        startActivity(intent);
    }
    public void dohvatiGeneriraniKod(View view) {
        Intent intent = new Intent(this, GeneriraniKodActivity.class);
        startActivity(intent);
    }
}