package hr.foi.morder.model;

import java.util.Date;

public class Korisnik {
    public Integer id;
    public String imePrezime;
    public String lozinka;
    public Date datumRodjenja;
    public String adresaPrebivalista;
    public TipKorisnika tipKorisnika;

    public Korisnik(Integer id, String imePrezime, String lozinka, Date datumRodjenja, String adresaPrebivalista, TipKorisnika tipKorisnika) {
        this.id = id;
        this.imePrezime = imePrezime;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.adresaPrebivalista = adresaPrebivalista;
        this.tipKorisnika = tipKorisnika;
    }

    public Korisnik() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getAdresaPrebivalista() {
        return adresaPrebivalista;
    }

    public void setAdresaPrebivalista(String adresaPrebivalista) {
        this.adresaPrebivalista = adresaPrebivalista;
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }


}

