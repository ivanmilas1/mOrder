package hr.foi.morder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import hr.foi.morder.model.Stol;

/**
 * The type Prikaz stolova activity table preview, shows table layout and defines table colors.
 * If table is taken red color, if table is free green color and yellow if order is in process.
 * @author Marko Fabijan Pavlović
 */
public class PrikazStolovaActivity extends AppCompatActivity {

    private FirebaseFirestore database;
    /**
     * The Crvena If table is taken red color
     */
    int crvena, /**
     * The Zuta if order is in process
     */
    zuta, /**
     * The Zelena if table is free
     */
    zelena;
    /**
     * The Btn stol 1 opens table content for table 1.
     */
    Button btnStol1, /**
     * The Btn stol 2. opens table content for table 2.
     */
    btnStol2, /**
     * The Btn stol 3. opens table content for table 3.
     */
    btnStol3, /**
     * The Btn stol 4. opens table content for table 4.
     */
    btnStol4, /**
     * The Btn stol 5. opens table content for table 5.
     */
    btnStol5, /**
     * The Btn stol 6.opens table content for table 6.
     */
    btnStol6, /**
     * The Btn stol 7. opens table content for table 7.
     */
    btnStol7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_stolova);
        initColors();
        final List<Button> listOfButtons = getButtonsInList();
        final List<Stol> listaStolova = new ArrayList<>();
        getTables(listOfButtons, listaStolova);
    }

    /**
     * Connects table with its button
     * @param listOfButtons Each button represents table
     * @param listaStolova Table list
     * @author Marko Fabijan PavloviÄ‡
     */
    private void getTables(final List<Button> listOfButtons, final List<Stol> listaStolova) {
        database = FirebaseFirestore.getInstance();
        database.collection("Stol")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Stol stol = documentSnapshot.toObject(Stol.class);
                                listaStolova.add(stol);
                            }
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                        setOnClickListenerForAllButtons(listOfButtons);
                        setButtonBehaviour(listOfButtons, listaStolova);
                    }
                });
    }

    /**
     * Defines table status
     * Colors table depending on status
     * Status "slobodan" green color
     * Status "narudzbaUPrirpemi" yellow color
     * Status "zauzet" red color
     * @param listOfButtons Each button represents table
     * @param listaStolova Table list
     * @author Marko Fabijan PavloviÄ‡
     */
    private void setButtonBehaviour(List<Button> listOfButtons, List<Stol> listaStolova) {
        for (Stol item : listaStolova) {
            for (Button itemButton : listOfButtons) {
                if (String.valueOf(item.id ).equals(itemButton.getText()) ){
                    if (item.stanje.equals("slobodan")) {
                        itemButton.setBackgroundColor(zelena);
                    } else if (item.stanje .equals("narudzbaUPripremi") ) {
                        itemButton.setBackgroundColor(zuta);
                    } else {
                        itemButton.setBackgroundColor(crvena);
                    }
                }
            }
        }
    }

    /**
     * Connecting each table button with table from database
     * On button click opening activity DetaljiNarudzbeActivity
     * Intent passing data about table id
     * @param listOfButtons Each button represents table
     * @author Marko Fabijan PavloviÄ‡
     */
    private void setOnClickListenerForAllButtons(List<Button> listOfButtons) {
        for (final Button itemButton : listOfButtons) {
            // seting for all buttons onClickListener to start DetaljiNaruzdbeActivity
            itemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), DetaljiNarudzbeActivity.class);
                    i.putExtra("stolID", itemButton.getText());
                    startActivity(i);
                }
            });
        }
    }

    /**
     * Defining table colors
     * crvena red
     * zuta yellow
     * zelena green
     * @author Marko Fabijan PavloviÄ‡
     */
    private void initColors() {
        //ako nema narudžbe
        crvena = Color.rgb(179, 5, 5);
        //ako je narudžba u izradi
        zuta = Color.rgb(225, 206, 132);
        //ako je narudžba poslužena
        zelena = Color.rgb(78, 255, 167);
    }

    /**
     * Hardcoding table values
     * Adding each button to list
     *
     * @return returning ListOfButton list which consist of button values
     */
    @NonNull
    private List<Button> getButtonsInList() {
        btnStol1 = findViewById(R.id.btnStol1);
        btnStol2 = findViewById(R.id.btnStol2);
        btnStol3 = findViewById(R.id.btnStol3);
        btnStol4 = findViewById(R.id.btnStol4);
        btnStol5 = findViewById(R.id.btnStol5);
        btnStol6 = findViewById(R.id.btnStol6);
        btnStol7 = findViewById(R.id.btnStol7);

        List<Button> listOfButtons = new ArrayList<>();
        listOfButtons.add(btnStol1);
        listOfButtons.add(btnStol2);
        listOfButtons.add(btnStol3);
        listOfButtons.add(btnStol4);
        listOfButtons.add(btnStol5);
        listOfButtons.add(btnStol6);
        listOfButtons.add(btnStol7);
        return listOfButtons;
    }
}