package com.nas.window;

import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private int width, height;
    private String title;

    private long window;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void init() {
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

    public boolean closed() {
        return glfwWindowShouldClose(window);
    }
}
