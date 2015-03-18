
public abstract class Animal
{
   protected String name;
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
   
   final public int getAttackDamage(){
      return (int)((this.speed * 0.15) + (this.power * 0.20) + (this.accuracy * 0.25));
   }
   
   final public int getNegation(){
      return (int)((this.speed * 0.10) + (this.power * 0.05) + (this.accuracy * 0.05));
   }
   
   final public char run(Animal other){
      char choice = interact(other);
      
      if (choice == 'I'){
         System.out.println(this.name + " decides to rest to restore " + ((int)(Math.pow(this.health, -1) * 1000)) + "hp!");
         this.health += (int)(Math.pow(this.health, -1) * 1000);
      }
      
      return choice;
   }
   
   // ToDo: vVv these abstract methods vVv
   public abstract String getName();
   
   // returns D->Defend, A->Attack, I->Ignore
   public abstract char interact(Animal other);
}