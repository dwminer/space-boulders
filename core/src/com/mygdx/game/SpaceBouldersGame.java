package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.model.*;

import java.util.ListIterator;
import java.util.LinkedList;

public class SpaceBouldersGame implements ApplicationListener
{
	
	private OrthographicCamera camera;
	private LinkedList<Asteroid> asteroids;
	private LinkedList<Projectile> playerProjectiles;
	private PlayerShip p1;
	private SpriteBatch batch;
	private Texture playerTexture, projectileTexture;

	@Override
	public void create()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		batch = new SpriteBatch();
		
		p1 = new PlayerShip(400, 300);
		playerTexture = new Texture(Gdx.files.internal("playership.png"));
		projectileTexture = new Texture(Gdx.files.internal("projectile.png"));
		playerProjectiles = new LinkedList<Projectile>();
		
		for (AsteroidType type : AsteroidType.values()) type.create();
		asteroids = new LinkedList<Asteroid>();
		asteroids.add(new Asteroid(AsteroidType.LARGE, 700, 500));
		asteroids.add(new Asteroid(AsteroidType.LARGE, 700, 100));
		asteroids.add(new Asteroid(AsteroidType.LARGE, 100, 500));
		asteroids.add(new Asteroid(AsteroidType.LARGE, 100, 100));
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
		
		batch.begin();
		
		//Now that's a mouthful
		batch.draw(playerTexture, p1.getX(), p1.getY(), 
				playerTexture.getWidth()/2, playerTexture.getHeight()/2, 
				playerTexture.getWidth(), playerTexture.getHeight(), 
				1, 1, 
				(float)Math.toDegrees(p1.getDirection()) - 90, //have to subtract 90 here because libgdx doesn't adhere to the 0 degrees = right convention
				0, 0, 
				playerTexture.getWidth(), playerTexture.getHeight(), 
				false, false);
		
		ListIterator<Projectile> projectileIter = playerProjectiles.listIterator();
		Projectile proj;
		while(projectileIter.hasNext())
		{
			proj = projectileIter.next();
			proj.update(Gdx.graphics.getDeltaTime()); 
			batch.draw(projectileTexture, proj.x, proj.y);
			ListIterator<Asteroid> asteroidIter = asteroids.listIterator();
			Asteroid rock;
			boolean removed = false;
			while(asteroidIter.hasNext() && !removed)
			{
				rock = asteroidIter.next();
				if (proj.overlaps(rock))
				{
					asteroidIter.remove();
					projectileIter.remove();
					removed = true;
					if (rock.destroy() != null)
					{
						for (Asteroid newRock : rock.destroy())
						{
							asteroidIter.add(newRock);
						}
					}
				}
			}
			
		}
		
		for (Asteroid rock : asteroids)
		{
			if (Intersector.overlaps(rock, p1))
			{
				System.out.println("Game over!");
				Gdx.app.exit();
			}
			batch.draw(rock.getTexture(), rock.x, rock.y);
			rock.update(Gdx.graphics.getDeltaTime());
		}
		
		batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			p1.turn(Gdx.graphics.getDeltaTime(), false);
			//camera.rotate((float) Math.toDegrees((Math.PI * 2 * Gdx.graphics.getDeltaTime()%Math.PI*2)));
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) p1.turn(Gdx.graphics.getDeltaTime(), true);
		if (Gdx.input.isKeyPressed(Keys.UP)) p1.accelerate(Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) playerProjectiles.add(p1.fire());
		
		p1.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
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
