import java.util.Date;
public class Buchung {
    private int mitgliedsnummer;
    private int kursnummer;
    private Date buchungsdatum;

    public Buchung(int mitgliedsnummer, int kursnummer, Date buchungsdatum) {
        this.mitgliedsnummer = mitgliedsnummer;
        this.kursnummer = kursnummer;
        this.buchungsdatum = buchungsdatum;
    }

    public int getMitgliedsnummer() {
        return mitgliedsnummer;
    }

    public void setMitgliedsnummer(int mitgliedsnummer) {
        this.mitgliedsnummer = mitgliedsnummer;
    }

    public int getKursnummer() {
        return kursnummer;
    }

    public void setKursnummer(int kursnummer) {
        this.kursnummer = kursnummer;
    }

    public Date getBuchungsdatum() {
        return buchungsdatum;
    }

    public void setBuchungsdatum(Date buchungsdatum) {
        this.buchungsdatum = buchungsdatum;
    }
}

