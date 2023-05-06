package Verwaltung;

import Fitnessstudio.Buchung;
import Fitnessstudio.Mitglied;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BuchungsVerwaltung {

    public Collection<Buchung> read(String fileName) {
        List<Buchung> buchungen = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ", ";
        try {
            br = new BufferedReader(new FileReader(fileName));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                int mitgliedsnummer = Integer.parseInt(values[0]);
                int kursnummer = Integer.parseInt(values[1]);
                Date buchungsdatum = new SimpleDateFormat("yyyy-MM-dd").parse(values[2]);
                Buchung buchung = new Buchung(mitgliedsnummer, kursnummer, buchungsdatum);
                buchungen.add(buchung);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buchungen;
    }

    public void writeForMember1(String filepath, Collection<Buchung> buchungen, Mitglied mitglied) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filepath))) {
            Collection<Buchung> temporaereBuchungsListe = new ArrayList<>();
            for (Buchung buchung : buchungen) {
                if (buchung.getMitgliedsnummer() == mitglied.getNummer()) {
                    temporaereBuchungsListe.add(buchung);
                }
            }
            outputStream.writeObject(temporaereBuchungsListe);
            System.out.println("Buchungen des Mitglieds " + mitglied.getEmail() + " wurden erfolgreich gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Buchungen des Mitglieds " + mitglied.getEmail() + ": " + e.getMessage());
        }
    }

    public void writeForMember2(String filepath, Collection<Buchung> buchungen, Mitglied mitglied) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filepath))) {
            for (Buchung buchung : buchungen) {
                if (buchung.getMitgliedsnummer() == mitglied.getNummer()) {
                    outputStream.writeObject(buchung);
                }
            }
            System.out.println("Buchungen des Mitglieds " + mitglied.getEmail() + " wurden erfolgreich gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Buchungen des Mitglieds " + mitglied.getEmail() + ": " + e.getMessage());
        }
    }

    public void writeAll(String filepath, Collection<Buchung> buchungen) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write("Mitgliedsnummer, Kursnummer, Buchungsdatum");
        for (Buchung buchung : buchungen) {
            writer.write("\n" + buchung.getMitgliedsnummer() + ", ");
            writer.write(buchung.getKursnummer() + ", ");
            writer.write(buchung.getBuchungsdatumAsString() + "");
        }
        writer.close();
    }

}

