import java.sql.SQLException;

public class Termin {
    private int terminId;
    private Patient patient;
    private int patientId;
    private String datumZeit;
    private String grund;
    private PatientDB patientDb = new PatientDB();

    //Konstruktor
    public Termin(int patientId, String datumZeit, String grund) {
        this.patientId = patientId;
        this.datumZeit = datumZeit;
        this.grund = grund;
    }

    public Termin(int terminId, int patientId, String datumZeit, String grund) {
        this.terminId = terminId;
        this.patientId = patientId;
        this.datumZeit = datumZeit;
        this.grund = grund;
    }

    //Getter
    public int getTerminId() {
        return terminId;
    }

    //Setter
    public void setTerminId(int terminId) {
        this.terminId = terminId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Patient getPatient() throws SQLException {
        PatientDB patientDb = new PatientDB();
        patient = patientDb.searchPatientById(patientId);
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDatumZeit() {
        return datumZeit;
    }

    public void setDatumZeit(String datumZeit) {
        this.datumZeit = datumZeit;
    }

    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

    @Override
    public String toString() {
        try {
            if (patient == null) {
                patient = patientDb.searchPatientById(patientId);
            }
            return "Termin {" + "Termin-ID: " + terminId + ", Patient: '" + patient.getNachname() + ", " + patient.getVorname()
                    + '\'' + ", wann: '" + getDatumZeit() +
                    '\'' + ", Grund: '" + getGrund() + '\'' + '}';
        } catch (SQLException e) {
            e.printStackTrace();
            return "Konnte nicht geladen werden";
        }
    }
}

