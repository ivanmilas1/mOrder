package hr.foi.morder.model;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Narudzba {
    private FirebaseFirestore database;
    Integer id;
    Double iznos_narudzbe;
    Integer korisnik_id;
    Integer racun_id;
    String status;

    public Narudzba(Integer id, Double iznos_narudzbe, Integer korisnik_id, Integer racun_id, String status) {
        this.id = id;
        this.iznos_narudzbe = iznos_narudzbe;
        this.korisnik_id = korisnik_id;
        this.racun_id = racun_id;
        this.status = status;
    }

    public Narudzba(Integer id, String status){
        this.id = id;
        this.status = status;
    }

    public Narudzba(Double cijena){
        this.iznos_narudzbe = cijena;
    }

    public Narudzba(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getIznos_narudzbe() {
        return iznos_narudzbe;
    }

    public void setIznos_narudzbe(Double iznos_narudzbe) {
        this.iznos_narudzbe = iznos_narudzbe;
    }

    public Integer getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(Integer korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public Integer getRacun_id() {
        return racun_id;
    }

    public void setRacun_id(Integer racun_id) {
        this.racun_id = racun_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", this.id);
        result.put("status", this.status);
        result.put("iznos_narudzbe", this.iznos_narudzbe);
        return result;
    }

    public void addCijenaNarudzbe(Double cijena) {
        Map<String, Object> idNarudzbe = new Narudzba(cijena).toMap();
        database.collection("Narudzba")
                .add(idNarudzbe)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });
    }

}

