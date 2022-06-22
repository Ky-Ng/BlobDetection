import Processing.Blob;
import Processing.Parameters;
import Processing.Util.BlobsUtil;
import Processing.Util.Drawer;
import Processing.Util.Pixel;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class Pipeline {
    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);

        //create image
        Mat imgSource = Imgcodecs.imread(Parameters.AssetsFolder + Parameters.ImageName);
        Mat blobProcess = imgSource.clone();
        Mat prePrune = imgSource.clone();
        Mat postPrune = imgSource.clone();

        // process image
        ArrayList<Blob> blobs = new ArrayList<>();

        for (int x = 0; x < imgSource.cols(); x += Parameters.Decimation) {
            for (int y = 0; y < imgSource.rows(); y+= Parameters.Decimation) {
                System.out.println("x: " + x + "| y: " + y);
                Pixel pixel = new Pixel(x,y, imgSource.get(y,x));

                if (blobs.isEmpty()){
                    System.out.println("empty blob list " + x);
                    if (BlobsUtil.getColorDistance(pixel.colorRGB, Parameters.TargetRGB) < Parameters.ColorThreshold){
                        blobs.add(new Blob(pixel));
                        System.out.println("First Blob Found");
                    }
                }

                for (int i = 0; i < blobs.size(); i++){
                    if (blobs.get(i).isInColorRange(pixel)){
                        if (blobs.get(i).isNear(pixel)){
                            blobs.get(i).addPixel(pixel);
                            System.out.println("Added pixel, XY: " + x + ", " + y + " to a blob");
                            break;
                        } else{
                            blobs.add(new Blob(pixel));
                            System.out.println("Added new Blob");
                        }
                    }
                }
                Imgproc.rectangle(blobProcess, new Point(x, y), new Point(x+3, y+3), new Scalar(150,30,150));
                Drawer.drawBlobs(blobProcess, blobs);


            }
        }

        //display image
        Drawer.drawBlobs(prePrune, blobs);

        BlobsUtil.pruneBlobs(blobs);
        Drawer.drawBlobs(postPrune, blobs);

        BlobsUtil.printDistance(blobs);
        BlobsUtil.printArea(blobs);

        HighGui.imshow("1 Source", imgSource);
        HighGui.imshow("2 Processing", blobProcess);
        HighGui.imshow("3 Pre Prune", prePrune);
        HighGui.imshow("4 Post Prune", postPrune);
        for (Blob b : blobs){
            System.out.println(b.getMinCorner() +""+ b.getMaxCorner());
        }
        HighGui.waitKey(0);
    }
}
