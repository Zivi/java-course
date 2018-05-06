import java.util.Random;

public class DiceRolling {
    // calculates the randomness of rolling a pair of dice
    public void simpleSimulate(int rolls) {
        Random rand = new Random();
        int [] counts = new int [13];
        
        for (int k = 0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counts[d1+d2] += 1;
        }
        
        for (int i = 2; i <= 12; i ++) {
            System.out.println(i + "'s=\t" + counts[i] + "\t" + 100.0 * counts[i]/rolls);    
        }
    }
}
