import java.util.Date;
public class Mitglied {
    private int nummer;
    private String vorname;
    private String nachname;
    private String email;
    private Date geburtsdatum;
    private String passwort;

    public Mitglied(int nummer, String vorname, String nachname, String email, Date geburtsdatum, String passwort) {
        this.nummer = nummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
        this.passwort = passwort;
    }

    public int getNummer() {
        return nummer;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getEmail() {
        return email;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getPasswort() {
        return passwort;
    }
}
