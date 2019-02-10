package hr.foi.morder.scannerlib;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class ValidacijaDostaveManager {
    private static ValidacijaDostaveManager instance;
    private AppCompatActivity activity;
    private HashMap<String, ValidacijaDostave> metodeValidacijeDostave;
    ValidacijaDostave selectedModule;
    Intent intent;

    public HashMap<String, ValidacijaDostave> getMetodeValidacijeDostave() {
        return metodeValidacijeDostave;
    }

    private ValidacijaDostaveManager() {
        metodeValidacijeDostave = new HashMap<>();


        metodeValidacijeDostave.put("QR", new ValidiranjePutemQRKodaFragment());
        metodeValidacijeDostave.put("Lozinka", new ValidiranjePrekoLozinkeFragment());
    }

    public static ValidacijaDostaveManager getInstance() {
        if (instance == null) {
            instance = new ValidacijaDostaveManager();
        }
        return instance;
    }

    public void setDrawerDependencies(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void startModule() {
        FragmentManager mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedModule.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void odabirMetodeValidacijeDostave(String buttonText, String racunGeneriraniKod, Context context) {
        selectedModule = metodeValidacijeDostave.get(buttonText);
        intent = new Intent(context, ValidiranjeActivity.class);
        intent.putExtra("Ocekivana vrijednost", racunGeneriraniKod);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}