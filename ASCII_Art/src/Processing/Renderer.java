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
        processor.convertImageToASCII();

        String ASCII_copy = processor.getASCII();
        String[] ASCII_by_row = ASCII_copy.split("\n");
        int row = 0;

        for (String toDisplay : ASCII_by_row){
            for (int col = 0; col < toDisplay.length(); col++) {
                Imgproc.putText(ASCII_art, toDisplay.substring(col, col+1), new Point(10 + col * 6, 10 + row * 10), Imgproc.FONT_HERSHEY_PLAIN, 0.5, new Scalar(0, 0, 0));
            }
            row++;
        }

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
