package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.foi.morder.adapters.DjelatnikRecyclerAdapter;
import hr.foi.morder.model.Djelatnik;

/**
 * The type Dodavanje radnika activity implemented for adding new workers in app and also to update data about them.
 *  * Set content view activity_dodavanje_radnika layout
 *  @author Ivan Milas
 */
public class DodavanjeRadnikaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    /**
     * The Djelatnik recycler adapter for showing data about all workers from database.
     */
    public  DjelatnikRecyclerAdapter djelatnikRecyclerAdapter;
    private List<Djelatnik> djelatnikList;
    private Button button;
    private EditText ime;
    private EditText email;
    private EditText lozinka;
    private Spinner spinnerTip;
    private FirebaseFirestore databaseDjelatnik;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_radnika);
        djelatnikList = new ArrayList<>();
        buildRecyclerView();
        databaseDjelatnik = FirebaseFirestore.getInstance();
        ime = findViewById(R.id.input_name);
        email = findViewById(R.id.input_email);
        lozinka = findViewById(R.id.input_password);
        button = findViewById(R.id.btn_signup);
        spinnerTip = findViewById(R.id.spinner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajDjelatnika();

            }
        });
        loadDjelatnici();

        Intent intent = getIntent();
        ((TextView)findViewById(R.id.input_name)).setText(intent.getStringExtra("Ime"));
        ((TextView)findViewById(R.id.input_email)).setText(intent.getStringExtra("Email"));
        String a = intent.getStringExtra("Azuriranje");
        if(TextUtils.isEmpty(a)){
            button.setText("Kreiraj korisnika");
         }
    }
    /**
     * The dodaj Djelatnika method for adding new worker. Data from text fields is updated to Firestore database.
     * Takes data from input fields and from spinner
     * Checking if e-mail is valid
     * Checking if field "naziv" is empty, it must not be empty
     * Checking if field "password" is empty, it must not be empty
     * If error occurs during fetching data, displays error message
     * @author Ivan Milas
     */
    private void dodajDjelatnika() {
        loadDjelatnici();
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
            if (!TextUtils.isEmpty(mail)&& android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                if (!TextUtils.isEmpty(password)) {
                    Map<String, Object> djelatnik = new Djelatnik(naziv, password, mail, tipKorisnika).toMap();
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
    /**
     * The load Djelatnici gets data from Firestore database collection "Djelatnik" , stores data in List djelatnikList and calls method djelatnikRecyclerAdapter.
     * Creating new adapter for recycler viwe
     * Setting recycler view layout manager from application context
     * Setting the adapter into recycler view
     * If error occurs during fetching data, displays error message
     * @author Ivan Milas
     */
    private void loadDjelatnici(){
        databaseDjelatnik.collection("Djelatnik")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Djelatnik> djelatnikList = new ArrayList<>();
                            for(DocumentSnapshot documentSnapshot: task.getResult()){
                                Djelatnik djelatnik = documentSnapshot.toObject(Djelatnik.class);
                                djelatnik.getImePrezime();
                                djelatnik.getEmail();
                                djelatnik.getTipDjelatnikaId();
                                djelatnikList.add(djelatnik);
                            }
                            djelatnikRecyclerAdapter = new DjelatnikRecyclerAdapter (getApplicationContext() ,djelatnikList ,  databaseDjelatnik);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(djelatnikRecyclerAdapter);
                        }
                        else{
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    /**
     * Build recycler view for creating new Recycler view and setting the adapter for this recycler view.
     * Find recycler view by id
     * Set layout manager and set adapter for recycler view
     * @author Ivan Milas
     */
    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(djelatnikRecyclerAdapter);
    }
}