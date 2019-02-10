package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * The type Main activity for login
 *
 * @author Danijel Pintarić
 */
public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbarSettings);
        setSupportActionBar(toolbar);

        // load default preferences
      //  PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return true;
        }
    }

    /**
     * Enter login guest starts activity for guest restaurant to login.
     *
     * @param view the view on Click from view calls new NarucivanjeActivity activity.
     * @author Danijel Pintarić
     */
    public void enterLoginGuest(View view) {
        Intent intent = new Intent(this, NarucivanjeActivity.class);
        startActivity(intent);
    }

    /**
     * Enter login employee starts activity for employee login.
     *
     * @param view the view on Click from view calls new IzbornikDjelatnikActivity activity.
     * @author Danijel Pintarić
     */
    public void enterLoginEmployee(View view) {
        Intent intent = new Intent(this, IzbornikDjelatnikActivity.class);
        startActivity(intent);
    }

    public void homeDelivery(View view) {
        Intent intent = new Intent(this, OdabirKorisnikaActivity.class);
        startActivity(intent);
    }
}