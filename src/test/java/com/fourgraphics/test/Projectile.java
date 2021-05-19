package com.fourgraphics.test;

import com.fourgraphics.*;

import static com.fourgraphics.SceneManager.destroy;

public class Projectile extends Script {
    String parentName; //parent info

    float projectileSpeed = 80f;
    Vector2 projectileDirection = new Vector2();
    float lifeTime = 2f; //tempo di vita in secondi
    float timeLived; //da quanto è nato

    public void Update() {
        //proiettile ad accelerazione
        transform.getPosition().sum(projectileDirection.multiply(projectileSpeed * SceneManager.deltaTime()));
        //proiettile a velocità costante
        /*Vector2 clone = new Vector2();
        clone.set(projectileDirection);
        transform.getPosition().sum(clone.multiply(projectileSpeed * SceneManager.deltaTime()));*/
        timeLived += SceneManager.deltaTime();
        if (timeLived >= lifeTime)
            destroy(gameObject);
    }

    public void SetParent(String name) {
        parentName = name;
    }

    @Override
    public void OnCollisionEnter(Collider self, Collider other, CollisionDirection direction) {
        if (other.gameObject.getName().equalsIgnoreCase("player") && !parentName.equalsIgnoreCase("player")) {
            //TODO: implement damage dealing
            other.gameObject.getComponent(PlayerCombat.class).damage();
            destroy(gameObject);
        } else if (parentName.equalsIgnoreCase("player") && other.gameObject.hasComponent(Enemy.class)) {
            other.gameObject.getComponent(Enemy.class).damage();
            destroy(gameObject);
        }
    }

    public static void CreateProjectile(Vector2 spawnPosition, Vector2 direction, String parent) {
        CircleCollider coll = new CircleCollider(true);
        Renderer renderer = new Renderer(SceneManager.getApp().color(255, 0, 0), DrawType.CIRCLE);
        Projectile proj = new Projectile();
        proj.projectileDirection.set(direction);
        proj.SetParent(parent);
        GameObject obj = new GameObject("projectile");
        obj.addComponent(coll);
        obj.addComponent(renderer);
        obj.addComponent(proj);
        obj.transform.setPosition(spawnPosition);
        obj.transform.setScale(25, 0);
        SceneManager.instantiate(obj);
    }
}
