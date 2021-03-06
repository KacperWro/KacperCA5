package DAOs;

import ClientServer.SummaryData;
import DTOs.Country;
import DTOs.CountryPopFilterComparator;
import Exceptions.DaoException;
import java.util.List;

public interface CountryDaoInterface {
    List<Country> findAllCountries() throws DaoException;
    Country findCountryByName(String inputCountryName) throws DaoException;
    void deleteCountryByName(String countryName) throws DaoException;
    void addCountry(Country newCountry) throws DaoException;
    List<Country> findCountriesUsingFilter(CountryPopFilterComparator filter) throws DaoException;
    List<String> findAllCountriesJson() throws DaoException;
    String findCountryByNameJson(String countryName) throws DaoException;
    SummaryData getSummaryData() throws DaoException;

}
