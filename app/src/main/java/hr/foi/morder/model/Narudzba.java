package hr.foi.morder.model;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Narudzba.
 */
public class Narudzba {
    private FirebaseFirestore database;
    /**
     * The Id. Order id number
     */
    Integer id;
    /**
     * The Iznos narudzbe. Order price
     */
    Double iznos_narudzbe;
    /**
     * The Korisnik id. User id
     */
    Integer korisnik_id;
    /**
     * The Racun id. Bill id
     */
    Integer racun_id;
    /**
     * The Status. Order status
     */
    String status;

    /**
     * Instantiates a new Narudzba.
     */
    public Narudzba() {
    }

    /**
     * Instantiates a new Narudzba.
     *
     * @param iznos_narudzbe the iznos narudzbe
     */
    public Narudzba(Double iznos_narudzbe) {
        this.iznos_narudzbe = iznos_narudzbe;
    }

    public Narudzba(Integer id, Double iznos, String status){
        this.id = id;
        this.iznos_narudzbe = iznos;
        this.status = status;
    }

    public Narudzba(Integer id, Double iznos, String status, Integer racunID){
        this.id = id;
        this.iznos_narudzbe = iznos;
        this.status = status;
        this.racun_id = racunID;
    }

    /**
     * Instantiates a new Narudzba.
     *
     * @param id             the id
     * @param iznos_narudzbe the iznos narudzbe
     * @param korisnik_id    the korisnik id
     * @param racun_id       the racun id
     * @param status         the status
     */
    public Narudzba(Integer id, Double iznos_narudzbe, Integer korisnik_id, Integer racun_id, String status) {
        this.id = id;
        this.iznos_narudzbe = iznos_narudzbe;
        this.korisnik_id = korisnik_id;
        this.racun_id = racun_id;
        this.status = status;
    }

    /**
     * Gets database.
     *
     * @return the database
     */
    public FirebaseFirestore getDatabase() {
        return database;
    }

    /**
     * Sets database.
     *
     * @param database the database
     */
    public void setDatabase(FirebaseFirestore database) {
        this.database = database;
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
     * Gets iznos narudzbe.
     *
     * @return the iznos narudzbe
     */
    public Double getIznos_narudzbe() {
        return iznos_narudzbe;
    }

    /**
     * Sets iznos narudzbe.
     *
     * @param iznos_narudzbe the iznos narudzbe
     */
    public void setIznos_narudzbe(Double iznos_narudzbe) {
        this.iznos_narudzbe = iznos_narudzbe;
    }

    /**
     * Gets korisnik id.
     *
     * @return the korisnik id
     */
    public Integer getKorisnik_id() {
        return korisnik_id;
    }

    /**
     * Sets korisnik id.
     *
     * @param korisnik_id the korisnik id
     */
    public void setKorisnik_id(Integer korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    /**
     * Gets racun id.
     *
     * @return the racun id
     */
    public Integer getRacun_id() {
        return racun_id;
    }

    /**
     * Sets racun id.
     *
     * @param racun_id the racun id
     */
    public void setRacun_id(Integer racun_id) {
        this.racun_id = racun_id;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * To map map.
     *
     * @return the map
     */
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", this.id);
        result.put("iznos_narudzbe", this.iznos_narudzbe);
        result.put("racun_id", this.racun_id);
        result.put("restoran", this.status);
        return result;
    }

    /**
     * Add cijena narudzbe.
     *
     * @param cijena the cijena
     */
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