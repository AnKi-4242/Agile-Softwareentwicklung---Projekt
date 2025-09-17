public class Patient {
    //Attribute
    private int patientId;
    private String nachname;
    private String vorname;
    private String geburtsDatum;
    private String adresse;
    private String telefonEmail;
    private String krankenkasse;

    //Konstruktor
    public Patient(String nachname, String vorname, String geburtsDatum, String adresse, String telefonEmail, String krankenkasse) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsDatum = geburtsDatum;
        this.adresse = adresse;
        this.telefonEmail = telefonEmail;
        this.krankenkasse = krankenkasse;
    }

    public Patient(int patientId, String nachname, String vorname, String geburtsDatum, String adresse, String telefonEmail, String krankenkasse) {
        this.patientId = patientId;
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsDatum = geburtsDatum;
        this.adresse = adresse;
        this.telefonEmail = telefonEmail;
        this.krankenkasse = krankenkasse;
    }

    //Getter:
    public int getPatientId() {
        return patientId;
    }

    //Setter:
    public void setPatientId(int patientenId) {
        this.patientId = patientenId;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(String geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelefonEmail() {
        return telefonEmail;
    }

    public void setTelefonEmail(String telefonEmail) {
        this.telefonEmail = telefonEmail;
    }

    public String getKrankenkasse() {
        return krankenkasse;
    }

    public void setKrankenkasse(String krankenkasse) {
        this.krankenkasse = krankenkasse;
    }

    @Override //nicht immer Klasse "Objects" vergessen!
    public String toString() {
        return "Patient{" + "ID = " + patientId + ", Nachname: '" + nachname +
                ", Vorname: " + vorname + '\'' + ", Geburtsdatum: '" + geburtsDatum +
                '\'' + "Anschrift: '" + adresse + '\'' + ", Telefon/E-Mail: '" + telefonEmail + '\'' + '}';
    }

}
