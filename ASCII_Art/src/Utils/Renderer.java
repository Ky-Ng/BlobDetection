package Utils;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.lang.reflect.Parameter;

public class Renderer {
    Mat imgSource;
    Mat resize;
    double scale;

    public Renderer(String AssetsFolder, String ImagePath, double scale){
        imgSource = Imgcodecs.imread(AssetsFolder+ImagePath);
        resize = imgSource.clone();
        System.out.println("Img Source Size " + imgSource.size());
        this.scale = scale;
    }

    public void display(){
        Imgproc.resize(imgSource, resize, scale(imgSource.size()));
        System.out.println("Resize Size " + resize.size());
//        HighGui.imshow("ASCII Art", imgSource);
        HighGui.imshow("Rescaled Art", resize);
        HighGui.waitKey(0);
    }

    Size scale(Size inputSize){
        return new Size(inputSize.width * scale, inputSize.height * scale);
    }
}
