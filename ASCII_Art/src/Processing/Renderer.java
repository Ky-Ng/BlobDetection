package Processing;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Renderer {
    Mat imgSource;
    Mat resize;
    double scale;
    Processer processer;

    public Renderer(String AssetsFolder, String ImagePath, double scale, String ASCII_ColorScale){
        this.imgSource = Imgcodecs.imread(AssetsFolder+ImagePath);
        this.resize = imgSource.clone();
        this.scale = scale;

        Imgproc.resize(imgSource, resize, scaleSize(imgSource));
        this.processer = new Processer(resize, ASCII_ColorScale);
    }

    public void display(){
//        HighGui.imshow("ASCII Art", imgSource);
        HighGui.imshow("Gray", processer.getGrayMat());
        HighGui.imshow("Rescaled Art", resize);
        HighGui.waitKey(0);
    }

    Size scaleSize(Mat input){
        return new Size(input.size().width * scale, input.size().height * scale);
    }

    public Processer getProcesser() {
        return processer;
    }
}
