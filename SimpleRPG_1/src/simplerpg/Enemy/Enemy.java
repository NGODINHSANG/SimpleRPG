package simplerpg.Enemy;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import simplerpg.BaseObj;
import simplerpg.Config;
import simplerpg.GameField;
import simplerpg.Launcher;
import simplerpg.SpriteAnimation;
import simplerpg.Adventurer.Player;
import simplerpg.Map.Map;

public class Enemy extends BaseObj
{
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	private int WIDTH = cf.getWidth();
	private int HEIGHT =  cf.getHeight();
	
	private EnemyState State;
	
	private ImageView walking = new ImageView();
	private ImageView attack = new ImageView();
	private ImageView death = new ImageView();
	private ImageView hurted = new ImageView();
	private ImageView spawn = new ImageView();
	
	private SpriteAnimation walkingani ;
	private SpriteAnimation attackani;
	private SpriteAnimation deathani;
	private SpriteAnimation hurtedani;
	private SpriteAnimation spawnani;
	
	private ImageView baseHP = new ImageView();
	private int curHP = 50;
	private ImageView[] HP = new ImageView[100];
	private boolean dead = false;
	
	public Enemy() {};
	public Enemy(int Id)
	{
		
		this.State = new EnemyState(Id);
		
		///Walking animation
		walking.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		walking.setImage(new Image(ImgLink+"walking"+"_"+"enemy"+"_"+NTS(State.getType())+".png"));
		walkingani = new SpriteAnimation(walking, Duration.millis(800), State.getCount_ani_running(), State.getColumn_ani_running(), State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Attack animation
		attack.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		attack.setImage(new Image(ImgLink+"attack"+"_"+"enemy"+"_"+NTS(State.getType())+".png"));
		attackani = new SpriteAnimation(attack, Duration.millis(2000*State.getAttackSpeed()), State.getCount_ani_attack(), State.getColumn_ani_attack(), State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Death animation
		death.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		death.setImage(new Image(ImgLink+"death"+"_"+"enemy"+"_"+NTS(State.getType())+".png"));
		deathani = new SpriteAnimation(death, Duration.millis(1000), State.getCount_ani_death(), State.getColumn_ani_death(), State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Hurted animation
		hurted.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		hurted.setImage(new Image(ImgLink+"hurted"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		hurtedani = new SpriteAnimation(hurted, Duration.millis(200), State.getCount_ani_hurted(), State.getColumn_ani_hurted(), State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Spawn
		spawn.setViewport(new Rectangle2D(0,0,State.getBGWIDTH(),State.getBGHEIGHT()));
		spawn.setImage(new Image(ImgLink+"spawn"+"_"+"enemy"+"_"+NTS(State.getType())+".png"));
		spawnani = new SpriteAnimation(spawn,Duration.millis(1000),State.getCount_ani_spawn(), State.getColumn_ani_spawn(), State.getBGWIDTH(), State.getBGHEIGHT(), State.getBGWIDTH(), State.getBGHEIGHT());
		
		
		///Base HP
		baseHP.setImage(new Image(ImgLink+"base_hp"+".png"));
		for(int i=0;i<curHP;i++)
		{
			HP[i] = new ImageView();
			HP[i].setImage(new Image(ImgLink+"HP"+".png"));
		}
	}
	private void SetUpAnimation(ImageView IV,Image Img,SpriteAnimation Ani,Duration duration,int Count,int Columns,int offsetX,int offsetY)
	{
		IV.setImage(Img);
		Ani.setDuration(duration);
		Ani.setCount(Count);
		Ani.setColumns(Columns);
		Ani.setOffsetX(offsetX);
		Ani.setOffsetY(offsetY);
	}
	
	private int[] Dx = {-1,1,-1,0,0};
	private int[] Dy = {-1,0,0,1,-1};
	private double directionX = 0;
	private double directionY = 0;
	private void Move(Map map,int type)
	{
		
		
		SetUpAnimation(
				walking,
				new Image(ImgLink+"walking"+"_"+"enemy"+"_"+NTS(State.getType())+".png"),
				walkingani,
				Duration.millis(800),
				State.getCount_ani_running(),
				State.getColumn_ani_running(),
				walkingani.getOffsetX(),
				State.getBGHEIGHT()*((directionX<0) ? 1 : 2)
				);
		
		if(cf.root.getChildren().indexOf(attack)>=0) cf.root.getChildren().remove(attack);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		
		if(checkStep(map,State.getX()+Dx[type]*State.getRunningSpeed(),State.getY()+Dy[type]*State.getRunningSpeed())==false)
		{
			State.setX(State.getX()+Dx[type]*State.getRunningSpeed());
			State.setY(State.getY()+Dy[type]*State.getRunningSpeed());
			walking.setX(State.getRenderX());
			walking.setY(State.getRenderY());
			
			baseHP.setX(State.getX()-State.getWIDTH()/2-(50-State.getWIDTH())/2);
			baseHP.setY(State.getY()-State.getBGHEIGHT()/2);
			
			double X = State.getX()-State.getWIDTH()/2-(50-State.getWIDTH())/2;
			for(int i=0;i<curHP;i++)
			{
				HP[i].setX(X+i);
				HP[i].setY(State.getY()-State.getBGHEIGHT()/2);
			}
		}
	}
	private void Attack(double directionX,Player player)
	{
		double playerX = player.getX();
		double playerY = player.getY();
		SetUpAnimation(
				attack,
				new Image(ImgLink+"attack"+"_"+"enemy"+"_"+NTS(State.getType())+".png"),
				attackani,
				Duration.millis(2000*State.getAttackSpeed()),
				State.getCount_ani_attack(),
				State.getColumn_ani_attack(),
				attackani.getOffsetX(), 
				State.getBGHEIGHT()*((directionX<0) ? 1 : 2)
				);
		
		attack.setX(State.getRenderX());
		attack.setY(State.getRenderY());
	
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		
		attackani.setOnFinished(e->
		{
			//System.out.println(1);
			double dis = (State.getX()-player.getX())*(State.getX()-player.getX())+(State.getY()-player.getY())*(State.getY()-player.getY());
			if(State.getType()==3)
			{
				GameField.getInstance().addEnemy(new Bullet(State.getType(),State.getX(),State.getY(),playerX,playerY));
			}
			if(State.getType()!=3 && dis<=State.getRange()*State.getRange() && Launcher.dead==0)
			{
				System.out.println(1);
				player.Hurted(State.getDamage());
			}
		});
		
		attackani.play();
	}
	public void Spawn()
	{
		double directionX = Math.random()*10;
		
		SetUpAnimation(
				spawn,
				new Image(ImgLink+"spawn"+"_"+"enemy"+"_"+NTS(State.getType())+".png"),
				spawnani,
				Duration.millis(3000),
				State.getCount_ani_spawn(),
				State.getColumn_ani_spawn(),
				spawnani.getOffsetX(),
				State.getBGHEIGHT()*((directionX<=5) ? 1 : 2)
				);
		
		spawnani.setOnFinished(e->{if(cf.root.getChildren().indexOf(spawn)>=0) cf.root.getChildren().remove(spawn);});
		spawnani.play();
	}
	public void Death()
	{
		
		SetUpAnimation(
				death,
				new Image(ImgLink+"death"+"_"+"enemy"+"_"+NTS(State.getType())+".png"),
				deathani,
				Duration.millis(1000),
				State.getCount_ani_death(),
				State.getColumn_ani_death(),
				deathani.getOffsetX(),
				State.getBGHEIGHT()*((directionX<0) ? 1 : 2)
				);
		
		/// Set Current HP =-1
		for(int i=curHP-1;i>=0;i--) if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		curHP=-1;
		
		/// Set(X,Y)
		death.setX(State.getRenderX());
		death.setY(State.getRenderY());
	
		/// Remove Walking + Attack
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack)>=0) cf.root.getChildren().remove(attack);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		
		/// Remove All
		deathani.setOnFinished(e->{erase();dead=true;});
		deathani.play();
	}
	public void Hurted(double Damage)
	{
		
		if(curHP<=0) return;
		walkingani.stop(); attackani.stop();
		///Calculate curHP
		double trueDamage = Damage-State.getDefense()*Damage/100.0 ;
		double hp = (State.getHP()-trueDamage>=0) ? State.getHP() - trueDamage : 0;
		int perHP =(int) Math.floor((hp/State.getMaxHP()) * 100.0);
		perHP/=2;
		State.setHP(hp);
		for(int i=curHP-1;i>=perHP;i--) if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		curHP=perHP;
		//System.out.println(curHP);
		if(curHP<=0) {Death();return;}
		
		
		
		SetUpAnimation(
				hurted,
				new Image(ImgLink+"hurted"+"_"+"enemy"+"_"+NTS(State.getType())+".png"),
				hurtedani,
				Duration.millis(500),
				State.getCount_ani_hurted(),
				State.getColumn_ani_hurted(),
				hurtedani.getOffsetX(),
				State.getBGHEIGHT()*((directionX<0) ? 1 : 2)
				);
		
		
		
		///Remove animation
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack)>=0) cf.root.getChildren().remove(attack);
		
		///Set up X,Y
		hurted.setX(State.getRenderX());
		hurted.setY(State.getRenderY());
		
		hurtedani.play();
	}
	
	public void calDirection(Map map,Player player)
	{
		if(attackani.getStatus() == Status.RUNNING || deathani.getStatus() == Status.RUNNING || hurtedani.getStatus() == Status.RUNNING || curHP<=0 || spawnani.getStatus()==Status.RUNNING) return;
		
		
		directionX= State.getX()-player.getX();
		directionY= State.getY()-player.getY();
		
		
		if(directionX*directionX+directionY*directionY <= State.getRange()*State.getRange())
		{
			Attack(directionX,player);
		}
		else
		{
			///Dx = {-1,1,-1,0,0};
			///Dy = {-1,0,0,1,-1};
			if(directionX<=0 && directionY<=0)
			{
				int move = (int)(Math.random()*10) %2;
				if(move==1)
				{
					Move(map,1);
					walkingani.play();
					Move(map,3);
					walkingani.play();
				}
				else
				{
					Move(map,3);
					walkingani.play();
					Move(map,1);
					walkingani.play();
				}
			}
			if(directionX<=0 && directionY>=0)
			{
				int move = (int)(Math.random()*10) %2;
				if(move==1)
				{
					Move(map,1);
					walkingani.play();
					Move(map,4);
					walkingani.play();
				}
				else
				{
					Move(map,4);
					walkingani.play();
					Move(map,1);
					walkingani.play();
				}
			}
			if(directionX>=0 && directionY<=0)
			{
				int move = (int)(Math.random()*10) %2;
				if(move==1)
				{
					Move(map,2);
					walkingani.play();
					Move(map,3);
					walkingani.play();
				}
				else
				{
					Move(map,3);
					walkingani.play();
					Move(map,2);
					walkingani.play();
				}
			}
			if(directionX>=0 && directionY>=0)
			{
				int move = (int)(Math.random()*10) %2;
				if(move==1)
				{
					Move(map,2);
					walkingani.play();
					Move(map,4);
					walkingani.play();
				}
				else
				{
					Move(map,4);
					walkingani.play();
					Move(map,2);
					walkingani.play();
				}
			}
		}
	}
	
	@Override
	public void render(GraphicsContext gc) 
	{
		for(int i=0;i<curHP;i++)
			if(cf.root.getChildren().indexOf(HP[i])<0) cf.root.getChildren().add(HP[i]);
		
		if(spawnani.getStatus() == Status.RUNNING)
		{
			if(cf.root.getChildren().indexOf(spawn)<0) cf.root.getChildren().add(spawn);
			return;
		}
		if(curHP<0 && deathani.getStatus() != Status.RUNNING) return;
		if(curHP<0 && deathani.getStatus() == Status.RUNNING) {if(cf.root.getChildren().indexOf(death)<0) cf.root.getChildren().add(death);return;}
		
		if(hurtedani.getStatus() == Status.RUNNING) if(cf.root.getChildren().indexOf(hurted)<0) cf.root.getChildren().add(hurted);
		if(attackani.getStatus() != Status.RUNNING && hurtedani.getStatus() != Status.RUNNING) if(cf.root.getChildren().indexOf(walking)<0) cf.root.getChildren().add(walking);
		
		if(attackani.getStatus() == Status.RUNNING && hurtedani.getStatus() != Status.RUNNING) if(cf.root.getChildren().indexOf(attack)<0) cf.root.getChildren().add(attack);
		
		if(cf.root.getChildren().indexOf(baseHP)<0) cf.root.getChildren().add(baseHP);
		
		
	}

	@Override
	public void update() 
	{
		
	}

	@Override
	public void erase()
	{
		if(cf.root.getChildren().indexOf(spawn)>=0) cf.root.getChildren().remove(spawn);
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack)>=0) cf.root.getChildren().remove(attack);
		if(cf.root.getChildren().indexOf(death)>=0) cf.root.getChildren().remove(death);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(baseHP)>=0) cf.root.getChildren().remove(baseHP);
		
