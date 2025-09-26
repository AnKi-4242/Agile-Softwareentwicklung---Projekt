# Praxisverwaltung – Projektarbeit

## 📌 Überblick

Dieses Projekt wurde im Rahmen einer Prüfungsleistung entwickelt und stellt eine Java-basierte Anwendung zur Verwaltung von Patienten und Terminen dar.  
Die Anwendung ermöglicht das Anlegen, Bearbeiten, Suchen und Löschen von Patientendaten sowie die Verwaltung von Terminen. Grundlage ist eine SQLite-Datenbank, die lokal eingebunden wird.

## ⚙️ Voraussetzungen

- Java Development Kit (JDK) ab Version 17
- IntelliJ IDEA (oder eine andere Java-IDE)
- SQLite JDBC-Treiber (z. B. sqlite-jdbc-3.50.3.0.jar, bereits im Projekt eingebunden)

## 🚀 Starten der Anwendung

1. Projekt in IntelliJ IDEA öffnen.
2. Sicherstellen, dass die Bibliothek `sqlite-jdbc-3.50.3.0.jar` im Projekt unter „External Libraries“ eingebunden ist.
3. `PraxisView.java` als Einstiegspunkt starten.
4. Die Benutzeroberfläche öffnet sich und erlaubt die Verwaltung von Patienten und Terminen.

## 🗄️ Datenbank

- Die SQLite-Datenbank `praxisdb.db` befindet sich im Ordner `/data` innerhalb des Projektverzeichnisses.
- Sie enthält Beispieldaten für Patienten und Termine, sodass die Anwendung sofort lauffähig ist.
- Neue Daten werden automatisch in dieser Datei gespeichert.

**Hinweis:**  
Sollte die Datenbankdatei fehlen, wird sie beim ersten Start neu erstellt. Die Tabellenstruktur ist im Code definiert.

## 📋 Funktionalitäten

**Patientenverwaltung:**
- Anlegen, Bearbeiten, Löschen von Patientendaten
- Suche nach Nachnamen

**Terminverwaltung:**
- Anlegen, Bearbeiten, Löschen von Terminen
- Suche nach Datum oder Patient-ID
- Übersichtliche GUI in Java Swing

## 🧪 Tests

- **JUnit-Tests:** Screenshots im Anhang des Projektberichts
- **Manuelle Tests:** Testtabelle im Anhang des Projektberichts dokumentiert

## 📚 Struktur des Projekts

- `/src` – Quellcode der Anwendung
- `/data` – SQLite-Datenbankdatei `praxisdb.db`
- `/out` – automatisch erstellte Compiled-Files (wird nicht benötigt)
- `README.md` – Kurzanleitung für Start und Nutzung

## 👤 Hinweis

Dieses Projekt entstand als Prüfungsleistung und ist nicht für die produktive Nutzung gedacht.