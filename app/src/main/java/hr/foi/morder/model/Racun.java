package hr.foi.morder.model;

import java.util.Date;

public class Racun {
    public int id;
    public Date vrijeme_izdavanja;
    public Stol stol;

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

    public Stol getStol() {
        return stol;
    }

    public void setStol(Stol stol) {
        this.stol = stol;
    }
}
