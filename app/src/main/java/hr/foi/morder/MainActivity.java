package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * The type Main activity for login
 * @author Danijel Pintarić
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Enter login guest starts activity for guest restaurant to login.
     *
     * @param view the view on Click from view calls new NarucivanjeActivity activity.
     *             @author Danijel Pintarić
     */
    public void enterLoginGuest(View view) {
        Intent intent = new Intent(this, NarucivanjeActivity.class);
        startActivity(intent);
    }

    /**
     * Enter login employee starts activity for employee login.
     *
     * @param view the view on Click from view calls new IzbornikDjelatnikActivity activity.
     *             @author Danijel Pintarić
     */
    public void enterLoginEmployee(View view) {
        Intent intent = new Intent(this, PrijavaDjelatnikActivity.class);
        startActivity(intent);
    }

    public void homeDelivery(View view) {
        Intent intent = new Intent(this, DostavaActivity.class);
        startActivity(intent);
    }
}