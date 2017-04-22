package Element_XML;

import java.util.ArrayList;

/**
 * Created by jerem on 11/04/2017.
 */
public class Token {

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
        return "    Token n°" + id + "[word=" + word + ",lemma=" + lemma + ",POS=" + POS + ",NER=" + NER + "]";

    }
}
