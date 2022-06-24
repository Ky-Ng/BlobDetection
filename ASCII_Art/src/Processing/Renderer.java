package Processing;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Renderer {
    Mat imgSource;
    Mat resize;
    Mat ASCII_art;
    Processor processor;

    public Renderer(){
        this.imgSource = Imgcodecs.imread(Parameters.AssetsFolder+Parameters.Image);
        this.resize = imgSource.clone();
        this.ASCII_art = imgSource.clone();

        Imgproc.rectangle(ASCII_art, new Point(0,0), new Point(ASCII_art.width(), ASCII_art.height()), new Scalar(255,255,255),-1);

        Imgproc.resize(imgSource, resize, scaleSize(imgSource));
        this.processor = new Processor(resize);
    }

    // in a 950x540 canvas, you can fit 180 "$" cols and 50 rows spaced 10 pixels apart
    public void displayToWindow(){
        // TODO: 6/23/22 divide the canvas into separate parts
//        String toDisplay = "";
//        for (int i = 0; i <  50; i ++){
//            if (i + 180 + (180*i) < processor.ASCII.length()) {
//                toDisplay = processor.ASCII.substring(i + (180 * i), i + 180 + (180 * i));
//                Imgproc.putText(ASCII_art, toDisplay, new Point(10, 10 + i * 10), Imgproc.FONT_HERSHEY_PLAIN, 0.5, new Scalar(0, 0, 0));
////            for (int j = 0; j<50; j++){
////
////            }
//            }
//          }
        processor.convertImageToASCII();
//        String toDisplay = "";
        String ASCII_copy = processor.getASCII();
        int start = 0, breakLineCharIndex;
        int row = 0;
        System.out.println(ASCII_copy);
        String[] ASCII_by_row = ASCII_copy.split("\n");
        System.out.println(ASCII_by_row.length);
        for (String toDisplay : ASCII_by_row){
//            System.out.println(str);
            Imgproc.putText(ASCII_art, toDisplay, new Point(10, 10 + row * 10), Imgproc.FONT_HERSHEY_PLAIN, 0.5, new Scalar(0, 0, 0));
            row++;
        }
        System.out.println("Done");

//        while (ASCII_copy.contains("\n")){
//            System.out.println("ASCII copy" + ASCII_copy);
//            breakLineCharIndex = ASCII_copy.indexOf("\n");
//            System.out.println("Break Line Char Index " + breakLineCharIndex);
//            toDisplay = ASCII_copy.substring(start, breakLineCharIndex);
//
//            System.out.println("Start " + start + " Break Line Char Index " + breakLineCharIndex + " row " + row + " | to Display " + toDisplay);
//
//            Imgproc.putText(ASCII_art, toDisplay, new Point(10, 10 + row * 10), Imgproc.FONT_HERSHEY_PLAIN, 0.5, new Scalar(0, 0, 0));
//
//
//
//            ASCII_copy = processor.getASCII().substring(breakLineCharIndex);
//            start = breakLineCharIndex;
//            row++;
//        }


        HighGui.imshow("ASCII Art ", ASCII_art);
        HighGui.imshow("Gray", processor.getGrayMat());
        HighGui.imshow("Rescaled Source", resize);
        HighGui.waitKey(0);
    }

    // in a 950x540 canvas, you can fit 180 "$" cols and 50 rows spaced 10 pixels apart
    public void debugWindowSize(){
        System.out.println("Canvas size "+ ASCII_art.size());
        String s = "";
        for (int i = 0; i <  180; i++){
            s+="$";
        }
        for (int j = 0; j<50; j++){
            Imgproc.putText(ASCII_art, s, new Point(10,10 + j*10), Imgproc.FONT_HERSHEY_PLAIN,0.5, new Scalar(0,0,0));
        }

        HighGui.imshow("ASCII Art ", ASCII_art);
        HighGui.waitKey(0);
    }

    public void displayToConsole(){
        processor.convertImageToASCII();
        System.out.println(processor.ASCII);
    }

    Size scaleSize(Mat input){
        return new Size(input.size().width * Parameters.Scale, input.size().height * Parameters.Scale);
    }

    public Processor getProcessor() {
        return processor;
    }
}
