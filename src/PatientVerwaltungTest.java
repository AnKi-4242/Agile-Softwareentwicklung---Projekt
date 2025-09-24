import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;


public class PatientVerwaltungTest {

    @Test
    public void testErstellePatient() throws SQLException {
            PatientDB patientDb = new PatientDB();
            PatientVerwaltung patV = new PatientVerwaltung(patientDb);

            Patient p = patV.erstellePatient("Muster", "Elmar", "10.10.1982", "Musterstraße 1, 10100 Musterhaus", "elmar@muster.de", "Musterkasse");

            assertEquals("Muster", p.getNachname());
            assertEquals("Elmar", p.getVorname());
            assertNotNull(p.getPatientId());
    }

    @Test
    public void testBearbeitePatient() throws SQLException {
            PatientDB patientDb = new PatientDB();
            PatientVerwaltung patV = new PatientVerwaltung(patientDb);

            Patient p = patV.erstellePatient("Dummy", "Emma", "09.09.2000", "Dummystraße 10, 20200 Dummyhausen", "emma@dummy.com", "Dummykasse");
            p.setAdresse("Dummyweg 20, 20200 Dummyhausen");
            boolean neu = patV.bearbeitePatient(p);
            assertTrue(neu);
            assertEquals("Dummyweg 20, 20200 Dummyhausen", patV.suchePatientNachId(p.getPatientId()).getAdresse());
    }
}
