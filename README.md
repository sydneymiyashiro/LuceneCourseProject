# Information Retrieval and Web Search Course Project: Modifying Lucene/Solr.

This project builds on the Lucene open source library. Modified code can be found in 
the demo folder of lucene.

## How to Run

Place the stopwords.txt file is in the project directory (cmpt456-project1-starter-code/stopwords.txt).
Place the build.xml file within cmpt456-project1-starter-code/lucene/demo/build.xml

After building the project with `ant -f lucene/core/build.xml; ant -f lucene/demo/build.xml`,


### Html Indexing (HtmlIndexFiles.java)

`ant -f lucene/demo/build.xml \
-Ddocs=lucene/demo/data/wiki-small/en/articles/ run-html-indexing-demo`

This program uses the StandardAnalyzer to create tokens from the result of the HTML parser. 
Then it converts them to lowercase and filters based on a predefined list of stop-words. 


### Simple Metrics (SimpleMetrics.java)
`ant -f lucene/demo/build.xml run-simple-metrics-demo`

This program takes some user input (1+ words) and changes it to lowercase, 
removes stopwords, and applies the Porter stemmer. Finally it outputs the document frequency 
(number of documents containing the term) and the term frequency (number of occurrences of 
the term across all documents) for each query word. 

Important: Phrases are split into individual words and frequencies are calculated per-word.
This program must be run after TFIDF Html Indexing for accurate results. 
Both query input and documents in the index are stemmed and the custom list of stopwords have been removed.

### TFIDF Html Indexing (TFIDFHtmlIndexFiles.java)
`ant -f lucene/demo/build.xml \
-Ddocs=lucene/demo/data/wiki-small/en/articles/ run-tfidf-indexing`

This program creates an index using CMPT456Analyzer and CMPT456Similarity. 

### TFIDF Search Index (TFIDFSearchFiles.java)
`ant -f lucene/demo/build.xml run-tfidf-search`

This program searches the index for query terms using CMPT456Analyzer and CMPT456Similarity 
which uses custom tf and idf formulas. Output includes the score using the custom similarity class. 

Queries may include multiple words. However results will include documents 
that may contain only one of the input words in them. If input is inside of quotes, 
then that phrase will be searched and all results will contain that phrase.


<!--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 -->
