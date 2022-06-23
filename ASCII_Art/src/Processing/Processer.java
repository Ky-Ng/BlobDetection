package Processing;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Processer {
    Mat gray;

    public Processer(Mat BGR){
        this.gray = BGR.clone();
        Imgproc.cvtColor(BGR, gray, Imgproc.COLOR_BGR2GRAY);
    }

    public Mat getGray() {
        return gray;
    }
}
