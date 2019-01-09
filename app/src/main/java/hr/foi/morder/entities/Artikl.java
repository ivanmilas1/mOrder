package hr.foi.morder.entities;

public class Artikl {
    private String naziv;
    private String jedinicna_mjera;
    private Double jedinicna_cijena;
    private Integer kategorija_id;
    private String slika;

    public Artikl(String naziv, String jedinicna_mjera, Double jedinicna_cijena, Integer kategorija_id, String slika) {
        this.naziv = naziv;
        this.jedinicna_mjera = jedinicna_mjera;
        this.jedinicna_cijena = jedinicna_cijena;
        this.kategorija_id = kategorija_id;
        this.slika = slika;
    }

    public Artikl(){

    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getJedinicna_mjera() {
        return jedinicna_mjera;
    }

    public void setJedinicna_mjera(String jedinicna_mjera) {
        this.jedinicna_mjera = jedinicna_mjera;
    }

    public Double getJedinicna_cijena() {
        return jedinicna_cijena;
    }

    public void setJedinicna_cijena(Double jedinicna_cijena) {
        this.jedinicna_cijena = jedinicna_cijena;
    }

    public Integer getKategorija_id() {
        return kategorija_id;
    }

    public void setKategorija_id(Integer kategorija_id) {
        this.kategorija_id = kategorija_id;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
