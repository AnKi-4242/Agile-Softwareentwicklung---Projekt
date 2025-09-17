public class Nutzer {
    //Attribute
    private String nutzername;
    private String passwortHash;
    private String rolle;

    //Konstruktor
    public Nutzer(String nutzerName, String passwortHash, String rolle) {
        this.nutzername = nutzerName;
        this.passwortHash = passwortHash;
        this.rolle = rolle;
    }

    //Getter und Setter
    public String getNutzername() {
        return nutzername;
    }

    public void setNutzername(String nutzername) {
        this.nutzername = nutzername;
    }

    public String getPasswortHash() {
        return passwortHash;
    }

    public void setPasswortHash(String passwortHash) {
        this.passwortHash = passwortHash;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}
