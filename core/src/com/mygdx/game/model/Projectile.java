package com.mygdx.game.model;

import com.badlogic.gdx.math.Circle;

public class Projectile extends Circle
{
	
	private float direction, xspeed, yspeed;

	public Projectile(float x, float y, float direction)
	{
		super();
		this.setX(x);
		this.setY(y);
		this.setRadius(2);
		this.direction = direction;
		this.xspeed = (float) (10 * Math.cos(direction));
		this.yspeed = (float) (10 * Math.sin(direction));
	}
	
	public void update(float time)
	{
		x += xspeed;
		y += yspeed;
		x %= 800;
		y %= 600;
		if (x < 0) x += 800;
		if (y < 0) y += 600;
	}

}
