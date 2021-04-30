package com.fourgraphics.gameobjects;

import com.fourgraphics.colliders.Collider;

public class Script {

    public GameObject gameObject; //l’oggetto a cui è attaccato questo componente
    public Transform transform; //: la posizione e la dimensione dell’oggetto a cui è attaccato questo componente

    public void Start() {} //il metodo che viene eseguito al caricamento della scena
    public void Update() {} //il metodo che viene eseguito ogni frame
    public void OnCollisionEnter(Collider self, Collider other) {} //eseguito quando l’oggetto entra in collisione con qualcosa


}