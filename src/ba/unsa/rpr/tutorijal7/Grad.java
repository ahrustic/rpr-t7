package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;

public class Grad implements Serializable {

    String naziv;
    int brojStanovnika;
    double[] temperature = new double[1000];
    int duzinaNiza;

    public Grad(){
        duzinaNiza = 0;
        temperature = new double[1000];
    }

    public Grad(String grad, Double[] temperature) {
        naziv = grad;
        duzinaNiza = temperature.length;
        this.temperature = new double[1000];
        for (int i = 0; i < duzinaNiza; i++)
            this.temperature[i] = temperature[i];
        brojStanovnika = 0;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }




}
