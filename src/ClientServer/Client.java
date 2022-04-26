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
            final String EXIT = "3";

            while (!exit)
            {
                System.out.println("\n*************************************");
                System.out.println("1. Display country by name");
                System.out.println("2. Display all countries");
                System.out.println("3. Exit");
                System.out.println("*************************************\n");

                System.out.print("Select menu item:");
                String option = in.next();

                socketWriter.println(option);

                if(option.equals(DISPLAY_COUNTRY_BY_NAME))
                {
                    //APPEND COUNTRY NAME TO SOCKETREADER.NEXTLINE()

                    in.nextLine();

                    System.out.print("Enter country name: ");
                    String countryName = in.nextLine();
                    socketWriter.println(countryName);

                    String countryJsonString = socketReader.nextLine();

                    Country country = gsonParser.fromJson(countryJsonString, Country.class);
                    country.displayCountry();
                }
                else if(option.equals(DISPLAY_ALL_COUNTRIES))
                {
                    Type countryListType = new TypeToken<ArrayList<Country>>(){}.getType();
                    String countriesJsonString = socketReader.nextLine();
                    List<Country> countryList = gsonParser.fromJson(countriesJsonString, countryListType);

                    display(countryList);
                }
                else if (option.equals(EXIT))
                {
                    exit = true;
                }
                else
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
            }

            System.out.println("Closing application........");

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
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


