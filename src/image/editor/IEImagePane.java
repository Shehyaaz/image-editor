/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.editor;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Shehyaaz, Shakshi, Shivangi
 */
public class IEImagePane extends TabPane {
        ScrollPane sp;
        StackPane stack;
        Canvas canvas;
        ImageView iv;
        Group group;
        
	public IEImagePane(){
		super();   
	}//end of constructor

	/**
	 * Adds a tab to the image pane. The tab contains
	 * a scroll pane which contains the image opened
	 * by the user.
	 * @param img An image to be opened in a new tab.
	 * @param tabName The name of the tab.
	 */
	public void addTab(Image img, String tabName){
		Tab newTab = new Tab();
		newTab.setText(tabName);
		newTab.setContent(createNewScrollPane(createNewPane(img)));
		getTabs().add(newTab);
	}//end of method
        
	/**
	 * Creates a new canvas, assigns the canvas an image
	 * and adds the appropriate event handlers.
	 * @param img The image to be displayed on the canvas.
	 * @return The created canvas.
	 */
	private StackPane createNewPane(Image img){
                iv = new ImageView(img);
		canvas = new Canvas(img.getWidth(), img.getHeight());
		stack = new StackPane();
                stack.getChildren().addAll(canvas,iv);
		return stack;
	}//end of method
        
	/**
	 * Creates a new scroll pane and adds the provided canvas
	 * to it.
	 * @param canvas The canvas to be set on the scroll pane.
	 * @return The created scroll pane.
	 */
	private ScrollPane createNewScrollPane(StackPane stack){
		sp = new ScrollPane();
                group = new Group();
                group.getChildren().add(stack);
		sp.setContent(group);
		sp.setStyle("-fx-background-color:LightGrey");
		return sp;
	}//end of method
        
        public Group getGroup(){
                return group;
        }//end of method
        
	/**
	 * Returns the image from the active tab.
	 * @return The image from the active tab.
	 */
        public WritableImage getCurrentImage()
	{
		return ((ScrollPane)(getSelectionModel().getSelectedItem().getContent())).getContent().snapshot(null, null);
	}//end if method
        
	/**
	 * Returns if there is an active tab or not.
	 * @return A boolean representing if there is an active tab.
	 */
	public boolean hasCurrentImage(){
		return getSelectionModel().getSelectedItem() != null;
	}//end of method
}//end of class

