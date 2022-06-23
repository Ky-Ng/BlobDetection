package Processing;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Renderer {
    Mat imgSource;
    Mat resize;
    Processor processor;

    public Renderer(){
        this.imgSource = Imgcodecs.imread(Parameters.AssetsFolder+Parameters.Image);
        this.resize = imgSource.clone();

        Imgproc.resize(imgSource, resize, scaleSize(imgSource));
        this.processor = new Processor(resize);
    }

    public void displayToWindow(){
//        HighGui.imshow("ASCII Art", imgSource);
        HighGui.imshow("Gray", processor.getGrayMat());
        HighGui.imshow("Rescaled Art", resize);
        HighGui.waitKey(0);
    }

    public void displayToConsole(){
        System.out.println(
                processor.getImageInASCII()
        );
    }

    Size scaleSize(Mat input){
        return new Size(input.size().width * Parameters.Scale, input.size().height * Parameters.Scale);
    }

    public Processor getProcessor() {
        return processor;
    }
}
