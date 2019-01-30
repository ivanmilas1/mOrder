package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import hr.foi.morder.model.Racun;
import hr.foi.morder.scannerlib.DostavaManager;

/**
 * The type Main activity for login
 *
 * @author Danijel Pintarić
 */
public class ValidiranjePrekoLozinkeActivity extends AppCompatActivity {
    EditText editTextGenericPassword;
    private FirebaseFirestore database;
    private Integer idRacun, maxRacunID = 0;
    private Long genericpasswordToCheck, enteredpasswordtoCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validiranje_preko_lozinke);
        editTextGenericPassword = findViewById(R.id.editTextPassword);
        database = FirebaseFirestore.getInstance();
    }

    public void onClickValidate(View view) {
        enteredpasswordtoCheck = Long.parseLong(editTextGenericPassword.getText().toString());

        DostavaManager dostavaManager = new DostavaManager() {
            @Override
            public void validirajNarudzbuDostave() {
                database.collection("Racun").whereEqualTo("status", "dostava").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                        Racun racun = documentSnapshot.toObject(Racun.class);
                                        idRacun = racun.getId();
                                        if (idRacun > maxRacunID) {
                                            maxRacunID = idRacun;
                                        }
                                    }
                                    getDeliveryBill();
                                } else {
                                    Log.d("Error", "Error getting data");
                                }
                            }
                        });
            }
        };
        dostavaManager.validirajNarudzbuDostave();
    }

    private void getDeliveryBill() {
        database.collection("Racun").whereEqualTo("id", maxRacunID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Racun racun = documentSnapshot.toObject(Racun.class);
                                genericpasswordToCheck = racun.getKod();
                            }
                            if (genericpasswordToCheck.equals(enteredpasswordtoCheck) ){
                                Toast.makeText(getApplicationContext(), "Dostava je uspješno izvršena", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), IzbornikDjelatnikActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Dostava nije uspješno izvršena", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }
}