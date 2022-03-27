package DAOs;

import DTOs.Country;
import Exceptions.DaoException;
import java.util.List;

public interface CountryDaoInterface {
    List<Country> findAllCountries() throws DaoException;

}
