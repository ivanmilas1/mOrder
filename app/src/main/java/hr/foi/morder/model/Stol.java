package hr.foi.morder.model;

public class Stol {

    public int StolID;

    public String stanjeNarudzbe;

    public int kategorijaId;

    public enum stanjeNarudzbe{
        slobodan, narudzbaUIzradi, narudzbaPosluzena
    }

    public Stol(int stolID) {
        StolID = stolID;
    }

    public int getStolID() {
        return StolID;    }

    public void setStolID(int stolID) {
        StolID = stolID;
    }

    public String getStanjeNarudzbe() {
        return stanjeNarudzbe;
    }

    public void setStanjeNarudzbe(String stanjeNarudzbe) {
        this.stanjeNarudzbe = stanjeNarudzbe;
    }

    public int getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(int kategorijaId) {
        this.kategorijaId = kategorijaId;
    }
}
