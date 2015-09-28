package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerShip extends Rectangle //But the ship is a triangle??? Rectangle is easier, interestingly enough
{
	//private final int MAXSPEED = 200; //pixels per second //They can go as fast as they want. Good luck surviving.
	private final double TURNRATE = Math.PI*2; //radians per second. Math.PI = One half circle in one second
	private final float ACCEL = 25; //pixels per second squared
	private float speed, xspeed, yspeed;
	private float direction;
	
	public PlayerShip(float x, float y)
	{
		super();
		this.x = x;
		this.y = y;
		this.setWidth(10); //Not proper size, hitboxes aren't quite right. (Probably not centered.)
		this.setHeight(20);
		this.direction = (float) (Math.PI/2.0);
	}
	
	/**
	 * 
	 * @param time - in seconds
	 */
	public void update(float time)
	{
		x += xspeed;
		y += yspeed;
		//TODO fix because java's modulo is dumb
		x %= 800;
		y %= 600;
		if (x < 0) x += 800;
		if (y < 0) y += 600;
	}
	
	public void accelerate(float time)
	{
		xspeed += ACCEL * time * Math.cos(direction);
		yspeed += ACCEL * time * Math.sin(direction);
	}
	
	/**
	 * 
	 * @param time - in seconds
	 * @param clockwise - true if turning clockwise, false if counter-clockwise
	 */
	public void turn(float time, boolean clockwise)
	{
		direction += TURNRATE * time * (clockwise ? -1.0 : 1.0);
		direction %= 2 * Math.PI;
	}
	
	public Projectile fire()
	{
		return new Projectile(x, y, direction);
	}
	
	public float getDirection()
	{
		return direction;
	}
}
