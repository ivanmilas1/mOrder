package hr.foi.morder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * The type Scanner activity starts a QR scanner.
 */
public class ScannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
    }
}
