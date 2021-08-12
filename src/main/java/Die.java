import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Die {

    private final int sides;
    private int value;
    private int[] probabilities = {};

    public Die(int dieSides) {
        this.sides = dieSides;
    }

    public Die(int dieSides, int[] dieProbabilities){
        this.sides = dieSides;
        this.probabilities = setProbabilities(dieProbabilities);
    }

    public int getSides(){
        return sides;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void roll(){
        Random number = new Random();
        int x = number.nextInt(getSides());
        if(probabilities.length != 0) {
            setValue(probabilities[x]);
        }
        else {
            setValue(x+1);
        }
    }

    public int[] setProbabilities(int[] probabilities){
        ArrayList<Integer> probabilitiesToSet = new ArrayList<>();

        for (int i = 0; i < sides; i++) {
            for (int j = 0; j < probabilities[i]; j++) {
                probabilitiesToSet.add(i+1);
            }
        }
        int[] newProbabilitiesArray = probabilitiesToSet.stream().mapToInt(i->i).toArray();

        int sumOfProbabilities = 0;
        boolean negativeNumber = false;
        boolean nonIntegerNumber = false;

        for (int i : probabilities){

            sumOfProbabilities += i;
            if (i < 0){
                negativeNumber = true;
            }
            if(i != (int)i){
                nonIntegerNumber = true;
            }
        }
        if(sumOfProbabilities < 1){
            throw new ProbabilitiesException("probability sum must be greater than 0");
        }
        if (negativeNumber){
            throw new ProbabilitiesException("negative probabilities not allowed");
        }
        if(nonIntegerNumber){
            throw new ProbabilitiesException("only integer values allowed");
        }
        return newProbabilitiesArray;
    }
    public static void main(String[] args) throws Exception {
        Die die6 = new Die(6);
        die6.roll();
        System.out.println(die6.value);

        Die dieDodgy6 = new Die(6, new int[]{1,1,1,1,1,2});
        Die dieFair6 = new Die(6, new int[]{1,1,1,1,1,1});

        Die die20 = DiceFactory.makeDie(20);
    }
}