package com.morder.morder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.narucivanje);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NavigationViewActivity.class);
                startActivity(i);
            }
        });
    }


}
