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
	private int /*x, y,*//*Don't want to override scope*/ diameter, speed, splitNum;
	private double direction;
	static Random generator = new Random();
	private Texture spriteImage;
	
	public Asteroid(AsteroidType type, int x, int y)
	{
		//I wonder if there's a better way to do this...
		this.x = (float)x;
		this.y = (float)y;
		this.diameter = type.diameter;
		this.speed = type.speed;
		this.splitType = type.splitType;
		this.splitNum = type.splitNum;
		this.direction = generator.nextDouble() * 2 * Math.PI;
		this.spriteImage = type.spriteImage;
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
	
	public List<Asteroid> destroy()
	{
		if (splitType != null)
		{
			LinkedList<Asteroid> toReturn = new LinkedList<Asteroid>();
			for (int i = 0; i < this.splitNum; i++)
			{
				toReturn.add(new Asteroid(this.splitType, (int)x, (int)y));
			}
			
			return toReturn;
		}
		else return null;
	}
	
	public int getX()
	{
		return (int)x;
	}
	
	public int getY()
	{
		return (int)y;
	}
}
