package Processing;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.awt.font.NumericShaper;
import java.lang.reflect.Parameter;

public class Processor {
    Mat gray;
    public Processor(Mat BGR){
        this.gray = BGR.clone();
        Imgproc.cvtColor(BGR, gray, Imgproc.COLOR_BGR2GRAY);
        System.out.println("Initialized Processor");
    }

    public Mat getGrayMat() {
        return gray;
    }

    public String convertToASCII(){
        String ret = "";
        for (int i = 0; i < gray.rows(); i+=Parameters.Decimation){
            for (int j = 0; j < gray.cols(); j+=Parameters.Decimation){
                ret += reverseMapColorToASCII(gray.get(i, j)[0]); // see OpenCVDocumentation.md
//                System.out.print(ret);
            }
            ret += "\n";
        }
        return ret;
    }

    public String mapColorToASCII(double grayShade){
        double darkness = grayShade / 255.0;
        int charIndex = (int) (Parameters.ASCII_ColorScale.length() * darkness);
        // if the charIndex is the very last (gray = 255 and darkness = 1.0) substring will return "" not " "
        charIndex = charIndex >= Parameters.ASCII_ColorScale.length() ? Parameters.ASCII_ColorScale.length() - 1 : charIndex;
        return Parameters.ASCII_ColorScale.substring(charIndex, charIndex+1);
    }
    public String reverseMapColorToASCII(double grayShade){
        double darkness = grayShade / 255.0;
        int charIndex = (int) (Parameters.ASCII_ColorScale.length() * darkness);
        // if the charIndex is the very first (black = 0 and darkness = 0), black needs to be " " substring will return "" not " "
        charIndex = charIndex <= 0 ? 1 : charIndex;
        return Parameters.ASCII_ColorScale.substring(
                Parameters.ASCII_ColorScale.length() - charIndex,
                Parameters.ASCII_ColorScale.length() - charIndex + 1
        );
    }
}
