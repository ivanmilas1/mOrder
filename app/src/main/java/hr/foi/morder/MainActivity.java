package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterLoginGuest(View view) {
        Intent intent = new Intent(this, NarucivanjeActivity.class);
        startActivity(intent);
    }

    public void enterLoginEmployee(View view) {
        Intent intent = new Intent(this, IzbornikDjelatnikActivity.class);
        startActivity(intent);
    }
}