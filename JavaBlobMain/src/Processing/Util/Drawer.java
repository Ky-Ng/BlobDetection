package Processing.Util;

import Processing.Blob;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class Drawer {
    static Scalar drawColor = new Scalar(130,130,130);

    public static void drawBLobs(Mat canvas, ArrayList<Blob> blobList){
        for (Blob b : blobList){
            Imgproc.rectangle(canvas, b.getMinCorner(), b.getMaxCorner(), drawColor);

        }
    }
}
