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
         
      if (animals.size() > 0)   
         System.out.println("The sole survivor is... " + animals.get(0).toString().toUpperCase());
      else
         System.out.println("It is a cruel world...");
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
      animals.add(new ExampleAnimal("Two", 25));
      animals.add(new ExampleAnimal("Four", 20));
      animals.add(new ExampleAnimal("Three", 22));
      animals.add(new ExampleAnimal("Five", 10));
      animals.add(new ExampleAnimal("One", 30));
   }
   
   public static void sortAnimals(){
      Animal fastestAnimal = new ExampleAnimal("speedTest", 0);
      ArrayList<Animal> newAnimals = new ArrayList<Animal>(animals.size());
      while (animals.size() > 0){
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