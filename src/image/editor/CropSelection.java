/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.editor;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

/**
 *
 * @author Shehyaaz, Shakshi, Shivangi
 */
public class CropSelection {

        final DragContext dragContext = new DragContext();
        Rectangle rect = new Rectangle();
        Stage mainStage;
        IEImagePane imgPane;


        public Bounds getBounds() {
            return rect.getBoundsInParent();
        }//end of method

    /**
     *
     * @param imgPane
     */
    public CropSelection(IEImagePane imgPane) {
            this.imgPane = imgPane;
            mainStage = Main.primaryStage;
            rect = new Rectangle( 0,0,0,0);
            rect.setStroke(Color.BLUE);
            rect.setStrokeWidth(1);
            rect.setStrokeLineCap(StrokeLineCap.ROUND);
            rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));

            this.imgPane.group.addEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressedEventHandler);
            this.imgPane.group.addEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEventHandler);
            this.imgPane.group.addEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);
        }//end of constructor
    
    void saveCropImage(){
            Bounds bounds = getBounds();
            int width = (int) bounds.getWidth();
            int height = (int) bounds.getHeight();

            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            parameters.setViewport(new Rectangle2D( bounds.getMinX(), bounds.getMinY(), width, height));

            WritableImage wi = new WritableImage( width, height);
            rect.setFill(Color.TRANSPARENT);
            rect.setStroke(Color.TRANSPARENT);
            imgPane.group.snapshot(parameters, wi);
   
            imgPane.group.removeEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressedEventHandler);
            imgPane.group.removeEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEventHandler);
            imgPane.group.removeEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);
            imgPane.canvas.setWidth(width);
            imgPane.canvas.setHeight(height);
            imgPane.iv.setFitHeight(height);
            imgPane.iv.setFitWidth(width);
            imgPane.iv.setImage(wi);
    }//end of image

        EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {    
                if( event.isSecondaryButtonDown())
                    return;
                // remove old rect
                rect.setX(0);
                rect.setY(0);
                rect.setWidth(0);
                rect.setHeight(0);

                imgPane.group.getChildren().remove(rect);
                // prepare new drag operation
                dragContext.mouseAnchorX = event.getX();
                dragContext.mouseAnchorY = event.getY();

                rect.setX(dragContext.mouseAnchorX);
                rect.setY(dragContext.mouseAnchorY);
                rect.setWidth(0);
                rect.setHeight(0);
                
                imgPane.group.getChildren().add(rect);
            }
       };

        EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if( event.isSecondaryButtonDown())
                    return;
                double offsetX = event.getX() - dragContext.mouseAnchorX;
                double offsetY = event.getY() - dragContext.mouseAnchorY;

                if( offsetX > 0)
                    rect.setWidth( offsetX);
                else {
                    rect.setX(event.getX());
                    rect.setWidth(dragContext.mouseAnchorX - rect.getX());
                }//end of else
                if( offsetY > 0) {
                    rect.setHeight( offsetY);
                } else {
                    rect.setY(event.getY());
                    rect.setHeight(dragContext.mouseAnchorY - rect.getY());
                }//end of else
            }
        };
        
        EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if( event.isSecondaryButtonDown())
                    return;
                saveCropImage();
                rect.setX(0);
                rect.setY(0);
                rect.setWidth(0);
                rect.setHeight(0);  
                imgPane.group.getChildren().remove(rect);
            }
        };

        private static final class DragContext {
            public double mouseAnchorX;
            public double mouseAnchorY;
        }//end of nested class
}//end of class
