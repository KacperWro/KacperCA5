package ClientServer;

import DTOs.Country;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        Gson gsonParser = new Gson();

        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

            boolean exit = false;
            final String DISPLAY_COUNTRY_BY_NAME = "1";
            final String DISPLAY_ALL_COUNTRIES = "2";
            final String ADD_NEW_COUNTRY = "3";
            final String DELETE_COUNTRY_BY_NAME = "4";
            final String EXIT = "5";

            while (!exit)
            {
                try
                {
                    System.out.println("\n*************************************");
                    System.out.println("1. Display country by name");
                    System.out.println("2. Display all countries");
                    System.out.println("3. Add new country");
                    System.out.println("4. Delete country by name");
                    System.out.println("5. Exit");
                    System.out.println("*************************************\n");

                    System.out.print("Select menu item:");
                    String option = in.next();

                    socketWriter.println(option);

                    if(option.equals(DISPLAY_COUNTRY_BY_NAME))
                    {
                        in.nextLine();

                        System.out.print("Enter country name: ");
                        String countryName = in.nextLine();
                        socketWriter.println(countryName);

                        String countryJsonString = socketReader.nextLine();

                        Country country = gsonParser.fromJson(countryJsonString, Country.class);

                        if(country != null)
                        {
                            country.displayCountry();
                        }
                        else
                        {
                            System.out.println("Country not found");
                        }

                    }
                    else if(option.equals(DISPLAY_ALL_COUNTRIES))
                    {
                        Type countryListType = new TypeToken<ArrayList<Country>>(){}.getType();
                        String countriesJsonString = socketReader.nextLine();
                        List<Country> countryList = gsonParser.fromJson(countriesJsonString, countryListType);

                        display(countryList);
                    }
                    else if (option.equals(ADD_NEW_COUNTRY))
                    {
                        int population;
                        double areaSqKm;
                        double popDensitySqKm;

                        in.nextLine();

                        System.out.print("Enter continent name: ");
                        String continent = in.nextLine();

                        System.out.print("Enter country name: ");
                        String countryName = in.nextLine();

                        System.out.print("Enter name of capital city: ");
                        String capital = in.nextLine();

                        do
                        {
                            System.out.print("Enter population (positive whole number): ");
                            population = in.nextInt();
                        }
                        while(population < 0);

                        do
                        {
                            System.out.print("Enter area in km^2 (positive number): ");
                            areaSqKm = in.nextDouble();
                        }
                        while(areaSqKm < 0);

                        do
                        {
                            System.out.print("Enter population density in km^2 (positive number): ");
                            popDensitySqKm = in.nextInt();
                        }
                        while(popDensitySqKm < 0);

                        String countryJsonString = gsonParser.toJson(new Country(continent, countryName, capital, population, areaSqKm, popDensitySqKm));
                        socketWriter.println(countryJsonString);

                        countryJsonString = socketReader.nextLine();
                        Country newCountry = gsonParser.fromJson(countryJsonString, Country.class);

                        if (newCountry != null)
                        {
                            System.out.println("Country has been successfully added");
                            newCountry.displayCountry();
                        }
                        else
                        {
                            System.out.println("Failed to add new country");
                        }
                    }
                    else if (option.equals(EXIT))
                    {
                        exit = true;
                    }
                    else if (option.equals(DELETE_COUNTRY_BY_NAME))
                    {
                        in.nextLine();

                        System.out.print("Enter country name: ");
                        String countryName = in.nextLine();

                        socketWriter.println(countryName);

                        System.out.println(socketReader.nextLine());
                    }
                    else
                    {
                        String input = socketReader.nextLine();
                        System.out.println("Client message: Response from server: \"" + input + "\"");
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid input, please try again");
                }
            }

            System.out.println("Closing application........");

            socketWriter.close();
            socketReader.close();
            socket.close();

        }
        catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
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
}


