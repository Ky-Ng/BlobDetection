package Debug;

import Processing.Blob;
import Processing.Parameters;
import Processing.Util.BlobsUtil;
import Processing.Util.Drawer;
import Processing.Util.Pixel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class DebugMerge {
    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);

        //create image
        Mat imgSource = Imgcodecs.imread(Parameters.AssetsFolder + Parameters.ImageName);

        // process image
        ArrayList<Blob> blobs = new ArrayList<>();

        Blob b1 = new Blob(new Pixel()); b1.addPixel(new Pixel(50,100, new Scalar(0,0,0)));
        Blob b2 = new Blob(new Pixel(10,30, new Scalar(0,0,0))); b2.addPixel(new Pixel(60,150, new Scalar(0,0,0)));
        blobs.add(b1);
        blobs.add(b2);

        //display image


//        BlobsUtil.pruneBlobs(blobs);

        BlobsUtil.printDistance(blobs);
        BlobsUtil.printArea(blobs);
        Drawer.drawBlobs(imgSource, blobs);
        HighGui.imshow("Debug", imgSource);

        HighGui.waitKey(0);
    }
}
