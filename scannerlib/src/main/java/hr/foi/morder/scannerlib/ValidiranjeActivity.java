package hr.foi.morder.scannerlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ValidiranjeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_start);
        initializeDostavaManager();
    }

    public void initializeDostavaManager()
    {
        DostavaManager dm = DostavaManager.getInstance();
        dm.setDrawerDependencies(this);
        dm.startModule();
    }
}