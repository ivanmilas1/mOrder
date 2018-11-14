package hr.foi.morder.entities;

public class Stol {

    public int StolID;

    public Object stanjeNarudzbe;

    public enum stanjeNarudzbe{
        slobodan, narudzbaUIzradi, narudzbaPosluzena
    }

    public Stol(int stolID) {
        StolID = stolID;
    }

    public int getStolID() {
        return StolID;
    }

    public void setStolID(int stolID) {
        StolID = stolID;
    }

    public Object getStanjeNarudzbe() {
        return stanjeNarudzbe;
    }

    public void setStanjeNarudzbe(Object stanjeNarudzbe) {
        this.stanjeNarudzbe = stanjeNarudzbe;
    }
}
