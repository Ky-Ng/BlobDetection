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

    public void displayToWindow(){
//        HighGui.imshow("ASCII Art", imgSource);

        // TODO: 6/23/22 divide the canvas into separate parts
//        int
//        for (int i = 0; i <)
        Imgproc.putText(ASCII_art, processor.ASCII, new Point(10,10), Imgproc.FONT_HERSHEY_PLAIN,0.5, new Scalar(0,0,0));

        HighGui.imshow("ASCII Art ", ASCII_art);
//        HighGui.imshow("Gray", processor.getGrayMat());
//        HighGui.imshow("Rescaled Source", resize);
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
