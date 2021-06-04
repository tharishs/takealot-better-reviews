package com.github.tharishs.tbr.util.lucene;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * @author Tharish Sooruth
 */
public class KeywordFinder {

    public static final CharArraySet ENGLISH_STOP_WORDS_SET;

    static {
        final List<String> stopWords = Arrays.asList(
                "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours", "yourself",
                "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
                "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this",
                "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has",
                "had", "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or",
                "because", "as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between",
                "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in",
                "out", "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when",
                "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some",
                "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can",
                "will", "just", "don", "should", "now", "product"
        );
        final CharArraySet stopSet = new CharArraySet(stopWords, false);
        ENGLISH_STOP_WORDS_SET = CharArraySet.unmodifiableSet(stopSet);
    }

    public static String stemming(String input) throws IOException {
        StringReader reader = new StringReader(input.toLowerCase());
        String stemmedString = "";

        ClassicTokenizer tokenizer = new ClassicTokenizer();
        tokenizer.setReader(reader);

        KStemFilter filter = new KStemFilter(tokenizer);
        CharTermAttribute attribute = filter.addAttribute(CharTermAttribute.class);
        filter.reset();

        while (filter.incrementToken()) {
            String stem = attribute.toString();
            stemmedString = stem;
        }

        if (!stemmedString.matches("[\\w-]+")) {
            return null;
        }

        return stemmedString.toLowerCase();
    }


    public static List<Keyword> guessFromString(String input) throws IOException {

        // hack to keep dashed words (e.g. "non-specific" rather than "non" and "specific")
        input = input.replaceAll("-+", "-0");
        // replace any punctuation char but dashes and apostrophes and by a space
        input = input.replaceAll("[\\p{Punct}&&[^'-]]+", " ");
        // replace most common English contractions
        input = input.replaceAll("(?:'(?:[tdsm]|[vr]e|ll))+\\b", "");

        ClassicTokenizer tokenizer = new ClassicTokenizer();
        StringReader reader = new StringReader(input.toLowerCase());
        tokenizer.setReader(reader);

        TokenStream tokenStream = new LowerCaseFilter(tokenizer);
        // remove dots from acronyms (and "'s" but already done manually above)
        tokenStream = new ClassicFilter(tokenizer);
        // convert any char to ASCII
        tokenStream = new ASCIIFoldingFilter(tokenizer);
        // remove english stop words
        tokenStream = new StopFilter(tokenizer, ENGLISH_STOP_WORDS_SET);

        List<Keyword> keywords = new LinkedList<Keyword>();
        CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);
        // for each token
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            String term = token.toString();
            // stemmize
            String stem = stemming(term);
            //String stem = term;
            if (stem != null) {
                // create the keyword or get the existing one if any
                Keyword keyword = find(keywords, new Keyword(stem.replaceAll("-0", "-")));
                // add its corresponding initial token
                keyword.add(term.replaceAll("-0", "-"));
            }
        }
        tokenStream.end();
        tokenStream.close();

        // reverse sort by frequency
        Collections.sort(keywords);

        return keywords;
    }

    private static <T> T find(Collection<T> collection, T example) {
        for (T element : collection) {
            if (element.equals(example)) {
                return element;
            }
        }
        collection.add(example);
        return example;
    }
}
