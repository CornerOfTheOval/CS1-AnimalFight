import java.util.Random;

public class ExampleAnimal extends Animal{
   public ExampleAnimal(String name){
      super(50, 25, 15);
      super.name = name;
   }

   public String toString(){
      return super.name;
   }
   
   public char interact(Animal animal){
      if (new Random().nextBoolean()){
         return 'A';
      }
      else if (new Random().nextBoolean()){
         return 'D';
      }
      else if (new Random().nextBoolean()){
         return 'I';
      }
      else{
         return 'R';
      }
   }
}