package com.fourgraphics;

import java.util.Map;

public class Input {

    private static InputManager manager;

    protected static void setup(InputManager manager)
    {
        Input.manager = manager;
    }

    public static boolean getKeyDown(int keyCode)
    {
        char k = Character.toUpperCase((char)keyCode);
        if(manager.keys.containsKey((int)k)) return manager.keys.get((int)k) == 1;
        return false;
    }

    public static boolean getKeyDown(String keyCode)
    {
        if(keyCode.length() == 1)
        {
            try{
                int value = Integer.parseInt(keyCode);
                if(manager.keys.containsKey(value+48)) return manager.keys.get(value+48) == 1;
            } catch (NumberFormatException nfe)
            {
                char k = keyCode.toUpperCase().charAt(0);
                if(manager.keys.containsKey((int)k)) return manager.keys.get((int)k) == 1;
            }
        } else
        {
            if(keyCode.equalsIgnoreCase("space")) if(manager.keys.containsKey(32)) return manager.keys.get(32) == 1;
            if(keyCode.equalsIgnoreCase("tab")) if(manager.keys.containsKey(9)) return manager.keys.get(9) == 1;
            if(keyCode.equalsIgnoreCase("esc")) if(manager.keys.containsKey(27)) return manager.keys.get(27) == 1;
            if(keyCode.equalsIgnoreCase("enter")) if(manager.keys.containsKey(10)) return manager.keys.get(10) == 1;

            //SHIFT
            if(keyCode.equalsIgnoreCase("shift")) if(manager.keys.containsKey(16)) return manager.keys.get(16) == 1;

            //CONTROL
            if(keyCode.equalsIgnoreCase("ctrl")) if(manager.keys.containsKey(17)) return manager.keys.get(17) == 1;

            //ALT
            if(keyCode.equalsIgnoreCase("alt")) if(manager.keys.containsKey(18)) return manager.keys.get(18) == 1;

            //ARROWS
            if(keyCode.equalsIgnoreCase("left")) if(manager.keys.containsKey(37)) return manager.keys.get(37) == 1;
            if(keyCode.equalsIgnoreCase("right")) if(manager.keys.containsKey(39)) return manager.keys.get(39) == 1;
            if(keyCode.equalsIgnoreCase("up")) if(manager.keys.containsKey(38)) return manager.keys.get(38) == 1;
            if(keyCode.equalsIgnoreCase("down")) if(manager.keys.containsKey(40)) return manager.keys.get(40) == 1;
        }
        return false;
    }

    public static boolean getKey(int keyCode)
    {
        char k = Character.toUpperCase((char)keyCode);
        if(manager.keys.containsKey((int)k)) return manager.keys.get((int)k) == 2;
        return false;
    }

    public static boolean getKey(String keyCode)
    {
        if(keyCode.length() == 1)
        {
            boolean isNumber = false;
            try{
                int value = Integer.parseInt(keyCode);
                isNumber = true;
                if(manager.keys.containsKey(value+48)) return manager.keys.get(value+48) == 2;
            } catch (NumberFormatException nfe)
            {
                char k = keyCode.toUpperCase().charAt(0);
                if(manager.keys.containsKey((int)k)) return manager.keys.get((int)k) == 2;
            }
        } else
        {
            if(keyCode.equalsIgnoreCase("space")) if(manager.keys.containsKey(32)) return manager.keys.get(32) == 2;
            if(keyCode.equalsIgnoreCase("tab")) if(manager.keys.containsKey(9)) return manager.keys.get(9) == 2;
            if(keyCode.equalsIgnoreCase("esc")) if(manager.keys.containsKey(27)) return manager.keys.get(27) == 2;
            if(keyCode.equalsIgnoreCase("enter")) if(manager.keys.containsKey(10)) return manager.keys.get(10) == 2;

            //SHIFT
            if(keyCode.equalsIgnoreCase("shift")) if(manager.keys.containsKey(16)) return manager.keys.get(16) == 2;

            //CONTROL
            if(keyCode.equalsIgnoreCase("ctrl")) if(manager.keys.containsKey(17)) return manager.keys.get(17) == 2;

            //ALT
            if(keyCode.equalsIgnoreCase("alt")) if(manager.keys.containsKey(18)) return manager.keys.get(18) == 2;

            //ARROWS
            if(keyCode.equalsIgnoreCase("left")) if(manager.keys.containsKey(37)) return manager.keys.get(37) == 2;
            if(keyCode.equalsIgnoreCase("right")) if(manager.keys.containsKey(39)) return manager.keys.get(39) == 2;
            if(keyCode.equalsIgnoreCase("up")) if(manager.keys.containsKey(38)) return manager.keys.get(38) == 2;
            if(keyCode.equalsIgnoreCase("down")) if(manager.keys.containsKey(40)) return manager.keys.get(40) == 2;
        }
        return false;
    }

    public static boolean getKeyUp(int keyCode)
    {
        char k = Character.toUpperCase((char)keyCode);
        if(manager.keys.containsKey((int)k)) return manager.keys.get((int)k) == 3;
        return false;
    }

