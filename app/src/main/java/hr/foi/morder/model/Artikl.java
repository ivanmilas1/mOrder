package hr.foi.morder.model;

public class Artikl {
    public String naziv;
    public String jedinicna_mjera;
    public Double jedinicna_cijena;
    public Integer kategorija_id;
    public String slika;
    public Integer id;

    public Artikl(Integer id, String naziv, String jedinicna_mjera, Double jedinicna_cijena, Integer kategorija_id, String slika) {
        this.naziv = naziv;
        this.jedinicna_mjera = jedinicna_mjera;
        this.jedinicna_cijena = jedinicna_cijena;
        this.kategorija_id = kategorija_id;
        this.slika = slika;
        this.id = id;
    }

    public Artikl(){

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
