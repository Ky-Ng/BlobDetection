import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class Pipeline {
    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);

        // show image
        Mat sourceFish = Imgcodecs.imread("/Users/kyleng/IdeaProjects/BlobDetection/JavaBlobMain/src/Assets/blueFish.jpg");

        HighGui.imshow("Source Image", sourceFish);
        HighGui.waitKey(0);
    }
}
