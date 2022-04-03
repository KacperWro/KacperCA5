package DAOs;

import DTOs.Country;
import Exceptions.DaoException;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlCountryDaoTest {
    Gson gsonParser = new Gson();

    @Test
    void findAllCountriesTest() throws DaoException {
        MySqlCountryDao countryDao = new MySqlCountryDao();

        List<Country> expectedResult = new ArrayList<>();
        expectedResult.add(new Country(1, "Europe", "Ireland","Dublin",5011500,70273,71.3));
        expectedResult.add(new Country(2, "Europe", "United Kingdom","London",67081000,242495,270.7));
        expectedResult.add(new Country(3, "Europe","Iceland","Reykjavik",366425,103000,3.5));
        expectedResult.add(new Country(4, "Europe","Luxembourg","Luxembourg City",633622,2586.4,242));
        expectedResult.add(new Country(5, "Europe","Liechtenstein","Vaduz",38896,160,237));
        expectedResult.add(new Country(6, "North America", "Mexico", "Mexico City", 126014024,1972550,61));
        expectedResult.add(new Country(7, "Europe", "Denmark","Copenhagen",5873420,42933 ,137.65));
        expectedResult.add(new Country(8, "Europe","Poland","Warsaw",38179800,312696 ,123));
        expectedResult.add(new Country(9, "Europe","Germany","Berlin", 83190556,357022 ,232));
        expectedResult.add(new Country(10, "North America", "United States of America","Washington",331893745,9833520,33.6));
        expectedResult.add(new Country(11, "Europe","France","Paris",67413000,	643801,116));
        expectedResult.add(new Country(12, "North America", "Canada", "Ottawa", 38436447,9984670,4.2));

        List<Country> actualResult = countryDao.findAllCountries();

        System.out.println("Testing Feature 7: findAllCountries()");
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void findCountryByNameTest() throws DaoException {
        MySqlCountryDao countryDao = new MySqlCountryDao();

        Country expectedResult = new Country(11, "Europe","France","Paris",67413000,	643801,116);
        Country actualResult = countryDao.findCountryByName("France");

        System.out.println("Testing Feature 8: findCountryByName()");
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void findAllCountriesJsonTest() throws DaoException {
        MySqlCountryDao countryDao = new MySqlCountryDao();

        List<Country> expectedResult = new ArrayList<>();
        expectedResult.add(new Country(1, "Europe", "Ireland","Dublin",5011500,70273,71.3));
        expectedResult.add(new Country(2, "Europe", "United Kingdom","London",67081000,242495,270.7));
        expectedResult.add(new Country(3, "Europe","Iceland","Reykjavik",366425,103000,3.5));
        expectedResult.add(new Country(4, "Europe","Luxembourg","Luxembourg City",633622,2586.4,242));
        expectedResult.add(new Country(5, "Europe","Liechtenstein","Vaduz",38896,160,237));
        expectedResult.add(new Country(6, "North America", "Mexico", "Mexico City", 126014024,1972550,61));
        expectedResult.add(new Country(7, "Europe", "Denmark","Copenhagen",5873420,42933 ,137.65));
        expectedResult.add(new Country(8, "Europe","Poland","Warsaw",38179800,312696 ,123));
        expectedResult.add(new Country(9, "Europe","Germany","Berlin", 83190556,357022 ,232));
        expectedResult.add(new Country(10, "North America", "United States of America","Washington",331893745,9833520,33.6));
        expectedResult.add(new Country(11, "Europe","France","Paris",67413000,	643801,116));
        expectedResult.add(new Country(12, "North America", "Canada", "Ottawa", 38436447,9984670,4.2));

        List<String> expectedResult1 = new ArrayList<>();

        for (Country country : expectedResult)
        {
            expectedResult1.add(gsonParser.toJson(country));
        }

        List<String> actualResult = countryDao.findAllCountriesJson();

        System.out.println("Testing Feature 11: findAllCountriesJson()");
        assertEquals(expectedResult1, actualResult);

    }

    @Test
    void findCountryByNameJsonTest() throws DaoException {
        MySqlCountryDao countryDao = new MySqlCountryDao();

        String expectedResult ="{\"id\":1,\"continent\":\"Europe\",\"countryName\":\"Ireland\",\"capital\":\"Dublin\",\"population\":5011500,\"areaSqKm\":70273.0,\"popDensitySqKm\":71.3}";
        String actualResult = countryDao.findCountryByNameJson("Ireland");

        System.out.println("Testing Feature 12: findCountryByNameJson()");
        assertEquals(expectedResult, actualResult);

    }

}