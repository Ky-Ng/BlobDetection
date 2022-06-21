import Processing.Blob;
import Processing.Util.Pixel;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ZDebugBlob {
    public static void main(String[] args) {
        Scalar color = new Scalar(0,0,0);
        Pixel pixel = new Pixel(1,5, color);
        Blob blob = new Blob(pixel);
        System.out.println(blob);

        Pixel addPixel = new Pixel(0,7, color);
        System.out.println("Add Pixel " + addPixel);

        blob.addPixel(addPixel);
        System.out.println(blob);

        Pixel nearPixel = new Pixel(300,12, color);
        System.out.println("is near " + nearPixel + "? "+ blob.isNear(nearPixel));
    }
}
