package com.noelcurtis.autocomplete;

import java.net.URL;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        try
        {
            if (args == null || args.length == 0)
            {
                System.out.print("Usage `java -jar autocomplete.jar yourfile.txt`");
                System.exit(1);
            }
            String filePath = args[0];

            System.out.println("\nHello, the current dictionary of words is as follows:\n");
            SearchComplete sc = new SearchComplete(filePath);
            sc.initialize();

            //  prompt the user to enter their name
            Scanner scan = new Scanner(System.in);
            while (true)
            {
                System.out.print("\n\nEnter a `exit` to exit");
                System.out.print("\n\nEnter a prefix for a dish (example: `ch`):");
                String dish = scan.nextLine();
                if (dish.equalsIgnoreCase("exit"))
                {
                    break; // exit
                }
                WeightedDish[] dishes = sc.getPossibleDishes(dish);
                printDishes(dishes);
            }
        } catch (Exception ex)
        {
            System.out.println("Error: " + ex.toString());
            System.exit(1);
        }
        System.exit(0);
    }

    private static void printDishes(WeightedDish[] dishes)
    {
        if (dishes == null || dishes.length == 0)
        {
            System.out.println("Nothing found!");
        }

        for (WeightedDish wd : dishes)
        {
            System.out.println(wd.getName());
        }
    }
}
