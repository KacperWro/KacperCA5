package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        app();
    }

    public static void app()
    {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        ArrayList<Country> countryList = new ArrayList<>();
        countryList.add(new Country("Europe", "Ireland","Dublin",5011500,70273,71.3));
        countryList.add(new Country("Europe","United Kingdom","London",67081000,242495,270.7));
        countryList.add(new Country("Europe","Iceland","Reykjavik",366425,103000,3.5));
        countryList.add(new Country("Europe","Luxembourg","Luxembourg City",633622,2586.4,242));
        countryList.add(new Country("Europe","Liechtenstein","Vaduz",38896,160,237));
        countryList.add(new Country("North America", "Mexico", "Mexico City", 126014024,1972550,61));
        countryList.add(new Country("Europe","Denmark","Copenhagen",5873420,42933 ,137.65));
        countryList.add(new Country("Europe","Poland","Warsaw",38179800,312696 ,123));
        countryList.add(new Country("Europe","Germany","Berlin", 83190556,357022 ,232));
        countryList.add(new Country("North America", "United States of America","Washington",331893745,9833520,33.6));
        countryList.add(new Country("Europe","France","Paris",67413000,	643801,116));
        countryList.add(new Country("North America", "Canada", "Ottawa", 38436447,9984670,4.2));

        HashMap<String, Country> countryHashMap = new HashMap<>();
        TreeMap<Integer, Country> countryTreeMap = new TreeMap<>();
        PriorityQueue<Country> countryPriorityQueueSim = new PriorityQueue<>(new CountryPopulationComparator());

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
                System.out.println("4. PriorityQueue Sequence Simulation");
                System.out.println("5. PriorityQueue Two-Field Comparison Demo");
                System.out.println("6. Exit");
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
                    countryPriorityQueueSim.add(countryHashMap.get("LIECHTENSTEIN"));
                    countryPriorityQueueSim.add(countryHashMap.get("ICELAND"));

                    System.out.println("Added two least populated countries");

                    countryPriorityQueueSim.add(countryHashMap.get("IRELAND"));
                    countryPriorityQueueSim.add(countryHashMap.get("DENMARK"));

                    System.out.println("Added two moderately populated countries");

                    countryPriorityQueueSim.remove().displayCountry();

                    System.out.println("COUNTRY REMOVED AND DISPLAYED");

                    countryPriorityQueueSim.add(countryHashMap.get("UNITED STATES OF AMERICA"));

                    System.out.println("Added one most populated country");

                    while(!countryPriorityQueueSim.isEmpty())
                    {
                        countryPriorityQueueSim.remove().displayCountry();
                    }

                    System.out.println("REMAINING COUNTRIES HAVE BEEN DISPLAYED AND REMOVED");
                }
                else if (option == 5)
                {
                    PriorityQueue<Country> pqTwoFields = new PriorityQueue<>(new CountryPopContinentComparator());
                    pqTwoFields.addAll(countryList);

                    while(!pqTwoFields.isEmpty())
                    {
                        pqTwoFields.remove().displayCountry();
                    }
                }
                else if (option == 6)
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

