package hr.foi.morder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Set;

import hr.foi.morder.scannerlib.ValidacijaDostave;
import hr.foi.morder.scannerlib.ValidacijaDostaveManager;
import hr.foi.morder.scannerlib.ValidiranjePrekoLozinkeFragment;
import hr.foi.morder.scannerlib.ValidiranjePutemQRKodaFragment;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    Set<String> strings;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).registerOnSharedPreferenceChangeListener(this);
       // SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("multi_select_list_preference_ValidationDeliveryModules")) {
            strings = sharedPreferences.getStringSet(key, null);
            HashMap<String, ValidacijaDostave> metodeValidacijeDostave = ValidacijaDostaveManager.getInstance().getMetodeValidacijeDostave();
            for(String item : strings) {
                switch (item){
                    case"QR": if (!metodeValidacijeDostave.containsKey(item)){
                        metodeValidacijeDostave.put(item, new ValidiranjePutemQRKodaFragment());
                    }
                    break;
                    case"Lozinka": if(!metodeValidacijeDostave.containsKey(item)){
                        metodeValidacijeDostave.put(item, new ValidiranjePrekoLozinkeFragment());
                    }
                    break;
                }
            }
        }
    }
}