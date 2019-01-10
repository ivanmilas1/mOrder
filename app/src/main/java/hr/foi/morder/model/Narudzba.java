package hr.foi.morder.model;

import java.util.Date;

public class Narudzba {
    public int id;
    public Date vrijeme_narudzbe;
    public double iznos_narudzbe;
    public Korisnik korisnik;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVrijeme_narudzbe() {
        return vrijeme_narudzbe;
    }

    public void setVrijeme_narudzbe(Date vrijeme_narudzbe) {
        this.vrijeme_narudzbe = vrijeme_narudzbe;
    }

    public double getIznos_narudzbe() {
        return iznos_narudzbe;
    }

    public void setIznos_narudzbe(double iznos_narudzbe) {
        this.iznos_narudzbe = iznos_narudzbe;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
