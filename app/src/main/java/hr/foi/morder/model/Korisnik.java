package hr.foi.morder.model;

import java.util.Date;

/**
 * The type Korisnik.
 */
public class Korisnik {
    /**
     * The Id. User id value.
     */
    public Integer id;
    /**
     * The Ime prezime. Users name and last name
     */
    public String imePrezime;
    /**
     * The Lozinka.  Users password.
     */
    public String lozinka;
    /**
     * The Datum rodjenja. Users date of birth
     */
    public Date datumRodjenja;
    /**
     * The Adresa prebivalista. Users adress
     */
    public String adresaPrebivalista;
    /**
     * The Tip korisnika. Type of user
     */
    public TipKorisnika tipKorisnika;

    /**
     * Instantiates a new Korisnik.
     *
     * @param id                 the id
     * @param imePrezime         the ime prezime
     * @param lozinka            the lozinka
     * @param datumRodjenja      the datum rodjenja
     * @param adresaPrebivalista the adresa prebivalista
     * @param tipKorisnika       the tip korisnika
     */
    public Korisnik(Integer id, String imePrezime, String lozinka, Date datumRodjenja, String adresaPrebivalista, TipKorisnika tipKorisnika) {
        this.id = id;
        this.imePrezime = imePrezime;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.adresaPrebivalista = adresaPrebivalista;
        this.tipKorisnika = tipKorisnika;
    }

    /**
     * Instantiates a new Korisnik.
     */
    public Korisnik() {
    }

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
     * Gets ime prezime.
     *
     * @return the ime prezime
     */
    public String getImePrezime() {
        return imePrezime;
    }

    /**
     * Sets ime prezime.
     *
     * @param imePrezime the ime prezime
     */
    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    /**
     * Gets lozinka.
     *
     * @return the lozinka
     */
    public String getLozinka() {
        return lozinka;
    }

    /**
     * Sets lozinka.
     *
     * @param lozinka the lozinka
     */
    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    /**
     * Gets datum rodjenja.
     *
     * @return the datum rodjenja
     */
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    /**
     * Sets datum rodjenja.
     *
     * @param datumRodjenja the datum rodjenja
     */
    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    /**
     * Gets adresa prebivalista.
     *
     * @return the adresa prebivalista
     */
    public String getAdresaPrebivalista() {
        return adresaPrebivalista;
    }

    /**
     * Sets adresa prebivalista.
     *
     * @param adresaPrebivalista the adresa prebivalista
     */
    public void setAdresaPrebivalista(String adresaPrebivalista) {
        this.adresaPrebivalista = adresaPrebivalista;
    }

    /**
     * Gets tip korisnika.
     *
     * @return the tip korisnika
     */
    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    /**
     * Sets tip korisnika.
     *
     * @param tipKorisnika the tip korisnika
     */
    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }


}

