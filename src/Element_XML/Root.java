package Element_XML;

import java.util.ArrayList;

/**
 * Created by jerem on 11/04/2017.
 */
public class Root {

    private ArrayList<Document> listDocument = new ArrayList<Document>();

    public void addDocument(Document d) {
        this.listDocument.add(d);
    }

    public String toString() {
        String str = "Ce noeud root contient : ";
        for(Document d : listDocument)
            str += "\n" + d;

        return str;
    }
}
