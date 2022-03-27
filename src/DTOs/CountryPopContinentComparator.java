package DTOs;
import java.util.Comparator;

public class CountryPopContinentComparator implements Comparator<Country> {

    @Override
    public int compare(Country c1, Country c2)
    {
        if (c1.getContinent().equalsIgnoreCase(c2.getContinent()))
        {
            return c1.getPopulation() - c2.getPopulation();
        }
        else
        {
            return c1.getContinent().compareTo(c2.getContinent());
        }
    }

}
