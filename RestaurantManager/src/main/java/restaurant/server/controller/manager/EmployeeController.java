<<<<<<< HEAD:RestaurantManager/src/main/java/restaurant/server/controller/manager/EmployeeController.java
package restaurant.server.controller.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import restaurant.server.controller.ConverterImageToFile;
import restaurant.server.controller.MapperController;
import restaurant.server.model.Employee;

public class EmployeeController {

	private ObjectInputStream inStream;
	private Socket clientSocket;

	public EmployeeController(ObjectInputStream inStream, Socket clientSocket) {
		this.inStream = inStream;
		this.clientSocket = clientSocket;
	}

	public void processingRequest(String[] operation) throws IOException {

		switch (operation[2]) {
		case "getAllEmployees":

			getAllEmployees();
			break;
		case "addEmployee":
			addEmployees();
			break;
		case "getAllImages":
			getAllEmployeesImages();
			break;

		default:
			break;
		}
	}

	private void getAllEmployees() {

		try {
			ArrayList<Employee> empl = MapperController.getEmployeeMapper().findALL();
			sendResponse(empl);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getAllEmployeesImages() {
		try {
			ArrayList<BufferedImage> imgs = MapperController.getEmployeeMapper().getAllImages();
			ArrayList<File> imageFiles = (new ConverterImageToFile()).convertToFiles(imgs);
			sendResponse(imageFiles);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private void addEmployees() {
		try {
			Employee employee = (Employee) inStream.readObject();
			File file = (File) inStream.readObject();
			boolean bool = MapperController.getEmployeeMapper().insert(employee, file);
			if (bool == true) {
				sendResponse(new String[] { "true", "Employee successfully inserted !" });
			} else {
				sendResponse(new String[] { "false", "The employee with this information already exists !" });
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void sendResponse(Object object) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
			output.writeObject(object);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
=======
package restaurant.server.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import restaurant.server.model.Employee;

public class ManagerController {

	private ObjectInputStream inStream;
	private Socket clientSocket;

	public ManagerController(ObjectInputStream inStream, Socket clientSocket) {
		this.inStream = inStream;
		this.clientSocket = clientSocket;
	}

	public void processingRequest(String operation) throws IOException {
		switch (operation) {
		case "manager-getAllEmployees":
			getAllEmployees();
			break;
		case "manager-addEmployee":
			addEmployees();
			break;
		case "manager-getAllEmployeesImages":
			getAllEmployeesImages();
			break;
		default:
			break;
		}
	}

	private void getAllEmployees() {

		try {
			ArrayList<Employee> empl = MapperController.getEmployeeMapper().findALL();
			sendResponse(empl);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void getAllEmployeesImages(){
		try{
			ArrayList<BufferedImage> imgs=MapperController.getEmployeeMapper().getAllImages();
			ArrayList<File> imageFiles=convertToFiles(imgs);
			sendResponse(imageFiles);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private ArrayList<File> convertToFiles(ArrayList<BufferedImage> imgs){
		ArrayList<File> imageFiles=new ArrayList<>();
		for(int i=0; i <= imgs.size(); i ++){
			try{
				File outputFile = new File("/image"+i+".jpg");
				ImageIO.write(imgs.get(i), "jpg", outputFile);
				outputFile.deleteOnExit();
			imageFiles.add(outputFile);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return imageFiles;
	}

	private void addEmployees() {
		try {
			Employee employee = (Employee) inStream.readObject();
			File file = (File) inStream.readObject();
			boolean bool = MapperController.getEmployeeMapper().insert(employee, file);
			if (bool == true) {
				sendResponse(new String[] { "true", "Employee successfully inserted !" });
			} else {
				sendResponse(new String[] { "false", "The employee with this information already exists !" });
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void sendResponse(Object object) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
			output.writeObject(object);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
>>>>>>> origin/master:RestaurantManager/src/main/java/restaurant/server/controller/ManagerController.java
