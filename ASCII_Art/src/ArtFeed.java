import Processing.Processer;
import Processing.Renderer;
import org.opencv.core.Core;
import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class ArtFeed {
    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);

        Renderer renderer = new Renderer(Parameters.AssetsFolder, Parameters.Image, Parameters.Scale, Parameters.ASCII_ColorScale);
        renderer.getProcesser().convertToASCII();
        renderer.display();

        System.out.println(renderer.getProcesser().mapColorToASCII(127));

    }
}
