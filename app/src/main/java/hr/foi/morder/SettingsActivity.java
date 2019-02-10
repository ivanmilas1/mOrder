package hr.foi.morder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Postavke aplikacije");

        if (findViewById(R.id.frameLayoutSettings) != null) {
            if (savedInstanceState != null)
                return;
            getFragmentManager().beginTransaction().add(R.id.frameLayoutSettings, new SettingsFragment()).commit();
        }
    }
}