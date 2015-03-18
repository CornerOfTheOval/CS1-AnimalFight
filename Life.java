import java.util.ArrayList;
import java.util.Random;

public class Life
{
   private Random randGen;
   
   public Life() 
   {
      randGen = new Random();
   }
   
   public void survive(List<Animal> theAnimals)
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
            // returns D->Defend, A->attack, I->Ignore
            
            char rOne = one.interact(two);
            
            
            char rTwo = two.interact(one);
            
            
            if((rOne == 'I' && rTwo == 'I') || 
               (rOne == 'D' && rTwo == 'D') ||
               (rOne == 'I' && rTwo == 'D') ||
               (rOne == 'D' && rTwo == 'I'))
               System.out.println("...They ignore each other.");
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
               
            }
            else if(rOne == 'A' && rTwo == 'A'){
               System.out.println("...They both attack each other!");
            }
            else if(rOne == '
            
         }
      }
   }

}