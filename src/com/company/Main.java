package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        app();
    }

    public static void app()
    {
        Scanner input = new Scanner(System.in);

        int option;
        boolean exit = false;

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Ireland","Dublin",5011500,70273,71.3));
        countries.add(new Country("United Kingdom","London",67081000,242495,270.7));
        countries.add(new Country("Iceland","Reykjavik",366425,103000,3.5));
        countries.add(new Country("Luxembourg","Luxembourg City",633622,2586.4,242));
        countries.add(new Country("Liechtenstein","Vaduz",38896,160,237));
        countries.add(new Country("Denmark","Copenhagen",5873420,42933 ,137.65));
        countries.add(new Country("Poland","Warsaw",38179800,312696 ,123));
        countries.add(new Country("Germany","Berlin", 83190556,357022 ,232));
        countries.add(new Country("United States of America","Washington",331893745,9833520,33.6));
        countries.add(new Country("France","Paris",67413000,	643801,116));

        while (!exit)
        {
            try
            {
                System.out.println("\n************************");
                System.out.println("1. Display all countries");
                System.out.println("2. Exit");
                System.out.println("************************\n");

                System.out.print("Select menu item:");
                option = input.nextInt();

                if (option == 1)
                {
                    for (Country country1 : countries)
                    {
                        country1.displayCountry();
                    }
                }
                else if (option == 2)
                {
                    exit = true;
                }
                else
                {
                    System.out.println("Invalid input, please try again");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input, please try again");
                input.nextLine();
            }


        }

    }


}
