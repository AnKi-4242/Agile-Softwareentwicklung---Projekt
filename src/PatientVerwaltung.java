import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientVerwaltung {

    private final PatientDB patientDb;
    private List<Patient> patientenListe = new ArrayList<>();

    //Konstruktor
    public PatientVerwaltung(PatientDB patientDb) {
        this.patientDb = patientDb;
        try {
            this.patientenListe = patientDb.getAllPatients();
        } catch (SQLException e) {
            System.out.println("Liste konnte nicht geladen werden: " + e.getMessage());
        }
    }

    //Methoden (CRUD)
    public Patient erstellePatient(String nachname, String vorname, String geburtsdatum, String adresse, String telefonEmail, String krankenkasse) {
        Patient patient = new Patient(nachname, vorname, geburtsdatum, adresse, telefonEmail, krankenkasse);
        try {
            patientDb.addPatient(patient);
            patientenListe.add(patient);
        } catch (SQLException e) {
            System.out.println("Patient konnte nicht erstellt werden: " + e.getMessage());
            ;
        }
        return patient;
    }

    public List<Patient> zeigeAllePatienten() {
        return patientenListe;
    }

    public Patient suchePatientNachId(int patientId) {
        try {
            Patient patient = patientDb.searchPatientById(patientId);
            if (patient == null) {
                System.out.println("Kein Patient mit dieser ID gefunden");//hier lassen? Oder in GUI?
            }
            return patient;
        } catch (SQLException e) {
            System.out.println("Fehler bei Suche nach Patient: " + e.getMessage());
            return null;
        }
    }

    public Patient suchePatientNachNamen(String nachname) {
        try {
            Patient patient = patientDb.searchPatientByLastname(nachname);
            if (patient == null) {
                System.out.println("Kein Patient unter diesem Nachnamen gefunden");
            }
            return patient;
        } catch (SQLException e) {
            System.out.println("Fehler bei Suche nach Patient: " + e.getMessage());
            return null;
        }
    }

    public boolean bearbeitePatient(Patient geaenderterPatient) {
        try {
            return patientDb.editPatient(geaenderterPatient);
        } catch (SQLException e) {
            System.out.println("Fehler bei Änderung des Eintrags: " + e.getMessage());
            return false;
        }
    }

    public boolean loeschePatient(int patientId) {
        try {
            Patient p = patientDb.searchPatientById(patientId);
            if (p != null) {
                patientDb.deletePatient(patientId);
                patientenListe.remove(p);
                return true;
            } else {
                System.out.println("Kein Patient gefunden");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen des Eintrags: " + e.getMessage());
        }
        return false;
    }
}
