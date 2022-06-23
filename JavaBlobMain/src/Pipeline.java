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
import java.util.Scanner;

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

        Point textDisplayPoint = new Point(10,440);
        Scalar textColor = new Scalar(0,0,0);
        int font = Imgproc.FONT_HERSHEY_PLAIN;
        double fontSize = 3.0;
        int fontThickness = 3;

        // process image
        ArrayList<Blob> blobs = new ArrayList<>();

        Imgproc.putText(blobProcess, "1. Find Blobs", textDisplayPoint, font, fontSize, textColor,fontThickness);
        HighGui.imshow("Demo", blobProcess);
        HighGui.waitKey(1500);

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
//                Imgproc.rectangle(blobProcess, new Point(x, y), new Point(x+3, y+3), new Scalar(150,30,150), 6);
                Drawer.drawBlobs(blobProcess, blobs);
                HighGui.imshow("Demo", blobProcess);
                HighGui.waitKey(15);
            }
        }

        // reset display to source
        HighGui.imshow("Demo", imgSource);
        HighGui.waitKey(300);

        // show the blobs created

        Imgproc.putText(prePrune, "2. Merge/Prune Blobs", textDisplayPoint, font, fontSize, textColor,fontThickness);
        HighGui.imshow("Demo", prePrune);
        HighGui.waitKey(1700);
        Drawer.drawBlobs(prePrune, blobs);

        HighGui.imshow("Demo", prePrune);
        HighGui.waitKey(1700);

        // show pruned blobs/final
        Imgproc.putText(imgSource, "3. Final Bounding Boxes", textDisplayPoint, font, fontSize, textColor,fontThickness);
        HighGui.imshow("Demo", imgSource);
        HighGui.waitKey(900);

        BlobsUtil.pruneBlobs(blobs);
        Drawer.drawBlobs(postPrune, blobs);



        Imgproc.putText(postPrune, "3. Final Bounding Boxes", textDisplayPoint, font, fontSize, textColor,fontThickness);
        HighGui.imshow("Demo", postPrune);
        HighGui.waitKey(1700);

        HighGui.imshow("Demo", postPrune);
        HighGui.waitKey(0);
    }
}
