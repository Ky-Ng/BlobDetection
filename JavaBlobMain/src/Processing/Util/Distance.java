package Processing.Util;

import Processing.Blob;
import org.opencv.core.Scalar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Distance {
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
            for (int j = 0; j < i; j++) {
                System.out.println("Distance between blobs " +
                        getDistance2D(blobs.get(i).getCenter(), blobs.get(j).getCenter())
                + " | Color Distance " + getColorDistance(blobs.get(i).getCenter().colorRGB, blobs.get(j).getCenter().colorRGB));
            }
        }


    }
}
