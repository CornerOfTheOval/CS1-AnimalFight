import java.util.Random;

public class FastAnimal extends ExampleAnimal{
   public FastAnimal(String trueName){
      super("Cheetah", trueName, new Random().nextInt(25) + 25, new Random().nextInt(5) + 25, 15);
   }
}