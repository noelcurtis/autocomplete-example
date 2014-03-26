package com.noelcurtis.autocomplete;

import java.net.URL;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("\nHello, the current dictionary of words is as follows:\n");
            URL url = App.class.getClassLoader().getResource("seeddata.txt");
            SearchComplete sc = new SearchComplete(url.getPath());
            sc.initialize();

            //  prompt the user to enter their name
            Scanner scan = new Scanner(System.in);
            while (true)
            {
                System.out.print("\n\nEnter a prefix for a dish (example: `ch`):");
                String dish = scan.nextLine();
                WeightedDish[] dishes = sc.getPossibleDishes(dish);
                printDishes(dishes);
                if (dish.equalsIgnoreCase("exit"))
                {
                    break; // exit
                }
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
