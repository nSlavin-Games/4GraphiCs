package com.fourgraphics;

import static java.lang.Thread.*;

import com.fourgraphics.logging.DebugConsole;
import com.fourgraphics.logging.internal.DebugConsoleInternal;
import com.fourgraphics.scenes.SceneManager;

public class FGCApp
{
    public FGCApp()
    {

    }

    //NOTE(samu):da implementare il cleaner, non usare Object.finalize()!

    public void Run()
    {
        DebugConsole.LaunchConsole();
        DebugConsole.Info("Initialized Logger");
        DebugConsoleInternal.InfoInternal("Initialized Logger", "Have fun debugging!", currentThread().getStackTrace());
        SceneManager.Run();
        while(true);
    }
}
