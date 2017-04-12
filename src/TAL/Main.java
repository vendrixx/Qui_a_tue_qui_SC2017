package TAL;

import Element_XML.Root;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.IOException;

/**
 * Created by jerem on 11/04/2017.
 */
public class Main {

    public static void main(String[] args) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance(); // création d'un parser SAX
            SAXParser parser = factory.newSAXParser();
            MyXMLHandler handler = new MyXMLHandler();
            parser.parse("murder_in_2000.txt.xml", handler); // parsing du document XML
            Root root = handler.getRoot();
            System.out.println(root);
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
