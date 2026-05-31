package michalmm.jtools;

import java.util.random.RandomGenerator;

public class ThrowDice {

    void main(String[] args) {
        if (args.length != 1) {
            help();
        }

        var number = getDiceNumber(args[0]);
        var randomNumber = RandomGenerator.getDefault().nextInt(1, number+1);
        IO.println("Throwing " + number + "-dice --> " + randomNumber);
    }

    private static int getDiceNumber(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            help();
            return -1;
        }
    }

    private static void help() {
        IO.println("Usage: ThrowDice <n-dice-number>");
        System.exit(1);
    }
}
