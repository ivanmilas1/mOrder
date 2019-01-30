package hr.foi.morder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.foi.morder.model.Artikl;

public class DodavanjeArtiklaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Artikl> artiklList;
    private Button btnDodajSliku;
    private Button btnDodajArtikl;
    private EditText nazivProizvoda;
    private EditText cijenaProizvoda;
    private Spinner spinner;
    private ImageView imageView;
    private FirebaseFirestore databaseArtikl;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public  StorageReference mStorageRef;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    public int artiklId = 0;
    public int tipArtikla = 0;


    @Override
    protected void onCreate(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_artikla);
        artiklList = new ArrayList<>();
        databaseArtikl = FirebaseFirestore.getInstance();
        nazivProizvoda = findViewById(R.id.input_naziv_proizvoda);
        cijenaProizvoda = findViewById(R.id.input_cijena);
        btnDodajArtikl =(Button) findViewById(R.id.btn_dodaj_proizvod);
        btnDodajSliku = (Button) findViewById(R.id.button_dodaj_sliku);
        spinner = findViewById(R.id.spinner_artikli);
        imageView = (ImageView)findViewById(R.id.imageViewDodavanjeProizvoda);
        progressDialog = new ProgressDialog(DodavanjeArtiklaActivity.this);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("Slike artikala");

        btnDodajArtikl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadSliku();

            }
        });
        btnDodajSliku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odaberiSliku();

            }
        });
    }

    private void uploadSliku() {
    progressDialog.setMessage("Upload slike...");
    progressDialog.show();
        Uri file = filePath;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = mStorageRef.child(nazivProizvoda.getText().toString());
        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        progressDialog.hide();
                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String downloadUrl = uri.toString();
                                dodajArtikl(downloadUrl);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.d("Error", "Error getting data");
                        progressDialog.hide();
                    }
                });
    }

    private void odaberiSliku() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Odaberite sliku proizvoda"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void dodajArtikl(final String url) {

        final String naziv = nazivProizvoda.getText().toString().trim();
        final Double cijena = Double.parseDouble(cijenaProizvoda.getText().toString());
        String tip = spinner.getSelectedItem().toString();
        switch (tip) {
            case "Piće":
                tipArtikla = 1;
                break;
            case "Jelo":
                tipArtikla = 2;
                break;
        }

        databaseArtikl.collection("Artikl").orderBy("id", Query.Direction.DESCENDING).limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Artikl artikl = documentSnapshot.toObject(Artikl.class);
                                artiklId = artikl.getId();
                                artiklId = artiklId + 1;

                            }
                            if(!TextUtils.isEmpty(naziv)){
                                if(!TextUtils.isEmpty(cijenaProizvoda.getText().toString())){
                                    final Map<String, Object> artikl = new Artikl(artiklId, naziv,"kom", cijena, tipArtikla , url).toMap();
                                    databaseArtikl.collection("Artikl").add(artikl)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                    Toast.makeText(getApplicationContext(), "Uspješno ste dodali  " + naziv, Toast.LENGTH_LONG).show();
                                                }
                                            });
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Morate unijeti cijenu proizvoda", Toast.LENGTH_LONG).show();
                                }

                            }else {
                                Toast.makeText(getApplicationContext(), "Morate unijeti naziv proizvoda", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
