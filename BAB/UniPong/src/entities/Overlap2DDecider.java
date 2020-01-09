package entities;

import java.awt.*;

public class Overlap2DDecider {
    // works only of rectangle and circle combinations
    Point[] pointsShape1;
    Point[] pointsShape2;
    boolean are2Rectangles = false;
    boolean areSphereAndRectangle = false;
    boolean areRectangleAndSphere = false;
    boolean are2Spheres = false;
    boolean overlapping = false;

    // @param
    public Overlap2DDecider(Point[] shape1Points, Point[] shape2Points) {
        // rectangles are ordered : top left, bottom left, top right, bottom right
        // circles are ordered : center, east-most point
        pointsShape1 = shape1Points;
        pointsShape2 = shape2Points;
        getShapeTypes();
        if(areLegalShapes()) {
            setOverlapping();
        }
    }

    private boolean areLegalShapes() {
        return are2Rectangles || areRectangleAndSphere || areSphereAndRectangle || are2Spheres;
    }

    private void getShapeTypes() {
        // we decide shape types based on number of points in each shape
        // 2 per circle and 4 per rectangle
        // 2 rectangles
        if(pointsShape1.length == 4 && pointsShape2.length == 4) { are2Rectangles = true; }
        // sphere and rect
        else if(pointsShape1.length == 2 && pointsShape2.length == 4) { areSphereAndRectangle = true; }
        // rect and sphere
        else if(pointsShape1.length == 4 && pointsShape2.length == 2) { areRectangleAndSphere = true; }
        // 2 spheres
        else if(pointsShape1.length == 2 && pointsShape2.length == 2) { are2Spheres = true; }
    }

    // currently for 2 rectangles only
    private void setOverlapping() {
        if(are2Rectangles) { // overlapping if either shape is in the other
            overlapping = isRect1inRect2(pointsShape1,pointsShape2) ||
                    isRect1inRect2(pointsShape2,pointsShape1);
        }
        else if(areRectangleAndSphere) { overlapping = false; }
        else if(areSphereAndRectangle) { overlapping = false; }
        else if(are2Spheres) { overlapping = false; }
    }

    public boolean getOverlapping() {
        return overlapping;
    }

    private boolean isRect1inRect2(Point[] rect1, Point[] rect2) {
        // if any 1 of 1's corners is withing xlines and ylines of 2, true, else false
       if(are2Rectangles) {
           int xLine2a = rect2[0].x;
           int xLine2b = rect2[3].x;
           int yLine2a = rect2[0].y;
           int yLine2b = rect2[2].y;
           if( xLine2a <= rect1[0].x && rect1[0].x < xLine2b ) {
               return yLine2a <= rect1[0].y && rect1[0].y < yLine2b;
           }
           if( xLine2a <= rect1[1].x && rect1[1].x < xLine2b ) {
               return yLine2a <= rect1[1].y && rect1[1].y < yLine2b;
           }
           if( xLine2a <= rect1[2].x && rect1[2].x < xLine2b ) {
               return yLine2a <= rect1[2].y && rect1[2].y < yLine2b;
           }
           if( xLine2a <= rect1[3].x && rect1[3].x < xLine2b ) {
               return yLine2a <= rect1[3].y && rect1[3].y < yLine2b;
           }
       }
       return false;
    }
}
