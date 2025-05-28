package test;

import com.nas.window.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(1270, 720, "Debug");
        window.init();

        while(!window.closed()) {
            window.update();
            window.swapBuffers();
        }
    }
}