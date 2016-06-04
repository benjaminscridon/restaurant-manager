package restaurant.server.datamapper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import com.mysql.jdbc.PreparedStatement;

import restaurant.server.model.Employee;

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

	public synchronized ArrayList<BufferedImage> getAllImages() throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		ArrayList<BufferedImage> images = new ArrayList<>();
		try {
			String selectSQL = "SELECT image From restaurant.employee";
			myStmt = (PreparedStatement) DBConnection.getConnection().prepareStatement(selectSQL);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				byte[] bytes = null;
				bytes = myRs.getBytes(1);
				if (bytes != null) {
					InputStream is = new ByteArrayInputStream(bytes);
					BufferedImage imag = ImageIO.read(is);
					BufferedImage img = imag;
					images.add(img);
				}
			}
			return images;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	public boolean insert(Employee e, File filename) {
		try {
			String statement = "INSERT INTO employee(password, job_title, name, birthday, address, email, mobile, hire_date, fire_date,image) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);
			dbStatement.setString(1, e.getPassword());
			dbStatement.setString(2, e.getJob_title());
			dbStatement.setString(3, e.getName());
			dbStatement.setDate(4, (Date) e.getBirthdate());
			dbStatement.setString(5, e.getAddress());
			dbStatement.setString(6, e.getEmail());
			dbStatement.setString(7, e.getMobile());
			dbStatement.setDate(8, (Date) e.getHire_date());
			dbStatement.setDate(9, (Date) e.getFire_date());
			FileInputStream fin = new FileInputStream(filename);
			dbStatement.setBinaryStream(10, (InputStream) fin, (int) filename.length());

			dbStatement.executeUpdate();
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

	}

	public Employee getNewestEmployee() {
		Employee o = new Employee();
		try {
			String statement = "SELECT max(employee_no) FROM employee;";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {

				int employee_no = rs.getInt("employee_no");
				o.setEmployee_no(employee_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public Employee find(int employee_id) {
		try {
			Employee e;
			String statement = "SELECT * FROM employee where employee_no=?";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);
			dbStatement.setInt(1, employee_id);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int employee_no = rs.getInt("employee_no");
				String password = rs.getString("password");
				String job_title = rs.getString("job_title");
				String name = rs.getString("name");
				Date birthdate = rs.getDate("birthday");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				Date hire_date = rs.getDate("hire_date");
				Date fire_date = rs.getDate("fire_date");
				e = new Employee(employee_no, password, job_title, name, birthdate, address, email, mobile, hire_date,
						fire_date);
				return e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Employee();
	}

	public boolean findEmployeeByEmail(String email) {
		boolean bool = false;
		try {
			String statement = "SELECT * FROM employee where email=?";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);
			dbStatement.setString(1, email);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int employee_no = rs.getInt("employee_no");
				if (employee_no != 0) {
					bool = true;
				}
			}
			return bool;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	public boolean findEmployeeById(int employee_no) {
		boolean bool = false;
		try {
			String statement = "SELECT * FROM employee where employee_no=?";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);
			dbStatement.setInt(1, employee_no);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int employee_id = rs.getInt("employee_no");
				if (employee_id != 0) {
					bool = true;
				}
			}
			return bool;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	public boolean findEmployeeByIdPassStatus(int employee_no, String pass, String job_title) {
		boolean bool = false;
		try {
			String statement = "SELECT * FROM employee where employee_no=? and password=? and job_title=?";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);
			dbStatement.setInt(1, employee_no);
			dbStatement.setString(2, pass);
			dbStatement.setString(3, job_title);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int employee_id = rs.getInt("employee_no");
				if (employee_id != 0) {
					bool = true;
				}
			}
			return bool;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	public ArrayList<Employee> findALL() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			Employee e;
			String statement = "SELECT * FROM employee";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int employee_no = rs.getInt("employee_no");
				String password = rs.getString("password");
				String job_title = rs.getString("job_title");
				String name = rs.getString("name");
				Date birthdate = rs.getDate("birthday");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				Date hire_date = rs.getDate("hire_date");
				Date fire_date = rs.getDate("fire_date");
				e = new Employee(employee_no, password, job_title, name, birthdate, address, email, mobile, hire_date,
						fire_date);
				employees.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	public boolean update(Employee e, File filename) {
		try {
			String statement = "UPDATE employee SET password=?, job_title=?, name=?, birthdate=?, address=?, email=?, mobile=?, hire_date=?, fire_date=?, image=? where employee_no=?";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);

			dbStatement.setString(1, e.getPassword());
			dbStatement.setString(2, e.getJob_title());
			dbStatement.setString(3, e.getName());
			dbStatement.setDate(4, (Date) e.getBirthdate());
			dbStatement.setString(5, e.getAddress());
			dbStatement.setString(6, e.getEmail());
			dbStatement.setString(7, e.getMobile());
			dbStatement.setDate(8, (Date) e.getHire_date());
			dbStatement.setDate(9, (Date) e.getFire_date());
			FileInputStream fin = new FileInputStream(filename);
			dbStatement.setBinaryStream(10, (InputStream) fin, (int) filename.length());
			dbStatement.setInt(11, e.getEmployee_no());
			dbStatement.executeUpdate();
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

	}

	public void delete(Employee e) {
		try {
			String statement = "DELETE FROM employee where employee_no=?";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement(statement);
			dbStatement.setInt(1, e.getEmployee_no());
			dbStatement.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
