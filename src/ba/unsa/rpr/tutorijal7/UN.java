package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;
import java.util.ArrayList;

public class UN implements Serializable {

    ArrayList<Drzava> drzave= new ArrayList<Drzava>();

    public ArrayList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(ArrayList<Drzava> drzave) {
        this.drzave = drzave;
    }


}
