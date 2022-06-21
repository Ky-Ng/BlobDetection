import Processing.Blob;
import Processing.Util.Distance;
import Processing.Util.Pixel;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class Pipeline {
    static String AssetsFolder = "/Users/kyleng/IdeaProjects/BlobDetection/JavaBlobMain/src/Assets/";
    static Scalar targetRGB = new Scalar(53, 143, 253);

    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);

        //create image
        Mat imgSource = Imgcodecs.imread(AssetsFolder + "blueFish.jpg");

        // process image
        ArrayList<Blob> blobs = new ArrayList<>();

        for (int x = 0; x < imgSource.cols(); x ++) {
            for (int y = 0; y < imgSource.rows(); y++) {
                Pixel pixel = new Pixel(x,y, imgSource.get(x,y));

                if (blobs.isEmpty()){
                    if (Distance.getColorDistance(pixel.colorRGB, targetRGB) < Blob.colorThreshold){
                        blobs.add(new Blob(pixel));
                    }
                }

                for (Blob b : blobs){
                    if (b.isInColorRange(pixel)){
                        if (b.isNear(pixel)){
                            b.addPixel(pixel);
                        } else{
                            blobs.add(new Blob(pixel));
                        }
                    }
                }
            }
        }



        //display image
        HighGui.imshow("Source Image", imgSource);
        HighGui.waitKey(0);
    }
}
