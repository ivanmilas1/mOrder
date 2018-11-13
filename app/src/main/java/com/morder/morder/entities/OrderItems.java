package com.morder.morder.entities;

import java.util.HashMap;
import java.util.Map;

public class OrderItems {
    Integer Artikl_id;
    Integer Racun_id;
    Integer cijena;
    Integer kolicina;

    public OrderItems(Integer artikl_id, Integer racun_id, Integer cijena, Integer kolicina) {
        Artikl_id = artikl_id;
        Racun_id = racun_id;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    public OrderItems() {

    }

    public Integer getArtikl_id() {
        return Artikl_id;
    }

    public void setArtikl_id(Integer artikl_id) {
        Artikl_id = artikl_id;
    }

    public Integer getRacun_id() {
        return Racun_id;
    }

    public void setRacun_id(Integer racun_id) {
        Racun_id = racun_id;
    }

    public Integer getCijena() {
        return cijena;
    }

    public void setCijena(Integer cijena) {
        this.cijena = cijena;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Artikl_id", this.Artikl_id);
        result.put("Racun_id", this.Racun_id);
        result.put("cijena", this.cijena);
        result.put("kolicina", this.kolicina);

        return result;
    }
}
