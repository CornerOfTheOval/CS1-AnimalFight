import java.util.Random;

public class PowerfulAnimal extends ExampleAnimal{
   public PowerfulAnimal(String trueName){
      super("Lion", trueName, 20, new Random().nextInt(25) + 25, new Random().nextInt(5) + 25);
   }
}