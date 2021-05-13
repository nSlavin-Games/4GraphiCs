package com.fourgraphics.colliders;

import com.fourgraphics.gameobjects.GameObject;
import com.fourgraphics.gameobjects.Vector2;

import processing.core.PApplet;

public class RectCollider extends Collider {

	public RectCollider(boolean dynamicObject) {

		super(dynamicObject);

	}

	public RectCollider(boolean dynamicObject, PApplet sketch) {

		super(dynamicObject, sketch);

	}

	public CollisionDirection CheckCollision(Collider other) {

		if (isDebug() == true) {
			sketch.rectMode(sketch.CENTER);
			sketch.fill(0, 255, 0, 200);
			sketch.rect(transform.getPosition().getX(), transform.getPosition().getY(), transform.getScale().getX(),
					transform.getScale().getY());

		}

		if (other instanceof RectCollider) {

			if (isDebug() == true) {
				sketch.rectMode(sketch.CENTER);
				sketch.fill(255, 0, 0, 200);
				sketch.rect(other.transform.getPosition().getX(), other.transform.getPosition().getY(),
						other.transform.getScale().getX(), other.transform.getScale().getY());

			}

			float distx = transform.getPosition().getX() - other.transform.getPosition().getX();
			float disty = transform.getPosition().getY() - other.transform.getPosition().getY();

			float hWidths = transform.getScale().getX() / 2 + other.transform.getScale().getX() / 2;
			float hHeights = transform.getScale().getY() / 2 + other.transform.getScale().getY() / 2;

			float overlapX = hWidths - Math.abs(distx);
			float overlapY = hHeights - Math.abs(disty);

			if (Math.abs(distx) <= hWidths && Math.abs(disty) <= hHeights) {

				if (overlapX > overlapY) {

					if (disty > 0) {
						return CollisionDirection.UP;
				
					} else {
						return CollisionDirection.DOWN;
					
					}

				}

				if (overlapY > overlapX) {

					if (distx > 0) {
						return CollisionDirection.LEFT;
				
					} else {
						return CollisionDirection.RIGHT;
					
					}

				}

			}

		}
		
		if (other instanceof CircleCollider) {

			if (isDebug() == true) {
				sketch.fill(255, 0, 0, 200);
				sketch.circle(other.transform.getPosition().getX(), other.transform.getPosition().getY(),
						other.transform.getScale().getX());
			}


			float distx = other.transform.getPosition().getX() - transform.getPosition().getX();
			float disty = other.transform.getPosition().getY() - transform.getPosition().getY();

			float hWidths = other.transform.getScale().getX() / 2 + transform.getScale().getX() / 2;
			float hHeights = other.transform.getScale().getX() / 2 + transform.getScale().getY() / 2;

			float overlapX = hWidths - Math.abs(distx);
			float overlapY = hHeights - Math.abs(disty);

			float testX = other.transform.getPosition().getX();
			float testY = other.transform.getPosition().getY();
			
			if(overlapX > overlapY)
			{
				if(disty > 0)
				{
					testY = transform.getPosition().getY()+transform.getScale().getY()/2;
					float dy = other.transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= other.transform.getScale().getX()/2)
					{
				
						return CollisionDirection.DOWN;
					}
					
				} else
				{
					testY = transform.getPosition().getY()-transform.getScale().getY()/2;
					float dy = other.transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= other.transform.getScale().getX()/2)
					{
				
						return CollisionDirection.UP;
					}
				}
			} else
			{
				if(distx > 0)
				{
					testX = transform.getPosition().getX()+transform.getScale().getX()/2;
					float dx = other.transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= other.transform.getScale().getX()/2)
					{
					
						return CollisionDirection.RIGHT;
					}
					
				} else
				{
					testX = transform.getPosition().getX()-transform.getScale().getX()/2;
					float dx = other.transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= other.transform.getScale().getX()/2)
					{
				
						return CollisionDirection.LEFT;
					}
				}
			}
		}

		return CollisionDirection.NONE;

	}

	public CollisionDirection CheckCollisionSnap(Collider other) {

		if (isDebug() == true) {
			sketch.rectMode(sketch.CENTER);
			sketch.fill(0, 255, 0, 200);
			sketch.rect(transform.getPosition().getX(), transform.getPosition().getY(), transform.getScale().getX(),
					transform.getScale().getY());

		}

		if (other instanceof RectCollider) {

			if (isDebug() == true) {
				sketch.rectMode(sketch.CENTER);
				sketch.fill(255, 0, 0, 200);
				sketch.rect(other.transform.getPosition().getX(), other.transform.getPosition().getY(),
						other.transform.getScale().getX(), other.transform.getScale().getY());

			}

			float distx = transform.getPosition().getX() - other.transform.getPosition().getX();
			float disty = transform.getPosition().getY() - other.transform.getPosition().getY();

			float hWidths = transform.getScale().getX() / 2 + other.transform.getScale().getX() / 2;
			float hHeights = transform.getScale().getY() / 2 + other.transform.getScale().getY() / 2;

			float overlapX = hWidths - Math.abs(distx);
			float overlapY = hHeights - Math.abs(disty);

			if (Math.abs(distx) <= hWidths && Math.abs(disty) <= hHeights) {
				
				transform.setPosition(transform.getPosition().getX(),transform.getPosition().getY());
				if (overlapX > overlapY) {

					if (disty > 0) {
						transform.getPosition().setY(other.transform.getPosition().getY()
								+other.transform.getScale().getX()/2
								+transform.getScale().getX()/2);
						return CollisionDirection.UP;
						
					} else {
						transform.getPosition().setY(other.transform.getPosition().getY()
								-other.transform.getScale().getX()/2
								-transform.getScale().getX()/2);
						return CollisionDirection.DOWN;
					
					}

				}

				if (overlapY > overlapX) {

					if (distx > 0) {
						transform.getPosition().setX(other.transform.getPosition().getX()
								+other.transform.getScale().getX()/2
								+transform.getScale().getX()/2);
						return CollisionDirection.LEFT;
						
					} else {
						transform.getPosition().setX(other.transform.getPosition().getX()
								-other.transform.getScale().getX()/2
								-transform.getScale().getX()/2);
						return CollisionDirection.RIGHT;
			
					}

				}

			}

		}
		
		if (other instanceof CircleCollider) {

			if (isDebug() == true) {
				sketch.fill(255, 0, 0, 200);
				sketch.circle(other.transform.getPosition().getX(), other.transform.getPosition().getY(),
						other.transform.getScale().getX());
			}


			float distx = other.transform.getPosition().getX() - transform.getPosition().getX();
			float disty = other.transform.getPosition().getY() - transform.getPosition().getY();

			float hWidths = other.transform.getScale().getX() / 2 + transform.getScale().getX() / 2;
			float hHeights = other.transform.getScale().getX() / 2 + transform.getScale().getY() / 2;

			float overlapX = hWidths - Math.abs(distx);
			float overlapY = hHeights - Math.abs(disty);

			float testX = other.transform.getPosition().getX();
			float testY = other.transform.getPosition().getY();
			
			if(overlapX > overlapY)
			{
				if(disty > 0)
				{
					testY = transform.getPosition().getY()+transform.getScale().getY()/2;
					float dy = other.transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= other.transform.getScale().getX()/2)
					{
						transform.getPosition().setY(other.transform.getPosition().getY()
								-other.transform.getScale().getX()/2
								-transform.getScale().getX()/2);
						return CollisionDirection.DOWN;
					}
					
				} else
				{
					testY = transform.getPosition().getY()-transform.getScale().getY()/2;
					float dy = other.transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= other.transform.getScale().getX()/2)
					{
						transform.getPosition().setY(other.transform.getPosition().getY()
								+other.transform.getScale().getX()/2
								+transform.getScale().getX()/2);
						return CollisionDirection.UP;
					}
				}
			} else
			{
				if(distx > 0)
				{
					testX = transform.getPosition().getX()+transform.getScale().getX()/2;
					float dx = other.transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= other.transform.getScale().getX()/2)
					{
						transform.getPosition().setX(other.transform.getPosition().getX()
								-other.transform.getScale().getX()/2
								-transform.getScale().getX()/2);
						return CollisionDirection.RIGHT;
					}
					
				} else
				{
					testX = transform.getPosition().getX()-transform.getScale().getX()/2;
					float dx = other.transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= other.transform.getScale().getX()/2)
					{
						transform.getPosition().setX(other.transform.getPosition().getX()
								+other.transform.getScale().getX()/2
								+transform.getScale().getX()/2);
						return CollisionDirection.LEFT;
					}
				}
			}
		}
		
		return CollisionDirection.NONE;

	}
}