package com.nas.utils;

import com.nas.app.BasicApplication;
import com.nas.render.Render;
import com.nas.scene.Scene;
import com.nas.window.Window;

public class Nastal {
    private int width = 1270, height = 720, fps = 60;

    private Window window;
    private Scene scene;
    private Render render;

    private BasicApplication application;

    public Nastal(String title, BasicApplication application) {
        this.application = application;
        window = new Window(width, height, fps, title);
        window.init();
        window.initVulkan();
    }

    public void start() {
        render = new Render();
        scene = new Scene();

        application.init(window, scene, render);

        while (!window.closed()) {
            if(window.isUpdating()) {
                window.update();
                application.update(window, scene, render);
                window.swapBuffers();
            }
        }
        window.stop();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }
}
