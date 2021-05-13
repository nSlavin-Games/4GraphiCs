package com.fourgraphics;

import processing.core.PApplet;

public class CircleCollider extends Collider {


	public CircleCollider(boolean dynamicObject) {

		super(dynamicObject);

	}

	protected CollisionDirection checkCollision(Collider other) {

		if(isDebug()) {
			sketch.fill(0,255,0,200);
			sketch.circle(transform.getPosition().getX(),transform.getPosition().getY(), transform.getScale().getX());
		}

		if (other instanceof CircleCollider) {

			if (isDebug()) {
				if(!other.isDynamic())
					sketch.fill(255, 0, 0, 200);
				sketch.circle(other.transform.getPosition().getX(), other.transform.getPosition().getY(),
						other.transform.getScale().getX());

			}

			float distX = transform.getPosition().getX() - other.transform.getPosition().getX();
			float distY = transform.getPosition().getY() - other.transform.getPosition().getY();
			float distance = (float) Math.sqrt(distX * distX + distY * distY);

			float hWidths= transform.getScale().getX()/2 + other.transform.getScale().getX()/2;
			float overlapX = hWidths - Math.abs(distX);
			float overlapY = hWidths - Math.abs(distY);

			if (distance < transform.getScale().getX()/2 + other.transform.getScale().getX()/2) {

				if (overlapX > overlapY) {

					if (distY > 0) {
						return CollisionDirection.UP;

					} else {
						return CollisionDirection.DOWN;

					}

				}

				if (overlapY > overlapX) {

					if (distX > 0) {
						return CollisionDirection.LEFT;

					} else {
						return CollisionDirection.RIGHT;

					}

				}
			}

		}
		if (other instanceof RectCollider) {

			if (isDebug()) {
				sketch.rectMode(sketch.CENTER);
				if(!other.isDynamic())
					sketch.fill(255, 0, 0, 200);
				sketch.rect(other.transform.getPosition().getX(), other.transform.getPosition().getY(),
						other.transform.getScale().getX(), other.transform.getScale().getY());
				
			}

			float distx = transform.getPosition().getX() - other.transform.getPosition().getX();
			float disty = transform.getPosition().getY() - other.transform.getPosition().getY();

			float hWidths = transform.getScale().getX() / 2 + other.transform.getScale().getX() / 2;
			float hHeights = transform.getScale().getX() / 2 + other.transform.getScale().getY() / 2;

			float overlapX = hWidths - Math.abs(distx);
			float overlapY = hHeights - Math.abs(disty);

			float testX = transform.getPosition().getX();
			float testY = transform.getPosition().getY();
			
			if(overlapX > overlapY)
			{
				if(disty > 0)
				{
					testY = other.transform.getPosition().getY()+other.transform.getScale().getY()/2;
					float dy = transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= transform.getScale().getX()/2)
					{
						return CollisionDirection.UP;
					}
					
				} else
				{
					testY = other.transform.getPosition().getY()-other.transform.getScale().getY()/2;
					float dy = transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= transform.getScale().getX()/2)
					{
						return CollisionDirection.DOWN;
					}
				}
			} else
			{
				if(distx > 0)
				{
					testX = other.transform.getPosition().getX()+other.transform.getScale().getX()/2;
					float dx = transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= transform.getScale().getX()/2)
					{
						return CollisionDirection.LEFT;
					}
					
				} else
				{
					testX = other.transform.getPosition().getX()-other.transform.getScale().getX()/2;
					float dx = transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= transform.getScale().getX()/2)
					{
						return CollisionDirection.RIGHT;
					}
				}
			}
		}
		return CollisionDirection.NONE;
	}

	protected CollisionDirection checkCollisionSnap(Collider other) {
		if(isDebug()) {
			sketch.fill(0,255,0,200);
			sketch.circle(transform.getPosition().getX(),transform.getPosition().getY(), transform.getScale().getX());
		}

		if (other instanceof CircleCollider) {

			if (isDebug()) {
				if(!other.isDynamic())
					sketch.fill(255, 0, 0, 200);
				sketch.circle(other.transform.getPosition().getX(), other.transform.getPosition().getY(),
						other.transform.getScale().getX());

			}

			float distX = transform.getPosition().getX() - other.transform.getPosition().getX();
			float distY = transform.getPosition().getY() - other.transform.getPosition().getY();
			float distance = (float) Math.sqrt(distX * distX + distY * distY);

			float hWidths= transform.getScale().getX()/2 + other.transform.getScale().getX()/2;
			float overlapX = hWidths - Math.abs(distX);
			float overlapY = hWidths - Math.abs(distY);

			if (distance < transform.getScale().getX()/2 + other.transform.getScale().getX()/2) {

				if (overlapX > overlapY) {

					if (distY > 0) {
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

					if (distX > 0) {
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
		if (other instanceof RectCollider) {

			if (isDebug()) {
				sketch.rectMode(sketch.CENTER);
				if(!other.isDynamic())
					sketch.fill(255, 0, 0, 200);
				sketch.rect(other.transform.getPosition().getX(), other.transform.getPosition().getY(),
						other.transform.getScale().getX(), other.transform.getScale().getY());
				
			}

			float distx = transform.getPosition().getX() - other.transform.getPosition().getX();
			float disty = transform.getPosition().getY() - other.transform.getPosition().getY();

			float hWidths = transform.getScale().getX() / 2 + other.transform.getScale().getX() / 2;
			float hHeights = transform.getScale().getX() / 2 + other.transform.getScale().getY() / 2;

			float overlapX = hWidths - Math.abs(distx);
			float overlapY = hHeights - Math.abs(disty);

			float testX = transform.getPosition().getX();
			float testY = transform.getPosition().getY();
			
			if(overlapX > overlapY)
			{
				if(disty > 0)
				{
					testY = other.transform.getPosition().getY()+other.transform.getScale().getY()/2;
					float dy = transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= transform.getScale().getX()/2)
					{
						transform.getPosition().setY(other.transform.getPosition().getY()
								+other.transform.getScale().getX()/2
								+transform.getScale().getX()/2);
						return CollisionDirection.UP;
					}
					
				} else
				{
					testY = other.transform.getPosition().getY()-other.transform.getScale().getY()/2;
					float dy = transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= transform.getScale().getX()/2)
					{
						transform.getPosition().setY(other.transform.getPosition().getY()
								-other.transform.getScale().getX()/2
								-transform.getScale().getX()/2);
						return CollisionDirection.DOWN;
					}
				}
			} else
			{
				if(distx > 0)
				{
					testX = other.transform.getPosition().getX()+other.transform.getScale().getX()/2;
					float dx = transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= transform.getScale().getX()/2)
					{
						transform.getPosition().setX(other.transform.getPosition().getX()
								+other.transform.getScale().getX()/2
								+transform.getScale().getX()/2);
						return CollisionDirection.LEFT;
					}
					
				} else
				{
					testX = other.transform.getPosition().getX()-other.transform.getScale().getX()/2;
					float dx = transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= transform.getScale().getX()/2)
					{
						transform.getPosition().setX(other.transform.getPosition().getX()
								-other.transform.getScale().getX()/2
								-transform.getScale().getX()/2);
						return CollisionDirection.RIGHT;
					}
				}
			}
		}
		return CollisionDirection.NONE;
	}

}