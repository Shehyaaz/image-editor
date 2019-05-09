/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.editor;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Shehyaaz, Shakshi, Shivangi
 */
public class FreeCropSelection {
    private Polyline poly;
    private Polygon polygon;
    Scene scene;
    IEImagePane imgPane;
    double minX,minY, maxX,maxY;
    
    public FreeCropSelection(IEImagePane imgPane) {
        this.scene = Main.scene;
        this.imgPane = imgPane;
        poly = new Polyline();
        poly.setStroke(Color.CORNFLOWERBLUE);
        poly.setStrokeWidth(1.5);
        poly.getStrokeDashArray().addAll(10d,10d);
        this.imgPane.group.addEventFilter(MouseEvent.MOUSE_ENTERED, onMouseEntered);
        this.imgPane.group.addEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDragged);
        this.imgPane.group.addEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleased);
        
        minX = Double.MAX_VALUE;
        maxX = -Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        maxY = -Double.MAX_VALUE;
    }//end of constructor
    
    void saveFreeCropImage(){
        polygon = new Polygon();
        polygon.getPoints().addAll(poly.getPoints());
        double bounds[] = getBoundingParameters();
        
        Shape shape = Shape.subtract(new Rectangle(minX,minY,bounds[0],bounds[1]),polygon);
        shape.setFill(Color.WHITE);
        
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        parameters.setViewport(new Rectangle2D(minX,minY, bounds[0], bounds[1]));
        WritableImage wi = new WritableImage((int)bounds[0],(int)bounds[1]);
        imgPane.group.snapshot(parameters, wi);
        
        imgPane.stack.getChildren().add(shape);
        scene.setCursor(Cursor.DEFAULT);
        
        imgPane.group.removeEventFilter(MouseEvent.MOUSE_ENTERED, onMouseEntered);
        imgPane.group.removeEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDragged);
        imgPane.group.removeEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleased);
        
        imgPane.canvas.setWidth(bounds[0]);
        imgPane.canvas.setHeight(bounds[1]);
        imgPane.iv.setFitHeight(bounds[1]);
        imgPane.iv.setFitWidth(bounds[0]);
        imgPane.iv.setImage(wi);
    }//end of method
    
    double[] getBoundingParameters(){
        Double[] points = polygon.getPoints().toArray(new Double[polygon.getPoints().size()]);
        for(int i=0; i < points.length-1; i+=2){
            if(points[i] < minX) minX = points[i];
            if(points[i] > maxX) maxX = points[i];
            
            if(points[i+1] < minY) minY = points[i+1];
            if(points[i+1] > maxY) maxY = points[i+1];
        }//end of loop
        double res[] ={maxX - minX, maxY-minY};
        return res;
    }//end of function
    
    EventHandler<MouseEvent> onMouseEntered = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.CROSSHAIR);
            }
    };
    
    EventHandler<MouseEvent> onMouseDragged = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if( event.isSecondaryButtonDown())
                    return;
                poly.getPoints().addAll(event.getX(),event.getY());
                imgPane.group.getChildren().add(poly);
            }
    };
    
    EventHandler<MouseEvent> onMouseReleased = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if( event.isSecondaryButtonDown())
                    return;
               saveFreeCropImage();
               imgPane.group.getChildren().remove(poly);  
            }
    };
}//end of class
