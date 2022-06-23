import Processing.Renderer;
import org.opencv.core.Core;
import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class Run {
    public static void main(String[] args) {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        System.out.println("Init OpenCV " + Core.VERSION);

        Renderer renderer = new Renderer();
        renderer.displayToConsole();
        renderer.displayToWindow();


    }
}
