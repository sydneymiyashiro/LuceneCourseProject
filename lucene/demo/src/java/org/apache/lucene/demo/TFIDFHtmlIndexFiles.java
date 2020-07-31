/*
Project 1: Part 3 (TFIDF HTML Index Files)

- extends HtmlIndexFiles 
- uses custom similarity class with modified tf and idf functions

Sydney Miyashiro
 */

package org.apache.lucene.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.benchmark.byTask.feeds.DemoHTMLParser;
import org.apache.lucene.benchmark.byTask.feeds.DocData;
import org.apache.lucene.benchmark.byTask.feeds.TrecContentSource;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;



/** Index all text files under a directory.
 * <p>
 * This is a command-line application demonstrating simple Lucene indexing.
 * Run it with no command-line arguments for usage information.
 */
public class TFIDFHtmlIndexFiles {
  
  private TFIDFHtmlIndexFiles() {}

  /** Index all text files under a directory. */
  public static void main(String[] args) {
    String usage = "java org.apache.lucene.demo.TFIDFHtmlIndexFiles"
                 + " [-index INDEX_PATH] [-docs DOCS_PATH] [-update]\n\n"
                 + "This indexes the documents in DOCS_PATH, creating a Lucene index"
                 + "in INDEX_PATH that can be searched with SearchFiles";
    String indexPath = "index";
    String docsPath = null;
    boolean create = true;
    for(int i=0;i<args.length;i++) {
      if ("-index".equals(args[i])) {
        indexPath = args[i+1];
        i++;
      } else if ("-docs".equals(args[i])) {
        docsPath = args[i+1];
        i++;
      } else if ("-update".equals(args[i])) {
        create = false;
      }
    }

    if (docsPath == null) {
      System.err.println("Usage: " + usage);
      System.exit(1);
    }

    final Path docDir = Paths.get(docsPath);
    if (!Files.isReadable(docDir)) {
      System.out.println("Document directory '" +docDir.toAbsolutePath()+ "' does not exist or is not readable, please check the path");
      System.exit(1);
    }
    
    Date start = new Date();
    try {
      System.out.println("Indexing to directory '" + indexPath + "'...");

      Directory dir = FSDirectory.open(Paths.get(indexPath));

      // Use the custom CMPT456 Analyzer
      Analyzer analyzer = new CMPT456Analyzer();
      IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

      // Use CMPT456Similarity custom functions
      Similarity customSimilarity = new CMPT456Similarity();
      iwc.setSimilarity(customSimilarity);

      if (create) {
        // Create a new index in the directory, removing any
        // previously indexed documents:
        iwc.setOpenMode(OpenMode.CREATE);
      } else {
        // Add new documents to an existing index:
        iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
      }

      IndexWriter writer = new IndexWriter(dir, iwc);
      indexDocs(writer, docDir);
      writer.close();

      Date end = new Date();
      System.out.println(end.getTime() - start.getTime() + " total milliseconds");

    } catch (IOException e) {
      System.out.println(" caught a " + e.getClass() +
       "\n with message: " + e.getMessage());
    }
  }

  /**
   * Indexes the given file using the given writer, or if a directory is given,
   * recurses over files and directories found under the given directory.
   * 
   * NOTE: This method indexes one document per input file.  This is slow.  For good
   * throughput, put multiple documents into your input file(s).  An example of this is
   * in the benchmark module, which can create "line doc" files, one document per line,
   * using the
   * <a href="../../../../../contrib-benchmark/org/apache/lucene/benchmark/byTask/tasks/WriteLineDocTask.html"
   * >WriteLineDocTask</a>.
   *  
   * @param writer Writer to the index where the given file/dir info will be stored
   * @param path The file to index, or the directory to recurse into to find files to index
   * @throws IOException If there is a low-level I/O error
   */
  static void indexDocs(final IndexWriter writer, Path path) throws IOException {
    if (Files.isDirectory(path)) {
      Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          try {
            indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
          } catch (IOException ignore) {
            // don't index files that can't be read.
          }
          return FileVisitResult.CONTINUE;
        }
      });
    } else {
      indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
    }
  }

  /** Indexes a single document */
  static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
    try {
      // initialize HTML parser
      DemoHTMLParser htmlParser = new DemoHTMLParser();

      // set arguments for html parsing function
      Date date = new Date(lastModified);
      InputStream targetStream = new FileInputStream(file.toString());
      InputSource inputSource = new InputSource(targetStream);

      // call HTML parser from DemoHTMLParser()
      DocData docData = htmlParser.parse(new DocData(), file.toString(), date, inputSource, new TrecContentSource());
      
      // make a new, empty document
      Document doc = new Document();

      // add path and date to document 
      Field pathField = new StringField("path", file.toString(), Field.Store.YES);
      doc.add(pathField);
      doc.add(new LongPoint("modified", lastModified));

      // index title and text content from html document
      InputStream title = new ByteArrayInputStream(docData.getTitle().getBytes(StandardCharsets.UTF_8));
      InputStream body = new ByteArrayInputStream(docData.getBody().getBytes(StandardCharsets.UTF_8));

      // add title to "title" field
      doc.add(new TextField("title", new BufferedReader(new InputStreamReader(title))));

      // add title and text body to "contents" for searching later
      doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(title))));
      doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(body))));

      if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
        // New index, so we just add the document (no old document can be there):
        System.out.println("adding " + file);
        writer.addDocument(doc);
      } else {
        // Existing index (an old copy of this document may have been indexed) so 
        // we use updateDocument instead to replace the old one matching the exact 
        // path, if present:
        System.out.println("updating " + file);
        writer.updateDocument(new Term("path", file.toString()), doc);
      }
    
    } catch (IOException e) {
        System.out.println(e.toString());
    } catch (SAXException e) {
        System.out.println(e.toString());
    }
  }
}