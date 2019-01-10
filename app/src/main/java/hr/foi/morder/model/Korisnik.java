package hr.foi.morder.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Korisnik {
    public String id;
    public String imePrezime;
    public String lozinka;
    public Date datumRodjenja;
    public String adresaPrebivalista;
    public TipKorisnika tipKorisnika;

   public Korisnik(String id,String imePrezime,String lozinka,String adresaPrebivalista){

        this.id = id;
        this.imePrezime = imePrezime;
        this.lozinka = lozinka;
        this.adresaPrebivalista = adresaPrebivalista;
    }

    public Korisnik(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

