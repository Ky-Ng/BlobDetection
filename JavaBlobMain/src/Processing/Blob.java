package Processing;

import Processing.Util.BlobsUtil;
import Processing.Util.Pixel;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

public class Blob {
    Pixel minCorner;
    Pixel maxCorner;
    Pixel center;
    Scalar blobRGB;

    static int distanceThreshold = 290;
    public static int colorThreshold = 150;

    public Blob (Pixel firstPixel){
        minCorner = firstPixel.copy();
        maxCorner = firstPixel.copy();//new Pixel(0,0,new Scalar(0,0,0));

        center = firstPixel;

        this.blobRGB = firstPixel.colorRGB;
    }

    public void addPixel(Pixel pixel) {
        minCorner.x = Math.min(minCorner.x, pixel.x);
        minCorner.y = Math.min(minCorner.y, pixel.y);

        maxCorner.x = Math.max(maxCorner.x, pixel.x);
        maxCorner.y = Math.max(maxCorner.y, pixel.y);

        center.x = (minCorner.x + maxCorner.x) / 2;
        center.y = (minCorner.y + maxCorner.y) / 2;
    }

    // Check if the target pixel is near enough to be considered part of this blob
    public boolean isNear(Pixel pixel){
        return BlobsUtil.getDistance2D(center, pixel) < distanceThreshold;
    }

    public boolean isInColorRange(Pixel pixel){
        return BlobsUtil.getColorDistance(blobRGB, pixel.colorRGB) < colorThreshold;
    }

    public Point getMinCorner(){
        return new Point(minCorner.x, minCorner.y);
    }
    public Point getMaxCorner(){
        return new Point(maxCorner.x +2, maxCorner.y+2);
    }

    public Pixel getCenter() {
        return center;
    }

    public int getArea(){
        return (maxCorner.x - minCorner.x) * (maxCorner.y - minCorner.y);
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
