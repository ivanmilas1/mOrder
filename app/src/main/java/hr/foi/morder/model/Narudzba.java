package hr.foi.morder.model;

public class Narudzba {
    public int id;
    public double iznos_narudzbe;
    public int korisnik_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getIznos_narudzbe() {
        return iznos_narudzbe;
    }

    public void setIznos_narudzbe(double iznos_narudzbe) {
        this.iznos_narudzbe = iznos_narudzbe;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }
}
