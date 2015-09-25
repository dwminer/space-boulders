package com.mygdx.game.model;

import java.awt.Point;
import java.util.Random;
//import com.badlogic.gdx.math.collision.*;


public class Asteroid
{
	private AsteroidType splitType;
	private int x, y, diameter, speed, splitNum;
	private double direction;
	static Random generator = new Random();
	
	public Asteroid(AsteroidType type)
	{
		//I wonder if there's a better way to do this...
		this.diameter = type.diameter;
		this.speed = type.speed;
		this.splitType = type.splitType;
		this.splitNum = type.splitNum;
		this.direction = generator.nextDouble() * 2 * Math.PI;
	}
	
	public void update()
	{
		x += speed * Math.cos(direction);
		y += speed * Math.sin(direction);
		if (true) //outside bounding box
		{
			//wrap around
		}
	}
	
	public Point getPos()
	{
		return new Point(x, y);
	}
}
