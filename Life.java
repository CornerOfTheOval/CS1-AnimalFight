import java.util.ArrayList;
import java.util.Random;

public class Life
{
   private Random randGen;
   private static boolean healing;
   
   public Life() 
   {
      randGen = new Random();
      healing = false;
   }
   
   public void survive(ArrayList<Animal> theAnimals)
   {
      for(int i = 0; i < theAnimals.size(); i++)
      {
         int opp = randGen.nextInt(theAnimals.size());
         Animal one = theAnimals.get(i);
         Animal two = theAnimals.get(opp);
         
         // returns D->Defend, A->Attack, I->Ignore
         char rOne = one.interact(two);
         char rTwo = two.interact(one);
         
         if (rOne == 'R'){
            System.out.println(one + " attempts to rest...");
         }
         if (rTwo == 'R' && i != opp){
            System.out.println(two + " attempts to rest...");
         }
         
         if(i == opp){
            System.out.println(one + " encounters no one.");
            //System.out.print(rOne); // Debug
         }
         else
         {
            System.out.println(one + " encounters " + two + ".");
            
            

            
            //System.out.print("1: " + rOne + "| 2: " + rTwo + "\n"); // Debug
            
            
            if((rOne == 'I' && rTwo == 'I') || 
               (rOne == 'D' && rTwo == 'D') ||
               (rOne == 'I' && rTwo == 'D') ||
               (rOne == 'D' && rTwo == 'I') ||
               (rOne == 'R' && rTwo == 'R') || 
               (rOne == 'R' && rTwo == 'D') ||
               (rOne == 'D' && rTwo == 'R') ||
               (rOne == 'I' && rTwo == 'R') ||
               (rOne == 'R' && rTwo == 'I')){
               System.out.println("...They ignore each other.");
            }
            else if(rOne == 'A' && rTwo == 'A'){
               System.out.println("...They attack each other!");
               System.out.println(one + " is hit for " + getAttackDamage(two) + "hp!");
               one.setDamage(getAttackDamage(two));
               System.out.println(two + " is hit for " + getAttackDamage(one) + "hp!");
               two.setDamage(getAttackDamage(one));
            }
            else if(rOne == 'A'){
               System.out.println("..." + one + " attacks " + two + "!");
               if (rTwo == 'D'){
                  System.out.println("..." + two + " defends itself!");
                  System.out.println(two + " is hit for " + (getAttackDamage(one) - getNegation(two)) + "hp!");
                  two.setDamage(getAttackDamage(one) - getNegation(two));
               }
               else if (rTwo == 'I'){
                  System.out.println("..." + two + " is caught unaware...");
                  System.out.println(two + " is hit for " + getAttackDamage(one) + "hp!");
                  two.setDamage(getAttackDamage(one));
               }
               else{
                  System.out.println("..." + two + " is caught while vulnerable...");
                  System.out.println(two + " is hit for " + (getAttackDamage(one) * 2) + "hp!");
                  two.setDamage(getAttackDamage(one) * 2);
               }
               
            }
            else if(rTwo == 'A'){
               System.out.println("..." + one + " is attacked by " + two + "!");
               if (rOne == 'D'){
                  System.out.println("..." + one + " defends itself!");
                  System.out.println(one + " is hit for " + (getAttackDamage(two) - getNegation(one)) + "hp!");
                  one.setDamage(getAttackDamage(two) - getNegation(one));
               }
               else if (rOne == 'I'){
                  System.out.println("..." + one + " is caught unaware...");
                  System.out.println(one + " is hit for " + getAttackDamage(two) + "hp!");
                  one.setDamage(getAttackDamage(two));
               }
               else{
                  System.out.println("..." + one + " is caught while vulnerable...");
                  System.out.println(one + " is hit for " + (getAttackDamage(two) * 2) + "hp!");
                  one.setDamage(getAttackDamage(two) * 2);
               }
            }
            
            if (one.getHealth() <= 0){
               System.out.println(one + " dies!");
               theAnimals.remove(one);
               i--;
            }
            if (two.getHealth() <= 0){
               System.out.println(two + " dies!");
               theAnimals.remove(two);
            }
         }
         
         if (rOne == 'R'){
            rest(one);
         }
         if (rTwo == 'R' && i != opp){
            rest(two);
         }
         
         System.out.println("\n");
      }
   }
   
   public String passcode(){
      if (healing){
         healing = false;
         return "healGood";
      }
      else{
         return "";
      }
   }
   
   private int getAttackDamage(Animal animal){
      return (int)((animal.getSpeed() * 0.15) + (animal.getPower() * 0.20) + (animal.getAccuracy() * 0.25));
   }
   
   private int getNegation(Animal animal){
      return (int)((animal.getSpeed() * 0.10) + (animal.getPower() * 0.05) + (animal.getAccuracy() * 0.05));
   }
   
   private void rest(Animal animal){
      int restoredHealth = (int)(Math.pow(animal.getHealth(), -1) * 1000);
      
      if (restoredHealth > 50 || restoredHealth < 0){
         System.out.println("..." + animal + " is hurt to badly to rest!");
      }
      else{
         System.out.println("..." + animal + " rests successfully and restores " + restoredHealth + "hp.");
         healing = true;
         animal.heal(restoredHealth, "healGood");
      }
   }
}