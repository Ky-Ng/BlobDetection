package Processing.Util;

import org.opencv.core.Scalar;

public class Pixel {
    public int x, y;
    public Scalar colorHSV;

    public Pixel(int x, int y, Scalar colorHSV){
        this.x = x;
        this.y = y;
        this.colorHSV = colorHSV;
    }

    public Pixel() {
        this.x = 0;
        this.y = 0;
    }
}
