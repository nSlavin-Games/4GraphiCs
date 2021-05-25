package com.fourgraphics;

public class CircleCollider extends Collider {


	public CircleCollider(boolean dynamicObject, boolean ignoreSnap) {

		super(dynamicObject, ignoreSnap);
	}

	protected void debugDisplay()
    {
        if(isDebug()) {
            sketch.fill(0,255,0,100);
            sketch.circle(Rescaler.resizeH(transform.getPosition().getX()), Rescaler.resizeH(transform.getPosition().getY()), Rescaler.resizeH(transform.getScale().getX()));
        }
    }

	protected CollisionDirection checkCollision(Collider other) {
		if (other instanceof CircleCollider) {
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

		if (other instanceof CircleCollider) {

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
								+transform.getScale().getX()/2
								+other.transform.getScale().getY()/2);
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
								-transform.getScale().getX()/2
								-other.transform.getScale().getY()/2);
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
								- other.transform.getScale().getX() / 2
								- transform.getScale().getX() / 2);
						return CollisionDirection.RIGHT;
					}
				}
			}
		}
		return CollisionDirection.NONE;
	}

	public CircleCollider clone() {
		return new CircleCollider(isDynamic(), ignoreSnap);
	}
}