package DAOs;

import DTOs.Country;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCountryDao extends MySqlDao implements CountryDaoInterface {

    @Override
    public List<Country> findAllCountries() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Country> countryList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM countries";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int countryID = resultSet.getInt("countryID");
                String continent = resultSet.getString("continent");
                String countryName = resultSet.getString("countryName");
                String capital = resultSet.getString("capital");
                int population = resultSet.getInt("population");
                double areaSqKm = resultSet.getDouble("areaSqKm");
                double popDensitySqKm = resultSet.getDouble("popDensitySqKm");
                Country country = new Country(countryID, continent, countryName, capital, population, areaSqKm, popDensitySqKm );
                countryList.add(country);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("findAllCountriesResultSet() " + e.getMessage());
        }
        finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("findAllCountries() " + e.getMessage());
            }
        }
        return countryList;
    }
    @Override
    public Country findCountryByName(String inputCountryName) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Country country = null;

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM countries WHERE countryName = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputCountryName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int countryID = resultSet.getInt("countryID");
                String continent = resultSet.getString("continent");
                String countryName = resultSet.getString("countryName");
                String capital = resultSet.getString("capital");
                int population = resultSet.getInt("population");
                double areaSqKm = resultSet.getDouble("areaSqKm");
                double popDensitySqKm = resultSet.getDouble("popDensitySqKm");

                country = new Country(countryID, continent, countryName, capital, population, areaSqKm, popDensitySqKm );

            }
        }
        catch (SQLException e)
        {
            throw new DaoException("findCountryByName() " + e.getMessage());
        }
        finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findCountryByName() " + e.getMessage());
            }
        }
        return country;     // reference to User object, or null value
    }
    @Override
    public void deleteCountryByName(String countryName) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try
        {
            connection = this.getConnection();

            String query = "DELETE FROM countries WHERE countryName = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, countryName);
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("deleteCountryByName() " + e.getMessage());
        } finally
        {
            try
            {
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("deleteCountryByName() " + e.getMessage());
            }
        }
    }

}
