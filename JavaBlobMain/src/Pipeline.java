import Processing.Util.Pixel;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.event.MouseEvent;
import java.util.Scanner;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class Pipeline {
    static String AssetsFolder = "/Users/kyleng/IdeaProjects/BlobDetection/JavaBlobMain/src/Assets/";
    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);

        // show image
        Mat imgSource = Imgcodecs.imread(AssetsFolder +
//                "rgb_image.jpg"
                "cyan.png"
        );

        HighGui.imshow("Source Image", imgSource);


        HighGui.waitKey(0);
    }
}
