package hr.foi.morder.adapters;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * The type Djelatnik pregled racuna list view holder.
 */
public class DjelatnikPregledRacunaListViewHolder
{
    /**
     * The Tv naziv. Textview for representing article name.
     */
    TextView tvNaziv;
    /**
     * The Iv slika. Image view for representing article image.
     */
    ImageView ivSlika;
    /**
     * The Tv cijena. Textview for representing article price.
     */
    TextView tvCijena;
    /**
     * The Btn dodaj. Button for adding article to an order.
     */
    ImageButton btnDodaj;
    /**
     * The Et kolicina. Textview for representing article quantity.
     */
    EditText etKolicina;
    /**
     * The Btn smanji. Button for removing article from order.
     */
    ImageButton btnSmanji;
    /**
     * The Ll main row. Layout for main row. Prints message about new articles.
     */
    LinearLayout llMainRow;
}