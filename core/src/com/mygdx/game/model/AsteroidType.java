package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Enum to store parameters for different types of asteroids
 * We're using this because different asteroids don't really behave differently, where inheritance would be useful.
 * They just have some different numbers that are more convenient to put in an enum.
 * If we wanted, we could even use a Factory pattern to generate various kinds of asteroids, but that's a bit overkill
 */
public enum AsteroidType
{
	SMALL(20, 100, null, 0, "asteroid1-small.png"),
	MEDIUM(40, 50, AsteroidType.SMALL, 2, "asteroid2-medium.png"),
	LARGE(80, 25, AsteroidType.MEDIUM, 2, "asteroid3-large.png");
	
	public AsteroidType splitType;
	public int speed, diameter, splitNum;
	public Texture spriteImage;
	private String assetName;
	//TODO sprite - not sure how exactly we'll read them with libgdx
	
	/**
	 * 
	 * @param diameter - in pixels, for now at least
	 * @param speed - pix/second
	 * @param splitType - what it should break into upon splitting
	 * @param splitNum - number of smaller asteroids to split into
	 */
	AsteroidType(int diameter, int speed, AsteroidType splitType, int splitNum, String assetName)
	{
		this.diameter = diameter;
		this.speed = speed;
		this.splitType = splitType;
		this.splitNum = splitNum;
		this.assetName = assetName;
	}

	public void create()
	{
		spriteImage = new Texture(Gdx.files.internal(assetName));
	}
	
	public void dispose()
	{
		if (spriteImage != null) spriteImage.dispose();
	}

	//If we want to make our game support multiple resolutions,
	//we can scale our sprite accordingly on resize here.
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	
}
