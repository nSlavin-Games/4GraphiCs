package com.fourgraphics;

import com.fourgraphics.logging.DebugConsole;

public class FGCApp
{
    public FGCApp()
    {

    }

    public void finalize()
    {

    }

    public void Run()
    {
        DebugConsole.LaunchConsole();
        DebugConsole.Info("Initialized Logger");
        while(true);
    }
}
