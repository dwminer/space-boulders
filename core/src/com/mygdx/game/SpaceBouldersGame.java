package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.model.AsteroidType;

public class SpaceBouldersGame implements ApplicationListener
{
	
	private OrthographicCamera camera;

	@Override
	public void create()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		for (AsteroidType type : AsteroidType.values()) type.create();
	}
	

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		for (AsteroidType type : AsteroidType.values())
		{
			type.dispose();
		}		
	}

}
