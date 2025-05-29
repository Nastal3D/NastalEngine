package com.nas.window;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWVulkan;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.*;

import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK13.*;

import static java.awt.event.KeyEvent.VK_S;
import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private int width, height;
    private String title;

    private boolean closed;
    private double fps_cap, time, processedTime = 0;

    private long window;
    private VkInstance instance;
    private long surface;
    private VkPhysicalDevice physicalDevice;
    private VkDevice device;
    private VkQueue graphicsQueue;

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

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);

        window = glfwCreateWindow(width, height, title, 0, 0);
        if(window == 0) {
            throw new RuntimeException("ERROR: window could not be created");
        }

        glfwMakeContextCurrent(window);

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

        glfwShowWindow(window);
    }

    public void initVulkan() {
        createInstance("Application");
        createSurface();
    }

    private void createInstance(String appName) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            VkApplicationInfo applicationInfo = VkApplicationInfo.calloc(stack)
                    .sType(VK_STRUCTURE_TYPE_APPLICATION_INFO)
                    .pApplicationName(stack.UTF8(appName))
                    .applicationVersion(VK_MAKE_VERSION(1, 0, 0))
                    .pEngineName(stack.UTF8("NastalEngine"))
                    .engineVersion(VK_MAKE_VERSION(0, 1, 3))
                    .apiVersion(VK_API_VERSION_1_0);

            if(GLFWVulkan.glfwGetRequiredInstanceExtensions() == null) {
                throw new IllegalStateException("ERROR: could not find required Vulkan extensions");
            }

            VkInstanceCreateInfo createInfo = VkInstanceCreateInfo.calloc(stack)
                    .sType(VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO)
                    .pApplicationInfo(applicationInfo)
                    .ppEnabledExtensionNames(GLFWVulkan.glfwGetRequiredInstanceExtensions())
                    .ppEnabledLayerNames(null);

            PointerBuffer pointerInstance = stack.mallocPointer(1);

            if(vkCreateInstance(createInfo, null, pointerInstance) != VK_SUCCESS) {
                throw new RuntimeException("ERROR: Failed to create instance");
            }

            instance = new VkInstance(pointerInstance.get(0), createInfo);
        }
    }

    public void createSurface() {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            LongBuffer pointerSurface = stack.mallocLong(1);

            if(GLFWVulkan.glfwCreateWindowSurface(instance, window, null, pointerSurface) != VK_SUCCESS) {
                throw new RuntimeException("ERROR: could not create window surface");
            }

            surface = pointerSurface.get(0);
        }
    }

    //TODO: Finish this code

    public void update() {
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);

        glfwGetWindowSize(window, widthBuffer, heightBuffer);

        width = widthBuffer.get(0);
        height = heightBuffer.get(0);

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getWindow() {
        return window;
    }
}
