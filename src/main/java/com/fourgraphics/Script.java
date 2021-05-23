package com.fourgraphics;

import processing.core.PApplet;

public abstract class Script extends Component{

    public void Start() {
    } //il metodo che viene eseguito al caricamento della scena

    public void Update() {
    } //il metodo che viene eseguito ogni frame

    public void FixedUpdate() {
    } //metodo di update eseguito ogni tot tempo preciso

    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction) {
    } //eseguito quando l’oggetto entra in collisione con qualcosa

    public Script clone(){return this;}
}