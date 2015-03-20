
public abstract class Animal
{
   protected String name;
   protected String trueName;
   private int health;
   private int speed;
   private int power;
   private int accuracy;
   
   public Animal(int speed, int power, int accuracy)
   {
      this.health = 100;
      this.speed = speed;
      this.power = power;
      this.accuracy = accuracy;
   }
   
   final public int getHealth() { return this.health; }
   final public int getSpeed() { return this.speed; }
   final public int getPower() { return this.power; }
   final public int getAccuracy() { return this.accuracy; }
   
   final public void setDamage(int damage){
      if(damage > 0)
         this.health -= damage;
   }
   
   final public void heal(int amount){
      if(new Life().healCheck()){
         this.health += amount;
      }
      else{
         System.out.println(this.name + " is killed for cheating!");
         this.health = 0;
      }
   }
   
   final public String toString(){
      return getName() + " [" + getTrueName() + "]";
   }
   
   // ToDo: vVv these abstract methods vVv + constructor
   public abstract String getName();
   public abstract String getTrueName();
   
   // returns D->Defend, A->Attack, I->Ignore, R->Rest
   public abstract char interact(Animal other);
}