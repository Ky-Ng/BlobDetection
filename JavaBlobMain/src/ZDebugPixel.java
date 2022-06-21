import Processing.Util.Pixel;
import org.opencv.core.Scalar;

public class ZDebugPixel {
    public static void main(String[] args) {
        Pixel pixel = new Pixel(1,5, new Scalar(0,0,0));
        System.out.println("Starting Pixel Val " + pixel);
        pixel.x = 3;
        pixel.y = 6;
        System.out.println("End Pixel Val " + pixel);
    }
}
