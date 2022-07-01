import Processing.Parameters;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.Arrays;
import java.util.Scanner;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class FindColorUI {
    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);
        System.out.println("Press any key to begin, type 3333 to exit.");
        Scanner s = new Scanner(System.in);

        //create image
        Mat imgSource = new Mat();
        Mat drawnImage;
        VideoCapture videoCapture = new VideoCapture(1);
        while (true) {
            videoCapture.read(imgSource);
            double[] BGR;

            int x = 0, y = 0;

                drawnImage = imgSource.clone();
                System.out.println("Enter X Coordinate");
                x = s.nextInt();
                System.out.println("Enter Y Coordinate");
                y = s.nextInt();

                BGR = imgSource.get(y, x);
                System.out.println("BGR at Coordinate " + x + ", " + y + " = " + Arrays.toString(BGR));

                Imgproc.rectangle(drawnImage, new Point(x, y), new Point(x, y), new Scalar(0, 255, 0), 10);
                HighGui.imshow("Target Point", drawnImage);
                HighGui.waitKey(30);


            System.out.println("RGB at Point = " + (int) BGR[2] + ", " + (int) BGR[1] + ", " + (int) BGR[0]);

        }
    }
}
