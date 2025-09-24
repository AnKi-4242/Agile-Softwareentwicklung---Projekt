import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class TerminVerwaltungTest {

    @Test
    public void testErstelleTermin() throws SQLException {
        PatientDB patDb = new PatientDB();
        PatientVerwaltung pV = new PatientVerwaltung(patDb);
        Patient p = pV.erstellePatient("Müller", "Marius", "02.02.1982", "Mariusmüllerstraße 5, 30300 Westernhagen", "marius@müller.de", "Müllerkasse");
        TerminDB terminDb = new TerminDB();
        TerminVerwaltung teV = new TerminVerwaltung(terminDb);

        Termin t = teV.erstelleTermin(p.getPatientId(), "10.10.2025, 11:30 Uhr", "Heiserkeit");

        assertNotNull(t);
        assertEquals(p.getPatientId(), t.getPatientId());
    }

    @Test
    public void testLoeschePatient() throws SQLException {
        PatientDB patDb = new PatientDB();
        PatientVerwaltung pV = new PatientVerwaltung(patDb);

        Patient p = pV.erstellePatient("Sanchez", "Rick", "05.05.1995", "Mortystraße 50, 50500 Rickhausen", "rick@morty.de", "Sanchezkasse");

        TerminDB terminDb = new TerminDB();
        TerminVerwaltung teV = new TerminVerwaltung(terminDb);

        Termin t = teV.erstelleTermin(p.getPatientId(), "11.11.2025, 11:11 Uhr", "Bauchschmerzen");

        boolean deleted = teV.loescheTermin(t.getTerminId());
        assertTrue(deleted);
    }
}
