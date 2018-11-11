package hr.foi.morder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.api.core.ApiFuture;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.ExecutionException;

public class PrijavaDjelatnikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);
    }

    public void onClickSignIn(View view) throws ExecutionException, InterruptedException {
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Reference to a Collection
        CollectionReference korisnik = db.collection("Korisnik");
        String pin = editTextPassword.getText().toString();
        Query query = korisnik.whereEqualTo(pin, true);
        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = (ApiFuture<QuerySnapshot>) query.get();
        // block on response
        //DocumentSnapshot document = future.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            System.out.println(document.getId());
        }
    }
}
