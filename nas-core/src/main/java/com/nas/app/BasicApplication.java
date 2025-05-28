package com.nas.app;

import com.nas.render.Render;
import com.nas.scene.Scene;
import com.nas.window.Window;

/**
 * {@inheritDoc}
* I made this project using Vulkan and lwjgl3
* To initialize the projects you go to void init and initialize whatever you want
 * If you want to code any game logic create a logic script
 *
 * @author mendwas
 */
public interface BasicApplication {
    public void init(Window window, Scene scene, Render render);

    public void update(Window window, Scene scene, Render render);

    public void render(Scene scene, Render render);

    public void input(Window window, Scene scene);
}