    public static boolean getKeyUp(String keyCode)
    {
        if(keyCode.length() == 1)
        {
            boolean isNumber = false;
            try{
                int value = Integer.parseInt(keyCode);
                isNumber = true;
                if(manager.keys.containsKey(value+48)) return manager.keys.get(value+48) == 3;
            } catch (NumberFormatException nfe)
            {
                char k = keyCode.toUpperCase().charAt(0);
                if(manager.keys.containsKey((int)k)) return manager.keys.get((int)k) == 3;
            }
        } else
        {
            if(keyCode.equalsIgnoreCase("space")) if(manager.keys.containsKey(32)) return manager.keys.get(32) == 3;
            if(keyCode.equalsIgnoreCase("tab")) if(manager.keys.containsKey(9)) return manager.keys.get(9) == 3;
            if(keyCode.equalsIgnoreCase("esc")) if(manager.keys.containsKey(27)) return manager.keys.get(27) == 3;
            if(keyCode.equalsIgnoreCase("enter")) if(manager.keys.containsKey(10)) return manager.keys.get(10) == 3;

            //SHIFT
            if(keyCode.equalsIgnoreCase("shift")) if(manager.keys.containsKey(16)) return manager.keys.get(16) == 3;

            //CONTROL
            if(keyCode.equalsIgnoreCase("ctrl")) if(manager.keys.containsKey(17)) return manager.keys.get(17) == 3;

            //ALT
            if(keyCode.equalsIgnoreCase("alt")) if(manager.keys.containsKey(18)) return manager.keys.get(18) == 3;

            //ARROWS
            if(keyCode.equalsIgnoreCase("left")) if(manager.keys.containsKey(37)) return manager.keys.get(37) == 3;
            if(keyCode.equalsIgnoreCase("right")) if(manager.keys.containsKey(39)) return manager.keys.get(39) == 3;
            if(keyCode.equalsIgnoreCase("up")) if(manager.keys.containsKey(38)) return manager.keys.get(38) == 3;
            if(keyCode.equalsIgnoreCase("down")) if(manager.keys.containsKey(40)) return manager.keys.get(40) == 3;
        }
        return false;
    }

    public static void createAxis(String name, String a, String b, String c, String d)
    {
        manager.createAxis(name,a,b,c,d);
    }

    public static void createButton(String name, String a, String b)
    {
        manager.createButton(name,a,b);
    }

    public static float getAxis(String axis)
    {
        if(manager.axes.containsKey(axis))
        {
            float axisValue = 0;
            String positive = manager.axes.get(axis).positive;
            String negative = manager.axes.get(axis).negative;
            String altPositive = manager.axes.get(axis).altPositive;
            String altNegative = manager.axes.get(axis).altNegative;
            if(manager.gamepad != null)
            {
                boolean gp = positive.contains("gamepad");
                boolean gn = negative.contains("gamepad");
                boolean gap = altPositive.contains("gamepad");
                boolean gan = altNegative.contains("gamepad");

                float posValue = gp ? manager.gamepad.getSlider(positive).getValue() : 0;
                float negValue = gn ? manager.gamepad.getSlider(negative).getValue() : 0;
                float altPosValue = gap ? manager.gamepad.getSlider(altPositive).getValue() : 0;
                float altNegValue = gan ? manager.gamepad.getSlider(altNegative).getValue() : 0;

                int pos = gp ? 0 : manager.axes.get(axis).axisToKey(positive);
                int neg = gn ? 0 : manager.axes.get(axis).axisToKey(negative);
                int altPos = gap ? 0 : manager.axes.get(axis).axisToKey(altPositive);
                int altNeg = gan ? 0 : manager.axes.get(axis).axisToKey(altNegative);

                float posTotal = gp ? posValue : pos > 0
                        ? manager.keys.containsKey(pos) ? 1 : 0 : 0;
                float negTotal = gn ? negValue : neg > 0
                        ? manager.keys.containsKey(neg) ? 1 : 0 : 0;
                float altPosTotal = gap ? altPosValue : altPos > 0
                        ? manager.keys.containsKey(altPos) ? 1 : 0 : 0;
                float altNegTotal = gan ? altNegValue : altNeg > 0
                        ? manager.keys.containsKey(altNeg) ? 1 : 0 : 0;

                axisValue = (posTotal - negTotal) + (altPosTotal - altNegTotal);
                axisValue = axisValue > 1 ? 1 : axisValue < -1 ? -1 : axisValue;
            } else {
                int pos = manager.axes.get(axis).axisToKey(positive);
                int neg = manager.axes.get(axis).axisToKey(negative);
                int altPos = manager.axes.get(axis).axisToKey(altPositive);
                int altNeg = manager.axes.get(axis).axisToKey(altNegative);

                axisValue = manager.keys.containsKey(pos)
                        || manager.keys.containsKey(altPos)
                        ? manager.keys.containsKey(neg)
                        || manager.keys.containsKey(altNeg) ? 0 : 1
                        : manager.keys.containsKey(neg)
                        || manager.keys.containsKey(altNeg) ? -1 : 0;
            }
            return axisValue;
        }
        return 0;
    }

