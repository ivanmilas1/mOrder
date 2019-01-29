package hr.foi.morder.model;

/**
 * The type Kategorija.
 */
public class Kategorija {
    private Integer id;
    private String naziv;

    /**
     * Instantiates a new Kategorija.
     *
     * @param id    Id of article category
     * @param naziv Name of article category
     */
    public Kategorija(Integer id, String naziv) {
        this.naziv = naziv;
        this.id = id;
    }

    /**
     * Instantiates a new Kategorija.
     */
    public Kategorija() {
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


}
