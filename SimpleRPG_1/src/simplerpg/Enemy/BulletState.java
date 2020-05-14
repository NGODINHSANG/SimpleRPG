package simplerpg.Enemy;

public class BulletState 
{
	private int bgwidth;
	private int bgheight;
	private String Name;
	private String NameHit;
	private int Column_ani_bullet;
	private int Count_ani_bullet;
	private int Row_ani_bullet;
	
	private int Column_ani_bullethit;
	private int Count_ani_bullethit;
	private int Row_ani_bullethit;
	
	private double Range;
	private double Speed;
	private double Damage;
	private int type;
	public BulletState(int id) 
	{
		this.type=id;
		switch(type)
		{
			case 3:
				this.bgwidth = 32;
				this.bgheight = 32;
				
				this.Column_ani_bullet =3 ;
				this.Count_ani_bullet = 3;
				this.Row_ani_bullet = 2;
				
				this.Column_ani_bullethit =3 ;
				this.Count_ani_bullethit = 3;
				this.Row_ani_bullethit = 2;
				
				this.Name = "bullet_enemy_03.png";
				this.NameHit = "bullethit_enemy_03.png";
					
				this.Damage = 40;
				this.Range = 15;
				this.Speed = 1;
				break;
		}
	}
	public int getBgwidth() {
		return bgwidth;
	}
	public int getBgheight() {
		return bgheight;
	}
	public int getColumn_ani_bullet() {
		return Column_ani_bullet;
	}
	public int getCount_ani_bullet() {
		return Count_ani_bullet;
	}
	public int getRow_ani_bullet() {
		return Row_ani_bullet;
	}
	public int getColumn_ani_bullethit() {
		return Column_ani_bullethit;
	}
	public int getCount_ani_bullethit() {
		return Count_ani_bullethit;
	}
	public int getRow_ani_bullethit() {
		return Row_ani_bullethit;
	}
	public double getRange() {
		return Range;
	}
	public double getSpeed() {
		return Speed;
	}
	public int getType() {
		return type;
	}
	public double getDamage() {
		return Damage;
	}
	public String getName() {
		return Name;
	}
	public String getNameHit() {
		return NameHit;
	}
	
	
}
