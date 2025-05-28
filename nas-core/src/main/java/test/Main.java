package test;

import com.nas.app.BasicApplication;
import com.nas.render.Render;
import com.nas.scene.Scene;
import com.nas.utils.Nastal;
import com.nas.window.Window;

/**
 * I made this project using Vulkan and lwjgl3
 * To initialize the projects you go to void init and initialize whatever you want
 * If you want to code any game logic create a logic script
 *
 * @author mendwas
 */

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
}