import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.coref.data.DocumentPreprocessor;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.CoreMap;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by jerem on 15/04/2017.
 */
public class Test_NLP {

    private static List<String> kills = new ArrayList<>();
    private static String killer = "";
    private static String victim = "";

    public static void main(String[] args) throws IOException {

        LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        lp.setOptionFlags(new String[] { "-maxLength", "80", "-retainTmpSubcategories" });
        //String[] sent = {"Holmes", "was", "reported", "to", "have", "visited", "a", "local", "pharmacy", "to", "purchase", "the", "drugs",
          //      "which", "he", "used", "to", "kill", "Howard", "Pitezel"};
        String[] sent = {"On", "June", "4", "2008", "salesman", "Travis", "Victor", "Alexander", "killed",
                "his", "ex-girlfriend", "Jodi", "Ann", "Arias", "in", "Mesa", "Arizona"};
        List<CoreLabel> rawWords = SentenceUtils.toCoreLabelList(sent);
        Tree parse = lp.apply(rawWords);
        //parse.pennPrint();
        System.out.println();

        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
        //System.out.println(tdl);

        HashSet<String> search = new HashSet<>();
        search.add("killed");
        search.add("kill");
        search.add("murdered");
        search.add("murder");
        search.add("assassination");

        for(TypedDependency td : tdl) { // pour chaque dépendance grammaticale
            System.out.println(td);
            for(String str : search) {
                if(td.gov().word() != null) { // si le mot n'est pas null
                    if(td.gov().word().toLowerCase().contains(str.toLowerCase())) { // on cherche une correspondance avec les synonymes de "murder"
                        if(td.reln().compareTo(EnglishGrammaticalRelations.DIRECT_OBJECT) == 0) { // si le mot est un objet direct d'un verbe comme "kill", "mudrered"...
                            // c'est qu'il fait partie des victimes
                            //System.out.println(td);
                            //System.out.println(td.reln() + " \t " + td.dep().word() + " \t " + td.gov().word() + " \n ");
                            //System.out.println("Victime : " + td.dep().word());
                            victim = td.dep().word();
                        }
                    }
                }
            }
            // d'un autre côté on essaye de connaitre le nom du tueur
            if(td.reln().compareTo(EnglishGrammaticalRelations.NOMINAL_SUBJECT) == 0 ||
                    td.reln().compareTo(EnglishGrammaticalRelations.SUBJECT) == 0 ||
                    td.reln().compareTo(EnglishGrammaticalRelations.NOMINAL_PASSIVE_SUBJECT) == 0) {
                //if(td.dep().word().charAt(0) == 'H') {
                    //System.out.println("Meurtrier : " + td.dep().word());
                    killer = td.dep().word();
                //}
            }
        }
        TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
        //tp.printTree(parse);

        kills.add(victim + " has been killed by " + killer);
        int i = 1;
        for(String str : kills) {
            System.out.println(i++ + ") " + str);
        }

        /*Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        //String text = "I like eat burgers";
        //String text = readFile("HHHolmes.txt", StandardCharsets.UTF_8);
        List<String> lines = readFile("HHHolmes.txt", StandardCharsets.UTF_8);
        List<String> linesWithMurderWords = new ArrayList<>();

        HashSet<String> search = new HashSet<>();
        /*search.add("killed");
        search.add("kill");
        search.add("murdered");
        search.add("murder");
        search.add("assassination");*//*
        search.add("Howard Pitezel");
        for(String line : lines) {
            for(String str : search) {
                if (line.toString().toLowerCase().indexOf(str.toLowerCase()) != -1) {
                    System.out.println(line);
                    linesWithMurderWords.add(line);
                }
            }
        }


        /*Annotation document = new Annotation(/*text*//*linesWithMurderWords.get(0));

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence : sentences) {
            for(CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                System.out.println("word: " + word + " pos: " + pos + " ne: " + ne);
            }
            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
            System.out.println("parse tree:\n" + tree);
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            System.out.println("dependency graph:\n" + dependencies);



        }
        Map<Integer, CorefChain> graph = document.get(CorefCoreAnnotations.CorefChainAnnotation.class);*/
    }


    public static List<String> readFile(String path, Charset encoding) throws IOException {
        //byte[] encoded = Files.readAllBytes(Paths.get(str));
        List<String> lines = Files.readAllLines(Paths.get(path), encoding);
        //return new String(encoded, encoding);
        return lines;
    }

}
