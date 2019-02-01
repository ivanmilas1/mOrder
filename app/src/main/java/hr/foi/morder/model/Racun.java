package hr.foi.morder.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Racun is a receipt which is generated when an order is confirmed by an employee.
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
    public Integer stol_id;
    public String status;
    public Long kod;
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

    public Racun(int id, String status, Long kod) {
        this.id = id;
        this.status = status;
        this.kod = kod;
    }


    public Integer getStol_id() {
        return stol_id;
    }

    public void setStol_id(Integer stol_id) {
        this.stol_id = stol_id;
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
        result.put("status", this.status);
        result.put("kod", this.kod);
        return result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getKod() {
        return kod;
    }

    public void setKod(Long kod) {
        this.kod = kod;
    }
}