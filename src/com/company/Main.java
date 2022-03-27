package com.company;

import DAOs.CountryDaoInterface;
import DAOs.MySqlCountryDao;
import DTOs.Country;
import DTOs.CountryPopContinentComparator;
import DTOs.CountryPopulationComparator;
import Exceptions.DaoException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws DaoException {
        app();
    }

    public static void app() throws DaoException {
        CountryDaoInterface ICountryDao = new MySqlCountryDao();

        Scanner input = new Scanner(System.in);
        boolean exit = false;

        List<Country> countryList = ICountryDao.findAllCountries();
//        Map<String, Country> countryHashMap = new HashMap<>();
        Map<Integer, Country> countryTreeMap = new TreeMap<>();
        Queue<Country> countryPriorityQueueSim = new PriorityQueue<>(new CountryPopulationComparator());

        for (Country country : countryList)
        {
//            countryHashMap.put(country.getCountryName().toUpperCase(),country);
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

                switch (option) {
                    case 1:
                        display(countryList);
                        break;
                    case 2:
                        input.nextLine();

                        System.out.print("Enter country name: ");
                        String countryName = input.nextLine();

//                        Country foundCountry = countryHashMap.get(countryName.toUpperCase());//Old version looked for country in Country HashMap
                        Country foundCountry = ICountryDao.findCountryByName(countryName);

                        if (foundCountry != null)
                        {
                            System.out.println();
                            foundCountry.displayCountry();
                        }
                        else
                        {
                            System.out.println("Country not found");
                        }
                        break;
                    case 3:
                        display(countryTreeMap);
                        break;
                    case 4:
//                        countryPriorityQueueSim.add(countryHashMap.get("LIECHTENSTEIN"));
//                        countryPriorityQueueSim.add(countryHashMap.get("ICELAND"));

                        countryPriorityQueueSim.add(ICountryDao.findCountryByName("LIECHTENSTEIN"));
                        countryPriorityQueueSim.add(ICountryDao.findCountryByName("ICELAND"));

                        System.out.println("Added two least populated countries");

//                        countryPriorityQueueSim.add(countryHashMap.get("IRELAND"));
//                        countryPriorityQueueSim.add(countryHashMap.get("DENMARK"));

                        countryPriorityQueueSim.add(ICountryDao.findCountryByName("IRELAND"));
                        countryPriorityQueueSim.add(ICountryDao.findCountryByName("DENMARK"));

                        System.out.println("Added two moderately populated countries");

                        countryPriorityQueueSim.remove().displayCountry();

                        System.out.println("COUNTRY REMOVED AND DISPLAYED");

//                        countryPriorityQueueSim.add(countryHashMap.get("UNITED STATES OF AMERICA"));

                        countryPriorityQueueSim.add(ICountryDao.findCountryByName("UNITED STATES OF AMERICA"));

                        System.out.println("Added one most populated country");

                        display(countryPriorityQueueSim);

                        System.out.println("REMAINING COUNTRIES HAVE BEEN DISPLAYED AND REMOVED");
                        break;
                    case 5:
                        PriorityQueue<Country> pqTwoFields = new PriorityQueue<>(new CountryPopContinentComparator());
                        pqTwoFields.addAll(countryList);

                        display(pqTwoFields);
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid input, please try again");
                        break;
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input, please try again");
                input.nextLine();
            }
        }
    }

    public static void display(List<Country> countryList)
    {
        if (countryList.size() < 1)
        {
            System.out.println("No countries found");
        }
        else
        {
            for (Country country : countryList)
            {
                country.displayCountry();
            }
        }

    }
    public static void display(Map<Integer, Country> countryMap)
    {
        for (Integer key : countryMap.keySet())
        {
            countryMap.get(key).displayCountry();
        }
    }
    public static void display(Queue<Country> countryQueue)
    {
        while(!countryQueue.isEmpty())
        {
            countryQueue.remove().displayCountry();
        }
    }
}

