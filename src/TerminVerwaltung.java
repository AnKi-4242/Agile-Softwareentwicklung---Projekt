import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TerminVerwaltung {
    //Attribute
    private final TerminDB terminDb;
    private final List<Termin> termineListe = new ArrayList<>();

    //Konstruktor
    public TerminVerwaltung(TerminDB terminDb) {
        this.terminDb = terminDb;
    }

    public Termin erstelleTermin(int patientId, String datumZeit, String grund) {
        Termin termin = new Termin(patientId, datumZeit, grund);
        try {
            terminDb.createAppointment(termin);
            terminDb.getPatientById(patientId);
            termineListe.add(termin);
        } catch (SQLException e) {
            System.out.println("Termin konnte nicht erstellt werden: " + e.getMessage());
        }
        return termin;
    }

    public List<Termin> zeigeAlleTermine() {
        return termineListe;
    }

    public Termin sucheTerminNachId(int terminId) {
        try {
            Termin termin = terminDb.searchAppointmentById(terminId);
            for (Termin t : termineListe) {
                if (terminId == t.getTerminId()) {
                    return termin;
                } else {
                    System.out.println("Termin konnte nicht gefunden werden");
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei Suche nach Termin: " + e.getMessage());
        }
        return null;
    }

    public Termin sucheTerminNachDatum(String datumZeit) {
        try {
            Termin termin = terminDb.searchAppointmentByDate(datumZeit);
            for (Termin t : termineListe) {
                if (Objects.equals(t.getDatumZeit(), datumZeit)) {
                    return termin;
                } else {
                    System.out.println("Termin konnte nicht gefunden werden");
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei Suche nach Termin: " + e.getMessage());
        }
        return null;
    }

    public boolean bearbeiteTermin(Termin geaenderterTermin) {
        try {
            return terminDb.editAppointment(geaenderterTermin);
        } catch (SQLException e) {
            System.out.println("Fehler bei Änderung des Termins: " + e.getMessage());
        }
        return false;
    }

    public boolean loescheTermin(int terminId) {
        try {
            Termin t = terminDb.searchAppointmentById(terminId);
            if (t.getTerminId() == terminId) {
                terminDb.deleteAppointment(t);
                termineListe.remove(t);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei Löschen des Termins: " + e.getMessage());
        }
        return false;
    }
}
