import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Life
{
   private Random randGen;
   private static boolean healing;
   
   public Life() 
   {
      randGen = new Random();
   }
   
   public void survive(ArrayList<Animal> theAnimals, ArrayList<Animal> deadAnimals)
   {
   
      for(int i = 0; i < theAnimals.size(); i++)
      {
         int opp = randGen.nextInt(theAnimals.size());
         Animal one = theAnimals.get(i);
         Animal two = theAnimals.get(opp);
         boolean oneHit = false;
         boolean twoHit = false;
         int oneDmg = 0;
         int twoDmg = 0;
         
         // returns D->Defend, A->Attack, I->Ignore
         char rOne = one.interact(two);
         char rTwo = two.interact(one);
         
         System.out.println(maintainLength(50, "[" + one.getTrueName() + "]"));
         
         if (rOne == 'R'){
            System.out.println(one + " attempts to rest...");
         }
         if (rTwo == 'R' && i != opp){
            System.out.println(two + " attempts to rest...");
         }
         
         if(i == opp){
            System.out.println(deadAnimals);
            System.out.println(one + " encounters no one.");
            //System.out.print(rOne); // Debug
         }
         else{
         
            System.out.println(one + " encounters " + two + "...");
            
            //System.out.print("1: " + rOne + "| 2: " + rTwo + "\n"); // Debug
            
            if (one.getSpeed() >= two.getSpeed()){
               if(rOne == 'A'){
                  twoDmg = attack(one, two, rTwo);
                  two.setDamage(twoDmg);
                  if (rTwo == 'A'){
                     if (checkDead(two)){
                        oneDmg = (attack(two, one, rOne) / 2);
                        theAnimals.remove(two);
                        deadAnimals.add(two);
                     }
                     else{
                        oneDmg = attack(two, one, rOne);
                     }
                  }
               }
               one.setDamage(oneDmg);
            }
            else{
               if(rTwo == 'A'){
                  oneDmg = attack(two, one, rOne);
                  one.setDamage(oneDmg);
                  if (rOne == 'A'){
                     if (checkDead(one)){
                        twoDmg = (attack(one, two, rTwo) / 2);
                        theAnimals.remove(one);
                        deadAnimals.add(one);
                     }
                     else{
                        twoDmg = attack(one, two, rTwo);
                     }
                  }
               }
               two.setDamage(twoDmg);
            }
            
            if (oneDmg == 0 && twoDmg == 0){
               System.out.println("...Nothing really happened.");
            }
            
            
         }
         
         if (one.getHealth() <= 0){
            System.out.println(one + " dies!");
            if (theAnimals.contains(one)){
               theAnimals.remove(one);
               deadAnimals.add(one);
            }
            i--;
         }
         if (two.getHealth() <= 0){
            System.out.println(two + " dies!");
            if (theAnimals.contains(two)){
               theAnimals.remove(two);
               deadAnimals.add(two);
            }
            
            if (opp < i){
               i--;
            }
         }
         
         if (rOne == 'R' && theAnimals.contains(one)){
            rest(one, (oneDmg > 0) ? true : false);
         }
         if (rTwo == 'R' && theAnimals.contains(two) && i != opp){
            rest(two, (twoDmg > 0) ? true : false);
         }
         
         
         
         System.out.println(maintainLength(50, "") +"\n");
         showStats(theAnimals, deadAnimals);
      }
   }
   
   private void showStats(ArrayList<Animal> animals, ArrayList<Animal> deadAnimals){
      System.out.println("Stats:");
      System.out.println(animals);
      System.out.println(deadAnimals);
   }
   
   private boolean checkDead(Animal animal){
      if (animal.getHealth() <= 0){
         System.out.println(animal + " is dying!");
         animal.reduceStats();
         return true;
      }
      return false;
   }
   
   private int attack(Animal attacker, Animal defender, char defDec){
      int attackDamage = 0;
   
      System.out.println("..." + attacker + " attacks " + defender + "...");
      if (getHit(attacker)){
         if (!getDodge(defender)){
            if (defDec == 'D'){
               System.out.println("..." + defender + " defends itself...");
               attackDamage += getAttackDamage(attacker) - getNegation(defender);
            }
            else if (defDec == 'I'){
               System.out.println("..." + defender + " is caught unaware...");
               attackDamage += getAttackDamage(attacker);
            }
            else{
               System.out.println("..." + defender + " is caught while vulnerable...");
               attackDamage += getAttackDamage(attacker) * 2;
            }
         }
         else{
            System.out.println("..." + defender + " dodges...");
         }
      }
      else{
         System.out.println("..." + attacker + " misses...");
      }
      
      if (attackDamage > 0 && getCritical(attacker) && getHit(attacker)){
         System.out.println("..." + attacker + " hits for critical damage...");
         attackDamage *= 2;
      }
      
      System.out.println("..." + defender + " is hit for " + attackDamage + "hp!\n");
      
      return attackDamage;
   }
   
   private String maintainLength(int length, String word){
      String completeWord = "";
      length -= word.length();
   
      if (length % 2 == 0){
         length /= 2;
         for (int i = 0; i < length; i++){
            completeWord += "-";
         }
         completeWord += word;
         for (int i = 0; i < length; i++){
            completeWord += "-";
         } 
      }
      else{
         length /= 2;
         for (int i = 0; i < length; i++){
            completeWord += "-";
         }
         completeWord += word;
         for (int i = 0; i < (length + 1); i++){
            completeWord += "-";
         } 
      }
      
      return completeWord;
   }
   
   private int getAttackDamage(Animal animal){
      return (int)((animal.getSpeed() * 0.05) + (animal.getPower() * 0.30) + (animal.getAccuracy() * 0.25));
   }
   
   private int getNegation(Animal animal){
      return (int)((animal.getSpeed() * 0.10) + (animal.getPower() * 0.15) + (animal.getAccuracy() * 0.05));
   }
   
   private boolean getCritical(Animal animal){
      int critChance = (int)((animal.getPower() * 0.15) + (animal.getSpeed() * 0.1));
      if (critChance <= 0){
         return false;
      }
      
      
      if (new Random().nextInt(critChance / 4) + 10 < new Random().nextInt(critChance / 2) + critChance){
         return true;
      }
      return false;
   }
   
   private boolean getDodge(Animal animal){
      int dodgeChance = (int)((animal.getSpeed() * 0.15) + (animal.getAccuracy() * 0.05));
      if (dodgeChance <= 0){
         return false;
      }
      
      if (new Random().nextInt(dodgeChance / 4) + 10 < new Random().nextInt(dodgeChance / 2) + dodgeChance){
         return true;
      }
      return false;
   }
   
   private boolean getHit(Animal animal){
      int hitChance = (int)((animal.getSpeed() * 0.10) + (animal.getAccuracy() * 0.40));
      if (hitChance <= 0){
         return false;
      }
      
      if (new Random().nextInt(hitChance / 4) + 10 < new Random().nextInt(hitChance / 2) + hitChance){
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
   
   public int compare(Animal firstAnimal, Animal secondAnimal){
      return firstAnimal.getSpeed() - secondAnimal.getSpeed();
   }
   
   public void checkForCheaters(ArrayList<Animal> animals)
   {
      for(int i = 0; i < animals.size();)
      {
         Animal checkedAnimal = animals.get(i);
         
         if((100 < (checkedAnimal.getSpeed()
                  + checkedAnimal.getPower()
                  + checkedAnimal.getAccuracy())) ||
             (50 < checkedAnimal.getSpeed() ||
              50 < checkedAnimal.getPower() ||
              50 < checkedAnimal.getAccuracy())){
            System.out.println(checkedAnimal + " is disqualified for trying to cheat...");
            animals.remove(i);
            continue;                                
         }
         
         i++;
      }
   }
   
   public static void sortAnimals(ArrayList<Animal> animals){
      Collections.sort(animals);
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
}