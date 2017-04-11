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
        String str = "  Un noeud sentence qui contient : ";
        str += "\n      un id qui vaut " + this.id;
        for(Tokens t : listTokens)
            str += "\n\n        " + t;
        return str;
    }
}
