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
        return BlobsUtil.getDistance2D(center, pixel) < Parameters.DistanceThreshold;
    }

    public boolean isInColorRange(Pixel pixel){
        return BlobsUtil.getColorDistance(blobRGB, pixel.colorRGB) < Parameters.ColorThreshold;
    }

    public void merge(Blob other){

        minCorner.x = (int) Math.min(minCorner.x, other.getMinCorner().x);
        minCorner.y = (int) Math.min(minCorner.y, other.getMaxCorner().y);

        maxCorner.x = (int) Math.max(maxCorner.x, other.getMaxCorner().x);
        maxCorner.y = (int) Math.max(maxCorner.y, other.getMaxCorner().y);
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
