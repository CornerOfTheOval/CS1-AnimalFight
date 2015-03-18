import java.util.ArrayList;

public class SurvivalOfTheFittest
{
   private static ArrayList<Animal> animals = new ArrayList<Animal>();
   private static Life life = new Life();
   
   public static void main(String[] args)
   {
      addAnimals();
      checkForCheaters();
      
      //run life as long as there's at least two animals left
      while(animals.size() > 1)
         life.survive(animals);
         
      System.out.println("The sole survivor is... " + animals.get(0).toUpperCase());
   }
   
   public void checkForCheaters()
   {
      for(int i = 0; i < animals.size();)
      {
         Animal checkedAnimal = animals.get(i);
         
         if(checkedAnimal.getHealth() < checkedAnimal.getSpeed()
                                      + checkedAnimal.getPower()
                                      + checkedAnimal.getAccuracy()){
            animals.remove(i);
            continue;                                
         }
         
         i++;
      }
   }
   
   public void addAnimals()
   {
      
   }
   
}