import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jerem on 26/03/2017.
 */

public class JDOM_TAL {

    static org.jdom2.Document document;
    static Element racine;
    static List sentences_with_H = new ArrayList();

    public static void main(String[] args) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            document = sxb.build(new File("murder_in_2000.txt.xml"));

            racine = document.getRootElement();
            /**/System.out.println(racine);
            Iterator i = racine.getContent().iterator();
            while(i.hasNext()) {
                Object obj = i.next();
                if(obj instanceof Element) {
                    Element child = (Element) obj;
                    /**///System.out.println(child);
                    //System.out.println(child.getChild("sentences"));
                    //System.out.println(child.getChild("sentences").getChild("sentence").getChild("tokens").getChild("token"));
                    Element sentences = child.getChild("sentences");
                    List sentence = sentences.getChildren("sentence");
                    //System.out.println(sentence.size());
                    Iterator i2 = sentence.iterator();
                    while(i2.hasNext()) {
                        Element elem = (Element) i2.next();
                        List tokens = elem.getChildren();
                        Iterator i3 = tokens.iterator();
                        while(i3.hasNext()) {
                            Element elem2 = (Element)i3.next();
                            List token = elem2.getChildren();
                            //System.out.println(token.size());
                            Iterator i4 = token.iterator();
                            while(i4.hasNext()) {
                                Element elem3 = (Element)i4.next();
                                List words = elem3.getChildren();
                                if(/*elem3.getChildText("NER") == "PERSON"*/elem3.getChild("NER").getTextTrim().equals("PERSON")) {
                                    System.out.println(elem3.getAttribute("id"));
                                    System.out.println(elem3.getChild("lemma").getText());
                                    String name = elem3.getChild("lemma").getText();
                                    if(name.charAt(0) == 'H') {
                                        //sentences_with_H.add(elem.getChildren().get);
                                    }
                                }
                            }
                        }
                        //System.out.println(elem.getChild("NER").getText());
                    }

                    Element token = child.getChild("sentences").getChild("sentence").getChild("tokens").getChild("token");
                    System.out.println(token.getChildText("NER"));
                    afficherTitre(token);
                    System.out.println("----------------------------------");
                    System.out.println(sentences_with_H.size());
                }
            }
        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        //afficheFiltre();
    }

    public static void afficherTitre(Element element) {
        Filter filtre = new Filter() {
            private static final long serialVersionUID = 1L;

            @Override
            public List filter(List list) {
                return null;
            }

            @Override
            public Object filter(Object o) {
                return null;
            }

            public boolean matches(Object arg0) {
                boolean resultat = true;

                /*if(arg0 instanceof Element){
                    Element element = (Element)arg0;
                    resultat = "CharacterOffsetEnd".equals(element.getName());
                }*/
                return resultat;
            }

            @Override
            public Filter<?> negate() {
                return null;
            }

            @Override
            public Filter refine(Filter filter) {
                return null;
            }

            @Override
            public Filter and(Filter filter) {
                return null;
            }

            @Override
            public Filter<?> or(Filter filter) {
                return null;
            }
        };
        List children = element.getContent(filtre);
        System.out.println(children.size());
        Iterator iterator = children.iterator();
        while (iterator.hasNext()) {
            Element fils = (Element) iterator.next();
            System.out.println(fils.getText());
        }
    }

    /*static void affiche() {
        try {
            XMLOutputter sortie = new XMLOutputter(org.jdom2.output.Format.getPrettyFormat());
            sortie.output(document, System.out);
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static void afficheALL() {
        List listSentences = racine.getChildren("document");
        System.out.println(racine);
        System.out.println(listSentences.size());
        Iterator i = listSentences.iterator();
        while(i.hasNext()) {
            System.out.println("On est bon");
            Element courant = (Element)i.next();
            System.out.println(courant.getChild("NER").getText());
        }
    }*/

    static void afficheFiltre() {
        Filter filtre = new Filter() {
            @Override
            public List filter(List list) {
                return null;
            }

            @Override
            public Object filter(Object o) {
                return null;
            }

            public boolean matches(Object obj) {
                if(!(obj instanceof Element))
                    return false;
                Element element = (Element)obj;

                int verifNER = 0;

                if(element.getChild("NER").getTextTrim().equals("PERSON")) {
                    verifNER++;
                }

                if(verifNER == 1)
                    return true;
                return false;
            }

            @Override
            public Filter<?> negate() {
                return null;
            }

            @Override
            public Filter refine(Filter filter) {
                return null;
            }

            @Override
            public Filter and(Filter filter) {
                return null;
            }

            @Override
            public Filter<?> or(Filter filter) {
                return null;
            }
        };

        List result = racine.getContent(filtre);

        Iterator i = result.iterator();
        while(i.hasNext()) {
            Element courant = (Element)i.next();
            System.out.println(courant.getAttributeValue("id"));
        }
    }
}
