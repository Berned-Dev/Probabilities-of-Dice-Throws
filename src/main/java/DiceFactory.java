public class DiceFactory {

    public static Die makeDie(int dieType){
        Die die = new Die(dieType);
        return die;
    }

    public static void main(String[] args) {
        Die die20 = DiceFactory.makeDie(20);
        Die anotherDie20 = DiceFactory.makeDie(20);
    }
}
