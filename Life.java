import java.util.ArrayList;
import java.util.Random;

public class Life
{
   private Random randGen;
   
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
         
         if(i == opp) 
            System.out.println(one.getName() + " encounters no one.");
         else
         {
            System.out.println(one.getName() + " encounters " + two.getName());
            // returns D->Defend, A->Attack, I->Ignore
            
            char rOne = one.run(two);
            
            
            char rTwo = two.run(one);
            
            
            if((rOne == 'I' && rTwo == 'I') || 
               (rOne == 'D' && rTwo == 'D') ||
               (rOne == 'I' && rTwo == 'D') ||
               (rOne == 'D' && rTwo == 'I'))
               System.out.println("...They ignore each other.");
            else if(rOne == 'A' && rTwo == 'A'){
               System.out.println("...They attack each other!");
               System.out.println(one.getName() + " is hit for " + two.getAttackDamage() + "hp!");
               one.setDamage(two.getAttackDamage());
               System.out.println(two.getName() + " is hit for " + one.getAttackDamage() + "hp!");
               two.setDamage(one.getAttackDamage());
            }
            else if(rOne == 'A'){
               System.out.println("..." + one.getName() + " attacks " + two.getName() + "!");
               if (rTwo == 'D'){
                  System.out.println("..." + two.getName() + " defends itself!");
                  System.out.println(two.getName() + " is hit for " + (one.getAttackDamage() - two.getNegation()) + "hp!");
                  two.setDamage(one.getAttackDamage() - two.getNegation());
               }
               else{
                  System.out.println("..." + two.getName() + " is caught unaware...");
                  System.out.println(two.getName() + " is hit for " + one.getAttackDamage() + "hp!");
                  two.setDamage(one.getAttackDamage());
               }
               
            }
            else if(rTwo == 'A'){
               System.out.println(".." + one.getName() + " is attacked by " + two.getName() + "!");
               if (rOne == 'D'){
                  System.out.println("..." + one.getName() + " defends itself!");
                  System.out.println(one.getName() + " is hit for " + (two.getAttackDamage() - one.getNegation()) + "hp!");
                  one.setDamage(two.getAttackDamage() - one.getNegation());
               }
               else{
                  System.out.println("..." + one.getName() + " is caught unaware...");
                  System.out.println(one.getName() + " is hit for " + two.getAttackDamage() + "hp!");
                  one.setDamage(two.getAttackDamage());
               }
            }
            
            if (one.getHealth() <= 0){
               System.out.println(one.getName() + " dies!");
               theAnimals.remove(i);
               i--;
            }
            if(two.getHealth() <= 0){
               System.out.println(two.getName() + " dies!");
               theAnimals.remove(opp);
            }
         }
      }
   }

}