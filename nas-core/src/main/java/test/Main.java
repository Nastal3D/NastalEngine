package test;

import com.nas.app.BasicApplication;
import com.nas.render.Render;
import com.nas.scene.Scene;
import com.nas.utils.Nastal;
import com.nas.window.Window;

public class Main implements BasicApplication {
    public static void main(String[] args) {
        Main app = new Main();
        Nastal nas = new Nastal("Debug", app); // new Nastal(title, application);
        nas.start();
    }

    @Override
    public void init(Window window, Scene scene, Render render) {

    }

    @Override
    public void update(Window window, Scene scene, Render render) {

    }

    @Override
    public void render(Scene scene, Render render) {

    }

    @Override
    public void input(Window window, Scene scene) {

    }
}