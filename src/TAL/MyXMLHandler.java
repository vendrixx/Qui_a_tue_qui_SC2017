package TAL;

import Element_XML.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by jerem on 11/04/2017.
 */
public class MyXMLHandler extends DefaultHandler {

    //private final String lastElementToRead;

    private String node = null;
    private Root root;
    private Document document;
    private Sentences sentences;
    private Sentence sentence;
    private Tokens tokens;
    private Token token;

    private ArrayList killer = new ArrayList();

    private final StringBuilder stringBuilder = new StringBuilder(64);
    private boolean elementOn = false;
    private String elementValue = null;

    private String tagCourant = "";

    private String word = "";

    /*public MyXMLHandler(String lastElementToRead) {
        this.lastElementToRead = lastElementToRead;
    }*/

    public void notationDecl(String name, String publicId, String systemId) {
        System.out.println(name + " - " + publicId + " - " + systemId);
    }

    public void error(SAXParseException e) throws SAXException {
        System.out.println("ERROR : " + e.getMessage());
        throw e;
    }

    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("FATAL ERROR : " + e.getMessage());
        throw e;
    }

    public void warning(SAXParseException e) throws SAXException {}

    /**
     * Début du parsing du document XML
     * @throws SAXException
     */
    public void startDocument() throws SAXException {
        System.out.println("Début du parsing");
    }

    /**
     * Fin du parsing du document XML
     * @throws SAXException
     */
    public void endDocument() throws SAXException {
        System.out.println("Fin du parsing");
    }

    /**
     * Intercepte les événements (méthode redéfinie)
     * @param qname contient le nom du noeud qui a créé l'événement
     * @param attrs liste des attributs du noeud en question
     * @throws SAXException
     */
    public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) throws SAXException {
        tagCourant = qname;
        node = qname;

        if(qname.equals("root")) {
            root = new Root();
        } else if(qname.equals("document")) {
            document = new Document();
        } else if(qname.equals("sentences")) {
            sentences = new Sentences();
        } else if(qname.equals("sentence")) {
            sentence = new Sentence();
            sentence.setId(attrs.getValue(0));
        } else if(qname.equals("tokens")) {
            tokens = new Tokens();
        } else if(qname.equals("token")) {
            token = new Token();
            token.setId(attrs.getValue(0));
            //System.out.println(token);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println("Fin de l'élément " + qName);

        /*if(lastElementToRead.equals(qName)) {
            throw new MySaxTerminatorException();
        }*/
        tagCourant = "";
        /*stringBuilder.setLength(0);
        if(qName.equals("token")) elementOn = true;
        */

        if(qName.equals("document")) {
            root.addDocument(document);
        } else if(qName.equals("sentences")) {
            document.addSentences(sentences);
        } else if(qName.equals("sentence")) {
            sentences.addSentence(sentence);
        } else if(qName.equals("tokens")) {
            sentence.addTokens(tokens);
        } else if(qName.equals("token")) {
            tokens.addToken(token);
        }

    }


    /**
     * Récupère la valeur d'un noeud (méthode redéfinie)
     * @param data contient tout le fichier
     * @param start indice où commence la veleur recherchée
     * @param end longueur de la chaîne
     */
    public void characters(char[] data, int start, int end) {
        String donnees = new String(data, start, end);
        if( !tagCourant.equals("")) {
            if (tagCourant.equals("word")) {
                //System.out.println("   Element = " + tagCourant + " , valeur = " + donnees);
                word = donnees;
            }

            //String str = new String(data, start, end);
            //node = stringBuilder.toString().trim();
            node = tagCourant;

            //System.out.println("Donnée du noeud " + node + " : " + str);
            if (node.equals("word")) {
                //str = new String(data, start, end);
                //System.out.println(str + "------");
                token.setWord(donnees);
                //System.out.println(token);
            } else if (node.equals("lemma")) {
                token.setLemma(donnees);
            } else if (node.equals("CharacterOffsetBegin")) {
                token.setCharacterOffsetBegin(donnees);
            } else if (node.equals("CharacterOffsetEnd")) {
                token.setCharacterOffsetEnd(donnees);
            } else if (node.equals("POS")) {
                token.setPOS(donnees);
            } else if (node.equals("NER")) {
                token.setNER(donnees);
                if (donnees.equals("PERSON") && word.charAt(0) == 'H' && !killer.contains(sentence)) {
                    System.out.println("Add killer");
                    killer.add(sentence);
                }
            } else if (node.equals("NormalizedNER")) {
                token.setNormalisedNER(donnees);
            } else if (node.equals("Timex")) {
                token.setTimex(donnees);
            }
        }

    }


    public void ignorableWhitespace(char[] data, int start, int end) throws SAXException {
        System.out.println("espaces inutiles rencontres : ..." + new String(data, start, end) +  "...");
    }

    public Root getRoot() { return root; }
    public ArrayList getKiller() { return killer; }

    public class MySaxTerminatorException extends SAXException {}
}
