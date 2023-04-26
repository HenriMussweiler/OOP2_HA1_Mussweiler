import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class KursVerwaltung {
    public static Collection<Kurs> read(String fileName) throws IOException {
        Collection<Kurs> kursCollection = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(";");
            int nummer = Integer.parseInt(values[0]);
            String name = values[1];
            Date datum = new Date(values[2]);
            Date startzeit = new Date(values[3]);
            int dauer = Integer.parseInt(values[4]);
            String trainer = values[5];
            int maxTeilnehmer = Integer.parseInt(values[6]);

            Kurs kurs = new Kurs(nummer, name, datum, startzeit, dauer, trainer, maxTeilnehmer);
            kursCollection.add(kurs);
        }
        reader.close();

        return kursCollection;
    }
}

