package com.mygdx.game.model;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
//import com.badlogic.gdx.math.collision.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 * An asteroid (erm... space boulder. yeah.)
 * Right now this extends Circle for the intersection calculations.
 * Considering all the casting I have to do it might be better to just roll my own intersection detection
 *
 */
public class Asteroid extends Circle 
{
	private AsteroidType splitType;
	private int speed, splitNum;
	private double direction;
	static Random generator = new Random();
	private Texture spriteImage;
	
	public Asteroid(AsteroidType type, float x, float y)
	{
		//super();
		//I wonder if there's a better way to do this...
		this.x = x;
		this.y = y;
		this.radius = type.diameter / 2;
		this.speed = type.speed;
		this.splitType = type.splitType;
		this.splitNum = type.splitNum;
		this.direction = generator.nextDouble() * 2.0 * Math.PI;
		this.spriteImage = type.spriteImage;
	}
	
	/**
	 * 
	 * @param time - in seconds
	 */
	public void update(float time)
	{
		x += speed * time * Math.cos(direction);
		y += speed * time * Math.sin(direction);
		x %= 800;
		y %= 600;
		if (x < 0) x += 800;
		if (y < 0) y += 600;
	}
	
	public List<Asteroid> destroy()
	{
		if (splitType != null)
		{
			LinkedList<Asteroid> toReturn = new LinkedList<Asteroid>();
			for (int i = 0; i < this.splitNum; i++)
			{
				toReturn.add(new Asteroid(this.splitType, x, y));
			}
			
			return toReturn;
		}
		else return null;
	}
	
	public Texture getTexture()
	{
		return spriteImage;
	}
}
