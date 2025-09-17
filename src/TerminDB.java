import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TerminDB {

    public Termin createAppointment(Termin termin) throws SQLException {
        String sql = "INSERT INTO termin (patient_id, datum_zeit, grund) VALUES (?, ?, ?)";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, termin.getPatientId());
            pst.setString(2, termin.getDatumZeit());
            pst.setString(3, termin.getGrund());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    termin.setTerminId(rs.getInt(1));
                }
            }
            return termin;
        }
    }

    public List<Termin> getAllAppointments() throws SQLException {
        List<Termin> termineListe = new ArrayList<>();
        String sql = "SELECT * FROM termin";
        //kein PS, weil ich nur was ausgeben lasse und keine Platzhalter hab, aber trd ein Statement:
        try (Connection conn = DatenbankPraxis.connection();
             Statement sts = conn.createStatement();
             ResultSet rs = sts.executeQuery(sql)) {
            while (rs.next()) {
                Termin t = new Termin(
                        rs.getInt("terminId"),
                        rs.getInt("patient_id"),
                        rs.getString("datum_zeit"),
                        rs.getString("grund")
                );
                termineListe.add(t);
            }
        }
        return termineListe;
    }

    public Termin searchAppointmentById(int terminId) throws SQLException {
        String sql = "SELECT * FROM termin WHERE termin_id = ?";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, terminId);
            //jetzt ist alles vorbereitet und die Query kann ausgeführt werden:
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Termin(
                            rs.getInt("termin_id"),
                            rs.getInt("patient_id"),
                            rs.getString("datum_zeit"),
                            rs.getString("grund")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    public Termin searchAppointmentByDate(String datumZeit) throws SQLException {
        String sql = "SELECT * FROM termin WHERE datum_zeit = ?";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(3, "datumZeit");
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Termin(
                            rs.getInt("terminId"),
                            rs.getInt("patientId"),
                            rs.getString("datumZeit"),
                            rs.getString("grund")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    public boolean editAppointment(Termin termin) throws SQLException {
        String sql = "UPDATE termin SET termin_id = ?, patient_id = ?, datum_zeit = ?, grund = ?";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, termin.getTerminId());
            pst.setInt(2, termin.getPatientId());
            pst.setString(3, termin.getDatumZeit());
            pst.setString(4, termin.getGrund());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteAppointment(Termin termin) throws SQLException {
        String sql = "DELETE FROM termin WHERE termin_id = ?";
        try (Connection conn = DatenbankPraxis.connection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, termin.getTerminId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public Patient getPatientById(int patientId) throws SQLException {
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
                }
            }
            return null;
        }
    }
}
