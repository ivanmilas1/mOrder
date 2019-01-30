package hr.foi.morder.model;

/**
 * The type Artikl represents an article, an item in the cart.
 */
public class Artikl {
    /**
     * The Id. Article id value
     */
    public Integer id;
    /**
     * The Naziv. Represents article name
     */
    public String naziv;
    /**
     * The Jedinicna mjera. Counting mesaure of article
     */
    public String jedinicna_mjera;
    /**
     * The Jedinicna cijena. Price per unit
     */
    public Double jedinicna_cijena;
    /**
     * The Kategorija id. Article category, which can be appetizer, main course, dessert or drink.
     */
    public Integer kategorija_id;
    /**
     * The Slika. Article picture
     */
    public String slika;

    /**
     * Instantiates a new Artikl.
     *
     * @param id               the id
     * @param naziv            the naziv
     * @param jedinicna_mjera  the jedinicna mjera
     * @param jedinicna_cijena the jedinicna cijena
     * @param kategorija_id    the kategorija id
     * @param slika            the slika
     */
    public Artikl(Integer id, String naziv, String jedinicna_mjera, Double jedinicna_cijena, Integer kategorija_id, String slika) {
        this.id = id;
        this.naziv = naziv;
        this.jedinicna_mjera = jedinicna_mjera;
        this.jedinicna_cijena = jedinicna_cijena;
        this.kategorija_id = kategorija_id;
        this.slika = slika;
    }

    /**
     * Instantiates a new Artikl.
     */
    public Artikl(){

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets naziv.
     *
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets naziv.
     *
     * @param naziv the naziv
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Gets jedinicna mjera.
     *
     * @return the jedinicna mjera
     */
    public String getJedinicna_mjera() {
        return jedinicna_mjera;
    }

    /**
     * Sets jedinicna mjera.
     *
     * @param jedinicna_mjera the jedinicna mjera
     */
    public void setJedinicna_mjera(String jedinicna_mjera) {
        this.jedinicna_mjera = jedinicna_mjera;
    }

    /**
     * Gets jedinicna cijena.
     *
     * @return the jedinicna cijena
     */
    public Double getJedinicna_cijena() {
        return jedinicna_cijena;
    }

    /**
     * Sets jedinicna cijena.
     *
     * @param jedinicna_cijena the jedinicna cijena
     */
    public void setJedinicna_cijena(Double jedinicna_cijena) {
        this.jedinicna_cijena = jedinicna_cijena;
    }

    /**
     * Gets kategorija id.
     *
     * @return the kategorija id
     */
    public Integer getKategorija_id() {
        return kategorija_id;
    }

    /**
     * Sets kategorija id.
     *
     * @param kategorija_id the kategorija id
     */
    public void setKategorija_id(Integer kategorija_id) {
        this.kategorija_id = kategorija_id;
    }

    /**
     * Gets slika.
     *
     * @return the slika
     */
    public String getSlika() {
        return slika;
    }

    /**
     * Sets slika.
     *
     * @param slika the slika
     */
    public void setSlika(String slika) {
        this.slika = slika;
    }

    /**
     * Load.
     */
    public void load(){

    }
}