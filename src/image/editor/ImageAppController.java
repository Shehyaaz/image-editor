package image.editor;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ImageAppController implements Initializable{
    private Stage mainStage ;
    private ImageFileManager fileManager;
    private IEImagePane imagePane;
    private CropSelection cropSelection ; //for cropping the image
    private FreeCropSelection freeCropSelection; //for free cropping the image
    double angle;
    Image image;

    @FXML
    private AnchorPane root;

    @FXML
    private MenuBar mb;

    @FXML
    private Menu file;

    @FXML
    private MenuItem openimage;

    @FXML
    private MenuItem saveimage;

    @FXML
    private MenuItem exit;

    @FXML
    private Menu edit;

    @FXML
    private MenuItem crop;
    
    @FXML
    private MenuItem freecrop;

    @FXML
    private MenuItem grayscale;

    @FXML
    private MenuItem blur;

    @FXML
    private MenuItem rotateimgright;

    @FXML
    private MenuItem rotateimgleft;

    @FXML
    private MenuItem textimg;

    @FXML
    private MenuItem doodle;

    @FXML
    private MenuItem reset;

    @FXML
    private Menu help;

    @FXML
    private MenuItem about;

    @FXML
    private Button openimgbtn;

    @FXML
    private Button saveimgbtn;

    @FXML
    private Button resetbtn;

    @FXML
    private Button cropbtn;
    
    @FXML
    private Button freecropbtn;

    @FXML
    private Button textimgbtn;

    @FXML
    private Button doodlebtn;

    @FXML
    private Button rotatebtn1;

    @FXML
    private Button rotatebtn2;

    @FXML
    private Button aboutbtn;

    @FXML
    private BorderPane border;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mainStage = Main.primaryStage;
        fileManager = new ImageFileManager();
        imagePane = new IEImagePane();
        border.setCenter(imagePane);
        angle = 0.0;
        image = null;
    }   //end of method

    @FXML
    void aboutUs(ActionEvent event) {

    }

    @FXML
    void blurImage(ActionEvent event) {
        if(!imagePane.hasCurrentImage())
            showErrorDialog("Select an image to crop.", mainStage);
        else
            imagePane.iv.setEffect(new GaussianBlur());
    }

    @FXML
    void cropImage(ActionEvent event) {
        if(!imagePane.hasCurrentImage())
            showErrorDialog("Select an image to crop.", mainStage);
        else{         
            cropSelection = new CropSelection(imagePane);            
        }//end of else    
    }

    @FXML
    void doodleImage(ActionEvent event) {

    }

    @FXML
    void exitApp(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Exit Pixy");
        a.setHeaderText("Are you sure you want to exit ?");
        Optional<ButtonType> op = a.showAndWait();
        
        if(op.get() == ButtonType.OK)
            Platform.exit();
    }
    
    @FXML
    void freeCropImage(ActionEvent event) {
        if(!imagePane.hasCurrentImage())
            showErrorDialog("Select an image to crop.", mainStage);
        else{
            freeCropSelection = new FreeCropSelection(imagePane);
        }
    }

    @FXML
    void openImage(ActionEvent event) {
        if(imagePane.hasCurrentImage()){
            openimage.disableProperty();
            openimgbtn.disableProperty();
        }
        else{
            try{
		final File imageFile = fileManager.chooseFile(mainStage);
                if(imageFile==null)
                    return;
		image = fileManager.loadImage(imageFile);
		imagePane.addTab(image, imageFile.getName());
            }//end of try
            catch(Exception exception){
		showErrorDialog("There was an error opening the selected image.", mainStage);
            }
        }
    }

    @FXML
    void resetImage(ActionEvent event) {
        if(!imagePane.hasCurrentImage())
            showErrorDialog("No image is selected.", mainStage);
        else{
            imagePane.stack.getChildren().clear();
            imagePane.iv.setFitHeight(image.getHeight());
            imagePane.iv.setFitWidth(image.getWidth());
            imagePane.canvas.setHeight(image.getHeight());
            imagePane.canvas.setWidth(image.getWidth());
            imagePane.iv.setImage(image);
            imagePane.iv.setRotate(0);
            imagePane.iv.setEffect(null);
            imagePane.stack.getChildren().addAll(imagePane.canvas,imagePane.iv);
        }
    }

    @FXML
    void rotateImageLeft(ActionEvent event) {
        if(!imagePane.hasCurrentImage())
            showErrorDialog("Select an image to rotate.", mainStage);
        else{
        angle = imagePane.iv.getRotate();
        imagePane.iv.setRotate(angle-90.0);
        }
    }

    @FXML
    void rotateImageRight(ActionEvent event) {
        if(!imagePane.hasCurrentImage())
            showErrorDialog("Select an image to rotate.", mainStage);
        else{
        angle = imagePane.iv.getRotate();
        imagePane.iv.setRotate(angle+90.0);
        }
    }

    @FXML
    void saveImage(ActionEvent event) {
        if(imagePane.hasCurrentImage())
            fileManager.saveImageDialog(mainStage, imagePane.getCurrentImage());
	else
            showErrorDialog("There is no image to save.", mainStage);
    }

    @FXML
    void textImage(ActionEvent event) {

    }

    @FXML
    void toGrayScale(ActionEvent event) {

    }
    
    void showErrorDialog(String error, Stage stage){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(null);
        a.setContentText(error);
        a.showAndWait();
    }//end of method   
}//end of class
