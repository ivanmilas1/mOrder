package hr.foi.morder.model;

public class Kategorija {
    private Integer id;
    private String naziv;

    public Kategorija(Integer id, String naziv) {
        this.naziv = naziv;
        this.id = id;
    }

    public Kategorija() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


}
