import java.sql.SQLOutput;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PraxisView praxis = new PraxisView();
            praxis.setVisible(true);
        }
        );
    }
}