package simplerpg.Enemy;

public class EnemyState 
{	
	private int type;
	
	private double X;
	private double Y;
	
	private int width;
	private int height;
	private int bgwidth;
	private int bgheight;
	
	private int Column_ani_running ;
	private int Count_ani_running ;
	private int Row_ani_running;
	
	private int Column_ani_attack;
	private int Count_ani_attack ;
	private int Row_ani_attack;
	
	private int Column_ani_death;
	private int Count_ani_death;
	private int Row_ani_death;
	
	private int Column_ani_hurted;
	private int Count_ani_hurted;
	private int Row_ani_hurted;
	
	private int Column_ani_spawn;
	private int Count_ani_spawn;
	private int Row_ani_spawn;
	
	private double MaxHP;
	private double HP;
	private double Damage;
	private double Defense;
	private double RunningSpeed;
	private double AttackSpeed;
	private double Range;
	
	public EnemyState(int Id)
	{
		this.type=Id;
		switch(Id)
		{
			case 1:
				this.height = 29;
				this.width  = 25;
				this.bgheight=50;
				this.bgwidth=50;
				
				this.Column_ani_running = 5;
				this.Count_ani_running = 5;
				this.Row_ani_running = 2;
				
				this.Column_ani_attack = 5;
				this.Count_ani_attack = 5;
				this.Row_ani_attack =2;
				
				this.Column_ani_death = 5;
				this.Count_ani_death = 5;
				this.Row_ani_death = 2;
				
				this.Column_ani_spawn = 5;
				this.Count_ani_spawn = 5;
				this.Row_ani_spawn = 2;
				
				this.Column_ani_hurted = 2;
				this.Count_ani_hurted =2;
				this.Row_ani_hurted = 2;
				
				this.MaxHP=100;
				this.HP = 100;
				this.Damage = 10;
				this.Defense = 5;
				this.RunningSpeed = 0.5;
				this.AttackSpeed = 0.4;
				this.Range = 25;
				break;
			case 2:
				this.height = 42;
				this.width = 18;
				this.bgheight=64;
				this.bgwidth= 64;
				
				this.Column_ani_running = 4;
				this.Count_ani_running = 4;
				this.Row_ani_running = 2;
				
				this.Column_ani_attack = 4;
				this.Count_ani_attack = 4;
				this.Row_ani_attack = 2;
				
				this.Column_ani_death = 7;
				this.Count_ani_death = 7;
				this.Row_ani_death = 2;
				
				this.Column_ani_spawn = 7;
				this.Count_ani_spawn = 7 ;
				this.Row_ani_spawn = 2;
				
				this.Column_ani_hurted = 3;
				this.Count_ani_hurted =3;
				this.Row_ani_hurted = 2;
				
				this.MaxHP =100;
				this.HP = 100;
				this.Damage = 10;
				this.Defense = 5;
				this.RunningSpeed = 0.5;
				this.AttackSpeed = 0.5;
				this.Range = 24;
				break;
			case 3:
				this.height = 35;
				this.width = 29;
				this.bgheight=64;
				this.bgwidth=64;
				
				
				
				this.Column_ani_running = 9;
				this.Count_ani_running = 9;
				this.Row_ani_running = 2;
				
				this.Column_ani_attack = 11;
				this.Count_ani_attack = 11;
				this.Row_ani_attack = 2;
				
				this.Column_ani_spawn = 8;
				this.Count_ani_spawn = 8;
				this.Row_ani_spawn = 2;
				
				this.Column_ani_death = 8;
				this.Count_ani_death = 8;
				this.Row_ani_death = 2;
				
				this.Column_ani_hurted = 3;
				this.Count_ani_hurted =3;
				this.Row_ani_hurted = 2;
				
				this.MaxHP =200;
				this.HP = 200;
				this.Damage = 40;
				this.Defense = 5;
				this.RunningSpeed = 0.5;
				this.AttackSpeed = 1.5;
				this.Range = 125;
				
				break;
		}
	}
	public int getColumn_ani_running() {
		return Column_ani_running;
	}

	public int getCount_ani_running() {
		return Count_ani_running;
	}

	public int getRow_ani_running() {
		return Row_ani_running;
	}

	public int getColumn_ani_attack() {
		return Column_ani_attack;
	}

	public int getCount_ani_attack() {
		return Count_ani_attack;
	}

	public int getRow_ani_attack() {
		return Row_ani_attack;
	}

	public int getColumn_ani_death() {
		return Column_ani_death;
	}

	public int getCount_ani_death() {
		return Count_ani_death;
	}

	public int getRow_ani_death() {
		return Row_ani_death;
	}
	
	public int getColumn_ani_hurted() {
		return Column_ani_hurted;
	}
	
	public int getCount_ani_hurted() {
		return Count_ani_hurted;
	}
	
	public int getRow_ani_hurted() {
		return Row_ani_hurted;
	}
	
	public double getMaxHP() {
		return MaxHP;
	}
	
	public int getColumn_ani_spawn() {
		return Column_ani_spawn;
	}
	public int getCount_ani_spawn() {
		return Count_ani_spawn;
	}
	public int getRow_ani_spawn() {
		return Row_ani_spawn;
	}
	public void setMaxHP(double maxHP) {
		MaxHP = maxHP;
	}
	
	public double getHP() {
		return HP;
	}

	public void setHP(double hP) {
		HP = hP;
	}

	public double getDamage() {
		return Damage;
	}

	public void setDamage(double damage) {
		Damage = damage;
	}

	public double getDefense() {
		return Defense;
	}

	public void setDefense(double defense) {
		Defense = defense;
	}

	public double getRunningSpeed() {
		return RunningSpeed;
	}

	public void setRunningSpeed(double runningSpeed) {
		RunningSpeed = runningSpeed;
	}

	public double getAttackSpeed() {
		return AttackSpeed;
	}

	public void setAttackSpeed(double attackSpeed) {
		AttackSpeed = attackSpeed;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setRange(double range) {
		Range = range;
	}
	
	public double getX()
	{
		return this.X;
	}
	public double getY()
	{
		return this.Y;
	}
	public void setX(double X)
	{
		this.X=X;
	}
	public void setY(double Y)
	{
		this.Y=Y;
	}
	public void setWIDTH(int width)
	{
		this.width=width;
	}
	public void setHEIGHT(int height)
	{
		this.height=height;
	}
	public int getWIDTH()
	{
		return this.width;
	}
	public int getHEIGHT()
	{
		return this.height;
	}
	public void setBGWIDTH(int width)
	{
		this.bgwidth=width;
	}
	public void setBGHEIGHT(int height)
	{
		this.bgheight=height;
	}
	public int getBGWIDTH()
	{
		return this.bgwidth;
	}
	public int getBGHEIGHT()
	{
		return this.bgheight;
	}
	public double getRange()
	{
		return this.Range;
	}
	public int getType()
	{
		return this.type;
	}
	
	public double getRenderX()
	{
		return this.X-this.bgwidth/2;
	}
	public double getRenderY()
	{
		return this.Y-this.bgheight/2;
	}
}
