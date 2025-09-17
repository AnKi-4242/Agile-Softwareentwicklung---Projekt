
# Bewerbungsprojekt: Patienten- und Terminverwaltung


Dieses Projekt ist eine kleine, java-basierte Anwendung zum Verwalten von Patienten und Terminen in einer fiktiven Arztpraxis. Es entstand als Übungsprojekt im Rahmen einer Weiterbildung im Bereich Softwareentwicklung, wurde von mir **selbständig konzipiert und entwickelt** und sollte die Inhalte der Weiterbildung vertiefen. Insbesondere wollte ich die Grundlagen von **Java**, **objektorientierter Programmierung** und **Datenbankanbindung (SQLite über JDBC)** in der Praxis sehen und üben.


## Funktionen:

-**Patientenverwaltung:**
- Anlegen neuer, Bearbeiten und Löschen bestehender Patienteneinträge
- Suche von Einträgen nach ID oder Nachname
- Anzeigen aller Patienten

-**Terminverwaltung**
- Anlegen neuer, Bearbeiten und Löschen bestehender Termine
- Suche nach Terminen anhand ID oder Datum
- Verknüpfung von Terminen und Patienten
- Anzeigen aller Termine

> Hinweis: Das Projekt entspricht als Lernprojekt meinem aktuellen Wissensstand in Java, es ist nicht als vollständige Software zu verstehen.

---

## Technologien:

- Programmiersprache: **Java**
- Datenbank: **SQLite** (eingebettet)
- Einbindung: **JDBC**
- IDE: **IntelliJ**

---

## Starten der Anwendung:

1. Projekt in Java-IDE öffnen (z.B. IntelliJ Idea)
2. Sicherstellen, dass SQLite-JDBC-Bibliothek eingebunden ist; Download: https://github.com/xerial/sqlite-jdbc
3. Die Verbindung zur SQLite-Datenbank befindet sich in der Datei "DatenbankPraxis.java"
4. Anwendung kann über main-Methode geöffnet werden, z.B. "Main.java"

---

## Datenbank und Schema:

Die Anwendung arbeitet mit einer SQLite-Datenbank mit den zwei Haupttabellen "patient" und "termin":

### Tabelle: patient

| Spalte       | Typ     | Beschreibung             |
|-------------|--------|-------------------------|
| patient_id  | INT    | Eindeutige ID (Primary Key) |
| nachname    | TEXT   | Nachname des Patienten  |
| vorname     | TEXT   | Vorname des Patienten   |
| geburtsdatum| TEXT   | Geburtsdatum (YYYY-MM-DD) |
| adresse     | TEXT   | Anschrift               |
| kontakt     | TEXT   | Telefon oder E-Mail     |
| kv          | TEXT   | Krankenkasse            |


### Tabelle: termin

| Spalte       | Typ     | Beschreibung             |
|-------------|--------|-------------------------|
| termin_id   | INT    | Eindeutige ID (Primary Key) |
| patient_id  | INT    | Verweis auf patient.patient_id |
| datum_zeit  | TEXT   | Datum und Uhrzeit       |
| grund       | TEXT   | Grund des Termins       |


###Dummy-Daten:
- Patient 1: Emma Musterfrau, 02.02.1970, Musterstraße 1, 20200 Musterhausen, emma@mustermail.de, Musterkasse
- Patient 2: Erich Mustermann, 03.03.1970, Musterstraße 10, 30300 Musterdorf, erich@mustermail.com, Musterkasse
- Termin: 01.10.2025, 10:30 Uhr, Musterung

---

## Projektstruktur:

Das Projekt liegt in einem flachen Aufbau mit den Klassen direkt im src-Ordner. Es ist wie folgt aufgebaut:

projektordner/
│
├─ src/
│ ├─ Patient.java ← Modellklasse für Patienten
│ ├─ Termin.java ← Modellklasse für Termine
│ ├─ Nutzer.java ← Modellklasse für Nutzer
│ ├─ PatientDB.java ← Datenbankzugriff für Patienten
│ ├─ TerminDB.java ← Datenbankzugriff für Termine
│ ├─ PatientVerwaltung.java ← Serviceklasse für Patienten-Operationen
│ ├─ TerminVerwaltung.java ← Serviceklasse für Termin-Operationen
│ └─ Main.java ← Einstiegspunkt / Startklasse
│

├─ .gitignore
└─ .idea/libraries/ ← externe Bibliotheken (z. B. sqlite-jdbc.jar)

- Damit die Struktur auch ohne Unterordner übersichtlich bleibt, sind die Klassen nach ihrer jeweiligen Funktion benannt
- Die externen Bibliotheken liegen im Unterordner "libraries"
- Die Modellklasse "Nutzer" wird aktuell noch nicht genutzt, sie soll später für die geplante Authentifizierungsmöglichkeit verwendet werden 

---

## Autorin

Anna Kickartz
Quereinsteigerin in der Softwareentwicklung, Projekt im Rahmen einer Weiterbildung eigenständig konzipiert und umgesetzt  