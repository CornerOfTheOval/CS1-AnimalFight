import java.util.ArrayList;
import java.util.Random;

public class Life
{
   private Random randGen;
   private static boolean healing;
   
   public Life() 
   {
      randGen = new Random();
   }
   
   public void survive(ArrayList<Animal> theAnimals)
   {
      for(int i = 0; i < theAnimals.size(); i++)
      {
         int opp = randGen.nextInt(theAnimals.size());
         Animal one = theAnimals.get(i);
         Animal two = theAnimals.get(opp);
         boolean oneHit = false;
         boolean twoHit = false;
         
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
               twoHit = true;
               oneHit = true;
            }
            else if(rOne == 'A'){
               System.out.println("..." + one + " attacks " + two + "!");
               twoHit = true;
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
               oneHit = true;
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
         }
         
         if (rOne == 'R'){
            rest(one, oneHit);
         }
         if (rTwo == 'R' && i != opp){
            rest(two, twoHit);
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
         
         System.out.println("\n");
      }
   }
   
   public boolean healCheck(){
      if (healing){
         healing = false;
         return true;
      }
      else{
         return false;
      }
   }
   
   private int getAttackDamage(Animal animal){
      return (int)((animal.getSpeed() * 0.15) + (animal.getPower() * 0.20) + (animal.getAccuracy() * 0.25));
   }
   
   private int getNegation(Animal animal){
      return (int)((animal.getSpeed() * 0.10) + (animal.getPower() * 0.05) + (animal.getAccuracy() * 0.05));
   }
   
   private boolean getDodge(Animal animal){
      int dodgeChance = (int)((animal.getSpeed() * 0.25) + (animal.getAccuracy() * 0.1));
      
      if (new Random().nextInt(90) + 10 < new Random().nextInt(dodgeChance)){
         return true;
      }
      return false;
   }
   
   private boolean getHit(Animal animal){
      int hitChance = (int)((animal.getSpeed() * 0.1) + (animal.getAccuracy() * 0.25));
      
      if (new Random().nextInt(90) + 10 < new Random().nextInt(hitChance)){
         return true;
      }
      return false;
   }
   
   private void rest(Animal animal, boolean hit){
      int restoredHealth = (int)(Math.pow(animal.getHealth(), -1) * 1000);
      
      if (restoredHealth > 50 || restoredHealth < 0){
         System.out.println("..." + animal + " is hurt to badly to rest!");
      }
      else if (hit){
         System.out.println("..." + animal + " was attacked while attempting to rest!");
      }
      else{
         System.out.println("..." + animal + " rests successfully and restores " + restoredHealth + "hp.");
         healing = true;
         animal.heal(restoredHealth);
      }
   }
   
   public void checkForCheaters(ArrayList<Animal> animals)
   {
      for(int i = 0; i < animals.size();)
      {
         Animal checkedAnimal = animals.get(i);
         
         if(100 <= (checkedAnimal.getSpeed()
                  + checkedAnimal.getPower()
                  + checkedAnimal.getAccuracy())){
            System.out.println(checkedAnimal + " is disqualified for trying to cheat...");
            animals.remove(i);
            continue;                                
         }
         
         i++;
      }
   }
   
   public static void sortAnimals(ArrayList<Animal> animals){
      Animal fastestAnimal = null;
      ArrayList<Animal> newAnimals = new ArrayList<Animal>(animals.size());
      while (animals.size() > 0){
         fastestAnimal = animals.get(0);
         for (Animal newAnimal : animals){
            if (newAnimal.getSpeed() > fastestAnimal.getSpeed()){
               fastestAnimal = newAnimal;
            }
         }
         newAnimals.add(fastestAnimal);
         animals.remove(fastestAnimal);
      }
      animals.addAll(newAnimals);
   }
}