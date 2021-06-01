package com.fourgraphics.scenes;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class SceneManager
{
    private static long window;

    private static float deltaTime;
    private static float time;

    public static void Run()
    {
        Start();
        Update();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private static void Start()
    {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if(!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        //Configure GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); //window hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); //window resizable

        //Creating the window
        window = glfwCreateWindow(1280, 720, "4GC Project", NULL, NULL);
        if(window == NULL)
            throw new RuntimeException("Failed to create GLFW Window");

        //setup key callback
        glfwSetKeyCallback(window, (window,key,scancode,action,mods) ->
        {
            if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window,true);
        });

        //Get thread stack and push new frame
        try(MemoryStack stack = stackPush())
        {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            //Get window size passed to the create window method
            glfwGetWindowSize(window, pWidth, pHeight);

            //Get resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            //Center window on primary monitor
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0))/2,
                    (vidmode.height() - pHeight.get(0))/2
            );
        } //popped automatically

        //Temporary OpenGL setup, will become Vulkan later
        glfwMakeContextCurrent(window);
        //Enable v-sync
        glfwSwapInterval(1);

        //Show window since setup is finished
        glfwShowWindow(window);
    }

    private static void Update()
    {
        //Actual game loop
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        //set the clear color
        glClearColor(0.176f, 0.286f, 0.462f,0.0f);

        //Run loop
        while(!glfwWindowShouldClose(window))
        {
            float lastTime = time;
            time = System.currentTimeMillis();
            deltaTime = (time-lastTime)/1000f;

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glfwSwapBuffers(window);

            glfwPollEvents();
        }
    }

    public static float DeltaTime()
    {
        return deltaTime;
    }
}
