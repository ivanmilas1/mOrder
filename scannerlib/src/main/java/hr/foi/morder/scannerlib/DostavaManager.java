package hr.foi.morder.scannerlib;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class DostavaManager
{
    private static DostavaManager instance;
    private AppCompatActivity activity;
    MetodaValidacijeDostave metodaValidacijeDostave = new ValidiranjePrekoLozinkeActivity();

    public void setMetodaValidacijeDostave(MetodaValidacijeDostave metodaValidacijeDostave) {
        this.metodaValidacijeDostave = metodaValidacijeDostave;
    }

    private DostavaManager()
    {
    }

    public static DostavaManager getInstance()
    {
        if(instance == null)
        {
            instance = new DostavaManager();
        }
        return instance;
    }

    public void setDrawerDependencies(AppCompatActivity activity)
    {
        this.activity = activity;
    }


    public void startModule()
    {
        FragmentManager mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, metodaValidacijeDostave.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}