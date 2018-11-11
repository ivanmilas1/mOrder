package com.morder.morder.entities;

public class Article {
    private String naziv;
    private String jedinicna_mjera;
    private Number jedinicna_cijena;
    private Number kategorija_id;
    private String slika;

    public Article(String naziv, String jedinicna_mjera, Number jedinicna_cijena, Number kategorija_id, String slika) {
        this.naziv = naziv;
        this.jedinicna_mjera = jedinicna_mjera;
        this.jedinicna_cijena = jedinicna_cijena;
        this.kategorija_id = kategorija_id;
        this.slika = slika;
    }

    public Article(){

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

    public Number getJedinicna_cijena() {
        return jedinicna_cijena;
    }

    public void setJedinicna_cijena(Number jedinicna_cijena) {
        this.jedinicna_cijena = jedinicna_cijena;
    }

    public Number getKategorija_id() {
        return kategorija_id;
    }

    public void setKategorija_id(Number kategorija_id) {
        this.kategorija_id = kategorija_id;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
