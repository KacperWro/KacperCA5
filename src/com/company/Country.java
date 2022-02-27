package com.company;

import java.util.Objects;

public class Country {
    //Fields
    private String countryName;
    private String capital;
    private int population;
    public double areaSqKm;
    public double popDensity;


    //Constructor
    public Country(String countryName, String capital, int population, double areaSqKm, double popDensity) {
        this.countryName = countryName;
        this.capital = capital;
        this.population = population;
        this.areaSqKm = areaSqKm;
        this.popDensity = popDensity;
    }


    //Getters
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

    public double getPopDensity() {
        return popDensity;
    }


    //Setters
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

    public void setPopDensity(double popDensity) {
        this.popDensity = popDensity;
    }

    //hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return population == country.population && Double.compare(country.areaSqKm, areaSqKm) == 0 && Double.compare(country.popDensity, popDensity) == 0 && Objects.equals(countryName, country.countryName) && Objects.equals(capital, country.capital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, capital, population, areaSqKm, popDensity);
    }
}
