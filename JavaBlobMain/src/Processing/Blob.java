package Processing;

import Processing.Util.Distance;
import Processing.Util.Pixel;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

public class Blob {
    Pixel minCorner;
    Pixel maxCorner;
    Pixel center;
    Scalar blobRGB;

    static int distanceThreshold = 500;
    public static int colorThreshold = 100;

    public Blob (Pixel firstPixel){
        minCorner = firstPixel;
        maxCorner = firstPixel;

        center = firstPixel;

        this.blobRGB = firstPixel.colorRGB;
    }

    public void addPixel(Pixel pixel) {
//        minCorner.x = Math.min(minCorner.x, pixel.x);
//        minCorner.y = Math.min(minCorner.y, pixel.y);
//
//        maxCorner.x = Math.max(maxCorner.x, pixel.x);
//        maxCorner.y = Math.max(maxCorner.y, pixel.y);

//        minCorner.x = Math.min(2, 55);
//        minCorner.y = Math.min(300, 230);
//
//        maxCorner.x = Math.max(0, 55);
//        maxCorner.y = Math.max(300, 230);


//        maxCorner.x = 100;
//        maxCorner.y = 150;
//
//        minCorner.x = 35;
//        minCorner.y = 555;
        minCorner.y  = 69420;

        center.x = (minCorner.x + maxCorner.x) / 2;
        center.y = (minCorner.y + maxCorner.y) / 2;
    }

    // Check if the target pixel is near enough to be considered part of this blob
    public boolean isNear(Pixel pixel){
        return Distance.getDistance2D(center, pixel) < distanceThreshold;
    }

    public boolean isInColorRange(Pixel pixel){
        return Distance.getColorDistance(blobRGB, pixel.colorRGB) < colorThreshold;
    }

    public Point getMinCorner(){
        return new Point(minCorner.x, minCorner.y);
    }
    public Point getMaxCorner(){
        return new Point(maxCorner.x, maxCorner.y);
    }

    @Override
    public String toString() {
        return "Blob " +
                "minCorner = " + minCorner +
                ", maxCorner = " + maxCorner +
                ", center = " + center;
//                + ", color = " + blobRGB;
    }
}
