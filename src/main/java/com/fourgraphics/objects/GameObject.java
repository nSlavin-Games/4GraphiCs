package com.fourgraphics.objects;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameObject
{
    public Multimap<Class<? extends Component>,Component> components = ArrayListMultimap.create();
    public Transform transform;

    public <T extends Component> T GetComponent(Class<T> component)
    {
        Collection<Component> values = components.get(component);
        return (T)components.get(component).iterator().next();
    }
}
