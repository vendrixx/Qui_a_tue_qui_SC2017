package Element_XML;

import java.util.ArrayList;

/**
 * Created by jerem on 11/04/2017.
 */
public class Sentences {

    private ArrayList<Sentence> listSentence = new ArrayList<>();

    public void addSentence(Sentence s) {
        this.listSentence.add(s);
    }

    public String toString() {
        String str = "  Un noeud sentences qui contient : ";
        for(Sentence s : listSentence)
            str += "\n\n        " + s;
        return str;
    }
}
