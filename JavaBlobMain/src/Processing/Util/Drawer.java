package Processing.Util;

import Processing.Blob;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class Drawer {
    Scalar drawColor = new Scalar(130,130,130);

    public Drawer (Mat canvas, ArrayList<Blob> blobList){
        for (Blob b : blobList){
            Imgproc.rectangle(canvas, b.getMinCorner(), b.getMinCorner(), drawColor);
        }
    }
}
