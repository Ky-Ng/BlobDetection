import Processing.Util.Pixel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class Pipeline {
    static String AssetsFolder = "/Users/kyleng/IdeaProjects/BlobDetection/JavaBlobMain/src/Assets/";
    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);

        // show image
        Mat imgSource = Imgcodecs.imread(AssetsFolder + "blueFish.jpg");

        Mat imgHSV = new Mat();
        Imgproc.cvtColor(imgSource, imgHSV,Imgproc.COLOR_RGB2HSV);

        for (int x = 0; x < imgHSV.rows(); x ++){
            for (int y = 0; y < imgHSV.cols(); y++){
                Pixel currentPixel = new Pixel(x,y, imgHSV.get(x, y));
                System.out.println(
                        "X " + currentPixel.x
                        + " Y " + currentPixel.y
                        + " HSV " + imgHSV.get(x,y)[0] + " " + imgHSV.get(x,y)[1] + " " + imgHSV.get(x,y)[2]
                );
            }
        }

        HighGui.imshow("Source Image", imgSource);
        HighGui.imshow("HSV Image", imgHSV);
        HighGui.waitKey(0);
    }
}
