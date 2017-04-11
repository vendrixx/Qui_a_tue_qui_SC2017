package Element_XML;

import java.util.ArrayList;

/**
 * Created by jerem on 11/04/2017.
 */
public class Token {

    /*private ArrayList<Word> listWord = new ArrayList<>();
    private ArrayList<Lemma> listLemma = new ArrayList<>();
    private ArrayList<CharacterOffsetBegin> listCharacterOffsetBegin = new ArrayList<>();
    private ArrayList<CharacterOffsetEnd> listCharacterOffsetEnd = new ArrayList<>();
    private ArrayList<POS> listPOS = new ArrayList<>();
    private ArrayList<NER> listNER = new ArrayList<>();
    private ArrayList<NormalizedNER> listNormalizedNER = new ArrayList<>();
    private ArrayList<Timex> listTimex = new ArrayList<>();
    private String id = null;

    public void add(Word word) {
        this.listWord.add(word);
    }

    public void add(Lemma lemma) {
        this.listLemma.add(lemma);
    }

    public void add(CharacterOffsetBegin characterOffsetBegin) {
        this.listCharacterOffsetBegin.add(characterOffsetBegin);
    }

    public void add(CharacterOffsetEnd characterOffsetEnd) {
        this.listCharacterOffsetEnd.add(characterOffsetEnd);
    }

    public void add(POS pos) {
        this.listPOS.add(pos);
    }

    public void add(NER ner) {
        this.listNER.add(ner);
    }

    public void add(NormalizedNER normalizedNER) {
        this.listNormalizedNER.add(normalizedNER);
    }

    public void add(Timex timex) {
        this.listTimex.add(timex);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        String str = "  Un noeud sentence qui contient : ";
        str += "\n      un id qui vaut " + this.id;
        for(Word word : listWord)
            str += "\n\n        " + word;

        for(Lemma lemma : listLemma)
            str += "\n\n        " + lemma;

        for(CharacterOffsetBegin characterOffsetBegin : listCharacterOffsetBegin)
            str += "\n\n        " + characterOffsetBegin;

        for(CharacterOffsetEnd characterOffsetEnd : listCharacterOffsetEnd)
            str += "\n\n        " + characterOffsetEnd;

        for(POS pos : listPOS)
            str += "\n\n        " + pos;

        for(NER ner : listNER)
            str += "\n\n        " + ner;

        for(NormalizedNER normalizedNER : listNormalizedNER)
            str += "\n\n        " + normalizedNER;

        for(Timex timex : listTimex)
            str += "\n\n        " + timex;

        return str;
    }*/

    private String word = null;
    private String lemma = null;
    private String characterOffsetBegin = null;
    private String characterOffsetEnd = null;
    private String POS = null;
    private String NER = null;
    private String normalisedNER = null;
    private String timex = null;

    private String id = null;

    public void setWord(String word) {
        this.word = word;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public void setCharacterOffsetBegin(String characterOffsetBegin) {
        this.characterOffsetBegin = characterOffsetBegin;
    }

    public void setCharacterOffsetEnd(String characterOffsetEnd) {
        this.characterOffsetEnd = characterOffsetEnd;
    }

    public void setPOS(String POS) {
        this.POS = POS;
    }

    public void setNER(String NER) {
        this.NER = NER;
    }

    public void setNormalisedNER(String normalisedNER) {
        this.normalisedNER = normalisedNER;
    }

    public void setTimex(String timex) {
        this.timex = timex;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "    Token [word=" + word + ",lemma=" + lemma + ",NER=" + NER + ",POS=" + POS + "]";
    }
}
