package hr.foi.morder.model;

import java.util.HashMap;
import java.util.Map;

public class Narudzba {
    Integer id;
    Integer iznos_narudzbe;
    Integer korisnik_id;
    Integer racun_id;
    String status;

    public Narudzba(Integer id, Integer iznos_narudzbe, Integer korisnik_id, Integer racun_id, String status) {
        this.id = id;
        this.iznos_narudzbe = iznos_narudzbe;
        this.korisnik_id = korisnik_id;
        this.racun_id = racun_id;
        this.status = status;
    }

    public Narudzba(Integer id){
        this.id = id;
    }

    public Narudzba(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIznos_narudzbe() {
        return iznos_narudzbe;
    }

    public void setIznos_narudzbe(Integer iznos_narudzbe) {
        this.iznos_narudzbe = iznos_narudzbe;
    }

    public Integer getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(Integer korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public Integer getRacun_id() {
        return racun_id;
    }

    public void setRacun_id(Integer racun_id) {
        this.racun_id = racun_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", this.id);

        return result;
    }


}