		for(int i=0;i<100;i++)
			if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
	}
	
	public double getX()
	{
		return this.State.getX();
	}
	public double getY()
	{
		return this.State.getY();
	}
	public void setX(double x)
	{
		this.State.setX(x);
		spawn.setX(State.getRenderX());
	}
	public void setY(double y)
	{
		this.State.setY(y);
		spawn.setY(State.getRenderY());
	}
	
	public int getWidth()
	{
		return this.State.getWIDTH();
	}
	public int getHeight()
	{
		return this.State.getWIDTH();
	}
	public boolean isDead()
	{
		return dead;
	}
 	public boolean checkStep(Map map,double X,double Y)
	{
		for(int i=(int)X-State.getWIDTH()/2;i<=(int)X+State.getWIDTH()/2;i++)
		{
			if(map.isAvailable(1.0*i, Y+State.getHEIGHT()/2)==false) return true;
		}
		return false;
	}
 	public boolean checkStep(Map map,double X,double Y,double width,double height)
 	{
 		for(int i=(int)X-(int)width/2;i<=(int)X+ (int)width/2;i++)
		{
 			for(int j=(int)Y-(int)height/2;j<=(int)Y+(int)height/2;j++)
 				if(map.isAvailable(1.0*i,j)==false) return true;
		}
		return false;
 	}
	
}
