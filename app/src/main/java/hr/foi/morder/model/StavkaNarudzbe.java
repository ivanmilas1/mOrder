package hr.foi.morder.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Stavka narudzbe. Represent articles per order, each order has multiple articles.
 */
public class StavkaNarudzbe {
    /**
     * The Artikl id. Article id.
     */
    public Integer artikl_id;
    /**
     * The Narudzba id. Order id.
     */
    public Integer narudzba_id;
    /**
     * The Cijena. Price which is sum of article price and quantity.
     */
    public double cijena;
    /**
     * The Kolicina. Quantity of article per order.
     */
    public Integer kolicina;
    /**
     * The Vrijeme narucivanja. Date and time when order was made
     */
    public Date vrijeme_narucivanja;

    /**
     * Instantiates a new Stavka narudzbe.
     */
    public StavkaNarudzbe() {
    }

    /**
     * Instantiates a new Stavka narudzbe.
     *
     * @param artikl_id  the artikl id
     * @param narudzbaId the narudzba id
     * @param cijena     the cijena
     * @param kolicina   the kolicina
     */
    public StavkaNarudzbe(Integer artikl_id, Integer narudzbaId, double cijena, Integer kolicina) {
        this.artikl_id = artikl_id;
        this.narudzba_id = narudzbaId;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    /**
     * Gets artikl id.
     *
     * @return the artikl id
     */
    public Integer getArtikl_id() {
        return artikl_id;
    }

    /**
     * Sets artikl id.
     *
     * @param artikl_id the artikl id
     */
    public void setArtikl_id(Integer artikl_id) {
        this.artikl_id = artikl_id;
    }

    /**
     * Gets narudzba id.
     *
     * @return the narudzba id
     */
    public Integer getNarudzba_id() {
        return narudzba_id;
    }

    /**
     * Sets narudzba id.
     *
     * @param narudzba_id the narudzba id
     */
    public void setNarudzba_id(Integer narudzba_id) {
        this.narudzba_id = narudzba_id;
    }

    /**
     * Gets cijena.
     *
     * @return the cijena
     */
    public double getCijena() {
        return cijena;
    }

    /**
     * Sets cijena.
     *
     * @param cijena the cijena
     */
    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    /**
     * Gets kolicina.
     *
     * @return the kolicina
     */
    public Integer getKolicina() {
        return kolicina;
    }

    /**
     * Sets kolicina.
     *
     * @param kolicina the kolicina
     */
    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    /**
     * Gets vrijeme narucivanja.
     *
     * @return the vrijeme narucivanja
     */
    public Date getVrijeme_narucivanja() {
        return vrijeme_narucivanja;
    }

    /**
     * Sets vrijeme narucivanja.
     *
     * @param vrijeme_narucivanja the vrijeme narucivanja
     */
    public void setVrijeme_narucivanja(Date vrijeme_narucivanja) {
        this.vrijeme_narucivanja = vrijeme_narucivanja;
    }

    /**
     * To map map.
     *
     * @return the map
     */
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("artikl_id", this.artikl_id);
        result.put("narudzba_id", this.narudzba_id);
        result.put("cijena", this.cijena);
        result.put("kolicina", this.kolicina);
        return result;
    }
}