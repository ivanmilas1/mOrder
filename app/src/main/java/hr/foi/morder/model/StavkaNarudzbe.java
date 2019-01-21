package hr.foi.morder.model;

import java.util.HashMap;
import java.util.Map;

public class StavkaNarudzbe {
    Integer artikl_id;
    Integer narudzbaId;
    Double cijena;
    Integer kolicina;

    public StavkaNarudzbe(Integer artikl_id, Integer narudzbaId, Double cijena, Integer kolicina) {
        this.artikl_id = artikl_id;
        this.narudzbaId = narudzbaId;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    public StavkaNarudzbe() {

    }

    public Integer getArtikl_id() {
        return artikl_id;
    }

    public void setArtikl_id(Integer artikl_id) {
        artikl_id = artikl_id;
    }

    public Integer getNarudzbaId() {
        return narudzbaId;
    }

    public void setNarudzbaId(Integer narudzbaId) {
        this.narudzbaId = narudzbaId;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("artikl_id", this.artikl_id);
        result.put("narudzba_id", this.narudzbaId);
        result.put("cijena", this.cijena);
        result.put("kolicina", this.kolicina);
        return result;
    }
}