    public static boolean getButtonDown(String button)
    {
        boolean returnValue = false;

        String main = manager.buttons.get(button).main;
        String alternative = manager.buttons.get(button).alt;
        if(manager.gamepad != null)
        {
            //System.out.print(m_manager.m_gamepadButtons.keySet());
            boolean gm = main.contains("Button");
            boolean ga = alternative.contains("Button");

            int km = gm ? 0 : manager.buttons.get(button).buttonToKey(main);
            int ka = ga ? 0 : manager.buttons.get(button).buttonToKey(alternative);

            boolean m = gm ? manager.gamepadButtons.containsKey(main) && manager.gamepadButtons.get(main) == 1
                    : getKeyDown(km);
            boolean a = ga ? manager.gamepadButtons.containsKey(alternative) && manager.gamepadButtons.get(alternative) == 1
                    : getKeyDown(ka);

            returnValue = m || a;
        } else {
            int m = manager.buttons.get(button).buttonToKey(main);
            int a = manager.buttons.get(button).buttonToKey(alternative);

            returnValue = getKeyDown(m)  || getKeyDown(a);
        }

        return returnValue;
    }

    public static boolean getButton(String button)
    {
        boolean returnValue = false;

        String main = manager.buttons.get(button).main;
        String alternative = manager.buttons.get(button).alt;
        if(manager.gamepad != null)
        {
            //System.out.print(m_manager.m_gamepadButtons.keySet());
            boolean gm = main.contains("Button");
            boolean ga = alternative.contains("Button");

            int km = gm ? 0 : manager.buttons.get(button).buttonToKey(main);
            int ka = ga ? 0 : manager.buttons.get(button).buttonToKey(alternative);

            boolean m = gm ? manager.gamepadButtons.containsKey(main) && manager.gamepadButtons.get(main) == 2
                    : getKey(km);
            boolean a = ga ? manager.gamepadButtons.containsKey(alternative) && manager.gamepadButtons.get(alternative) == 2
                    : getKey(ka);

            returnValue = m || a;
        } else {
            int m = manager.buttons.get(button).buttonToKey(main);
            int a = manager.buttons.get(button).buttonToKey(alternative);

            returnValue = getKey(m)  || getKey(a);
        }

        return returnValue;
    }

    public static boolean getButtonUp(String button)
    {
        boolean returnValue = false;

        String main = manager.buttons.get(button).main;
        String alternative = manager.buttons.get(button).alt;
        if(manager.gamepad != null)
        {
            //System.out.print(m_manager.m_gamepadButtons.keySet());
            boolean gm = main.contains("Button");
            boolean ga = alternative.contains("Button");

            int km = gm ? 0 : manager.buttons.get(button).buttonToKey(main);
            int ka = ga ? 0 : manager.buttons.get(button).buttonToKey(alternative);

            boolean m = gm ? manager.gamepadButtons.containsKey(main) && manager.gamepadButtons.get(main) == 3
                    : getKeyUp(km);
            boolean a = ga ? manager.gamepadButtons.containsKey(alternative) && manager.gamepadButtons.get(alternative) == 3
                    : getKeyUp(ka);

            returnValue = m || a;
        } else {
            int m = manager.buttons.get(button).buttonToKey(main);
            int a = manager.buttons.get(button).buttonToKey(alternative);

            returnValue = getKeyDown(m)  || getKeyDown(a);
        }

        return returnValue;
    }

    public static boolean getMouseButtonDown(int button)
    {
        if(manager.mouseButtons.containsKey(button)) return manager.mouseButtons.get(button) == 1;
        return false;
    }

    public static boolean getMouseButton(int button)
    {
        if(manager.mouseButtons.containsKey(button)) return manager.mouseButtons.get(button) == 2;
        return false;
    }

    public static boolean getMouseButtonUp(int button)
    {
        if(manager.mouseButtons.containsKey(button)) return manager.mouseButtons.get(button) == 3;
        return false;
    }

    protected static void updateKeyStatus()
    {
        for(Map.Entry<Integer,Integer> m : manager.keys.entrySet())
        {
            if(manager.keys.get(m.getKey()) == 1) manager.keys.put(m.getKey(),2);
        }

        for(Map.Entry<Integer,Integer> m : manager.mouseButtons.entrySet())
        {
            if(manager.mouseButtons.get(m.getKey()) == 1) manager.mouseButtons.put(m.getKey(),2);
        }

        if(manager.gamepad != null)
        {
            manager.setButtonPress();
            manager.setButtonRelease();
            manager.gamepadButtons.entrySet().removeIf(e -> e.getValue() == 4);
        }

        manager.keys.entrySet().removeIf(e -> e.getValue() == 3);
        manager.mouseButtons.entrySet().removeIf(e -> e.getValue() == 3);
    }
}
