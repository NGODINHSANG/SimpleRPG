package simplerpg;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import simplerpg.Adventurer.Arrow;
import simplerpg.Adventurer.Player;
import simplerpg.Adventurer.PlayerState;
import simplerpg.Enemy.Bullet;
import simplerpg.Enemy.Enemy;
import simplerpg.Map.EntityInfo;
import simplerpg.Map.Map;

public class GameField extends BaseObj
{
	private Config cf = Config.getInstance();
	private static GameField instance;
    public static GameField getInstance()
    {
        if(instance==null)instance= new GameField();
        return instance;
    }

    private Map map;
    private Player player;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private ArrayList<Arrow> arrowList = new ArrayList<>();
    
    public GameField()
    {
    	///Map
    	map = new Map(NTS(Launcher.MapId));	
    	
    	///Player
    	SpawnPlayer();
    	
    	cf.camera.translateZProperty().set(-700);
    	
    }
	@Override
	public void render(GraphicsContext gc) 
	{	
		map.render(gc);
		ArrayList<EntityInfo> Entity = map.getEntity();
		for(int i=0;i<Entity.size();i++) 
			if(cf.root.getChildren().indexOf(map.getImageEntity(i))<0) cf.root.getChildren().add(map.getImageEntity(i));
		player.render(gc);
		for(int i=0;i<arrowList.size();i++) 
		{
			if(arrowList.get(i).isDead()) continue;
			arrowList.get(i).render();
		}
		for(int i=0;i<enemyList.size();i++)
		{
			if(enemyList.get(i).getClass()==Bullet.class)
			{
				Bullet b =(Bullet) enemyList.get(i);
				if(b.getDead()==true) continue;
				b.render();
				continue;
			}
			enemyList.get(i).render(gc);
		}
		player.render(gc);
		
	}

	private int direction =1;
	@Override
	public void update() 
	{
		cf.camera.translateXProperty().set(player.getX());
		cf.camera.translateYProperty().set(player.getY());
		
		map.update();
		for(int i=0;i<enemyList.size();i++)
		{
			//System.out.println(i+" "+enemyList.get(i).getClass());
			if(enemyList.get(i).getClass()==Bullet.class)
			{
				Bullet b = (Bullet) enemyList.get(i);
				if(b.getDead()==true) continue;
				b.Run(map, player);
				continue;
			}
			enemyList.get(i).calDirection(map,player);
		}
		for(int i=0;i<arrowList.size();i++)
		{
			for(int j=0;j<enemyList.size();j++)
			{
				if(enemyList.get(j).getClass()==Bullet.class) continue;
				if(arrowList.get(i).isDead()==false)
					arrowList.get(i).Run(map, enemyList.get(j));
			}
		}
		for(int i=(int)player.getX()-player.getWidth()/2;i<=(int)player.getX()+player.getWidth()/2;i++)
		{
			EntityInfo e = map.isRoom(i*1.0, player.getY()+player.getHeight()/2);
			if(e== null) continue;
			spawnEnemy(e.getMinX(),e.getMaxX(),e.getMinY(),e.getMaxY());
			e.setState(2);
			
		}
		Launcher.scene.setOnKeyPressed(e->
		{
			if(e.getCode() == KeyCode.PAGE_UP) cf.camera.translateZProperty().set(cf.camera.getTranslateZ()+10);
			else if(e.getCode() == KeyCode.PAGE_DOWN) cf.camera.translateZProperty().set(cf.camera.getTranslateZ()-10);
			
			if(e.getCode() == KeyCode.W)
			{
				direction = 1;
				player.Move(map,direction);
			}
			if(e.getCode() == KeyCode.S)
			{
				direction = 3;
					
				player.Move(map,direction);				
			}
			if(e.getCode() ==  KeyCode.A)
			{
				direction = 2;
				player.Move(map,direction);
			}
			if(e.getCode() == KeyCode.D)
			{
				direction = 4;
				player.Move(map,direction);
			}
			if(e.getCode() == KeyCode.J)
			{
				player.Attack01(direction,enemyList);
			}
			if(e.getCode() == KeyCode.K)
			{
				player.Attack02(direction,enemyList);
			}
			if(e.getCode() == KeyCode.L)
			{
				player.Attack03(direction,enemyList);
			}
		
		});
	}

	@Override
	public void erase() 
	{
		map.erase();
		player.erase();
		//enemy.erase();
	}
	private void spawnEnemy(double MinX,double MaxX,double MinY,double MaxY)
	{
		Random rand = new Random();
		for(int i=1;i<=map.getEPR();i++)
		{
			Enemy e = new Enemy(rand.nextInt(map.getSpecies())+1);
			enemyList.add(e);
			int x,y;
			do
			{
				x= rand.nextInt((int)(MaxX-MinX+1))+(int)MinX;
				y= rand.nextInt((int)(MaxY-MinY+1))+(int)MinY;
			}while(e.checkStep(map, x, y)==true);
			e.setX(x);e.setY(y);
			e.Spawn();
		}
		
		map.setEPR(map.getEPR()+rand.nextInt(2)+1);
	}
	private void SpawnPlayer()
	{
		Random rand = new Random();
		player = new Player(); 
		ArrayList<EntityInfo> Room=map.getRoom();
		for(int i=0;i<Room.size();i++)
		{
			EntityInfo e = Room.get(i);
			if(e.getTypeRoom() == 2)
			{
				//System.out.println(e.getMinX()+" "+e.getMaxX()+" "+e.getMinY()+" "+e.getMaxY());
				int x,y;
				do
				{
					x= rand.nextInt((int)(e.getMaxX()-e.getMinX()+1))+(int)e.getMinX();
					y= rand.nextInt((int)(e.getMaxY()-e.getMinY()+1))+(int)e.getMinY();
				}while(player.checkStep(map, x, y)==true);
				player.setX(x); player.setY(y);
			}
		}
	}
	public void addEnemy(Enemy e)
	{
		enemyList.add(e);
	}
	public void addArrow(Arrow a)
	{
		arrowList.add(a);
	}

}
