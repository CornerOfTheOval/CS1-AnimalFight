import java.util.Random;

public class ExampleCheatingAnimal extends Animal{
   public ExampleCheatingAnimal(int speed, int power, int accuracy){
      // any param unnecessary
      super(speed, power, accuracy);
      super.name = "CHEATER"; // the animal's name
      super.trueName = "ExampleAnimal"; // the creator's name (your name)
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
         heal(10000000);
      }
      
      return 'R';
   }
}