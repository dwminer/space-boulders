package com.mygdx.game.model;

import com.badlogic.gdx.math.Circle;

public class Projectile extends Circle
{
	
	private float direction, xspeed, yspeed;

	public Projectile(float x, float y, float direction, float xspeed, float yspeed)
	{
		super();
		this.setX(x);
		this.setY(y);
		this.setRadius(1);
		this.direction = direction;
		this.xspeed = (float) (xspeed + 200 * Math.cos(direction));
		this.yspeed = (float) (yspeed + 200 * Math.sin(direction));
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
