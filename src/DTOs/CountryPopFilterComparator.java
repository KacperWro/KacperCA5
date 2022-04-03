package DTOs;
import java.util.Comparator;

public class CountryPopFilterComparator implements Comparator<Country> {

    @Override
    public int compare(Country c1, Country c2)
    {
        return 50000000 - c1.getPopulation();
    }

}
