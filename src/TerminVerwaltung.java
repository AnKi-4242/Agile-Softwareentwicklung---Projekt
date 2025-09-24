import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TerminVerwaltung {
    //Attribute
    private final TerminDB terminDb;
    private final List<Termin> termineListe = new ArrayList<>();
    private PatientVerwaltung patV;

    //Konstruktor
    public TerminVerwaltung(TerminDB terminDb) {
        this.terminDb = terminDb;
        try {
            termineListe.addAll(terminDb.getAllAppointments());
        } catch (SQLException e) {
            System.out.println("Fehler beim Laden der Termine: " + e.getMessage());
        }
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

    public List<String> zeigeAlleTermine() {
        List<String> ausgabe = new ArrayList<>();
        for (Termin t : termineListe) {
            ausgabe.add("Termin am: " + t.getDatumZeit() + ", wegen " + t.getGrund()
                + " für: Patient-ID " + t.getPatientId());
        }
        return ausgabe;
    }

    public List<Termin> zeigeTerminListeAusDb() {
        try {
            return terminDb.getAllAppointments();
        } catch (SQLException e) {
            System.out.println("Fehler beim Laden der Termine: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Termin sucheTerminNachId(int terminId) {
        try {
            Termin termin = terminDb.searchAppointmentById(terminId);
            for (Termin t : termineListe) {
                if (termin != null) {
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
            if (termin!= null) {
                return termin;
            } else {
                System.out.println("Termin konnte nicht gefunden werden");
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
            if (t != null) {
                terminDb.deleteAppointment(t);
                termineListe.removeIf(existing -> existing.getTerminId() == terminId);
                return true;
            } else {
                System.out.println("Kein Termin mit ID " + terminId + " gefunden");
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei Löschen des Termins: " + e.getMessage());
        }
        return false;
    }
} //Habe zeigeAlleTermine auskommentiert, mal sehen obs ohne klappt
