package hr.foi.morder.model;

/**
 * The type Tip korisnika is a type of a user in the application, which can be employee or customer.
 */
public class TipKorisnika {
    /**
     * The Id. Users id value, uniqe.
     */
    public Integer id;
    /**
     * The Naziv. User name.
     */
    public String naziv;
    /**
     * The Opis. User description
     */
    public String opis;

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

    /**
     * Gets naziv.
     *
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets naziv.
     *
     * @param naziv the naziv
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Gets opis.
     *
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets opis.
     *
     * @param opis the opis
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }
}
