package Verwaltung;

import Fitnessstudio.Kurs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class KursVerwaltung {
    public Collection<Kurs> read(String fileName) throws IOException {
        Collection<Kurs> kursCollection = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null && !"".equals(line)) {
            String[] values = line.split(",");
            int nummer = Integer.parseInt(values[0]);
            String name = values[1];
            Date datum = null;
            try {
                datum = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(values[2] + " " + values[3]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            int dauer = Integer.parseInt(values[4]);
            String trainer = values[5];
            int maxTeilnehmer = Integer.parseInt(values[6]);

            Kurs kurs = new Kurs(nummer, name, datum, dauer, trainer, maxTeilnehmer);
            kursCollection.add(kurs);
        }
        reader.close();

        return kursCollection;
    }
}

