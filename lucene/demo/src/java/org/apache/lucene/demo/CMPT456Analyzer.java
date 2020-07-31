/*
Project 1: Part 2 (Tokenization)

- filters out stopwords using stopwords.txt
- changes input to lowercase
- stems words with Porter Stemmer


Sydney Miyashiro
 */

package org.apache.lucene.demo;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WordlistLoader;
import org.apache.lucene.analysis.en.PorterStemFilter;



/**
 * Filters {@link StandardTokenizer} with {@link StandardFilter}, {@link
 * LowerCaseFilter} and {@link StopFilter}, using a list of
 * English stop words.
 */
public final class CMPT456Analyzer extends StopwordAnalyzerBase {

  /** An customized set containing some common English words that are not usually useful
  for searching.*/
  public static final CharArraySet ENGLISH_STOP_WORDS_SET;
  
  static {
    List<String> lines = Collections.emptyList(); 
    try { 
      // read all words from stopwords.txt 
      Path stopwordsPath = Paths.get("stopwords.txt");
      System.out.println("Retrieved the path: " + stopwordsPath.toString());

      lines = Files.readAllLines(stopwordsPath, StandardCharsets.UTF_8); 

    } catch (IOException e) { 
      // Unable to read file, so use old stopwords list
      System.out.println("Unable to read stopwords.txt");

      lines = Arrays.asList(
        "a", "an", "and", "are", "as", "at", "be", "but", "by",
        "for", "if", "in", "into", "is", "it",
        "no", "not", "of", "on", "or", "such",
        "that", "the", "their", "then", "there", "these",
        "they", "this", "to", "was", "will", "with"
      );
    }

    final List<String> stopWords = lines;
    final CharArraySet stopSet = new CharArraySet(stopWords, false);
    ENGLISH_STOP_WORDS_SET = CharArraySet.unmodifiableSet(stopSet); 
  
  }

  /** Default maximum allowed token length */
  public static final int DEFAULT_MAX_TOKEN_LENGTH = 255;

  private int maxTokenLength = DEFAULT_MAX_TOKEN_LENGTH;

  /** An unmodifiable set containing some common English words that are usually not
  useful for searching. */
  public static final CharArraySet STOP_WORDS_SET = ENGLISH_STOP_WORDS_SET;

  /** Builds an analyzer with the given stop words.
   * @param stopWords stop words */
  public CMPT456Analyzer(CharArraySet stopWords) {
    super(stopWords);
  }

  /** Builds an analyzer with the default stop words ({@link #STOP_WORDS_SET}).
   */
  public CMPT456Analyzer() {
    this(STOP_WORDS_SET);
  }

  /** Builds an analyzer with the stop words from the given reader.
   * @see WordlistLoader#getWordSet(Reader)
   * @param stopwords Reader to read stop words from */
  public CMPT456Analyzer(Reader stopwords) throws IOException {
    this(loadStopwordSet(stopwords));
  }


  public void setMaxTokenLength(int length) {
    maxTokenLength = length;
  }
    
  /** Returns the current maximum token length
   * 
   *  @see #setMaxTokenLength */
  public int getMaxTokenLength() {
    return maxTokenLength;
  }

  // change input to lower case, remove stopwords, and stem with Porter Stemmer
  @Override
  protected TokenStreamComponents createComponents(final String fieldName) {
    final StandardTokenizer src = new StandardTokenizer();
    src.setMaxTokenLength(maxTokenLength);
    TokenStream tok = new StandardFilter(src);
    tok = new LowerCaseFilter(tok);
    tok = new StopFilter(tok, stopwords);
    tok = new PorterStemFilter(tok);
    return new TokenStreamComponents(src, tok) {
      @Override
      protected void setReader(final Reader reader) {
        // So that if maxTokenLength was changed, the change takes
        // effect next time tokenStream is called:
        src.setMaxTokenLength(CMPT456Analyzer.this.maxTokenLength);
        super.setReader(reader);
      }
    };
  }

}
