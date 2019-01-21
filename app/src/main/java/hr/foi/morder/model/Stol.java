package hr.foi.morder.model;

public class Stol {

    public int id;
    public String stanje;
    public Racun racun;

    public enum stanjeNarudzbe{
        slobodan, narudzbaUIzradi, narudzbaPosluzena
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }
}
