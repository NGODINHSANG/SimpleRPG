package simplerpg.Adventurer;


import java.util.ArrayList;

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
import simplerpg.Enemy.Bullet;
import simplerpg.Enemy.Enemy;
import simplerpg.SpriteAnimation;
import simplerpg.Map.Map;

public class Player extends BaseObj
{
	private Config cf = Config.getInstance();
	
	private String ImgLink = cf.getLink();
	
	private ImageView walking = new ImageView();
	private ImageView attack1= new ImageView();
	private ImageView death = new ImageView();
	private ImageView hurted = new ImageView();
	private ImageView attack2 = new ImageView();
	private ImageView attack3 = new ImageView();
	
	private SpriteAnimation walkingani;
	private SpriteAnimation attackani1;
	private SpriteAnimation deathani;
	private SpriteAnimation hurtedani;
	private SpriteAnimation attackani2;
	private SpriteAnimation attackani3;
	PlayerState State = PlayerState.getState();
	
	private ImageView baseHP = new ImageView();
	private int curHP = 50;
	private ImageView[] HP = new ImageView[100];
	
	public Player()
	{
		
		///Walking animation
		walking.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		walking.setImage(new Image(ImgLink+"walking"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		walkingani =  new SpriteAnimation(walking, Duration.millis(800), 9, 9, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Attack animation
		attack1.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		attack1.setImage(new Image(ImgLink+"slash"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		attackani1 = new SpriteAnimation(attack1, Duration.millis(800), 6, 6, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Attack animation
		attack2.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		attack2.setImage(new Image(ImgLink+"thrust"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		attackani2 = new SpriteAnimation(attack2, Duration.millis(800), 6, 6, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
			
		///Attack animation
		attack3.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		attack3.setImage(new Image(ImgLink+"bow"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		attackani3 = new SpriteAnimation(attack3, Duration.millis(800), 11, 11, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
						
		///Death animation
		death.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		death.setImage(new Image(ImgLink+"attacking"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		deathani = new SpriteAnimation(death, Duration.millis(800), 6, 6, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Hurted animation
		hurted.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		hurted.setImage(new Image(ImgLink+"hurted"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		hurtedani = new SpriteAnimation(hurted, Duration.millis(200), 2, 2, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///HP
		baseHP.setImage(new Image(ImgLink+"base_hp"+".png"));
		for(int i=0;i<curHP;i++)
		{
			HP[i] = new ImageView();
			HP[i].setImage(new Image(ImgLink+"HP"+".png"));
		}
		
	}
	@Override
	public void render(GraphicsContext gc) 
	{
		if(cf.root.getChildren().indexOf(baseHP)<0) cf.root.getChildren().add(baseHP);
		
		for(int i=0;i<curHP;i++)
			if(cf.root.getChildren().indexOf(HP[i])<0) cf.root.getChildren().add(HP[i]);
		
		if(curHP<0 && deathani.getStatus() != Status.RUNNING) return;
		if(curHP<0 && deathani.getStatus() == Status.RUNNING) {if(cf.root.getChildren().indexOf(death)<0) cf.root.getChildren().add(death);return;}
		
		if(hurtedani.getStatus() == Status.RUNNING) if(cf.root.getChildren().indexOf(hurted)<0) cf.root.getChildren().add(hurted);
		if(attackani1.getStatus() != Status.RUNNING && hurtedani.getStatus() != Status.RUNNING && attackani2.getStatus() != Status.RUNNING && attackani3.getStatus() != Status.RUNNING) if(cf.root.getChildren().indexOf(walking)<0) cf.root.getChildren().add(walking);
		
		if(attackani1.getStatus() == Status.RUNNING && hurtedani.getStatus() != Status.RUNNING && attackani2.getStatus() != Status.RUNNING && attackani3.getStatus() != Status.RUNNING) if(cf.root.getChildren().indexOf(attack1)<0) cf.root.getChildren().add(attack1);
		
		if(attackani1.getStatus() != Status.RUNNING && hurtedani.getStatus() != Status.RUNNING && attackani2.getStatus() == Status.RUNNING && attackani3.getStatus() != Status.RUNNING) if(cf.root.getChildren().indexOf(attack2)<0) cf.root.getChildren().add(attack2);
	
		if(attackani1.getStatus() != Status.RUNNING && hurtedani.getStatus() != Status.RUNNING && attackani2.getStatus() != Status.RUNNING && attackani3.getStatus() == Status.RUNNING) if(cf.root.getChildren().indexOf(attack3)<0) cf.root.getChildren().add(attack3);
	}
	private int[] Dx = {0,0,-1,0,1};
	private int[] Dy = {0,-1,0,1,0};
	private void SetUpAnimation(ImageView IV,Image Img,SpriteAnimation Ani,Duration duration,int Count,int Columns,int offsetX,int offsetY)
	{
		IV.setImage(Img);
		Ani.setDuration(duration);
		Ani.setCount(Count);
		Ani.setColumns(Columns);
		Ani.setOffsetX(offsetX);
		Ani.setOffsetY(offsetY);
	}
	private int curDirection = 1;
	public void Move(Map map,int type)
	{
		curDirection =type;
		if(attackani1.getStatus() != Status.STOPPED || curHP<0 || hurtedani.getStatus() != Status.STOPPED || attackani2.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED) return;
		
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		
		
		SetUpAnimation(
				walking,
				new Image(ImgLink+"walking"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				walkingani,
				Duration.millis(500),
				9,
				9,
				walkingani.getOffsetX(),
				State.getBGHEIGHT()*type
				);
		
		walkingani.setOnFinished(e->{});
		walkingani.play();
		
		double step = State.getRunningSpeed();
		if(checkStep(map,State.getX()+Dx[type]*step,State.getY()+Dy[type]*step)==false)
		{
			State.setY(State.getY()+step*Dy[type]);
			State.setX(State.getX()+step*Dx[type]);
			
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
	private boolean checkRange01(Enemy enemy)
	{
		System.out.println(curDirection);
		if(curDirection==1 && State.getY()>=enemy.getY() && State.getY()-enemy.getY() <= State.getRange() && Math.abs(enemy.getX()-State.getX())<=State.getWidthRange()) return true;
		if(curDirection==2 && State.getX()>=enemy.getX() && State.getX()-enemy.getX() <= State.getRange() && Math.abs(enemy.getY()-State.getY())<=State.getWidthRange()) return true;
		if(curDirection==3 && enemy.getY()>=State.getY() && enemy.getY()-State.getY() <= State.getRange() && Math.abs(enemy.getX()-State.getX())<=State.getWidthRange()) return true;
		if(curDirection==4 && enemy.getX()>=State.getX() && enemy.getX()-State.getX() <= State.getRange() && Math.abs(enemy.getY()-State.getY())<=State.getWidthRange()) return true;
		return false;
	}
	private boolean checkRange02(Enemy enemy)
	{
		if(curDirection==1 && State.getY()>=enemy.getY() && State.getY()-enemy.getY() <= State.getRange()+10 && Math.abs(enemy.getX()-State.getX())<=State.getWidthRange()-5) return true;
		if(curDirection==2 && State.getX()>=enemy.getX() && State.getX()-enemy.getX() <= State.getRange()+10 && Math.abs(enemy.getY()-State.getY())<=State.getWidthRange()-5) return true;
		if(curDirection==3 && enemy.getY()>=State.getY() && enemy.getY()-State.getY() <= State.getRange()+10 && Math.abs(enemy.getX()-State.getX())<=State.getWidthRange()-5) return true;
		if(curDirection==4 && enemy.getX()>=State.getX() && enemy.getX()-State.getX() <= State.getRange()+10 && Math.abs(enemy.getY()-State.getY())<=State.getWidthRange()-5) return true;
		return false;
	}
	public void Attack01(int direction,ArrayList<Enemy> enemyList)
	{
		if(curHP<0 || hurtedani.getStatus()!=Status.STOPPED || attackani2.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED) return;
		
		SetUpAnimation(
				attack1,
				new Image(ImgLink+"slash"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				attackani1,
				Duration.millis(1000*State.getAttackSpeed()),
				6,
				6,
				attackani1.getOffsetX(),
				State.getBGHEIGHT()*direction
				);
		
		/// Set (X,Y)
		attack1.setX(State.getRenderX());
		attack1.setY(State.getRenderY());
		
		/// Remove Walking
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		
		attackani1.setOnFinished(e->
		{
			if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
			for(int i=0;i<enemyList.size();i++)
			{
				Enemy enemy=enemyList.get(i);
				if(enemy.getClass() == Bullet.class) continue;
				if(checkRange01(enemy)==true )
				{
					enemy.Hurted(State.DealDamage());
				}
			}
		});
		attackani1.play();
	}
	public void Attack02(int direction,ArrayList<Enemy> enemyList)
	{
		if(curHP<0 || hurtedani.getStatus()!=Status.STOPPED || attackani1.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED) return;
		
		SetUpAnimation(
				attack2,
				new Image(ImgLink+"thrust"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				attackani2,
				Duration.millis(1500*State.getAttackSpeed()),
				6,
				6,
				attackani2.getOffsetX(),
				State.getBGHEIGHT()*direction
				);
		
		/// Set (X,Y)
		attack2.setX(State.getRenderX());
		attack2.setY(State.getRenderY());
		
		/// Remove Walking
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		
		attackani2.setOnFinished(e->
		{
			if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
			for(int i=0;i<enemyList.size();i++)
			{
				Enemy enemy=enemyList.get(i);
				if(enemy.getClass() == Bullet.class) continue;
				if(checkRange02(enemy)==true )
				{
					enemy.Hurted(1.2*State.DealDamage());
				}
			}
		});
		attackani2.play();
	}
	private boolean target = false;
	Enemy targetEnemy ;
	public void Attack03(int direction,ArrayList<Enemy> enemyList)
	{
		if(curHP<0 || hurtedani.getStatus()!=Status.STOPPED || attackani1.getStatus()!=Status.STOPPED || attackani2.getStatus()!=Status.STOPPED) return;
		
		target=false;
		
		double minDis = State.getRange()+200;
		targetEnemy = new Enemy();
		for(int i=0;i<enemyList.size();i++)
		{
			Enemy enemy=enemyList.get(i);
			if(enemy.getClass() == Bullet.class || enemy.isDead()==true) continue;
			double dis = (enemy.getX()-State.getX()) * (enemy.getX()-State.getX()) + (enemy.getY() - State.getY()) * (enemy.getY()- State.getY());
			if(minDis*minDis>dis)
			{
				targetEnemy = enemy;
				target= true;
			}
		}
		if(target==true)
		{
			double limitX = State.getX()+minDis;
			double limitY = State.getY();
			
			double Xu = targetEnemy.getX()-State.getX();
			double Yu = targetEnemy.getY()-State.getY();
			double Xv = limitX-State.getX();
			double Yv = limitY-State.getY();
			
			double Angle = Math.acos((Xu*Xv+Yu*Yv)/(Math.sqrt(Xu*Xu+Yu*Yu)*Math.sqrt(Xv*Xv+Yv*Yv)));
			Angle=Angle*180/Math.PI;
			System.out.println(Angle);
			if(Angle <= 45) direction =4;
			else if(Angle <= 135 && Yu<=0) direction =1;
			else if(Angle <= 135 && Yu>=0) direction =3;
			else if(Angle <=180) direction = 2;
		}
		curDirection=direction;
		
		SetUpAnimation(
				attack3,
				new Image(ImgLink+"bow"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				attackani3,
				Duration.millis(1500*State.getAttackSpeed()),
				11,
				11,
				attackani3.getOffsetX(),
				State.getBGHEIGHT()*direction
				);
		
		/// Set (X,Y)
		attack3.setX(State.getRenderX());
		attack3.setY(State.getRenderY());
		
		/// Remove Walking
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		
		attackani3.setOnFinished(e->
		{
			if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
			if(target==true)
				GameField.getInstance().addArrow(new Arrow(curDirection,State.getX(),State.getY(),targetEnemy.getX(),targetEnemy.getY(),0.4*State.DealDamage(),NTS(Launcher.PlayerId)));
		});
		attackani3.play();
	}
	public void Death()
	{
		
		SetUpAnimation(
				death,
				new Image(ImgLink+"death"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				deathani,
				Duration.millis(1000),
				6,
				6,
				deathani.getOffsetX(),
				State.getBGHEIGHT()
				);
		
		/// Set Current HP =-1
		for(int i=curHP-1;i>=0;i--) if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		curHP=-1;
		
		/// Set(X,Y)
		death.setX(State.getRenderX());
		death.setY(State.getRenderY());
		
		/// Remove Walking + Attack
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		/// RemoveAll
		deathani.setOnFinished(e->{erase();Launcher.dead=1;});
		
		deathani.play();
	}
	public void Hurted(double Damage)
	{
		if(curHP<=0) return;
		double trueDamage = (State.getEvasion()==false) ? Damage-State.getDefense()*Damage/100.0 : 0;
		double hp = (State.getHP()-trueDamage>=0) ? State.getHP() - trueDamage : 0;
		int perHP =(int) Math.floor((hp/State.getMaxHP()) * 100.0);
		perHP/=2;
		State.setHP(hp);
		
		for(int i=curHP-1;i>=perHP;i--) if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		curHP=perHP;
		if(curHP<=0) {Death();return;}
		
		walkingani.stop(); attackani1.stop();attackani2.stop();attackani3.stop();
		
		SetUpAnimation(
				hurted,
				new Image(ImgLink+"hurted"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				hurtedani,
				Duration.millis(200),
				2,
				2,
				hurtedani.getOffsetX(),
				State.getBGHEIGHT()*curDirection
				);
		
		
		
		///Remove animation
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		
		///Set up X,Y
		hurted.setX(State.getRenderX());
		hurted.setY(State.getRenderY());
		
		hurtedani.play();
	}
	@Override
	public void erase() 
	{
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		if(cf.root.getChildren().indexOf(death)>=0) cf.root.getChildren().remove(death);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(baseHP)>=0) cf.root.getChildren().remove(baseHP);
		
		for(int i=0;i<100;i++)
			if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		
	}	
	@Override
	public void update() 
	{
		
	}
	public boolean isFighting()
	{
		return State.isFighting();
	}
	public double getX()
	{
		return State.getX();
	}
	public double getY()
	{
		return State.getY();
	}
	public void setX(double x)
	{
		State.setX(x);
		walking.setX(State.getRenderX());
	}
	public void setY(double y)
	{
		State.setY(y);
		walking.setY(State.getRenderY());
	}
	public int getWidth()
	{
		return this.State.getWIDTH();
	}
	public int getHeight()
	{
		return this.State.getHEIGHT();
	}
	public double getHP()
	{
		return this.State.getHP();
	}
	public boolean checkStep(Map map,double X,double Y)
	{
		for(int i=(int)X-State.getWIDTH()/2;i<=(int)X+State.getWIDTH()/2;i++)
		{
			if(map.isAvailable(1.0*i, Y+State.getHEIGHT()/2)==false) return true;
		}
		return false;
	}
	
}
