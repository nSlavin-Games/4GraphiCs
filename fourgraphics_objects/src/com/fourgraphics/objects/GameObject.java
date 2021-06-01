package com.fourgraphics.objects;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Random;

public class GameObject {
    public Multimap<Class<? extends Component>, Component> components;
    public Transform transform;
    public String name;


    /**
     * @deprecated Temporary GameObject Creator, try to avoid using it!
     */
    @Deprecated
    public GameObject(){
        name = new Random().ints(97, 123)
                .limit(16)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        transform = new Transform();
        components = ArrayListMultimap.create();
    }

    public GameObject(Multimap<Class<? extends Component>, Component> components, String name){
        this.components = components;   //TODO(samue): use addComponent
        this.name = name;
    }
    public GameObject(Multimap<Class<? extends Component>, Component> components, String name, Transform transform){
        this.components = components;   //TODO(samue): use addComponent
        this.name = name;
        this.transform = transform;
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(Class<T> component) {
        Collection<Component> values = components.get(component);
        return (T) components.get(component).iterator().next();
    }
}
