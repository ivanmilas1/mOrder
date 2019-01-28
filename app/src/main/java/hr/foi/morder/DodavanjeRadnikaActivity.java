package hr.foi.morder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.foi.morder.adapters.KorisnikRecyclerAdapter;
import hr.foi.morder.model.Djelatnik;

public class DodavanjeRadnikaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    KorisnikRecyclerAdapter korisnikRecyclerAdapter;

    List<Djelatnik> djelatnikList;

    Button button;
    EditText ime;
    EditText email;
    EditText lozinka;
    Spinner spinnerTip;

    FirebaseFirestore databaseDjelatnik;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_radnika);

        djelatnikList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseDjelatnik = FirebaseFirestore.getInstance();

        ime = (EditText) findViewById(R.id.input_name);
        email = (EditText) findViewById(R.id.input_email);
        lozinka = (EditText) findViewById(R.id.input_password);
        button = (Button) findViewById(R.id.btn_signup);
        spinnerTip = (Spinner) findViewById(R.id.spinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajDjelatnika();

            }
        });

    }

    private void dodajDjelatnika() {

        Integer tipKorisnika = null;
        String naziv = ime.getText().toString().trim();
        String mail = email.getText().toString();
        String password = lozinka.getText().toString();
        String tip = spinnerTip.getSelectedItem().toString();
        switch (tip) {
            case "Konobar":
                tipKorisnika =1;
                break;
            case "Voditelj":
                tipKorisnika =2;
                break;
            case "Administrator":
                tipKorisnika =3;
                break;

        }


        if (!TextUtils.isEmpty(naziv)) {
            if (!TextUtils.isEmpty(mail)) {
                if (!TextUtils.isEmpty(password)) {

                    Map<String, Object> djelatnik = new Djelatnik(naziv, mail, password, tipKorisnika).toMap();
                    databaseDjelatnik.collection("Djelatnik")
                            .add(djelatnik)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                }
                            });


                    Toast.makeText(this, "Korisnik je uspješno dodan", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "Morate unijeti lozinku", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Morate unijeti email korisnika", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Morate unijeti ime korisnika", Toast.LENGTH_LONG).show();
        }
    }
}
