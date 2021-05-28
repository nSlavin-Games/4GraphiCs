package com.fourgraphics;

import com.fourgraphics.DebugConsole;

import java.lang.reflect.Method;

public class Utils
{
    public static Method getMethodInCurrentClass(String methodName)
    {
        try
        {
            Class callingClass = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
            return callingClass.getDeclaredMethod(methodName);
        } catch(Exception e)
        {
            DebugConsole.ErrorInternal("Method Retrieval Error", "There has been an error getting the specified method [" + methodName + "]. Make sure the spelling is correct", e.getStackTrace(), e.getStackTrace());
        }
        return null;
    }
}
