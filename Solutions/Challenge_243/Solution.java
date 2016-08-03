// https://www.reddit.com/r/dailyprogrammer/comments/3v4zsf/20151202_challenge_243_intermediate_jennys_fruit/
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Solution {

    private static Map<String, Integer> prices;
    private static Map<String, Integer> count;
    private static List<String> output;

    public static void main(String[] args){
        Scanner input = null;
        prices = new HashMap<>();
        count = new HashMap<>();
        output = new ArrayList<>();
        while (input == null) {
            try {
                Scanner consoleInput = new Scanner(System.in);
                System.out.println("Enter the input file location: ");
                String fileLoc = consoleInput.nextLine().trim();
                File inputFile = new File(fileLoc);
                input = new Scanner(inputFile);
            } catch (FileNotFoundException e) {
                System.out.println("Oops something went wrong. Please try again");
                System.out.println();
            }
        }

        while (input.hasNextLine()){
            String inputLine = input.nextLine();
            String fruitName = inputLine.substring(0, inputLine.indexOf(' '));
            int fruitPrice = Integer.parseInt(inputLine.substring(inputLine.indexOf(' ') + 1));

            prices.put(fruitName, fruitPrice);
            count.put(fruitName, 0);
        }

        bruteForce(500);
        for (String combo: output){
            System.out.println(combo);
        }
    }

    private static void bruteForce(int remainingCents){
        if (remainingCents == 0){
            printResults();
        } else {
            for (String fruit : prices.keySet()) {
                int afterPurchaseBalance = remainingCents - prices.get(fruit);
                if (afterPurchaseBalance >= 0){
                    int fruitCount = count.get(fruit);
                    count.put(fruit, fruitCount + 1);
                    bruteForce(afterPurchaseBalance);
                    count.put(fruit, fruitCount);
                }
            }
        }
    }

    private static void printResults(){
        String outputText = "";
        for (String fruit: count.keySet()){
            int fruitCount = count.get(fruit);
            if (fruitCount > 0){
                if (fruitCount > 0){
                    fruit = fruit + 's';
                }
                outputText += fruitCount + " " + fruit + ", ";
            }
        }
        if (!output.contains(outputText))
            output.add(outputText);
    }
}