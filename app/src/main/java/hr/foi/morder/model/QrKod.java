package hr.foi.morder.model;

import android.widget.ImageView;

/**
 * The type Qr kod. A QR code is generated when a customer takes an order.
 * QR code holds a receipt ID which is used by delivery worker to check the order.
 */
public class QrKod {
    private Integer id;
    /**
     * The Slika koda.
     */
    public ImageView slikaKoda;
    /**
     * The Broj narudzbe.
     */
    public Integer brojNarudzbe;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
