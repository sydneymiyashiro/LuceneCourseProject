/*
Project 1: Part 3 (Stand-alone file to compute doc freq, term freq)

- takes user input and applies the custom analyzer 
  (changes it to lowercase, removes stopwords, applies Porter Stemmer)

- custom analyzer is used to match how words are stored in the index
  for ex. 'dogs' will return results for 'dog'; search results would otherwise be empty for 'dogs'
  since all words are stemmed before being indexed 

- reads index to calculate the document and term frequencies

Sydney Miyashiro

 */
package org.apache.lucene.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.ParseException;


public class SimpleMetrics {

  private SimpleMetrics() {}

  /* Reads index to collect document frequency and term frequency info. */
  public static void main(String[] args) {

    String indexPath = "index";
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    // create a query parser with the custom-CMPT456 Analyzer
    Analyzer analyzer = new CMPT456Analyzer();
    QueryParser parser = new QueryParser("contents", analyzer);

    while (true) {
      // prompt the user to enter query
      System.out.println("Enter query: ");
      String line = null;
      
      try {
        // get input 
        line = in.readLine();

      } catch (IOException e) {
        System.out.println(e.toString());
      }

      if (line == null || line.length() == -1) {
        break;
      }

      // remove whitespaces at beginning/end
      line = line.trim();
      if (line.length() == 0) {
        break;
      }

      Query query = null;
      try {
        // apply the query parser to the input using CMPT456 Analyzer
        System.out.println("Removing stopwords, applying Porter Stemmer to input...");
        query = parser.parse(line);
      
      } catch (ParseException e) {
        System.out.println("Unable to parse input...");
      }

      if (query == null) {
        System.out.println("No query after removing stopwords/applying Porter Stemmer.");
        break;
      }
      

      System.out.println("Original Input: " + line + ", After Parsing: " + query.toString("contents"));
      System.out.println();

      // open index reader, retrieve metrics for query terms
      try {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));

        // split query into query terms to support multi-term queries
        String[] queryTerms = query.toString("contents").split(" ");

        // calculate metrics separately for each term in the query 
        for (int i = 0; i < queryTerms.length; i++) {

          // create instances of the term within the contents field
          Term termInContents = new Term("contents", queryTerms[i]);

          // Document Frequency
          // returns the number of documents containing the term
          // assume that if term occurs in title, it would also occur in contents
          int docFreqContents = reader.docFreq(termInContents);

          // Term Frequency
          // returns the total number of occurrences of the term across all documents
          long termFreqContents = reader.totalTermFreq(termInContents);

          System.out.println("Term: " + queryTerms[i] + ", DocFreq: " + docFreqContents + ", TermFreq: " + termFreqContents);
        }

        System.out.println();

      } catch (IOException e) {
          System.out.println(e.toString());
      }
      
    }
  }
}