import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class PraxisView extends JFrame {
    private PatientDB patDb = new PatientDB();
    private TerminDB terminDb = new TerminDB();
    private PatientVerwaltung patV = new PatientVerwaltung(patDb);
    private TerminVerwaltung teV = new TerminVerwaltung(terminDb);

    private JTable patientTable;
    private JTable terminTable;

    private DefaultTableModel patientTableModel;
    private DefaultTableModel terminTableModel;

    //Konstruktor
    public PraxisView() {
        setTitle("Patienten- und Terminverwaltung");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabPane = new JTabbedPane();

        //Panel für Patienten:
        JPanel patientPanel = createPatientPanel();
        //Panel für Termin:
        JPanel terminPanel = createTerminPanel();

        //Tabs hinzufügen:
        tabPane.addTab("Patienten", patientPanel);
        tabPane.addTab("Termine", terminPanel);

        add(tabPane);
        refreshPatientTable();
        refreshTerminTable();
    }

    //Patienten-Bereich:
    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        //Button Patientensuche:
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField tfSuche = new JTextField(20);
        JButton btnSuche = new JButton("Suche");

        searchPanel.add(new JLabel("Suche"));
        searchPanel.add(tfSuche);
        searchPanel.add(btnSuche);

        panel.add(searchPanel, BorderLayout.NORTH);

        btnSuche.addActionListener(e -> {
            String keyword = tfSuche.getText().trim();
            patientTableModel.setRowCount(0);
            if (!keyword.isEmpty()) {
                Patient p = patV.suchePatientNachNamen(keyword);
                if (p != null) {
                    Object[] row = {
                            p.getPatientId(),
                            p.getNachname(),
                            p.getVorname(),
                            p.getGeburtsDatum(),
                            p.getAdresse(),
                            p.getTelefonEmail(),
                            p.getKrankenkasse()
                    };
                    patientTableModel.addRow(row);
                } else {
                    JOptionPane.showMessageDialog(this, "Kein Patient gefunden");
                }
            } else {
                refreshPatientTable();
            }
        }
        );


        //Tabelle für Patienten:
        patientTableModel = new DefaultTableModel(new String[]{"ID", "Nachname", "Vorname", "Geburtsdatum",
        "Adresse", "Telefon/Email", "Krankenkasse"}, 0);
        JTable patientTable = new JTable(patientTableModel);
        JScrollPane patientScrollPane = new JScrollPane(patientTable);

        //Buttons für Patienten:
        JPanel patientButtonPanel = new JPanel();
        JButton btnAddPatient = new JButton("Neuen Patienten erfassen");
        JButton btnEditPatient = new JButton("Eintrag bearbeiten");
        JButton btnDeletePatient = new JButton("Eintrag löschen");
        JButton btnRefreshPatient = new JButton("Aktualisieren");

        patientButtonPanel.add(btnAddPatient);
        patientButtonPanel.add(btnEditPatient);
        patientButtonPanel.add(btnDeletePatient);
        patientButtonPanel.add(btnRefreshPatient);

        //Action-Listener für Patienten:
        btnAddPatient.addActionListener(e -> zeigePatientDialog(null));

        btnEditPatient.addActionListener(e ->  {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) patientTableModel.getValueAt(selectedRow, 0);
                Patient p = patV.suchePatientNachId(id);
                zeigePatientDialog(p);
            } else {
                JOptionPane.showMessageDialog(this, "Bitte einen Patienten auswählen");
            }
        });

        btnDeletePatient.addActionListener(e -> {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) patientTableModel.getValueAt(selectedRow, 0);
                if (patV.loeschePatient(id)) {
                    refreshPatientTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bitte einen Patienten auswählen");
            }
        });

        btnRefreshPatient.addActionListener(e -> refreshPatientTable());

        panel.add(new JScrollPane(patientTable), BorderLayout.CENTER);
        panel.add(patientButtonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void zeigePatientDialog(Patient patient) {
        JTextField tfNachname = new JTextField(20);
        JTextField tfVorname = new JTextField(20);
        JTextField tfGeburtsdatum = new JTextField(20);
        JTextField tfAdresse = new JTextField(50);
        JTextField tfTelefonEmail = new JTextField(30);
        JTextField tfKrankenkasse = new JTextField(30);

        if (patient != null) {
            tfNachname.setText(patient.getNachname());
            tfVorname.setText(patient.getVorname());
            tfGeburtsdatum.setText(patient.getGeburtsDatum());
            tfAdresse.setText(patient.getAdresse());
            tfTelefonEmail.setText(patient.getTelefonEmail());
            tfKrankenkasse.setText(patient.getKrankenkasse());
        }

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nachname:"));
        panel.add(tfNachname);
        panel.add(new JLabel("Vorname:"));
        panel.add(tfVorname);
        panel.add(new JLabel("Geburtsdatum:"));
        panel.add(tfGeburtsdatum);
        panel.add(new JLabel("Adresse:"));
        panel.add(tfAdresse);
        panel.add(new JLabel("Telefon/Email:"));
        panel.add(tfTelefonEmail);
        panel.add(new JLabel("Versichert:"));
        panel.add(tfKrankenkasse);

        int result = JOptionPane.showConfirmDialog(this, panel,
                (patient == null ? "Neuen Patienten hinzufügen" : "Patient bearbeiten"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                if (patient == null) {
                    patV.erstellePatient(
                            tfNachname.getText(),
                            tfVorname.getText(),
                            tfGeburtsdatum.getText(),
                            tfAdresse.getText(),
                            tfTelefonEmail.getText(),
                            tfKrankenkasse.getText()
                    );
                } else {
                    Patient geaenderterPatient = new Patient(
                            patient.getPatientId(),
                            tfNachname.getText(),
                            tfVorname.getText(),
                            tfGeburtsdatum.getText(),
                            tfAdresse.getText(),
                            tfTelefonEmail.getText(),
                            tfKrankenkasse.getText()
                    );
                    patV.bearbeitePatient(geaenderterPatient);

                } refreshPatientTable();
            }
    }

    private void refreshPatientTable() {
        patientTableModel.setRowCount(0);
        List<Patient> alle = patV.holePatientenListe();

        for (Patient p : alle) {
            Object[] row = {
                    p.getPatientId(),
                    p.getNachname(),
                    p.getVorname(),
                    p.getGeburtsDatum(),
                    p.getAdresse(),
                    p.getTelefonEmail(),
                    p.getKrankenkasse()
            };
            patientTableModel.addRow(row);
        }
    }

    //Termin-Bereich:
    private JPanel createTerminPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        //Button Terminsuche:
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField tfSuche = new JTextField(20);
        JButton btnSuche = new JButton("Suche");

        searchPanel.add(new JLabel("Suche"));
        searchPanel.add(tfSuche);
        searchPanel.add(btnSuche);

        panel.add(searchPanel, BorderLayout.NORTH);

        btnSuche.addActionListener(e -> {
          String keyword = tfSuche.getText().trim();
          terminTableModel.setRowCount(0);
          if (!keyword.isEmpty()) {
              Termin t = teV.sucheTerminNachDatum(keyword);
              if (t != null) {
                  Object[] row = {
                          t.getTerminId(),
                          t.getPatientId(),
                          t.getDatumZeit(),
                          t.getGrund()
                  };
                  terminTableModel.addRow(row);
              } else {
                  JOptionPane.showMessageDialog(this, "Kein Termin zu diesem Datum gefunden");
              }
          } else {
              refreshTerminTable();
          }
        }
        );


        String[] spalten = {"Termin-ID", "Patient-ID", "Datum/Uhrzeit", "Grund"};
        terminTableModel = new DefaultTableModel(spalten, 0);
        terminTable = new JTable(terminTableModel);

        //Buttons:
        JPanel terminButtonPanel = new JPanel();
        JButton btnAddTermin = new JButton("Neuen Termin anlegen");
        JButton btnEditTermin = new JButton("Termin bearbeiten");
        JButton btnDeleteTermin = new JButton("Termin löschen");
        JButton btnRefreshTermin = new JButton("Aktualisieren");

        terminButtonPanel.add(btnAddTermin);
        terminButtonPanel.add(btnEditTermin);
        terminButtonPanel.add(btnDeleteTermin);
        terminButtonPanel.add(btnRefreshTermin);

        //Action-Listener:
        btnAddTermin.addActionListener(e -> zeigeTerminDialog(null));

        btnEditTermin.addActionListener(e -> {
            int selectedRow = terminTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) terminTableModel.getValueAt(selectedRow, 0);
                Termin t = teV.sucheTerminNachId(id);
                zeigeTerminDialog(t);
            } else {
                JOptionPane.showMessageDialog(this, "Bitte einen Termin auswählen");
            }
        });

        btnDeleteTermin.addActionListener(e -> {
            int selectedRow = terminTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) terminTableModel.getValueAt(selectedRow, 0);
                teV.loescheTermin(id);
                refreshTerminTable();
            } else {
                JOptionPane.showMessageDialog(this, "Bitte einen Termin auswählen");
            }
        });

        btnRefreshTermin.addActionListener(e -> refreshTerminTable());

        panel.add(new JScrollPane(terminTable), BorderLayout.CENTER);
        panel.add(terminButtonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void zeigeTerminDialog(Termin termin) {
        String[] patientOptions = new String[patV.zeigeAllePatienten().size()];
        int[] patientIds = new int[patientOptions.length];

        for (int i = 0; i < patientOptions.length; i++) {
            Patient p = patV.zeigeAllePatienten().get(i);
            patientOptions[i] = p.getNachname() + ", " + p.getVorname() + " (ID: " + p.getPatientId() + ")";
            patientIds[i] = p.getPatientId();
        }

        int selectedPatientId = -1;
        if (patientOptions.length > 0) {
            String selected = (String) JOptionPane.showInputDialog(
                    this,
                    "Wählen Sie einen Patienten",
                    "Patient auswählen",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    patientOptions,
                    patientOptions[0]
            );

            if (selected != null) {
                int idStart = selected.indexOf("ID: ") + 4;
                int idEnd = selected.indexOf(")", idStart);
                selectedPatientId = Integer.parseInt(selected.substring(idStart, idEnd));
            }
        }

        JTextField tfDatumZeit = new JTextField(20);
        JTextField tfGrund = new JTextField(20);

        if (termin != null) {
            tfDatumZeit.setText(termin.getDatumZeit());
            tfGrund.setText(termin.getGrund());
            selectedPatientId = termin.getPatientId();
        }

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Patient-ID:"));
        panel.add(new JLabel(selectedPatientId > 0 ? String.valueOf(selectedPatientId) : "Noch nicht ausgewählt"));
        panel.add(new JLabel("Datum/Uhrzeit: "));
        panel.add(tfDatumZeit);
        panel.add(new JLabel("Grund: "));
        panel.add(tfGrund);

        int result = JOptionPane.showConfirmDialog(this, panel,
                (termin == null ? "Neuen Termin hinzufügen" : "Termin bearbeiten"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (selectedPatientId > 0) {
                if (termin == null) {
                    teV.erstelleTermin(selectedPatientId, tfDatumZeit.getText(), tfGrund.getText());
                } else {
                    Termin geaenderterTermin = new Termin(
                            termin.getTerminId(),
                            selectedPatientId,
                            tfDatumZeit.getText(),
                            tfGrund.getText()
                    );
                    teV.bearbeiteTermin(geaenderterTermin);
                }
                refreshTerminTable();
            } else {
                JOptionPane.showMessageDialog(this, "Kein Patient ausgewählt");
            }
        }

    }

    private void refreshTerminTable() {
        terminTableModel.setRowCount(0);
        List<Termin> alleTermine = teV.zeigeTerminListeAusDb();
        for (Termin t : alleTermine) {
            Object[] row = {
                    t.getTerminId(),
                    t.getPatientId(),
                    t.getDatumZeit(),
                    t.getGrund()
            };
            terminTableModel.addRow(row);
        }
    }
}
