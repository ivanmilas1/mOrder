package hr.foi.morder.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Djelatnik represents an employee in the restaurant.
 */
public class Djelatnik {
    /**
     * The Ime prezime first and last name of user
     */
    public String imePrezime;
    /**
     * The Lozinka. Users password
     */
    public String lozinka;
    /**
     * The Email. Users email
     */
    public String email;
    /**
     * The Tip djelatnika id. User type
     */
    public int tipDjelatnikaId;

    /**
     * Instantiates a new Djelatnik.
     *
     * @param imePrezime      User First and last name
     * @param lozinka          User Password
     * @param email           User email
     * @param tipDjelatnikaId  User type
     */
    public Djelatnik(String imePrezime, String lozinka, String email, int tipDjelatnikaId) {
        this.imePrezime = imePrezime;
        this.lozinka = lozinka;
        this.email = email;
        this.tipDjelatnikaId = tipDjelatnikaId;
    }

    /**
     * Instantiates a new Djelatnik.
     */
    public Djelatnik(){}

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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets tip djelatnika id.
     *
     * @return the tip djelatnika id
     */
    public int getTipDjelatnikaId() {
        return tipDjelatnikaId;
    }

    /**
     * Sets tip djelatnika id.
     *
     * @param tipDjelatnikaId the tip djelatnika id
     */
    public void setTipDjelatnikaId(int tipDjelatnikaId) {
        this.tipDjelatnikaId = tipDjelatnikaId;
    }

    /**
     * Maping objects for Firestore database
     *
     * @return the map
     */
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("imePrezime", this.imePrezime);
        result.put("lozinka", this.lozinka);
        result.put("email", this.email);
        result.put("tipDjelatnikaId", this.tipDjelatnikaId);
        return result;
    }
}

