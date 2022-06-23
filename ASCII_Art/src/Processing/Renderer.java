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

    public Renderer(String AssetsFolder, String ImagePath, double scale){
        imgSource = Imgcodecs.imread(AssetsFolder+ImagePath);
        resize = imgSource.clone();
//        System.out.println("Img Source Size " + imgSource.size());
        this.scale = scale;
        Imgproc.resize(imgSource, resize, scaleSize(imgSource));
        this.processer = new Processer(resize);

    }

    public void display(){
        System.out.println("Resize Size " + resize.size());
//        HighGui.imshow("ASCII Art", imgSource);

        HighGui.imshow("Gray", processer.getGray());
        HighGui.imshow("Rescaled Art", resize);
        HighGui.waitKey(0);
    }

    Size scaleSize(Mat input){
        return new Size(input.size().width * scale, input.size().height * scale);
    }
}
