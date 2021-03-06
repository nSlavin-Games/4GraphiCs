package com.fourgraphics;

public class RectCollider extends Collider {

	public RectCollider(boolean dynamicObject, boolean ignoreSnap) {

		super(dynamicObject, ignoreSnap);
		//setDebug(true);
	}

	protected void debugDisplay()
	{
		//System.out.print("trying to debug " + gameObject.getName() + " status: " + isDebug() + "...");
		if (isDebug()) {
			//System.out.print(" DONE!");
			sketch.rectMode(sketch.CENTER);
			sketch.fill(0, 255, 0, 100);
			sketch.rect(Rescaler.resizeH(transform.getPosition().getX()), Rescaler.resizeH(transform.getPosition().getY()), Rescaler.resizeH(transform.getScale().getX()),
					Rescaler.resizeH(transform.getScale().getY()));

		}
		//System.out.println();
	}

	protected CollisionDirection checkCollision(Collider other) {
		if (other instanceof RectCollider) {

			float distx = transform.getPosition().getX() - other.transform.getPosition().getX();
			float disty = transform.getPosition().getY() - other.transform.getPosition().getY();

			float hWidths = transform.getScale().getX() / 2 + other.transform.getScale().getX() / 2;
			float hHeights = transform.getScale().getY() / 2 + other.transform.getScale().getY() / 2;

			float overlapX = hWidths - Math.abs(distx);
			float overlapY = hHeights - Math.abs(disty);

			if(overlapX > overlapY)
			{
				if(Math.abs(distx) <= hWidths)
				{
					if(previousPosition.getY() < other.transform.getPosition().getY() && transform.getPosition().getY() > other.transform.getPosition().getY())
					{
						previousPosition.set(transform.getPosition());
						return CollisionDirection.DOWN;
					} else if(previousPosition.getY() > other.transform.getPosition().getY() && transform.getPosition().getY() < other.transform.getPosition().getY())
					{
						previousPosition.set(transform.getPosition());
						return CollisionDirection.UP;
					}
				}
			} else
			{
				if(Math.abs(disty) <= hHeights)
				{
					if (previousPosition.getX() < other.transform.getPosition().getX() && transform.getPosition().getX() > other.transform.getPosition().getX())
					{
						previousPosition.set(transform.getPosition());
						return CollisionDirection.RIGHT;
					} else if (previousPosition.getX() > other.transform.getPosition().getX() && transform.getPosition().getX() < other.transform.getPosition().getX())
					{
						previousPosition.set(transform.getPosition());
						return CollisionDirection.LEFT;
					}
				}
			}

			if (Math.abs(distx) <= hWidths && Math.abs(disty) <= hHeights) {

				if (overlapX > overlapY) {

					if (disty > 0) {
						previousPosition.set(transform.getPosition());
						return CollisionDirection.UP;
					} else {
						previousPosition.set(transform.getPosition());
						return CollisionDirection.DOWN;
					}

				}

				if (overlapY > overlapX) {

					if (distx > 0) {
						previousPosition.set(transform.getPosition());
						return CollisionDirection.LEFT;
					} else {
						previousPosition.set(transform.getPosition());
						return CollisionDirection.RIGHT;
					}

				}

			}

		}
		
		if (other instanceof CircleCollider) {
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
				if(previousPosition.getY() < other.transform.getPosition().getY() && transform.getPosition().getY() > other.transform.getPosition().getY()
				&& distx <= hWidths)
				{
					previousPosition.set(transform.getPosition());
					return CollisionDirection.UP;
				} else if (previousPosition.getY() > other.transform.getPosition().getY() && transform.getPosition().getY() < other.transform.getPosition().getY()
						&& distx <= hWidths)
				{
					previousPosition.set(transform.getPosition());
					return CollisionDirection.DOWN;
				}

				if(disty > 0)
				{
					testY = transform.getPosition().getY()+transform.getScale().getY()/2;
					float dy = other.transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= other.transform.getScale().getX()/2)
					{
						previousPosition.set(transform.getPosition());
						return CollisionDirection.DOWN;
					}
					
				} else
				{
					testY = transform.getPosition().getY()-transform.getScale().getY()/2;
					float dy = other.transform.getPosition().getY() - testY;
					float distance = (float)Math.sqrt(dy*dy);
					if(distance <= other.transform.getScale().getX()/2)
					{
						previousPosition.set(transform.getPosition());
						return CollisionDirection.UP;
					}
				}
			} else
			{
				if(previousPosition.getX() < other.transform.getPosition().getX() && transform.getPosition().getX() > other.transform.getPosition().getX()
						&& disty <= hHeights)
				{
					previousPosition.set(transform.getPosition());
					return CollisionDirection.RIGHT;
				} else if (previousPosition.getX() > other.transform.getPosition().getX() && transform.getPosition().getX() < other.transform.getPosition().getX()
						&& disty <= hHeights)
				{
					previousPosition.set(transform.getPosition());
					return CollisionDirection.LEFT;
				}

				if(distx > 0)
				{
					testX = transform.getPosition().getX()+transform.getScale().getX()/2;
					float dx = other.transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= other.transform.getScale().getX()/2)
					{
						previousPosition.set(transform.getPosition());
						return CollisionDirection.RIGHT;
					}
					
				} else
				{
					testX = transform.getPosition().getX()-transform.getScale().getX()/2;
					float dx = other.transform.getPosition().getX() - testX;
					float distance = (float)Math.sqrt(dx*dx);
					if(distance <= other.transform.getScale().getX()/2)
					{
						previousPosition.set(transform.getPosition());
						return CollisionDirection.LEFT;
					}
				}
			}
		}

		previousPosition.set(transform.getPosition());
		return CollisionDirection.NONE;

	}

    protected CollisionDirection checkCollisionSnap(Collider other) {

        if (other instanceof RectCollider) {
            float distx = transform.getPosition().getX() - other.transform.getPosition().getX();
            float disty = transform.getPosition().getY() - other.transform.getPosition().getY();

            float hWidths = transform.getScale().getX() / 2 + other.transform.getScale().getX() / 2;
            float hHeights = transform.getScale().getY() / 2 + other.transform.getScale().getY() / 2;

            float overlapX = hWidths - Math.abs(distx);
            float overlapY = hHeights - Math.abs(disty);

            if(overlapX > overlapY) {
                if(Math.abs(distx) <= hWidths) {
                    if(previousPosition.getY() < other.transform.getPosition().getY() && transform.getPosition().getY() > other.transform.getPosition().getY()) {

                        transform.getPosition().setY(other.transform.getPosition().getY()
                                -other.transform.getScale().getY()/2
                                -transform.getScale().getY()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.DOWN;
                    } else if(previousPosition.getY() > other.transform.getPosition().getY() && transform.getPosition().getY() < other.transform.getPosition().getY()) {

                        transform.getPosition().setY(other.transform.getPosition().getY()
                                +other.transform.getScale().getY()/2
                                +transform.getScale().getY()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.UP;
                    }
                }
            } else {
                if(Math.abs(disty) <= hHeights) {
                    if (previousPosition.getX() < other.transform.getPosition().getX() && transform.getPosition().getX() > other.transform.getPosition().getX()) {
                        transform.getPosition().setX(other.transform.getPosition().getX()
                                -other.transform.getScale().getX()/2
                                -transform.getScale().getX()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.RIGHT;

                    } else if (previousPosition.getX() > other.transform.getPosition().getX() && transform.getPosition().getX() < other.transform.getPosition().getX()) {
                        transform.getPosition().setX(other.transform.getPosition().getX()
                                +other.transform.getScale().getX()/2
                                +transform.getScale().getX()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.LEFT;

                    }
                }
            }

            if (Math.abs(distx) <= hWidths && Math.abs(disty) <= hHeights) {

                transform.setPosition(transform.getPosition().getX(),transform.getPosition().getY());
                if (overlapX > overlapY) {

                    if (disty > 0) {
                        transform.getPosition().setY(other.transform.getPosition().getY()
                                +other.transform.getScale().getY()/2
                                +transform.getScale().getY()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.UP;

                    } else {
                        transform.getPosition().setY(other.transform.getPosition().getY()
                                -other.transform.getScale().getY()/2
                                -transform.getScale().getY()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.DOWN;

                    }

                }

                if (overlapY > overlapX) {

                    if (distx > 0) {
                        transform.getPosition().setX(other.transform.getPosition().getX()
                                +other.transform.getScale().getX()/2
                                +transform.getScale().getX()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.LEFT;

                    } else {
                        transform.getPosition().setX(other.transform.getPosition().getX()
                                -other.transform.getScale().getX()/2
                                -transform.getScale().getX()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.RIGHT;

                    }

                }

            }

        }

        if (other instanceof CircleCollider) {

            float distx = other.transform.getPosition().getX() - transform.getPosition().getX();
            float disty = other.transform.getPosition().getY() - transform.getPosition().getY();

            float hWidths = other.transform.getScale().getX() / 2 + transform.getScale().getX() / 2;
            float hHeights = other.transform.getScale().getX() / 2 + transform.getScale().getY() / 2;

            float overlapX = hWidths - Math.abs(distx);
            float overlapY = hHeights - Math.abs(disty);

            float testX = other.transform.getPosition().getX();
            float testY = other.transform.getPosition().getY();

            if(overlapX > overlapY) {
                if(previousPosition.getY() < other.transform.getPosition().getY() && transform.getPosition().getY() > other.transform.getPosition().getY()
                        && distx <= hWidths) {
                    transform.getPosition().setY(other.transform.getPosition().getY()
                            +other.transform.getScale().getX()/2
                            +transform.getScale().getY()/2);
                    previousPosition.set(transform.getPosition());
                    return CollisionDirection.UP;
                } else if (previousPosition.getY() > other.transform.getPosition().getY() && transform.getPosition().getY() < other.transform.getPosition().getY()
                        && distx <= hWidths) {
                    transform.getPosition().setY(other.transform.getPosition().getY()
                            -other.transform.getScale().getX()/2
                            -transform.getScale().getY()/2);
                    previousPosition.set(transform.getPosition());
                    return CollisionDirection.DOWN;
                }

                if(disty > 0) {
                    testY = other.transform.getPosition().getY()+other.transform.getScale().getY()/2;
                    float dy = transform.getPosition().getY() - testY;
                    float distance = (float)Math.sqrt(dy*dy);
                    if(distance <= transform.getScale().getX()/2) {
                        transform.getPosition().setY(other.transform.getPosition().getY()
                                +other.transform.getScale().getX()/2
                                +transform.getScale().getY()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.UP;
                    }

                } else {
                    testY = other.transform.getPosition().getY()-other.transform.getScale().getY()/2;
                    float dy = transform.getPosition().getY() - testY;
                    float distance = (float)Math.sqrt(dy*dy);
                    if(distance <= transform.getScale().getX()/2) {
                        transform.getPosition().setY(other.transform.getPosition().getY()
                                -other.transform.getScale().getX()/2
                                -transform.getScale().getY()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.DOWN;
                    }
                }
            } else {
                if(previousPosition.getX() < other.transform.getPosition().getX() && transform.getPosition().getX() > other.transform.getPosition().getX()
                        && disty <= hHeights) {
                    transform.getPosition().setX(other.transform.getPosition().getX()
                            -other.transform.getScale().getX()/2
                            -transform.getScale().getX()/2);
                    previousPosition.set(transform.getPosition());
                    return CollisionDirection.RIGHT;
                } else if (previousPosition.getX() > other.transform.getPosition().getX() && transform.getPosition().getX() < other.transform.getPosition().getX()
                        && disty <= hHeights) {
                    transform.getPosition().setX(other.transform.getPosition().getX()
                            +other.transform.getScale().getX()/2
                            +transform.getScale().getX()/2);
                    previousPosition.set(transform.getPosition());
                    return CollisionDirection.LEFT;
                }

                if(distx > 0) {
                    testX = other.transform.getPosition().getX()+other.transform.getScale().getX()/2;
                    float dx = transform.getPosition().getX() - testX;
                    float distance = (float)Math.sqrt(dx*dx);
                    if(distance <= transform.getScale().getX()/2) {
                        transform.getPosition().setX(other.transform.getPosition().getX()
                                +other.transform.getScale().getX()/2
                                +transform.getScale().getX()/2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.LEFT;
                    }

                } else {
                    testX = other.transform.getPosition().getX()-other.transform.getScale().getX()/2;
                    float dx = transform.getPosition().getX() - testX;
                    float distance = (float)Math.sqrt(dx*dx);
                    if(distance <= transform.getScale().getX()/2) {
                        transform.getPosition().setX(other.transform.getPosition().getX()
                                -other.transform.getScale().getX()/2
                                - transform.getScale().getX() / 2);
                        previousPosition.set(transform.getPosition());
                        return CollisionDirection.RIGHT;
                    }
                }
            }
        }
        previousPosition.set(transform.getPosition());
        return CollisionDirection.NONE;

    }

    public RectCollider clone() {
        return new RectCollider(isDynamic(), ignoreSnap);
    }
}