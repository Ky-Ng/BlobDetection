import Processing.Blob;
import Processing.Util.Distance;
import Processing.Util.Drawer;
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
            System.out.println("Rows: " + imgSource.rows() + " Cols: " + imgSource.cols());
        int xDebug = 11, yDebug = 383;
        System.out.println(
                "X " + xDebug  + " Y " + yDebug
                        + " BGR " + (int)imgSource.get(xDebug,yDebug)[0] + ", " + (int)imgSource.get(xDebug,yDebug)[1] + ", " + (int)imgSource.get(xDebug,yDebug)[2]
            + " RGB " + (int)imgSource.get(xDebug,yDebug)[2] + ", " + (int)imgSource.get(xDebug,yDebug)[1] + ", " + (int)imgSource.get(xDebug,yDebug)[0]
            );
        for (int x = 0; x < imgSource.rows(); x +=30) {
            for (int y = 0; y < imgSource.cols(); y+=30) {
//                System.out.println("x: " + x + "| y: " + y);
                Pixel pixel = new Pixel(x,y, imgSource.get(x,y));

                if (blobs.isEmpty()){
                    System.out.println("empty blob list" + x);
                    if (Distance.getColorDistance(pixel.colorRGB, targetRGB) < Blob.colorThreshold){
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
            }
        }



        //display image
        Drawer.drawBLobs(imgSource, blobs);
        HighGui.imshow("Source Image", imgSource);
        HighGui.waitKey(0);
    }
}
