import java.util.Random;

public class ExampleAnimal extends Animal{
   public ExampleAnimal(String name, String trueName, int speed, int power, int accuracy){
      // any param unnecessary
      super(speed, power, accuracy);
      super.name = name; // the animal's name
      super.trueName = trueName; // the creator's name (your name)
   }

   public String getName(){
      return super.name;
   }
   
   public String getTrueName(){
      return super.trueName;
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