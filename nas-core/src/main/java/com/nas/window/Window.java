package com.nas.window;

import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private int width, height;
    private String title;

    private boolean closed;
    private double fps_cap, time, processedTime = 0;

    private long window;

    public Window(int width, int height, int fps, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        this.fps_cap = fps;
    }

    public void init() {
        closed = false;

        if(!glfwInit()) {
            throw new RuntimeException("ERROR: glfw could not be initialized");
        }
        window = glfwCreateWindow(width, height, title, 0, 0);
        if(window == 0) {
            throw new RuntimeException("ERROR: window could not be created");
        }
        glfwMakeContextCurrent(window);

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

        glfwShowWindow(window);
    }

    public void update() {
        glfwPollEvents();
    }

    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public double getTime() {
        return (double) System.nanoTime() / (double) 1000000000;
    }

    public boolean closed() {
        return glfwWindowShouldClose(window);
    }

    public void stop() {
        glfwTerminate();
        closed = true;
    }

    public boolean isUpdating() {
        if (!closed) {
            double nextTime = getTime();
            double passedTime = nextTime - time;
            processedTime += passedTime;
            time = nextTime;

            while (processedTime > 1.0/fps_cap) {
                processedTime -= 1.0/fps_cap;
                return true;
            }
        }
        return false;
    }
}
