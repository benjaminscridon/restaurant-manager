package restaurant.datamapper;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import com.mysql.jdbc.PreparedStatement;


/**
 * 
 * @author Beniamin
 *
 */
public class EmployeeMapper {

	public synchronized boolean uploadPicture(int id, File imgfile) {

		PreparedStatement myStmt = null;
		try {

			myStmt = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement("UPDATE usersdetails set" + " picture=? " + " where idUser=?");

			FileInputStream fin = new FileInputStream(imgfile);
			myStmt.setBinaryStream(1, (InputStream) fin, (int) imgfile.length());
			myStmt.setInt(2, id);
			myStmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public synchronized Image getImage(int id) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			String selectSQL = "SELECT picture From usersdetails where idUser=?;";
			myStmt = (PreparedStatement) DBConnection.getConnection().prepareStatement(selectSQL);
			myStmt.setInt(1, id);
			myRs = myStmt.executeQuery();

			byte[] bytes = null;
			if (myRs.next()) {
				bytes = myRs.getBytes(1);
			}
			if (bytes != null) {
				InputStream is = new ByteArrayInputStream(bytes);
				BufferedImage imag = ImageIO.read(is);
				Image image = imag;
				return image;
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}
	
	
	
}
