package restaurant.datamapper;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnection {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	private java.sql.Statement instruction;
	private java.sql.Connection con;

	private DBConnection() {
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
			instruction = ((java.sql.Connection) con).createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			((Statement) con).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuerry(String querry) {
		ResultSet ret = null;
		try {
			ret = instruction.executeQuery(querry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public boolean execute(String querry) {
		boolean done = false;
		try {
			done = instruction.execute(querry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}

	public java.sql.PreparedStatement prepareStatement(String query) throws Exception {
		return con.prepareStatement(query);

	}

	public static DBConnection getConnection() {
		DBConnection con = new DBConnection();
		return con;
	}

}

