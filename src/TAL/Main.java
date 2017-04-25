package TAL;

import Element_XML.Root;
import org.w3c.dom.DOMException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.*;
import java.util.HashSet;

/**
 * Created by jerem on 11/04/2017.
 */
public class Main {

    public static void main(String[] args) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance(); // création d'un parser SAX
            factory.setValidating(true);
            factory.setFeature("http://apache.org/xml/features/validation/dynamic", true);

            File file = new File("murder_in_2000.txt.xml");
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            SAXParser parser = factory.newSAXParser();
            MyXMLHandler handler = new MyXMLHandler();
            try {
                parser.parse(is, handler); // parsing du document XML
                Root root = handler.getRoot();
                //System.out.println(root);
                HashSet<String> search = new HashSet<>();
                search.add("killed");
                search.add("kill");
                search.add("murdered");
                search.add("murder");
                search.add("assassination");
                for(Object o : handler.getKiller()) {
                    for(String str : search) {
                        if (o.toString().toLowerCase().indexOf(str.toLowerCase()) != -1) {
                            System.out.println(o);
                        }
                    }
                }
            } catch (SAXParseException e) {}
        } catch (DOMException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
