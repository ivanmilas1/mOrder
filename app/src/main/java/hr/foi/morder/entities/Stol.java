package hr.foi.morder.entities;

public class Stol {

    public int id;

    public String stanje;

    public int narudzba_id;

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

    public int getNarudzba_id() {
        return narudzba_id;
    }

    public void setNarudzba_id(int narudzba_id) {
        this.narudzba_id = narudzba_id;
    }
}
