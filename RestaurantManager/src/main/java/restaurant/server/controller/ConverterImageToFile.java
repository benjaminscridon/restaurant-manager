package restaurant.server.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ConverterImageToFile {
	public ArrayList<File> convertToFiles(ArrayList<BufferedImage> imgs) {
		ArrayList<File> imageFiles = new ArrayList<>();
		for (int i = 0; i < imgs.size(); i++) {
			try {
				File outputFile = new File("src/main/resources/employees/image" + i + ".jpg");
				ImageIO.write(imgs.get(i), "jpg", outputFile);
				outputFile.deleteOnExit();
				imageFiles.add(outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imageFiles;
	}

}
