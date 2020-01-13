package entities;

import java.awt.*;

public class Overlap2DDecider {
    // works only of rectangle and circle combinations
    Point[] pointsShape1;
    Point[] pointsShape2;
    double overlapArea;
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
            //System.out.println("Legal Shapes");
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
            //System.out.println("In setOverlapping: are2Rectangles");
            if(isRect1inRect2(pointsShape1,pointsShape2) ||
                    isRect1inRect2(pointsShape2,pointsShape1)) {
                overlapping = true;
                setOverlapArea();
            }
        }
        else if(areRectangleAndSphere) { overlapping = false; }
        else if(areSphereAndRectangle) { overlapping = false; }
        else if(are2Spheres) { overlapping = false; }
    }

    private void setOverlapArea() {
        if(are2Rectangles) {
            int r1x1 = pointsShape1[0].x; // rectangle 1 x axis 1...
            int r1x2 = pointsShape1[2].x;
            int r1y1 = pointsShape1[0].y;
            int r1y2 = pointsShape1[1].y;
            int r2x1 = pointsShape2[0].x;
            int r2x2 = pointsShape2[2].x;
            int r2y1 = pointsShape2[0].y;
            int r2y2 = pointsShape2[1].y;
            double xAxisOverlap = Math.max(0,Math.min(r1x1,r2x1) - Math.max(r1x2,r2x2));
            double yAxisOverlap = Math.max(0, Math.min(r1y1,r2y1) - Math.max(r1y2,r2y2));
            overlapArea = xAxisOverlap * yAxisOverlap;
            System.out.println("oA is:" + overlapArea);
        }
        else if(areRectangleAndSphere) {
            overlapArea = 0;
        }
        else if(areSphereAndRectangle) {
            overlapArea = 0;
        }
        else if(are2Spheres) {
            overlapArea = 0;
        }
    }

    public double getOverlapArea() {
        return overlapArea;
    }

    public boolean getOverlapping() {
        return overlapping;
    }

    private boolean isRect1inRect2(Point[] rect1, Point[] rect2) {
        // if any 1 of 1's corners is withing xlines and ylines of 2, true, else false
       if(are2Rectangles) {
           //System.out.println("2 rects in isRect1inRect2");
           int xLine2a = rect2[0].x;
           int xLine2b = rect2[2].x;
           int yLine2a = rect2[0].y;
           int yLine2b = rect2[1].y;
           /*
           System.out.println("Lines:_" + xLine2a + "_" + xLine2b + "_" + yLine2a + "_" + yLine2b);
           for (Point i: rect1) {
               System.out.println(i.x + "_" + i.y);
           }
           for (Point i: rect2) {
               System.out.println(i.x + "_" + i.y);
           }
            */
           if( xLine2a <= rect1[0].x && rect1[0].x < xLine2b ) {
               if( yLine2a <= rect1[0].y && rect1[0].y < yLine2b ) {
                   //System.out.println("Rect in rect");
                   return true;
               }
           }
           if( xLine2a <= rect1[1].x && rect1[1].x < xLine2b ) {
               if( yLine2a <= rect1[1].y && rect1[1].y < yLine2b ) {
                   //System.out.println("Rect in rect");
                    return true;
               }
           }
           if( xLine2a <= rect1[2].x && rect1[2].x < xLine2b ) {
               if( yLine2a <= rect1[2].y && rect1[2].y < yLine2b ) {
                   //System.out.println("Rect in rect");
                   return true;
               }
           }
           if( xLine2a <= rect1[3].x && rect1[3].x < xLine2b ) {
               if( yLine2a <= rect1[3].y && rect1[3].y < yLine2b ) {
                   //System.out.println("Rect in rect");
                   return true;
               }
           }
       }
       return false;
    }
}
