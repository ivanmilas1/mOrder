package hr.foi.morder;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import hr.foi.morder.scannerlib.CodeGenerateFragment;

/**
 * The type Dostava activity implements the fragment which generates a QR code
 * depending on receipt ID. This code is later used to check correctness of the order.
 * @author Marko Fabijan Pavlović
 */
public class DostavaActivity extends AppCompatActivity implements CodeGenerateFragment.OnFragmentInteractionListener {

    /**
     * The Image view on which screen is set generated QR code.
     */
    ImageView imageView;
    /**
     * The Button on which click is started the process of generating QR code.
     */
    Button button;
    /**
     * The Manager which is used for transaction between fragment and activity.
     */
    FragmentManager manager;
    /**
     * The Fragment which consists code to generate QR code.
     */
    CodeGenerateFragment fragment;
    /**
     * The Id narudzbe is the ID of receipt which is issued for order delivery.
     */
    String idNarudzbe;
    /**
     * The Lista.
     */
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dostava);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.btnPlaceOrder);

        lista = new ArrayList<>();
        lista.add("probno");

        manager = getSupportFragmentManager();
        fragment = (CodeGenerateFragment)manager.findFragmentById(R.id.fragmentProbni);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.generateCode(imageView, lista.toString().trim());
            }
        });
    }

    /**
     * On fragment interaction.
     *
     * @param uri the uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
