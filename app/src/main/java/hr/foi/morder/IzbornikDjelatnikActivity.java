package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * The type Izbornik djelatnik activity defines which activity user want to start next.
 *
 * @author Danijel Pintarić
 */

public class IzbornikDjelatnikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik_djelatnik);
    }

    /**
     * On click view orders starts PrikazStolovaActivity activity.
     *
     * @param view the view on Click from view calls new intent
     * @author Danijel Pintarić
     */
    public void OnClickViewOrders(View view) {
        Intent i = new Intent(this, PrikazStolovaActivity.class);
        startActivity(i);
    }

    /**
     * On click sign out returns to MainActivity activity.
     *
     * @param view the view on Click from this view logs out from session
     * @author Danijel Pintarić
     */
    public void OnClickSignOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * On click view djelatnik starts DodavanjeDjelatnikaActivity activity
     *
     * @param view the view
     * @author Danijel Pintarić
     */
    public void OnClickViewDjelatnik(View view) {
        Intent intent = new Intent(this, DodavanjeDjelatnikaActivity.class);
        startActivity(intent);
    }

    public void OnClickViewArticles(View view) {
        Intent intent = new Intent(this, DodavanjeArtiklaActivity.class);
        startActivity(intent);
    }

    public void OnClickValidateDeliveryOrder(View view) {
        Intent intent = new Intent(this, ProvjeriDostavuActivity.class);
        startActivity(intent);
    }
}