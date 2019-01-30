package hr.foi.morder.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Racun {

    public int id;
    public Date vrijeme_izdavanja;
    public Integer stol_id;

    public Racun() {
    }

    public Racun(int id, Integer stol) {
        this.id = id;
        this.stol_id = stol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
