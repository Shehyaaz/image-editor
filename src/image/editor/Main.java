/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.editor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.util.Duration;

/**
 *
 * @author Shehyaaz, Shakshi, Shivangi
 */
public class Main extends Application {
    public static Stage primaryStage;
    public static Scene scene;
    @Override
    public void start(Stage mainStage) {
        primaryStage = mainStage;
        primaryStage.getIcons().add(new Image("file:resources/icon.png"));
        primaryStage.setTitle("Pixy Image Editor");
       // primaryStage.setScene(loadSplash()); // loading Splash Screen
       loadSplash(primaryStage);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ImageApp.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
      
    }//end of method
    
    private void loadSplash(Stage primaryStage){
        Stage splash=new Stage();
        Scene splashscene;
        try{
            AnchorPane root=new AnchorPane();
            StackPane pane = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
            root.getChildren().setAll(pane);
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3),pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3),pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            
            fadeIn.play(); //play first transition
            
            fadeIn.setOnFinished((e)->{
                fadeOut.play(); //play second transition
            });
            
            fadeOut.setOnFinished((e)->{
                    splash.close();
            });
            
            splashscene = new Scene(root);
            splash.initOwner(primaryStage);
            splash.initModality(Modality.WINDOW_MODAL);
            splash.getIcons().add(new Image("file:resources/icon.png"));
            splash.setTitle("Pixy Image Editor");
            splash.setScene(splashscene);
            splash.showAndWait();
        }//end of try
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }//end of catch//end of catch
        
    }//end of method
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}// end of class
