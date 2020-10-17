package com.asyprod.worldatlas;

public class countryModel {
    String name,capitalName,region,subRegion,population,Lats,Longs,demonym,Area,timeZone,nativeName,currencyName,currencySymbol,flagURL,domain,a3Code,callingCode,languages,borders;
    public countryModel(String name,String capitalName,String region,String subRegion,String population,String Lats,String Longs,String demonym,String Area,String timeZone,String nativeName,String currencyName,String currencySymbol,String flagURL,String borders,String domain,String a3Code,String callingCode,String languages){
        this.name = name;
        this.capitalName = capitalName;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.Lats = Lats;
        this.Longs = Longs;
        this.demonym = demonym;
        this.Area = Area;
        this.timeZone = timeZone;
        this.nativeName = nativeName;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.flagURL = flagURL;
        this.borders = borders;
        this.domain = domain;
        this.a3Code = a3Code;
        this.callingCode = callingCode;
        this.languages = languages;
        this.borders = borders;
    }

    public String getBorders() {
        return borders;
    }

    public String getArea() {
        return Area;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getDemonym() {
        return demonym;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public String getLats() {
        return Lats;
    }

    public String getLongs() {
        return Longs;
    }

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getPopulation() {
        return population;
    }

    public String getRegion() {
        return region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getDomain() {
        return domain;
    }

    public String getA3Code() {
        return a3Code;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public String getLanguages() {
        return languages;
    }
}
