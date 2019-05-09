/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package image.editor;

import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
/**
 *
 * @author sony
 
 class MainMenu {
    MenuBar mb;
    EventHandler<ActionEvent> handler;
    MainMenu(MenuBar mb,EventHandler<ActionEvent> handler){
        this.mb = mb;
        this.handler = handler;
    }//end of constructor
    void makeImageFileMenu(){
        Menu imageFileMenu = new Menu("_File"); // add mnemonic
        // create the menu items
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem close = new MenuItem("Close");
        MenuItem exit = new MenuItem("Exit");
        // add items to the menu
        imageFileMenu.getItems().addAll(open,save,close, new SeparatorMenuItem(), exit);
        // add keyboard accelerators for the menu items
        open.setAccelerator(KeyCombination.keyCombination("shortcut+O"));
        save.setAccelerator(KeyCombination.keyCombination("shortcut+S"));
        close.setAccelerator(KeyCombination.keyCombination("shortcut+Q"));
        exit.setAccelerator(KeyCombination.keyCombination("Alt+X"));
        // set action event handlers
        open.setOnAction(handler);
        save.setOnAction(handler);
        close.setOnAction(handler);
        exit.setOnAction(handler);
        //add this menu to the menu bar
        mb.getMenus().add(imageFileMenu);
    }//end of method
    // create the Edit menu
    void makeEditMenu(){
        Menu editMenu = new Menu("_Edit");
        // create the menu item
        MenuItem crop = new MenuItem("Crop");
        MenuItem contrast = new MenuItem("Contrast");
        MenuItem blur = new MenuItem("Blur");
        MenuItem sharpen = new MenuItem("Sharpen");
        MenuItem grayscale = new MenuItem("Gray Scale");
        MenuItem textoverlay = new MenuItem("Text Overlay");
        MenuItem doodle = new MenuItem("Doodle");
        MenuItem reset = new MenuItem("Reset");
        reset.setAccelerator(KeyCombination.keyCombination("shortcut+Z"));
        // add items to the menu
        editMenu.getItems().addAll(crop,contrast,blur,sharpen,grayscale,textoverlay,doodle,new SeparatorMenuItem(),reset);
        // set action event handlers
        crop.setOnAction(handler);
        contrast.setOnAction(handler);
        blur.setOnAction(handler);
        sharpen.setOnAction(handler);
        grayscale.setOnAction(handler);
        textoverlay.setOnAction(handler);
        doodle.setOnAction(handler);
        reset.setOnAction(handler);
        // add edit menu to the menu bar
        mb.getMenus().add(editMenu);
    }//end of method
    // create the Help menu
    void makeHelpMenu(){
        Menu helpMenu = new Menu("_Help");
        // create the menu item
        MenuItem about = new MenuItem("About");
        //add items to the menu
        helpMenu.getItems().add(about);
        // set event handler
        about.setOnAction(handler);
        //add help menu to the menu bar
        mb.getMenus().add(helpMenu);
    }//end of method
    // create the tool bar
    void makeToolBar(ToolBar tb){
        //loading image icons
        ImageView fileopen = new ImageView("../fileopen.png");
        ImageView filesave = new ImageView("../filesave.png");
        ImageView imgcrop = new ImageView("../imgcrop.jpg");
        ImageView imgcontrast = new ImageView("../imgcontrast.jpg");
        ImageView imgblur = new ImageView("../imgblur.jpg");
        ImageView imgsharpen = new ImageView("../imgsharpen.png");
        ImageView imggray = new ImageView("../imggray.png");
        ImageView imgtext = new ImageView("../imgtext.png");
        ImageView imgdoodle = new ImageView("../imgdoodle.png");
        //setting image size
        fileopen.setFitHeight(15); fileopen.setFitWidth(15);
        filesave.setFitHeight(15); filesave.setFitWidth(15);
        imgcrop.setFitHeight(15); imgcrop.setFitWidth(15);
        imgcontrast.setFitHeight(15); imgcontrast.setFitWidth(15);
        imgblur.setFitHeight(15); imgblur.setFitWidth(15);
        imgsharpen.setFitHeight(15); imgsharpen.setFitWidth(15);
        imggray.setFitHeight(15); imggray.setFitWidth(15);
        imgtext.setFitHeight(15); imgtext.setFitWidth(15);
        imgdoodle.setFitHeight(15); imgdoodle.setFitWidth(15);
        // create buttons
        Button btnopen = new Button("Open",fileopen);
        Button btnsave = new Button("Save",filesave);
        Button btncrop = new Button("Crop",imgcrop);
        Button btncontrast = new Button("Contrast",imgcontrast);
        Button btnblur = new Button("Blur",imgblur);
        Button btnsharpen = new Button("Sharpen",imgsharpen);
        Button btngray = new Button("Gray Scale",imggray);
        Button btntext = new Button("Text Overlay",imgtext);
        Button btndoodle = new Button("Doodle",imgdoodle);
        
    }// end of method
}// end of class
*/