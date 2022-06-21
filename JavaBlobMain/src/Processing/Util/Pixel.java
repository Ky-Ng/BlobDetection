package Processing.Util;

import org.opencv.core.Scalar;

public class Pixel {
    public int x, y;
    public Scalar colorRGB;

    public Pixel(int x, int y, Scalar colorRGB){
        this.x = x;
        this.y = y;
        this.colorRGB = colorRGB;
    }

    public Pixel(int x, int y, double[] colorBGR){
        this.x = x;
        this.y = y;
        this.colorRGB = new Scalar(colorBGR[2], colorBGR[1], colorBGR[1]);
    }

    public Pixel() {
        this.x = 0;
        this.y = 0;
    }
}
