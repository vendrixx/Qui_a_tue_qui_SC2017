package Element_XML;

import java.util.ArrayList;

/**
 * Created by jerem on 11/04/2017.
 */
public class Tokens {

    private ArrayList<Token> listToken = new ArrayList<>();

    public void addToken(Token t) {
        this.listToken.add(t);
    }

    /*public String toString() {
        String str = "      Un noeud tokens qui contient : ";
        for(Token t : listToken)
            str += "\n        " + t;
        return str;
    }*/

    public String toString() {
        String str = "";
        for(Token t : listToken)
            str += t;
        return str;
    }
}
