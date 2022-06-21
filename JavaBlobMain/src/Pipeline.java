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

        for (int x = 0; x < imgSource.cols(); x +=30) {
            for (int y = 0; y < imgSource.rows(); y+=30) {
                System.out.println("x: " + x + "| y: " + y);
                Pixel pixel = new Pixel(x,y, imgSource.get(y,x));

                if (blobs.isEmpty()){
                    System.out.println("empty blob list " + x);
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
                Imgproc.rectangle(imgSource, new Point(x, y), new Point(x+3, y+3), new Scalar(150,30,150));
//                Drawer.drawBLobs(imgSource, blobs);
                HighGui.imshow("Source Image", imgSource);
                HighGui.waitKey(30);
            }
        }

        //display image
        Drawer.drawBLobs(imgSource, blobs);
        Distance.printDistance(blobs);
        HighGui.imshow("Source Image", imgSource);
        HighGui.waitKey(0);
    }
}
