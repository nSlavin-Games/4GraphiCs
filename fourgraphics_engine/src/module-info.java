module fourgraphics {
    //transitive stuff
    requires transitive fourgraphics.scenes;
    requires transitive fourgraphics.logging;
    requires transitive fourgraphics.math;
    requires transitive fourgraphics.objects;
    requires transitive com.google.common;
    requires transitive guava;

    //internal intransitive stuff
    requires fourgraphics.logging.internal;
    exports com.fourgraphics;
}