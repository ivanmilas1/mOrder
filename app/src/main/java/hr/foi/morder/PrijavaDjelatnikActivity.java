package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.ExecutionException;

import hr.foi.morder.entities.Korisnik;

public class PrijavaDjelatnikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);
    }

    public void onClickSignIn(View view) throws ExecutionException, InterruptedException {
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        String pin = editTextPassword.getText().toString();
        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Korisnik")
                .whereEqualTo("lozinka", pin)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Korisnik korisnik = null;
                            for (DocumentSnapshot document : task.getResult()) {
                                korisnik = document.toObject(Korisnik.class);
                            }
                            if (korisnik != null){
                                Toast.makeText(getApplicationContext(), "Dobrodošli " + korisnik.getImePrezime(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), IzbornikDjelatnikActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Neuspješna prijava", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}