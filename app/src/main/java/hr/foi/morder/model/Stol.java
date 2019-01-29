package hr.foi.morder.model;

import java.util.HashMap;
import java.util.Map;

public class Stol {

    public int id;
    public String stanje;
    public Racun racun;
    public Integer narudzba_id;

    public enum stanjeNarudzbe{
        slobodan, narudzbaUIzradi, narudzbaPosluzena
    }

    public Stol() {

    }

    public Stol(Integer id){
        this.id = id;

    }

    public Integer getNarudzba_id() {
        return narudzba_id;
    }

    public void setNarudzba_id(Integer narudzba_id) {
        this.narudzba_id = narudzba_id;
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
