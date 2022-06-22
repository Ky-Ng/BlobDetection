package Processing.Util;

import Processing.Blob;
import org.opencv.core.Scalar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BlobsUtil {
    public static int getDistance2D(Pixel pixel1, Pixel pixel2){
        return (int) Math.sqrt(
                Math.pow((pixel1.x - pixel2.x), 2) + Math.pow((pixel1.y - pixel2.y), 2)
        );
    }

    // Formula for finding how close (distance) of 2 colors from Coding Train video
    public static int getColorDistance(Scalar color1, Scalar color2){
        return (int) Math.sqrt(
                Math.pow((color1.val[0] - color2.val[0]), 2)
                        + Math.pow((color1.val[1] - color2.val[1]), 2)
                        + Math.pow((color1.val[2] - color2.val[2]), 2)
        );
    }

    public static void printDistance(ArrayList<Blob> blobs){
        for (int i = 0; i < blobs.size(); i++) {
            for (int j = 1; j < i; j++) {
                System.out.println("Distance between blobs " +
                        getDistance2D(blobs.get(i).getCenter(), blobs.get(j).getCenter())
                + " | Color Distance " + getColorDistance(blobs.get(i).getCenter().colorRGB, blobs.get(j).getCenter().colorRGB));
            }
        }
    }

    public static void printArea(ArrayList<Blob> blobs){
        for(Blob b : blobs){
            System.out.println("Area " +b.getArea());
        }
    }

    //remove small blobs and merge close ones
    public static void pruneBlobs(ArrayList<Blob> blobs) {
        // remove the small blobs
        for (int i = 0; i < blobs.size(); i++) {
            if (blobs.get(i).getArea() < 5) {
                blobs.remove(i);
                i--;
            }
        }

        // Merge Blobs that are touching
        for (int i = 0; i < blobs.size(); i++) {
            for (int j = i+1; j < blobs.size(); j++) {
                if(blobsIntersecting(blobs.get(i), blobs.get(j))){
                    System.out.println("merged " + i + " with " + j);
                    System.out.println("removing " + j);
                    blobs.get(i).merge(blobs.get(j));
                    blobs.remove(j);
                    j--;
                }
            }
        }
    }

    public static boolean blobsIntersecting(Blob b1, Blob b2){
        return  isBetween(b1.getMinCorner().x, b1.getMaxCorner().x, b2.getMinCorner().x) ||
                isBetween(b1.getMinCorner().x, b1.getMaxCorner().x, b2.getMaxCorner().x) ||
                isBetween(b2.getMinCorner().y, b2.getMinCorner().y, b1.getMinCorner().y) ||
                isBetween(b2.getMinCorner().y, b2.getMaxCorner().y, b1.getMaxCorner().y);
    }

    public static boolean isBetween(double start, double end, double target){
        return target >= start && target <= end;
    }
}
