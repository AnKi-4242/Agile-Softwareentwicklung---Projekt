import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatenbankPraxis {
    public static Connection connection() throws SQLException {
        String url = "jdbc:sqlite:data/dbpraxis.db";
        return DriverManager.getConnection(url);
    }
}
