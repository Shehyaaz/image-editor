/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

package image.editor;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Shehyaaz, Shakshi, Shivangi
 * This class defines the main window with tool bars and edit options


public class ImageEditor extends Application {
    public static boolean isSplashed = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        try{
            //Parent root = FXMLLoader.load(getClass().getResource("ImageApp.fxml"));
            AnchorPane root = new AnchorPane();
            Scene scene = new Scene(root,300,300);
            stage.setTitle("Pixy Image Editor");
            stage.getIcons().add(new Image("file:resources/icon.png"));
            stage.setScene(scene);
            stage.show();
        }//end of try
        catch(Exception e){
            e.printStackTrace();
        }//end of ctach
    }//end of start
    
    /**
     * @param args the command line arguments
   
    public static void main(String[] args) {
        launch(args);
    } //end of main
}//end of class


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package image.editor;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.canvas.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

/**
 *
 * @author Shehyaaz, Shakshi, Shivangi
 * This class defines the main window with tool bars and edit options

public class ImageEditor extends Application {
    //instance variables
    MenuBar mb;
    EventHandler<ActionEvent> handler;
    ToolBar tb;
    MainMenu menu;
    /**
     * @param args the command line arguments
     
    public static void main(String[] args) {
        launch(args);
    } //end of main
    @Override
    public void start(Stage myStage){
	//System.out.println("Inside the start() method");
	myStage.setTitle("Pixy Image Editor");
	// using a BorderPane for root node
	final BorderPane rootNode = new BorderPane(); //root node
        Scene myscene = new Scene(rootNode,500,500); //set scene
	myStage.setScene(myscene); // set scene
	//create menu bar
	mb = new MenuBar();
        //event handler that will handle menu action events.
	handler = new CustomEventHandler();
        menu = new MainMenu(mb,handler); // instantiating MainMenu class
	// create the image file menu bar
        menu.makeImageFileMenu();
        // create edit image menu
        menu.makeEditMenu();
	// create help menu
        menu.makeHelpMenu();
        // create toolbar
        menu.makeToolBar(tb);
        
        ImageView bg = new ImageView("../background.jpeg");// background image
        Canvas canvas = new Canvas(bg.getFitWidth(),bg.getFitHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D(); //get graphics context for canvas
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Verdana",70));
        gc.setFill(Color.PINK);
        gc.fillText("Pixy-Image Editor", canvas.getWidth()/ 2, canvas.getHeight()/ 2); //drawing text on the canvas
        StackPane sproot = new StackPane(); 
        sproot.getChildren().addAll(bg,canvas);
	//add the menu bar to the top of the border pane
	rootNode.setTop(mb);
        rootNode.setCenter(sproot);
	myStage.show(); // show the stage and its scene
    }//end of start
}// end of class
*/