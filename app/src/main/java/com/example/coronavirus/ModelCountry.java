package com.example.coronavirus;

import android.content.Context;

public class ModelCountry {
   private String flag,country,totalcases,deaths,active,todaycases,todaydeath,recovered,critical;

    public ModelCountry(Context context, int customlist) {
    }

    public ModelCountry(String flag, String country, String totalcases, String deaths, String active, String todaycases, String todaydeath, String recovered, String critical) {
        this.flag = flag;
        this.country = country;
        this.totalcases = totalcases;
        this.deaths = deaths;
        this.active = active;
        this.todaycases = todaycases;
        this.todaydeath = todaydeath;
        this.recovered = recovered;
        this.critical = critical;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTotalcases() {
        return totalcases;
    }

    public void setTotalcases(String totalcases) {
        this.totalcases = totalcases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getTodaydeath() {
        return todaydeath;
    }

    public void setTodaydeath(String todaydeath) {
        this.todaydeath = todaydeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
