package ba.unsa.rpr.tutorijal7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Tutorijal {

    public static ArrayList<Grad> ucitajGradove() throws FileNotFoundException {
        ArrayList<Grad> gradovi = new ArrayList<>();
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileReader("mjerenja.txt"));
        } catch (FileNotFoundException greska) {
            throw greska;
        }

        if (ulaz == null) return null;

        ulaz.useDelimiter("\n");
        try {
            while (ulaz.hasNext()) {
                String podaciGradova = ulaz.next();
                String[] podaci = podaciGradova.split(",");
                String grad = podaci[0];
                List<Double> nizTemperatura = new ArrayList<>();
                if (grad.matches("[A-Z][a-zA-Z]*,\\s[A-Z][a-zA-Z]*") == false)
                    throw new IllegalArgumentException("Neispravno ime grada");
                for (int i = 1; i < podaci.length; i++) {
                    if (podaci[i].matches("[A-Z][a-zA-Z]*,\\s[A-Z][a-zA-Z]*") == true)//"[a-zA-Z]+"))
                        throw new IllegalArgumentException("Teperature nisu ispravne");
                    nizTemperatura.add(Double.parseDouble(podaci[i]));
                }
                gradovi.add(new Grad(grad, nizTemperatura.toArray(new Double[0])));
            }
        } catch (Exception greska) {

        }
        return gradovi;
    }



    public static void main(String[] args) {


    }
}
