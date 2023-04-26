import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(fileName));
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
}

