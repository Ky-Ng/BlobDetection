package Processing.Util;

import org.opencv.core.Scalar;

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
}
