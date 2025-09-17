import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDB {


    //Methode zum Hinzufügen des Patienten zur Datenbank
    public Patient addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (nachname, vorname, geburtsdatum, adresse, kontakt, kv) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, patient.getNachname());
            pst.setString(2, patient.getVorname());
            pst.setString(3, patient.getGeburtsDatum());
            pst.setString(4, patient.getAdresse());
            pst.setString(5, patient.getTelefonEmail());
            pst.setString(6, patient.getKrankenkasse());
            pst.executeUpdate();

            try (ResultSet result = pst.getGeneratedKeys()) {
                if (result.next()) {
                    int id = result.getInt(1);
                    patient.setPatientId(id);
                }
            }
            return patient;
        }
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patientenListe = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        try (Connection conn = DatenbankPraxis.connection();
             Statement sts = conn.createStatement();
             ResultSet rs = sts.executeQuery(sql)) {
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("nachname"),
                        rs.getString("vorname"),
                        rs.getString("geburtsdatum"),
                        rs.getString("adresse"),
                        rs.getString("kontakt"),
                        rs.getString("kv")
                );
                patientenListe.add(p);
            }
        }
        return patientenListe;
    }

    //Methode searchPatientFromId()
    public Patient searchPatientById(int patientId) throws SQLException {
        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, patientId);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    return new Patient(
                            rs.getInt("patient_id"),
                            rs.getString("nachname"),
                            rs.getString("vorname"),
                            rs.getString("geburtsdatum"),
                            rs.getString("adresse"),
                            rs.getString("kontakt"),
                            rs.getString("kv")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    //Methode searchPatientFromLastname()
    public Patient searchPatientByLastname(String nachname) throws SQLException {
        String sql = "SELECT * FROM patient WHERE nachname = ?";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(2, "nachname");

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("patient_id"),
                            rs.getString("nachname"),
                            rs.getString("vorname"),
                            rs.getString("geburtsdatum"),
                            rs.getString("adresse"),
                            rs.getString("kontakt"),
                            rs.getString("kv")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    //Methode editPatient()
    public boolean editPatient(Patient patient) throws SQLException {
        String sql = "UPDATE patient SET patient_id = ?, nachname = ?, vorname = ?, geburtsdatum = ?, adresse = ?, kontakt = ?, kv = ?";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, patient.getPatientId());
            pst.setString(2, patient.getNachname());
            pst.setString(3, patient.getVorname());
            pst.setString(4, patient.getGeburtsDatum());
            pst.setString(5, patient.getAdresse());
            pst.setString(6, patient.getTelefonEmail());
            pst.setString(7, patient.getKrankenkasse());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        }
    }

    //Methode deletePatient()
    public boolean deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patient WHERE patient_id = ?";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, patientId);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
