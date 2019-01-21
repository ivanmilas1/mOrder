package hr.foi.morder.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Djelatnik {
    public String imePrezime;
    public String lozinka;
    public String email;
    public int tipDjelatnikaId;

    public Djelatnik(String imePrezime, String lozinka, String email, int tipDjelatnikaId) {
        this.imePrezime = imePrezime;
        this.lozinka = lozinka;
        this.email = email;
        this.tipDjelatnikaId = tipDjelatnikaId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipDjelatnikaId() {
        return tipDjelatnikaId;
    }

    public void setTipDjelatnikaId(int tipDjelatnikaId) {
        this.tipDjelatnikaId = tipDjelatnikaId;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("imePrezime", this.imePrezime);
        result.put("lozinka", this.lozinka);
        result.put("email", this.email);
        result.put("tipDjelatnikaId", this.tipDjelatnikaId);
        return result;
    }
}

