package hr.foi.morder.scannerlib;

import android.support.v4.app.Fragment;

public interface MetodaValidacijeDostave
{
    void validirajNarudzbuDostave(String result);
    Fragment getFragment();
}