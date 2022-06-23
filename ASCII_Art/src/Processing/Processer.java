package Processing;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.awt.font.NumericShaper;
import java.lang.reflect.Parameter;

public class Processer {
    Mat gray;
    String ASCII_ColorScale;
    public Processer(Mat BGR, String ASCII_ColorScale){
        this.ASCII_ColorScale = ASCII_ColorScale;
        this.gray = BGR.clone();
        Imgproc.cvtColor(BGR, gray, Imgproc.COLOR_BGR2GRAY);
        System.out.println("Initialized Processor");
    }

    public Mat getGrayMat() {
        return gray;
    }

    public String convertToASCII(){
        String ret = "";
        for (int i = 0; i < gray.rows(); i++){
            for (int j = 0; j < gray.cols(); j++){
                ret += mapColorToASCII(gray.get(i, j)[0]); // see OpenCVDocumentation.md
//                System.out.print(ret);
            }
            ret += "\n";
        }
        System.out.print(ret);
        return ret;
    }

    public String mapColorToASCII(double grayShade){
        double darkness = grayShade / 255.0;
        int charIndex = (int) (ASCII_ColorScale.length() * darkness);
        // if the charIndex is the very last (gray = 255 and darkness = 1.0) substring will return "" not " "
        charIndex = charIndex >= ASCII_ColorScale.length() ? ASCII_ColorScale.length() - 1 : charIndex;
        return ASCII_ColorScale.substring(charIndex, charIndex+1);
    }
}
