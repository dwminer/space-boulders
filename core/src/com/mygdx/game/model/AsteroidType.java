package com.mygdx.game.model;

public enum AsteroidType
{
	SMALL(20, 100, null, 0),
	MEDIUM(40, 50, AsteroidType.SMALL, 2),
	LARGE(80, 25, AsteroidType.MEDIUM, 2);
	
	public AsteroidType splitType;
	public int speed, diameter, splitNum;
	//TODO sprite - not sure how exactly we'll read them with libgdx
	
	/**
	 * 
	 * @param diameter - in pixels, for now at least
	 * @param speed - pix/second
	 * @param splitType - what it should break into upon splitting
	 * @param splitNum - number of smaller asteroids to split into
	 */
	AsteroidType(int diameter, int speed, AsteroidType splitType, int splitNum)
	{
		this.diameter = diameter;
		this.speed = speed;
		this.splitType = splitType;
		this.splitNum = splitNum;
	}
}
