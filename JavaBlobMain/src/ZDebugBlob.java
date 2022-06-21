import Processing.Blob;
import Processing.Util.Pixel;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ZDebugBlob {
    public static void main(String[] args) {
        Pixel pixel = new Pixel(1,5, new Scalar(0,0,0));
        Blob blob = new Blob(pixel);
        System.out.println(blob);

        System.out.println("Min Corner = " + blob.getMinCorner());
        System.out.println("Max Corner = " + blob.getMaxCorner());

        Pixel addPixel = new Pixel(3,7, new Scalar(0,0,0));
        System.out.println("Add Pixel " + addPixel);

        blob.addPixel(addPixel);
        System.out.println(blob);
    }
}
