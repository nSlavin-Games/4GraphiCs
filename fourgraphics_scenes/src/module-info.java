module fourgraphics.scenes {
    requires org.lwjgl;
    requires org.lwjgl.opengl;
    requires org.lwjgl.glfw;
    requires transitive org.lwjgl.glfw.natives;
    requires transitive org.lwjgl.natives;
    requires transitive org.lwjgl.opengl.natives;
    exports com.fourgraphics.scenes;
}