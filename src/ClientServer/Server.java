package ClientServer;

import DAOs.CountryDaoInterface;
import DAOs.MySqlCountryDao;
import DTOs.Country;
import Exceptions.DaoException;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Server
{
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            CountryDaoInterface ICountryDao = new MySqlCountryDao();
            Gson gsonParser = new Gson();

            String message;
            final String DISPLAY_COUNTRY_BY_NAME = "1";
            final String DISPLAY_ALL_COUNTRIES = "2";
            final String ADD_NEW_COUNTRY = "3";
            final String DELETE_COUNTRY_BY_NAME = "4";
            final String GET_SUMMARY_DATA = "5";
            final String EXIT = "6";

            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    try{
                        if (message.equals(DISPLAY_COUNTRY_BY_NAME))
                        {
                            String countryName = socketReader.readLine();
                            String countryJsonString = ICountryDao.findCountryByNameJson(countryName);
                            socketWriter.println(countryJsonString);
                        }
                        else if (message.equals(DISPLAY_ALL_COUNTRIES))
                        {
                            String countriesJsonString = gsonParser.toJson(ICountryDao.findAllCountries());
                            socketWriter.println(countriesJsonString);
                        }
                        else if (message.equals(ADD_NEW_COUNTRY))
                        {
                            String countryJsonString = socketReader.readLine();
                            Country newCountry = gsonParser.fromJson(countryJsonString, Country.class);
                            ICountryDao.addCountry(newCountry);

                            newCountry = ICountryDao.findCountryByName(newCountry.getCountryName());
                            countryJsonString = gsonParser.toJson(newCountry);

                            socketWriter.println(countryJsonString);
                        }
                        else if (message.equals(DELETE_COUNTRY_BY_NAME))
                        {
                            String countryName = socketReader.readLine();
                            ICountryDao.deleteCountryByName(countryName);
                            socketWriter.println("Country has been successfully deleted");
                        }
                        else if (message.equals(GET_SUMMARY_DATA))
                        {
                            String summaryDataJson = gsonParser.toJson(ICountryDao.getSummaryData());
                            socketWriter.println(summaryDataJson);
                        }
                        else
                        {
                            socketWriter.println("'" + message + "' is not a valid menu item");
                        }
                    }
                    catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
                    {
                        socketWriter.println("Invalid Parameter");
                    } catch (DaoException e) {
                       socketWriter.println("ERROR: Server has failed to fetch country data");
                    }

                }

                socket.close();

            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}
