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

    public Pixel(int x, int y, double[] colorHSV){
        this.x = x;
        this.y = y;
        this.colorHSV = new Scalar(colorHSV[0], colorHSV[1], colorHSV[2]);
    }

    public Pixel() {
        this.x = 0;
        this.y = 0;
    }
}
