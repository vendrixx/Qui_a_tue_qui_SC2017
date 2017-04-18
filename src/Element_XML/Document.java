package Element_XML;

import java.util.ArrayList;

/**
 * Created by jerem on 11/04/2017.
 */
public class Document {

    private ArrayList<Sentences> listSentences = new ArrayList<Sentences>();

    public void addSentences(Sentences s) {
        this.listSentences.add(s);
    }

    public String toString() {
        String str = " Un noeud document contient : ";
        for(Sentences s : listSentences)
            str += "\n  " + s;
        return str;
    }
}
