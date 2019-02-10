package hr.foi.morder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Set;

import hr.foi.morder.scannerlib.ValidacijaDostave;
import hr.foi.morder.scannerlib.ValidacijaDostaveManager;
import hr.foi.morder.scannerlib.ValidiranjePrekoLozinkeFragment;
import hr.foi.morder.scannerlib.ValidiranjePutemQRKodaFragment;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    Set<String> odabraneMetodeValidacije;
    Context context;
    int maxValidationModules;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        context = getActivity().getApplicationContext();
        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);

        Resources resources = getResources(); //assuming in an activity for example, otherwise you can provide a context.
        maxValidationModules = resources.getStringArray(R.array.preferences_ValidationDeliveryModules_entries).length;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("multi_select_list_preference_ValidationDeliveryModules")) {
            odabraneMetodeValidacije = sharedPreferences.getStringSet(key, null);
            HashMap<String, ValidacijaDostave> metodeValidacijeDostave = ValidacijaDostaveManager.getInstance().getMetodeValidacijeDostave();

            if(metodeValidacijeDostave.size() != 0){
                metodeValidacijeDostave.clear();
            }

            if (odabraneMetodeValidacije.isEmpty()) {
                getPreferenceManager().getSharedPreferences().edit().clear().commit();
                PreferenceManager.setDefaultValues(context, R.xml.preferences, true);
                setPreferenceScreen(null);
                addPreferencesFromResource(R.xml.preferences);
                Toast.makeText(context, "Jedan od modula mora biti označen, postavke su sada vraćene na početne vrijednosti", Toast.LENGTH_LONG).show();
            }
            else {
                for (String item : odabraneMetodeValidacije) {
                    switch (item) {
                        case "QR":
                            if (!metodeValidacijeDostave.containsKey(item)) {
                                metodeValidacijeDostave.put(item, new ValidiranjePutemQRKodaFragment());
                            }
                            break;
                        case "Lozinka":
                            if (!metodeValidacijeDostave.containsKey(item)) {
                                metodeValidacijeDostave.put(item, new ValidiranjePrekoLozinkeFragment());
                            }
                            break;
                    }
                }
            }
        }
    }
}