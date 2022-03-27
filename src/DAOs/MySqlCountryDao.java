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

            String query = "SELECT * FROM COUNTRIES";
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
        } catch (SQLException e)
        {
            throw new DaoException("findAllCountriesResultSet() " + e.getMessage());
        } finally
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

}
