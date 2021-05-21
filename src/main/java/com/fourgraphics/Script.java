package com.fourgraphics;

import processing.core.PApplet;

public class Script {

    public GameObject gameObject; //l’oggetto a cui è attaccato questo componente
    public Transform transform; //la posizione e la dimensione dell’oggetto a cui è attaccato questo componente
    protected PApplet sketch; //l'app principale

    public Script() {
        sketch = SceneManager.getApp();
    }

    public void Start() {
    } //il metodo che viene eseguito al caricamento della scena

    public void Update() {
    } //il metodo che viene eseguito ogni frame

    public void FixedUpdate() {
    } //metodo di update eseguito ogni tot tempo preciso

    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction) {
    } //eseguito quando l’oggetto entra in collisione con qualcosa
}