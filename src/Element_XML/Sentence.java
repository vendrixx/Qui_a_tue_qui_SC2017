package Element_XML;

import java.util.ArrayList;

/**
 * Created by jerem on 11/04/2017.
 */
public class Sentence {

    private ArrayList<Tokens> listTokens = new ArrayList<>();
    private String id = null;

    public void addTokens(Tokens t) {
        this.listTokens.add(t);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        String str = "  Un noeud sentence d'id " + this.id + " qui contient : ";
        for(Tokens t : listTokens)
            str += "\n        " + t;
        return str;
    }

    /*public String toString() {
        String str = "";
        for(Tokens t : listTokens)
            str += t;
        return str;
    }*/
}
