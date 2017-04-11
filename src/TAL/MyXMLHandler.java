package TAL;

import Element_XML.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by jerem on 11/04/2017.
 */
public class MyXMLHandler extends DefaultHandler {

    private String node = null;
    private Root root;
    private Document document;
    private Sentences sentences;
    private Sentence sentence;
    private Tokens tokens;
    private Token token;

    /**
     * Début du parsing du document XML
     * @throws SAXException
     */
    /*public void startDocument() throws SAXException {
        System.out.println("Début du parsing");
    }*/

    /**
     * Fin du parsing du document XML
     * @throws SAXException
     */
    /*public void endDocument() throws SAXException {
        System.out.println("Fin du parsing");
    }*/

    /**
     * Intercepte les événements (méthode redéfinie)
     * @param qname contient le nom du noeud qui a créé l'événement
     * @param attrs liste des attributs du noeud en question
     * @throws SAXException
     */
    public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) throws SAXException {
        /*System.out.println("------------------------------------------");
        System.out.println("qname = " + qname);*/
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
        }
        /*switch (qname) {
            case "root":
                root = new Root();
                break;
            case "document":
                document = new Document();
                break;
            case "sentences":
                sentences = new Sentences();
                break;
            case "sentence":
                sentence = new Sentence();
                sentence.setId(attrs.getValue(0));
                break;
            case "tokens":
                tokens = new Tokens();
                break;
            case "token":
                token = new Token();
                token.setId(attrs.getValue(0));
                break;
        }*/
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println("Fin de l'élément " + qName);
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
        /*switch (qName) {
            case "document":
                root.addDocument(document);
                break;
            case "sentences":
                document.addSentences(sentences);
                break;
            case "sentence":
                sentences.addSentence(sentence);
                break;
            case "tokens":
                sentence.addTokens(tokens);
                break;
            case "token":
                tokens.addToken(token);
                break;
        }*/
    }


    /**
     * Récupère la valeur d'un noeud (méthode redéfinie)
     * @param data contient tout le fichier
     * @param start indice où commence la veleur recherchée
     * @param end longueur de la chaîne
     */
    public void characters(char[] data, int start, int end) {
        //System.out.println("**********************************************");
        String str = new String(data, start, end);
        //System.out.println("Donnée du noeud " + node + " : " + str);
        if(node.equals("word")) {
            token.setWord(str);
        } else if(node.equals("lemma")) {
            token.setLemma(str);
        } else if(node.equals("CharacterOffsetBegin")) {
            token.setCharacterOffsetBegin(str);
        } else if(node.equals("CharacterOffsetEnd")) {
            token.setCharacterOffsetEnd(str);
        } else if(node.equals("POS")) {
            token.setPOS(str);
        } else if(node.equals("NER")) {
            token.setNER(str);
        } else if(node.equals("NormalizedNER")) {
            token.setNormalisedNER(str);
        } else if(node.equals("Timex")) {
            token.setTimex(str);
        }
        /*switch (node) {
            case "word":
                token.setWord(str);
                break;
            case "lemma":
                token.setLemma(str);
                break;
            case "CharacterOffsetBegin":
                token.setCharacterOffsetBegin(str);
                break;
            case "CharacterOffsetEnd":
                token.setCharacterOffsetEnd(str);
                break;
            case "POS":
                token.setPOS(str);
                break;
            case "NER":
                token.setNER(str);
                break;
            case "Normalized NER":
                token.setNormalisedNER(str);
                break;
            case "Timex":
                token.setTimex(str);
                break;
        }*/
    }

    public Root getRoot() { return root; }
}
