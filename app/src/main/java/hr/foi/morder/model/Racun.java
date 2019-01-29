package hr.foi.morder.model;

import java.util.Date;

/**
 * The type Racun.
 */
public class Racun {
    /**
     * The Id. Bill id value.
     */
    public int id;
    /**
     * The Vrijeme izdavanja. Date and time of ordering
     */
    public Date vrijeme_izdavanja;
    /**
     * The Stol. Table on whom order was made
     */
    public Stol stol;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets vrijeme izdavanja.
     *
     * @return the vrijeme izdavanja
     */
    public Date getVrijeme_izdavanja() {
        return vrijeme_izdavanja;
    }

    /**
     * Sets vrijeme izdavanja.
     *
     * @param vrijeme_izdavanja the vrijeme izdavanja
     */
    public void setVrijeme_izdavanja(Date vrijeme_izdavanja) {
        this.vrijeme_izdavanja = vrijeme_izdavanja;
    }

    /**
     * Gets stol.
     *
     * @return the stol
     */
    public Stol getStol() {
        return stol;
    }

    /**
     * Sets stol.
     *
     * @param stol the stol
     */
    public void setStol(Stol stol) {
        this.stol = stol;
    }
}
