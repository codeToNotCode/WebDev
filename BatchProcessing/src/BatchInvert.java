/*
 * To convert all selected images to their negatives, make a new copy and save it to disk 
 */

import edu.duke.*;
import java.io.*;

public class BatchInvert {
	
	public void makeInvertCopies(){
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			ImageResource image = new ImageResource(f);
			ImageResource negImage = makeNegative(image);
			String iName = image.getFileName();
			String newName = "inverted-" + iName;
			negImage.setFileName(f.getParentFile() + "/"+ newName);
			negImage.draw();
			negImage.save();
		}
	}
	private ImageResource makeNegative(ImageResource image){
		ImageResource im = new ImageResource(image.getWidth(), image.getHeight());
		for(Pixel p : im.pixels()){
			int X = p.getX();
			int Y = p.getY();
			Pixel origPix = image.getPixel(X, Y);
			p.setRed(255 - origPix.getRed());
			p.setGreen(255 - origPix.getGreen());
			p.setBlue(255 - origPix.getBlue());
		}
		return im;
	}
	public static void main(String[] args) {
		BatchInvert bi = new BatchInvert();
		bi.makeInvertCopies();
	}
}