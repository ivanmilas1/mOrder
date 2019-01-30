package hr.foi.morder.model;

/**
 * The type Stol.
 */

public class Stol {

    /**
     * The Id. Table id, every table ha unique id value.
     */
    public int id;
    /**
     * The Stanje. Checking if table is free, taken or in order processing.
     */
    public String stanje;
    /**
     * The Racun. Bill which is made on current table.
     */
    public Racun racun;

    /**
     * The enum Stanje narudzbe.
     */
    public enum stanjeNarudzbe{
        /**
         * Slobodan stanje narudzbe.
         */
        slobodan, /**
         * Narudzba u izradi stanje narudzbe.
         */
        narudzbaUIzradi, /**
         * Narudzba posluzena stanje narudzbe.
         */
        narudzbaPosluzena
    }

    /**
     * Gets id.
     *
     * @return the id
     */
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

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets stanje.
     *
     * @return the stanje
     */
    public String getStanje() {
        return stanje;
    }

    /**
     * Sets stanje.
     *
     * @param stanje the stanje
     */
    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    /**
     * Gets racun.
     *
     * @return the racun
     */
    public Racun getRacun() {
        return racun;
    }

    /**
     * Sets racun.
     *
     * @param racun the racun
     */
    public void setRacun(Racun racun) {
        this.racun = racun;
    }
}
