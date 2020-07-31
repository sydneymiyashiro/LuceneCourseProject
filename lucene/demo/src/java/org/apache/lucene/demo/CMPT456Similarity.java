/*
Project 1: Part 3 (Similarity Calculations TF/IDF)

- modifies tf and idf functions 

Sydney Miyashiro
 */

package org.apache.lucene.demo;


import org.apache.lucene.search.similarities.ClassicSimilarity;


public class CMPT456Similarity extends ClassicSimilarity {
  public CMPT456Similarity() {} 

  /** Implemented as sqrt(1 + freq). */
  @Override
  public float tf(float freq) {
    //System.out.println("TF: " + (float)Math.sqrt(1.0 + freq));
    return (float)Math.sqrt(1.0 + freq);
  }

  /** Implemented as log((docCount+2)/(docFreq+2)) + 1. */
  @Override
  public float idf(long docFreq, long docCount) {
    //System.out.println("IDF: " + (float)(Math.log((docCount+2)/(double)(docFreq+2)) + 1.0));
    return (float)(Math.log((docCount+2)/(double)(docFreq+2)) + 1.0);
  }


  @Override
  public String toString() {
    return "CMPT456Similarity";
  }
}