package Start;

import Verwaltung.BuchungsVerwaltung;
import Verwaltung.KursVerwaltung;
import Verwaltung.MitgliederVerwaltung;
import Fitnessstudio.*;

import java.text.ParseException;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Test {
    private static MitgliederVerwaltung mitgliederVerwaltung = new MitgliederVerwaltung();
    private static KursVerwaltung kursVerwaltung = new KursVerwaltung();
    private static BuchungsVerwaltung buchungsVerwaltung = new BuchungsVerwaltung();
    private static Mitglied mitglied;
    private static Collection<Mitglied> mitglieder;
    private static Collection<Kurs> kurse;
    private static Collection<Buchung> buchungen;
    private static final String filepath = "src/BereitgestellteDateien/";

    public static void main(String[] args) {
        // Lade Mitglieder, Kurse und Buchungen aus CSV-Dateien
        try {
            mitglieder = mitgliederVerwaltung.read(filepath + "mitglieder.csv");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            kurse = kursVerwaltung.read(filepath +"kurse.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buchungen = buchungsVerwaltung.read(filepath + "buchungen.csv");

        // Login des Mitglieds und anzeigen des Menüs
        boolean success = true;
        while(success) {
            if (mitglied != null) {
                anzeigenMenu();
            } else {
                success = login();
            }
        }
    }

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);

        // Anmeldeinformationen vom Benutzer abfragen
        System.out.println("Bitte melden Sie sich an!");
        System.out.print("E-Mail-Adresse: ");
        String email = scanner.nextLine();
        System.out.print("Passwort: ");
        String password = scanner.nextLine();

        // Verwaltung.MitgliederVerwaltung-Objekt erstellen und Fitnessstudio.Mitglied einloggen
        boolean success = mitgliederVerwaltung.login(email, password);

        if (success) {
            System.out.println("Anmeldung erfolgreich.");
            mitglied = mitgliederVerwaltung.getMitglied(email, password);
        } else {
            System.out.println("Anmeldung fehlgeschlagen.");
            System.out.println("Programm wird beendet.");
        }
        return success;
    }

    private static void anzeigenMenu() {
        Scanner scanner = new Scanner(System.in);
        String option = "";
        while (!option.equals("e")) {
            System.out.println("Menü:");
            System.out.println("a) Fitnessstudio.Kurs buchen");
            System.out.println("b) Buchungen anzeigen");
            System.out.println("c) Fitnessstudio.Buchung stornieren");
            System.out.println("d) Speichern");
            System.out.println("e) Abmelden");
            System.out.print("Option wählen (a-e): ");
            option = scanner.nextLine();
            switch (option) {
                case "a":
                    buchenKurs();
                    break;
                case "b":
                    anzeigenBuchungen();
                    break;
                case "c":
                    stornierenBuchung();
                    break;
                case "d":
                    speichern();
                    break;
                case "e":
                    abmelden();
                    break;
                default:
                    System.out.println("Fehler: Ungültige Option.");
                    break;
            }
        }
    }

    private static void abmelden() {
        mitglied = null;
    }

    private static void buchenKurs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Verfügbare Kurse:");
        for (Kurs kurs : kurse) {
            if (kurs.getTeilnehmerzahl() < kurs.getMaximaleTeilnehmer()) {
                System.out.println(kurs.getNummer() + ") " + kurs.getName() + " am " + kurs.getDatum() + " um " + kurs.getStartzeit() + " (noch " + (kurs.getMaximaleTeilnehmer() - kurs.getTeilnehmerzahl()) + " freie Plätze)");
            }
        }
        System.out.print("Kursnummer wählen: ");
        int kursNummer = scanner.nextInt();
        Kurs gebuchterKurs = null;
        for (Kurs kurs : kurse) {
            if (kurs.getNummer() == kursNummer) {
                if (kurs.getTeilnehmerzahl() < kurs.getMaximaleTeilnehmer()) {
                    gebuchterKurs = kurs;
                } else {
                    System.out.println("Fehler: Der Fitnessstudio.Kurs ist bereits voll.");
                    return;
                }
            }
        }
        if (gebuchterKurs == null) {
            System.out.println("Fehler: Kursnummer nicht gefunden.");
            return;
        }
        Buchung neueBuchung = new Buchung(mitglied.getNummer(), gebuchterKurs.getNummer(), new Date());
        buchungen.add(neueBuchung);
        gebuchterKurs.setTeilnehmerzahl(gebuchterKurs.getTeilnehmerzahl() + 1);
        System.out.println("Erfolgreich gebucht für Fitnessstudio.Kurs " + gebuchterKurs.getName() + " am " + gebuchterKurs.getDatum() + " um " + gebuchterKurs.getStartzeit() + ".");
    }

    private static void anzeigenBuchungen() {
        System.out.println("Buchungen für " + mitglied.getVorname() + " " + mitglied.getNachname() + ":");
        boolean gefunden = false;
        for (Buchung buchung : buchungen) {
            if (buchung.getMitgliedsnummer() == mitglied.getNummer()) {
                for (Kurs kurs : kurse) {
                    if (kurs.getNummer() == buchung.getKursnummer()) {
                        System.out.println("- " + kurs.getName() + " am " + kurs.getDatum() + " um " + kurs.getStartzeit());
                        gefunden = true;
                        break;
                    }
                }
            }
        }
        if (!gefunden) {
            System.out.println("Keine Buchungen gefunden.");
        }
    }

    private static void stornierenBuchung() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie die Nummer der Fitnessstudio.Buchung ein, die storniert werden soll: ");
        int buchungsNummer = scanner.nextInt();
        Buchung buchung = null;
        for (Buchung b : buchungen) {
            if (b.getMitgliedsnummer() == mitglied.getNummer() && b.getKursnummer() == buchungsNummer) {
                buchung = b;
                break;
            }
        }
        if (buchung == null) {
            System.out.println("Fehler: Fitnessstudio.Buchung nicht gefunden.");
            return;
        }
        Kurs kurs = null;
        for (Kurs k : kurse) {
            if (k.getNummer() == buchung.getKursnummer()) {
                kurs = k;
                break;
            }
        }
        if (kurs == null) {
            System.out.println("Fehler: Fitnessstudio.Kurs nicht gefunden.");
            return;
        }
        buchungen.remove(buchung);
        kurs.setTeilnehmerzahl(kurs.getTeilnehmerzahl() - 1);
        System.out.println("Fitnessstudio.Buchung erfolgreich storniert.");
    }

    private static void speichern() {

        //TODO Unterschiedliche Fälle müssen noch berücksichtigt werden (1) Buchungen des angemeldeten Mitglieds oder (2) aller Mitglieder analog zur buchungen.csv Datei

        try {
            FileWriter writer = new FileWriter("buchungen.csv");
            writer.write("Mitgliedsnummer,Kursnummer,Buchungsdatum\n");
            for (Buchung buchung : buchungen) {
                writer.write(buchung.getMitgliedsnummer() + "," + buchung.getKursnummer() + "," + buchung.getBuchungsdatum() + "\n");
            }
            writer.close();
            System.out.println("Buchungen erfolgreich gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler: Buchungen konnten nicht gespeichert werden.");
            e.printStackTrace();
        }
    }

}
