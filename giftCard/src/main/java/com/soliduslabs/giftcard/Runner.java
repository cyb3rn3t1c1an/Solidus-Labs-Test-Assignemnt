package com.soliduslabs.giftcard;

import com.soliduslabs.giftcard.domain.Gift;
import com.soliduslabs.giftcard.domain.Tuple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Please, provide two arguments: file name and amount of money");
            System.exit(-1);
        }

        String inputFile = args[0];
        int availableMoney = Integer.parseInt(args[1]);

        Tuple<Gift> possiblePair = findGiftPair(inputFile, availableMoney);

        if (possiblePair == null) {
            System.out.println("Not possible");
        } else {
            System.out.println(possiblePair);
        }

    }

    private static Tuple<Gift> findGiftPair(String inputFile, int availableMoney) {
        Tuple<Gift> possiblePair = null;
        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader(new File(inputFile)))) {
            Gift firstGift, secondGift;
            String currentLine1, currentLine2;
            while ((currentLine1 = bufferedReader1.readLine()) != null) {
                firstGift = Gift.fromString(currentLine1);
                try (BufferedReader bufferedReader2 = new BufferedReader(new FileReader(new File(inputFile)))) {
                    while ((currentLine2 = bufferedReader2.readLine()) != null) {

                        if (currentLine1.equals(currentLine2)) continue;

                        secondGift = Gift.fromString(currentLine2);
                        int giftsPrice = firstGift.getPrice() + secondGift.getPrice();
                        if (giftsPrice > availableMoney) {
                            break;
                        }

                        possiblePair = new Tuple<>(firstGift, secondGift);

                        if (giftsPrice == availableMoney) {
                            return possiblePair;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find find file with name " + inputFile);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Unable to read file with name " + inputFile);
            System.exit(-1);
        }
        return possiblePair;
    }
}
