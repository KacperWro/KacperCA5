package DAOs;

import DTOs.Country;
import Exceptions.DaoException;
import java.util.List;

public interface CountryDaoInterface {
    List<Country> findAllCountries() throws DaoException;
    Country findCountryByName(String inputCountryName) throws DaoException;
    void deleteCountryByName(String countryName) throws DaoException;
    void addCountry(Country newCountry) throws DaoException;

}
