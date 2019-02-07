package hr.foi.morder.scannerlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ValidiranjeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scanner_start);
        initializeDostavaManager();
    }

    public void initializeDostavaManager()
    {
        ValidacijaDostaveManager dm = ValidacijaDostaveManager.getInstance();
        dm.setDrawerDependencies(this);
        dm.startModule();
    }
}