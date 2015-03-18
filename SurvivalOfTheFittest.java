import java.util.ArrayList;

public class SurvivalOfTheFittest
{
   private static ArrayList<Animal> animals = new ArrayList<Animal>();
   public static Life life = new Life();
   
   public static void main(String[] args)
   {
      addAnimals();
      checkForCheaters();
      
      //run life as long as there's at least two animals left
      while(animals.size() > 1)
         life.survive(animals);
         
      if (animals.size() > 0)   
         System.out.println("The sole survivor is... " + animals.get(0).toString().toUpperCase());
      else
         System.out.println("There is no survivor...");
   }
   
   public static void checkForCheaters()
   {
      for(int i = 0; i < animals.size();)
      {
         Animal checkedAnimal = animals.get(i);
         
         if(checkedAnimal.getHealth() <= checkedAnimal.getSpeed()
                                      + checkedAnimal.getPower()
                                      + checkedAnimal.getAccuracy()){
            System.out.println(checkedAnimal + " has decided to cheat...");
            animals.remove(i);
            continue;                                
         }
         
         i++;
      }
   }
   
   public static void addAnimals()
   {
      animals.add(new ExampleAnimal("One"));
      animals.add(new ExampleAnimal("Two"));
      animals.add(new ExampleAnimal("Three"));
      animals.add(new ExampleAnimal("Four"));
      animals.add(new ExampleAnimal("Five"));
   }
   
}