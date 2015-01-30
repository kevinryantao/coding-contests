package codechef.justmultiply;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by ktao on 1/25/15.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numTestCases = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < numTestCases; i++){
            String[] input = scanner.nextLine().split(" ");

            long modulo = Long.parseLong(input[0]);

            Main main = new Main();

            System.out.println(main.solve(modulo, input[1]));
        }


    }

    private String solve(long modulo, String wholeExpression) {
        // step one, split the expression up into strings.
        String[] expressionArray = wholeExpression.split("\\*\\*");
        BigInteger bigModulo = new BigInteger(Long.toString(modulo));

        BigInteger lastBase = new BigInteger(expressionArray[0]).mod(bigModulo);

        BigInteger currentTotal = new BigInteger("1");

        for(int i = 1; i < expressionArray.length; i++){
            String[] split = expressionArray[i].split("\\*");
            String exponent = split[0];
            BigInteger bigExponent = new BigInteger(exponent);
            BigInteger pow = lastBase.modPow(bigExponent, bigModulo);
            currentTotal = (currentTotal.multiply(pow)).mod(bigModulo);

            if(split.length > 1){
                String newBase = split[1];
                lastBase = new BigInteger(newBase);
            }
        }

        return currentTotal.toString();
    }
}
