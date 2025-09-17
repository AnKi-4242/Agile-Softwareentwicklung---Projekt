import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        try {
            PatientDB patientDb = new PatientDB();
            TerminDB terminDb = new TerminDB();

            PatientVerwaltung patV = new PatientVerwaltung(patientDb);
            TerminVerwaltung teV = new TerminVerwaltung(terminDb);

            Patient patient1 = patV.erstellePatient(
                    "Muster", "Emma", "19.10.1988", "Musterweg 1, 20200 Mustern", "emma@mustermail.de", "Musterkasse");
            Patient patient2 = patV.erstellePatient(
                    "Dummy", "Jonas", "07.08.2000", "Dummystraße 2, 30300 Dummydorf", "jonas@dummymail.de", "dummykasse");

            Termin termin1 = teV.erstelleTermin(patient1.getPatientId(), "12.12.2025", "Musterung");

            Termin termin2 = teV.erstelleTermin(patient2.getPatientId(), "15.11.2025", "Routineuntersuchung");

            System.out.println("Alle Patienten: ");
            for (Patient p : patV.zeigeAllePatienten()) {
                System.out.println(p.getNachname() + ", " + p.getVorname() + ", ID: " + p.getPatientId());
            }

            System.out.println("Alle Termine: ");
            for (Termin t : teV.zeigeAlleTermine()) {
                Patient pat = patientDb.searchPatientFromId(t.getPatientId());
                if (pat != null) {
                    System.out.println("Termin für " + pat.getNachname() + ", " + pat.getVorname() + ", am "
                            + t.getDatumZeit() + " wegen: " + t.getGrund());
                } else {
                    System.out.println("Termin für " + pat.getNachname() + ", " + pat.getVorname() + " am "
                    + t.getDatumZeit() + " konnte nicht gefunden werden");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}