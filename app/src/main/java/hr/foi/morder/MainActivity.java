package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterLoginGuest(View view) {

    }

    public void enterLoginEmployee(View view) {
        Intent intent = new Intent(this, PrijavaDjelatnikActivity.class);
        startActivity(intent);
    }
}