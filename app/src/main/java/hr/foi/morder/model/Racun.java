package hr.foi.morder.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Racun.
 */
public class Racun {
    /**
     * The Id. Bill id value.
     */

    public int id;
    public Date vrijeme_izdavanja;
    /**
     * The Stol. Table on whom order was made
     */
    public Integer stol_id;
    public String dostava;
    public Integer sifra;

    public Integer getSifra() {
        return sifra;
    }

    public void setSifra(Integer sifra) {
        this.sifra = sifra;
    }


    public Racun() {
    }

    public Racun(int id, Integer stol) {
        this.id = id;
        this.stol_id = stol;
    }

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

    public void setVrijeme_izdavanja(Date vrijeme_izdavanja) {
        this.vrijeme_izdavanja = vrijeme_izdavanja;
    }

    public Integer getStol() {
        return stol_id;
    }

    public void setStol(Integer stol) {
        this.stol_id = stol;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", this.id);
        result.put("stol_id", this.stol_id);
        return result;
    }

    public String getDostava() {
        return dostava;
    }

    public void setDostava(String dostava) {
        this.dostava = dostava;
    }

    public Racun(int id, String dostava) {
        this.id = id;
        this.dostava = dostava;
    }
}