# CMPT 456 Project 1

This project builds on the Lucene library. Modified code can be found in 
the demo folder of lucene.

## How to Run

Place the stopwords.txt file is in the project directory (cmpt456-project1-starter-code/stopwords.txt).
Place the build.xml file within cmpt456-project1-starter-code/lucene/demo/build.xml

After building the project with `ant -f lucene/core/build.xml; ant -f lucene/demo/build.xml`,


### Html Indexing (HtmlIndexFiles.java)

`ant -f lucene/demo/build.xml \
-Ddocs=lucene/demo/data/wiki-small/en/articles/ run-html-indexing-demo`


### Simple Metrics (SimpleMetrics.java)
`ant -f lucene/demo/build.xml run-simple-metrics-demo`

This program takes some user input (1+ words) and calculates the document frequency, term frequency for each individual word in the query. Note that this input is changed to lowercase, stopwords are removed, and words are stemmed using the Porter stemmer. 


### TFIDF Html Indexing (TFIDFHtmlIndexFiles.java)
`ant -f lucene/demo/build.xml \
-Ddocs=lucene/demo/data/wiki-small/en/articles/ run-tfidf-indexing`

This program creates an index using CMPT456Analyzer and CMPT456Similarity. 

### TFIDF Search Index (TFIDFSearchFiles.java)
`ant -f lucene/demo/build.xml run-tfidf-search`

This program searches the index for query terms using CMPT456Analyzer and CMPT456Similarity.
Output includes the score using the custom similarity class. 


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
