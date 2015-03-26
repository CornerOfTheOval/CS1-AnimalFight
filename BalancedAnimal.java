import java.util.Random;

public class BalancedAnimal extends ExampleAnimal{
   public BalancedAnimal(String trueName){
      super("Human", trueName, new Random().nextInt(15) + 25, new Random().nextInt(5) + 25, new Random().nextInt(5) + 25);
   }
}