/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.editor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Shehyaaz, Shakshi, Shivangi
 */

/**
 * This class allows for the opening of image files by providing
 * a file chooser and open and save dialogs.
 */
public class ImageFileManager {

	private final FileChooser fileChooser;
	private final ObservableList<String> extensionStrings;

	public ImageFileManager(){
		this.extensionStrings = FXCollections.observableArrayList(
                                ".jpg",                               
			        ".png",
			        ".gif"
			    );
		fileChooser = new FileChooser();
		initExtensionFilters();
	} // end of constructor

	/**
	 * Opens a file chooser open dialog. Returns the
	 * selected file or null if no file was selected.
	 * @param stage The parent stage of the file chooser.
	 * @return The chosen file. Null if no file was selected.
	 */
	public File chooseFile(Stage stage){
		return fileChooser.showOpenDialog(stage);
	}//end of method

	/**
	 * Opens a file chooser save dialog. Returns the selected
	 * file or null if no file was selected.
	 * @param stage The parent stage of the file chooser.
	 * @return The chosen file. Null if no file was selected.
	 */
	public File saveFile(Stage stage){
		return fileChooser.showSaveDialog(stage);
	}//end of method

	/**
	 * Loads an image from a given file and returns the image.
	 * @param imageFile The file of the image to be loaded.
	 * @return An image opened from the given file.
	 * @throws Exception
	 */
	public Image loadImage(File imageFile) throws Exception{
		if(imageFile != null){
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			return image;
		}// end of if
		else
                    return null;
	}// end of method

	/**
	 * Opens a dialog to save an image.It allows the user to choose
           a location for the image and a file extension for the file. 
	 * @param stage The parent stage of the save image stage.
	 * @param image The image to be saved.
         * */
	public void saveImageDialog(final Stage stage, final WritableImage image){
                File chosenFile = fileChooser.showSaveDialog(stage);
                if(chosenFile==null) return;
                
                BufferedImage bufImageARGB = SwingFXUtils.fromFXImage(image, null);
                BufferedImage bufImageRGB = new BufferedImage(bufImageARGB.getWidth(), bufImageARGB.getHeight(), BufferedImage.OPAQUE);
                Graphics2D graphics = bufImageRGB.createGraphics();
                graphics.drawImage(bufImageARGB, 0, 0, null);
                try {
                    ImageIO.write(bufImageRGB,"jpg", chosenFile);
                    //System.out.println( "Image saved to " + file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                graphics.dispose();
	}//end of method
        
	/**
	 * Initializes the extensions filters used by the file chooser
	 * and adds the filters to the file chooser.
	 */
	private void initExtensionFilters(){                
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		FileChooser.ExtensionFilter extFilterGIF = new FileChooser.ExtensionFilter("gif files (*.gif)", "*.gif");
		fileChooser.getExtensionFilters().addAll(extFilterJPG,extFilterPNG, extFilterGIF);
	}//end of method
}//end of class
