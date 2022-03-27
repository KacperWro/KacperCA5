package DTOs;

import java.util.Objects;

public class Country {
    //Fields
    private int id;
    private String continent;
    private String countryName;
    private String capital;
    private int population;
    public double areaSqKm;
    public double popDensitySqKm;

    //Constructor
    public Country(int id, String continent, String countryName, String capital, int population, double areaSqKm, double popDensitySqKm) {
        this.id = id;
        this.continent = continent;
        this.countryName = countryName;
        this.capital = capital;
        this.population = population;
        this.areaSqKm = areaSqKm;
        this.popDensitySqKm = popDensitySqKm;
    }

    public Country(String continent, String countryName, String capital, int population, double areaSqKm, double popDensitySqKm) {
        this.id = 0;
        this.continent = continent;
        this.countryName = countryName;
        this.capital = capital;
        this.population = population;
        this.areaSqKm = areaSqKm;
        this.popDensitySqKm = popDensitySqKm;
    }

    public Country()
    {

    }

    public void displayCountry()
    {
        System.out.println("\n" + this.countryName.toUpperCase());
        System.out.println("--------------------------------");
        System.out.println("Country ID        : " + this.id);
        System.out.println("Continent         : " + this.continent);
        System.out.println("Capital City      : " + this.capital);
        System.out.println("Population        : " + this.population);
        System.out.println("Area              : " + this.areaSqKm + " km^2");
        System.out.println("Population Density: " + this.popDensitySqKm + "/km^2");
        System.out.println("--------------------------------");
    }

    //Getters
    public int getId(){ return id; }
    public String getContinent(){ return continent; }
    public String getCountryName() {
        return countryName;
    }
    public String getCapital() {
        return capital;
    }
    public int getPopulation() {
        return population;
    }
    public double getAreaSqKm() {
        return areaSqKm;
    }
    public double getPopDensitySqKm() {
        return popDensitySqKm;
    }


    //Setters
    public void setId(int id){ this.id = id; }
    public void setContinent(String continent){ this.continent = continent; }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public void setAreaSqKm(double areaSqKm) {
        this.areaSqKm = areaSqKm;
    }
    public void setPopDensitySqKm(double popDensitySqKm) {
        this.popDensitySqKm = popDensitySqKm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && population == country.population && Double.compare(country.areaSqKm, areaSqKm) == 0 && Double.compare(country.popDensitySqKm, popDensitySqKm) == 0 && Objects.equals(continent, country.continent) && Objects.equals(countryName, country.countryName) && Objects.equals(capital, country.capital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, continent, countryName, capital, population, areaSqKm, popDensitySqKm);
    }
}
