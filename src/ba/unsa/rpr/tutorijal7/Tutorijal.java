package ba.unsa.rpr.tutorijal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Tutorijal {

    private static org.w3c.dom.Document Document;

    public static ArrayList<Grad> ucitajGradove() throws FileNotFoundException {
        ArrayList<Grad> gradovi = new ArrayList<>();
        Scanner ulaz = null;
        /*try {
            ulaz = new Scanner(new FileReader("mjerenja.txt"));
        } catch (FileNotFoundException greska) {
            throw greska;
        }*/

        if (ulaz == null) return null;

        ulaz.useDelimiter("\n");//zanemaruje ove znakove
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
            System.out.println("Greška: "+ greska);
        }
        return gradovi;
    }

    public static void ucitajElementeIDodajTemperature(Element element, UN un, ArrayList<Grad> gradovi) {
        NodeList djecaDatoteke = element.getChildNodes();

        ArrayList<Drzava> drzave=new ArrayList<>();

        for (int i=0; i<djecaDatoteke.getLength(); i++) {
            Node drzava = djecaDatoteke.item(i);
            if (drzava instanceof Element) {
                int brojStanovnikaDrzave = Integer.parseInt(((Element) drzava).getAttribute("stanovnika"));
                String nazivDrzave = ((Element) drzava).getElementsByTagName("naziv").item(0).getTextContent();
                Double povrsinaDrzave = Double.parseDouble(((Element) drzava).getElementsByTagName("povrsina").item(0).getTextContent());
                String jedinicaZaPovrsinu = ((Element)((Element) drzava).getElementsByTagName("povrsina").item(0)).getAttribute("jedinica");

                NodeList glavniGrad = ((Element) drzava).getElementsByTagName("glavnigrad");
                String nazivGlavnogGradaDrzave = ((Element)glavniGrad.item(0)).getElementsByTagName("naziv").item(0).getTextContent();
                int brojStanovnikaGlavnogGradaDrzave = Integer.parseInt(((Element)glavniGrad.item(0)).getAttribute("stanovnika"));

                Drzava novaDrzava=new Drzava();
                novaDrzava.setBrojStanovnika(brojStanovnikaDrzave);
                novaDrzava.setJedinicaZaPovrsinu(jedinicaZaPovrsinu);
                novaDrzava.setNaziv(nazivDrzave);
                novaDrzava.setPovrsina(povrsinaDrzave);

                Grad noviGrad=new Grad();
                noviGrad.setNaziv(nazivGlavnogGradaDrzave);
                noviGrad.setBrojStanovnika(brojStanovnikaGlavnogGradaDrzave);

                for(Grad g: gradovi){
                    if(g.getNaziv().equals(nazivGlavnogGradaDrzave)){
                        noviGrad.setTemperature(g.getTemperature());
                        novaDrzava.setGlavniGrad(noviGrad);
                        drzave.add(novaDrzava);
                        break;
                    }
                }
            }
        }
        un.setDrzave(drzave);
    }



    static UN ucitajXml(ArrayList<Grad> gradovi) {
        UN un = new UN();
        Document dokument = Document;
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            dokument = docReader.parse(new File("drzave.xml"));

        }
        catch (Exception greska) {
            System.out.println("Greška: "+ greska);
        }

        Element korijenDatoteke = dokument.getDocumentElement();
        ucitajElementeIDodajTemperature(korijenDatoteke, un, gradovi);
        return un;
    }

     public static void zapisiXml(UN un) {
        try{
            XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(un);
            izlaz.close();
        } catch (FileNotFoundException greska){
            System.out.println("Greska:" + greska);
        }
    }

    public static void main(String[] args) {
       /* ArrayList<Grad> gradovi = new ArrayList<>();
        try {
            gradovi = ucitajGradove();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        UN un = ucitajXml(gradovi);
        zapisiXml(un);*/
    }
}
