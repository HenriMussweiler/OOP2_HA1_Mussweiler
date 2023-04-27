package Verwaltung;

import Fitnessstudio.Mitglied;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class MitgliederVerwaltung {
    private Collection<Mitglied> mitglieder;

    public MitgliederVerwaltung() {
        this.mitglieder = new ArrayList<>();
    }

    public Collection<Mitglied> read(String fileName) throws IOException, ParseException {
        this.mitglieder = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(", ");
                int nummer = Integer.parseInt(fields[0]);
                String vorname = fields[1];
                String nachname = fields[2];
                String email = fields[3];
                Date geburtsdatum = new SimpleDateFormat("dd.MM.yyyy").parse(fields[4]);
                String passwort = fields[5];
                Mitglied mitglied = new Mitglied(nummer, vorname, nachname, email, geburtsdatum, passwort);
                this.mitglieder.add(mitglied);
            }
        }
        return this.mitglieder;
    }

    public boolean login(String email, String password) {
        for (Mitglied mitglied : this.mitglieder) {
            if (mitglied.getEmail().equals(email) && mitglied.getPasswort().equals(password)) {
                return true;
            }
        }
        return false;
    }


    public Mitglied getMitglied(String email, String password) {
        for (Mitglied mitglied : this.mitglieder) {
            if (mitglied.getEmail().equals(email) && mitglied.getPasswort().equals(password)) {
                return mitglied;
            }
        }
        return null;
    }
}

