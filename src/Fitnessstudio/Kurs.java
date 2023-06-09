package Fitnessstudio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Kurs {
    private int nummer;
    private String name;
    private Date datum;
    private int dauer;
    private String trainer;
    private int maximaleTeilnehmer;
    private int teilnehmerzahl;

    public Kurs(int nummer, String name, Date datum, int dauer, String trainer, int maximaleTeilnehmer) {
        this.nummer = nummer;
        this.name = name;
        this.datum = datum;
        this.dauer = dauer;
        this.trainer = trainer;
        this.maximaleTeilnehmer = maximaleTeilnehmer;
    }

    // Getter and Setter methods

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public int getMaximaleTeilnehmer() {
        return maximaleTeilnehmer;
    }

    public void setMaximaleTeilnehmer(int maximaleTeilnehmer) {
        this.maximaleTeilnehmer = maximaleTeilnehmer;
    }

    public int getTeilnehmerzahl() {
        return teilnehmerzahl;
    }

    public void setTeilnehmerzahl(int teilnehmerzahl) {
        this.teilnehmerzahl = teilnehmerzahl;
    }

    public String getDatumAsString() {
        return new SimpleDateFormat("dd.MM.yyyy 'um' HH:mm 'Uhr'").format(this.datum);
    }
}

