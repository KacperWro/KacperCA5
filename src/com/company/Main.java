package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        app();
    }

    public static void app()
    {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        ArrayList<Country> countryList = new ArrayList<>();
        countryList.add(new Country("Ireland","Dublin",5011500,70273,71.3));
        countryList.add(new Country("United Kingdom","London",67081000,242495,270.7));
        countryList.add(new Country("Iceland","Reykjavik",366425,103000,3.5));
        countryList.add(new Country("Luxembourg","Luxembourg City",633622,2586.4,242));
        countryList.add(new Country("Liechtenstein","Vaduz",38896,160,237));
        countryList.add(new Country("Denmark","Copenhagen",5873420,42933 ,137.65));
        countryList.add(new Country("Poland","Warsaw",38179800,312696 ,123));
        countryList.add(new Country("Germany","Berlin", 83190556,357022 ,232));
        countryList.add(new Country("United States of America","Washington",331893745,9833520,33.6));
        countryList.add(new Country("France","Paris",67413000,	643801,116));

        HashMap<String, Country> countryHashMap = new HashMap<>();
        TreeMap<Integer, Country> countryTreeMap = new TreeMap<>();

        for (Country country : countryList)
        {
            countryHashMap.put(country.getCountryName().toUpperCase(),country);
            countryTreeMap.put(country.getPopulation(), country);
        }

        while (!exit)
        {
            try
            {
                System.out.println("\n*************************************");
                System.out.println("1. Display all countries");
                System.out.println("2. Display country by name");
                System.out.println("3. Display all countries by population");
                System.out.println("4. Exit");
                System.out.println("*************************************\n");

                System.out.print("Select menu item:");
                int option = input.nextInt();

                if (option == 1)
                {
                    for (Country country : countryList)
                    {
                        country.displayCountry();
                    }
                }
                else if (option == 2)
                {
                    input.nextLine();

                    System.out.print("Enter country name: ");
                    String countryName = input.nextLine();

                    Country foundCountry = countryHashMap.get(countryName.toUpperCase());

                    if (foundCountry != null)
                    {
                        System.out.println();
                        foundCountry.displayCountry();
                    }
                    else
                    {
                        System.out.println("Country not found");
                    }
                }
                else if (option == 3)
                {
                    for (Integer key : countryTreeMap.keySet())
                    {
                        countryTreeMap.get(key).displayCountry();
                    }
                }
                else if (option == 4)
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
