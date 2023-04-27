package Fitnessstudio;

import java.util.Date;

public class Kurs {
    private Integer nummer;
    private String name;
    private Date datum;
    private Date startzeit;
    private Integer dauer;
    private String trainer;
    private Integer maximaleTeilnehmer;
    private Integer teilnehmerzahl;

    public Kurs(Integer nummer, String name, Date datum, Date startzeit, Integer dauer, String trainer, Integer maximaleTeilnehmer) {
        this.nummer = nummer;
        this.name = name;
        this.datum = datum;
        this.startzeit = startzeit;
        this.dauer = dauer;
        this.trainer = trainer;
        this.maximaleTeilnehmer = maximaleTeilnehmer;
    }

    // Getter and Setter methods

    public Integer getNummer() {
        return nummer;
    }

    public void setNummer(Integer nummer) {
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

    public Date getStartzeit() {
        return startzeit;
    }

    public void setStartzeit(Date startzeit) {
        this.startzeit = startzeit;
    }

    public Integer getDauer() {
        return dauer;
    }

    public void setDauer(Integer dauer) {
        this.dauer = dauer;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Integer getMaximaleTeilnehmer() {
        return maximaleTeilnehmer;
    }

    public void setMaximaleTeilnehmer(Integer maximaleTeilnehmer) {
        this.maximaleTeilnehmer = maximaleTeilnehmer;
    }

    public Integer getTeilnehmerzahl() {
        return teilnehmerzahl;
    }

    public void setTeilnehmerzahl(Integer teilnehmerzahl) {
        this.teilnehmerzahl = teilnehmerzahl;
    }
}

