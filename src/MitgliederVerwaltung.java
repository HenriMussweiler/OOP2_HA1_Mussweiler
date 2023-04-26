import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class MitgliederVerwaltung {
    private List<Mitglied> mitgliederListe;

    public MitgliederVerwaltung() {
        this.mitgliederListe = new ArrayList<>();
    }

    public Collection<Mitglied> read(String fileName) throws IOException, ParseException {
        Collection<Mitglied> mitglieder = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                int nummer = Integer.parseInt(fields[0]);
                String vorname = fields[1];
                String nachname = fields[2];
                String email = fields[3];
                Date geburtsdatum = new SimpleDateFormat("dd.MM.yyyy").parse(fields[4]);
                String passwort = fields[5];
                Mitglied mitglied = new Mitglied(nummer, vorname, nachname, email, geburtsdatum, passwort);
                mitglieder.add(mitglied);
            }
        }
        return mitglieder;
    }

    public boolean login(String email, String password) {
        for (Mitglied mitglied : mitgliederListe) {
            if (mitglied.getEmail().equals(email) && mitglied.getPasswort().equals(password)) {
                test.setMitglied(mitglied);
                return true;
            }
        }
        return false;
    }


}

