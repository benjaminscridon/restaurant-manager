package restaurant.client.waiter.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ConverterFileToImage {

	public ArrayList<javafx.scene.image.Image> convertFilesToImages(ArrayList<File> fileImages){
		ArrayList<javafx.scene.image.Image> images=new ArrayList<>();
		for(int i=0; i < fileImages.size(); i ++){
			try{
				BufferedImage image=ImageIO.read(fileImages.get(i));
				Image imagefx=SwingFXUtils.toFXImage(image,null);
				images.add(imagefx);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return images;
	}
}
