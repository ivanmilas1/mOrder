package hr.foi.morder.model;

import java.util.HashMap;
import java.util.Map;

public class Narudzba {
    public int id;
    public double iznos_narudzbe;
    public int racun_id;

    public Narudzba(int id, double iznos_narudzbe, int racun_id){
        id = this.id;
        iznos_narudzbe  = this.iznos_narudzbe;
        racun_id = this.racun_id;
    }
    public Narudzba(){

    }
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

    public int getRacun_id() {
        return racun_id;
    }

    public void setRacun_id(int racun_id) {
        this.racun_id = racun_id;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", this.id);
        result.put("iznos_narudzbe", this.iznos_narudzbe);
        result.put("racun_id", this.racun_id);
        return result;
    }
}
