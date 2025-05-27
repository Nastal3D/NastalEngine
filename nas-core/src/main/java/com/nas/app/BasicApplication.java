package com.nas.app;

import com.nas.render.Render;
import com.nas.scene.Scene;
import com.nas.window.Window;

public interface BasicApplication {
    void cleanup();

    public void init(Window window, Scene scene);

    public void update(Window window, Scene scene);

    public void render(Window window, Render render);
}
