/*
 * To convert all selected images to grayscale, make a new copy and save it to disk 
 */

import edu.duke.*;
import java.io.*;

public class BatchGray {
	
	public void makeGrayCopies(){
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			ImageResource image = new ImageResource(f);
			ImageResource grayImage = makeGray(image);
			String iName = image.getFileName();
			String newName = "gray-" + iName;
			grayImage.setFileName(f.getParentFile() + "/" +newName);
			grayImage.draw();
			grayImage.save();
		}
	}
	private ImageResource makeGray(ImageResource image){
		ImageResource im = new ImageResource(image.getWidth(), image.getHeight());
		for(Pixel p : im.pixels()){
			int X = p.getX();
			int Y = p.getY();
			Pixel origPix = image.getPixel(X, Y);
			int avg = (origPix.getRed()+origPix.getGreen()+origPix.getBlue())/3;
			p.setRed(avg);
			p.setGreen(avg);
			p.setBlue(avg);
		}
		return im;
	}
	public static void main(String[] args) {
		BatchGray bg = new BatchGray();
		bg.makeGrayCopies();
	}
}