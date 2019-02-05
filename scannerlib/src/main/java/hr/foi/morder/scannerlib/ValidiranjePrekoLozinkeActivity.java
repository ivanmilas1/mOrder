package hr.foi.morder.scannerlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The type Main activity for login
 *
 * @author Danijel Pintarić
 */
public class ValidiranjePrekoLozinkeActivity extends Fragment implements MetodaValidacijeDostave
{
    EditText editTextGenericPassword;
    String genericpasswordToCheck, enteredpasswordtoCheck;
    Button validateGenericPassword;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.activity_validiranje_preko_lozinke, container, false);
        editTextGenericPassword = v.findViewById(R.id.editTextPassword);
        validateGenericPassword = v.findViewById(R.id.buttonValidateDeliveryViaPassword);
        validateGenericPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredpasswordtoCheck = editTextGenericPassword.getText().toString();
                validirajNarudzbuDostave(enteredpasswordtoCheck);
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        genericpasswordToCheck = intent.getStringExtra("Ocekivana vrijednost");
    }

    public void validirajNarudzbuDostave(String result)
    {
        if (genericpasswordToCheck.equals(result))
        {
            Toast.makeText(getActivity().getApplicationContext(), "Dostava je uspješno izvršena", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(), "Dostava nije uspješno izvršena", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}