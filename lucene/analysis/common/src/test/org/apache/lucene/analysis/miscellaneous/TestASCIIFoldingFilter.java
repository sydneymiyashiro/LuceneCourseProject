/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.analysis.miscellaneous;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TestASCIIFoldingFilter extends BaseTokenStreamTestCase {
  /**
   * Pop one input token's worth of tokens off the filter and verify that they are as expected.
   */
  void assertNextTerms(String expectedUnfolded, String expectedFolded, ASCIIFoldingFilter filter,
      CharTermAttribute termAtt) throws Exception {
    assertTrue(filter.incrementToken());
    assertEquals(expectedFolded, termAtt.toString());
    if (filter.isPreserveOriginal() && !expectedUnfolded.equals(expectedFolded)) {
      assertTrue(filter.incrementToken());
      assertEquals(expectedUnfolded, termAtt.toString());
    }
  }

  // testLain1Accents() is a copy of TestLatin1AccentFilter.testU().
  public void testLatin1Accents() throws Exception {
    TokenStream stream = whitespaceMockTokenizer("Des mot cl??s ?? LA CHA??NE ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ??"
      +" ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ??"
      +" ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ?? ??? ???");
    ASCIIFoldingFilter filter = new ASCIIFoldingFilter(stream, random().nextBoolean());

    CharTermAttribute termAtt = filter.getAttribute(CharTermAttribute.class);
    filter.reset();
    assertNextTerms("Des", "Des", filter, termAtt);
    assertNextTerms("mot", "mot", filter, termAtt);
    assertNextTerms("cl??s", "cles", filter, termAtt);
    assertNextTerms("??", "A", filter, termAtt);
    assertNextTerms("LA", "LA", filter, termAtt);
    assertNextTerms("CHA??NE", "CHAINE", filter, termAtt);
    assertNextTerms("??", "A", filter, termAtt);
    assertNextTerms("??", "A", filter, termAtt);
    assertNextTerms("??", "A", filter, termAtt);
    assertNextTerms("??", "A", filter, termAtt);
    assertNextTerms("??", "A", filter, termAtt);
    assertNextTerms("??", "A", filter, termAtt);
    assertNextTerms("??", "AE", filter, termAtt);
    assertNextTerms("??", "C", filter, termAtt);
    assertNextTerms("??", "E", filter, termAtt);
    assertNextTerms("??", "E", filter, termAtt);
    assertNextTerms("??", "E", filter, termAtt);
    assertNextTerms("??", "E", filter, termAtt);
    assertNextTerms("??", "I", filter, termAtt);
    assertNextTerms("??", "I", filter, termAtt);
    assertNextTerms("??", "I", filter, termAtt);
    assertNextTerms("??", "I", filter, termAtt);
    assertNextTerms("??", "IJ", filter, termAtt);
    assertNextTerms("??", "D", filter, termAtt);
    assertNextTerms("??", "N", filter, termAtt);
    assertNextTerms("??", "O", filter, termAtt);
    assertNextTerms("??", "O", filter, termAtt);
    assertNextTerms("??", "O", filter, termAtt);
    assertNextTerms("??", "O", filter, termAtt);
    assertNextTerms("??", "O", filter, termAtt);
    assertNextTerms("??", "O", filter, termAtt);
    assertNextTerms("??", "OE", filter, termAtt);
    assertNextTerms("??", "TH", filter, termAtt);
    assertNextTerms("??", "U", filter, termAtt);
    assertNextTerms("??", "U", filter, termAtt);
    assertNextTerms("??", "U", filter, termAtt);
    assertNextTerms("??", "U", filter, termAtt);
    assertNextTerms("??", "Y", filter, termAtt);
    assertNextTerms("??", "Y", filter, termAtt);
    assertNextTerms("??", "a", filter, termAtt);
    assertNextTerms("??", "a", filter, termAtt);
    assertNextTerms("??", "a", filter, termAtt);
    assertNextTerms("??", "a", filter, termAtt);
    assertNextTerms("??", "a", filter, termAtt);
    assertNextTerms("??", "a", filter, termAtt);
    assertNextTerms("??", "ae", filter, termAtt);
    assertNextTerms("??", "c", filter, termAtt);
    assertNextTerms("??", "e", filter, termAtt);
    assertNextTerms("??", "e", filter, termAtt);
    assertNextTerms("??", "e", filter, termAtt);
    assertNextTerms("??", "e", filter, termAtt);
    assertNextTerms("??", "i", filter, termAtt);
    assertNextTerms("??", "i", filter, termAtt);
    assertNextTerms("??", "i", filter, termAtt);
    assertNextTerms("??", "i", filter, termAtt);
    assertNextTerms("??", "ij", filter, termAtt);
    assertNextTerms("??", "d", filter, termAtt);
    assertNextTerms("??", "n", filter, termAtt);
    assertNextTerms("??", "o", filter, termAtt);
    assertNextTerms("??", "o", filter, termAtt);
    assertNextTerms("??", "o", filter, termAtt);
    assertNextTerms("??", "o", filter, termAtt);
    assertNextTerms("??", "o", filter, termAtt);
    assertNextTerms("??", "o", filter, termAtt);
    assertNextTerms("??", "oe", filter, termAtt);
    assertNextTerms("??", "ss", filter, termAtt);
    assertNextTerms("??", "th", filter, termAtt);
    assertNextTerms("??", "u", filter, termAtt);
    assertNextTerms("??", "u", filter, termAtt);
    assertNextTerms("??", "u", filter, termAtt);
    assertNextTerms("??", "u", filter, termAtt);
    assertNextTerms("??", "y", filter, termAtt);
    assertNextTerms("??", "y", filter, termAtt);
    assertNextTerms("???", "fi", filter, termAtt);
    assertNextTerms("???", "fl", filter, termAtt);
    assertFalse(filter.incrementToken());
  }

  // Test that we do not emit duplicated tokens when preserve original is on
  public void testUnmodifiedLetters() throws Exception {
    TokenStream stream = whitespaceMockTokenizer("?? ?? ?? END");
    ASCIIFoldingFilter filter = new ASCIIFoldingFilter(stream, true);

    CharTermAttribute termAtt = filter.getAttribute(CharTermAttribute.class);
    filter.reset();
    assertNextTerms("??", "??", filter, termAtt);
    assertNextTerms("??", "??", filter, termAtt);
    assertNextTerms("??", "??", filter, termAtt);
    assertNextTerms("END", "END", filter, termAtt);
    assertFalse(filter.incrementToken());
  }

  // The following Perl script generated the foldings[] array automatically
  // from ASCIIFoldingFilter.java:
  //
  //    ============== begin get.test.cases.pl ==============
  //
  //    use strict;
  //    use warnings;
  //
  //    my $file = "ASCIIFoldingFilter.java";
  //    my $output = "testcases.txt";
  //    my %codes = ();
  //    my $folded = '';
  //
  //    open IN, "<:utf8", $file || die "Error opening input file '$file': $!";
  //    open OUT, ">:utf8", $output || die "Error opening output file '$output': $!";
  //
  //    while (my $line = <IN>) {
  //      chomp($line);
  //      # case '\u0133': // <char> <maybe URL> [ description ]
  //      if ($line =~ /case\s+'\\u(....)':.*\[([^\]]+)\]/) {
  //        my $code = $1;
  //        my $desc = $2;
  //        $codes{$code} = $desc;
  //      }
  //      # output[outputPos++] = 'A';
  //      elsif ($line =~ /output\[outputPos\+\+\] = '(.+)';/) {
  //        my $output_char = $1;
  //        $folded .= $output_char;
  //      }
  //      elsif ($line =~ /break;/ && length($folded) > 0) {
  //        my $first = 1;
  //        for my $code (sort { hex($a) <=> hex($b) } keys %codes) {
  //          my $desc = $codes{$code};
  //          print OUT '      ';
  //          print OUT '+ ' if (not $first);
  //          $first = 0;
  //          print OUT '"', chr(hex($code)), qq!"  // U+$code: $desc\n!;
  //        }
  //        print OUT qq!      ,"$folded", // Folded result\n\n!;
  //        %codes = ();
  //        $folded = '';
  //      }
  //    }
  //    close OUT;
  //
  //    ============== end get.test.cases.pl ==============
  //
  public void testAllFoldings() throws Exception {
    // Alternating strings of:
    //   1. All non-ASCII characters to be folded, concatenated together as a
    //      single string.
    //   2. The string of ASCII characters to which each of the above
    //      characters should be folded.
    String[] foldings = {
      "??"  // U+00C0: LATIN CAPITAL LETTER A WITH GRAVE
      + "??"  // U+00C1: LATIN CAPITAL LETTER A WITH ACUTE
      + "??"  // U+00C2: LATIN CAPITAL LETTER A WITH CIRCUMFLEX
      + "??"  // U+00C3: LATIN CAPITAL LETTER A WITH TILDE
      + "??"  // U+00C4: LATIN CAPITAL LETTER A WITH DIAERESIS
      + "??"  // U+00C5: LATIN CAPITAL LETTER A WITH RING ABOVE
      + "??"  // U+0100: LATIN CAPITAL LETTER A WITH MACRON
      + "??"  // U+0102: LATIN CAPITAL LETTER A WITH BREVE
      + "??"  // U+0104: LATIN CAPITAL LETTER A WITH OGONEK
      + "??"  // U+018F: LATIN CAPITAL LETTER SCHWA
      + "??"  // U+01CD: LATIN CAPITAL LETTER A WITH CARON
      + "??"  // U+01DE: LATIN CAPITAL LETTER A WITH DIAERESIS AND MACRON
      + "??"  // U+01E0: LATIN CAPITAL LETTER A WITH DOT ABOVE AND MACRON
      + "??"  // U+01FA: LATIN CAPITAL LETTER A WITH RING ABOVE AND ACUTE
      + "??"  // U+0200: LATIN CAPITAL LETTER A WITH DOUBLE GRAVE
      + "??"  // U+0202: LATIN CAPITAL LETTER A WITH INVERTED BREVE
      + "??"  // U+0226: LATIN CAPITAL LETTER A WITH DOT ABOVE
      + "??"  // U+023A: LATIN CAPITAL LETTER A WITH STROKE
      + "???"  // U+1D00: LATIN LETTER SMALL CAPITAL A
      + "???"  // U+1E00: LATIN CAPITAL LETTER A WITH RING BELOW
      + "???"  // U+1EA0: LATIN CAPITAL LETTER A WITH DOT BELOW
      + "???"  // U+1EA2: LATIN CAPITAL LETTER A WITH HOOK ABOVE
      + "???"  // U+1EA4: LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND ACUTE
      + "???"  // U+1EA6: LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND GRAVE
      + "???"  // U+1EA8: LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND HOOK ABOVE
      + "???"  // U+1EAA: LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND TILDE
      + "???"  // U+1EAC: LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND DOT BELOW
      + "???"  // U+1EAE: LATIN CAPITAL LETTER A WITH BREVE AND ACUTE
      + "???"  // U+1EB0: LATIN CAPITAL LETTER A WITH BREVE AND GRAVE
      + "???"  // U+1EB2: LATIN CAPITAL LETTER A WITH BREVE AND HOOK ABOVE
      + "???"  // U+1EB4: LATIN CAPITAL LETTER A WITH BREVE AND TILDE
      + "???"  // U+1EB6: LATIN CAPITAL LETTER A WITH BREVE AND DOT BELOW
      + "???"  // U+24B6: CIRCLED LATIN CAPITAL LETTER A
      + "???"  // U+FF21: FULLWIDTH LATIN CAPITAL LETTER A
      ,"A", // Folded result

       "??"  // U+00E0: LATIN SMALL LETTER A WITH GRAVE
       + "??"  // U+00E1: LATIN SMALL LETTER A WITH ACUTE
       + "??"  // U+00E2: LATIN SMALL LETTER A WITH CIRCUMFLEX
       + "??"  // U+00E3: LATIN SMALL LETTER A WITH TILDE
       + "??"  // U+00E4: LATIN SMALL LETTER A WITH DIAERESIS
       + "??"  // U+00E5: LATIN SMALL LETTER A WITH RING ABOVE
       + "??"  // U+0101: LATIN SMALL LETTER A WITH MACRON
       + "??"  // U+0103: LATIN SMALL LETTER A WITH BREVE
       + "??"  // U+0105: LATIN SMALL LETTER A WITH OGONEK
       + "??"  // U+01CE: LATIN SMALL LETTER A WITH CARON
       + "??"  // U+01DF: LATIN SMALL LETTER A WITH DIAERESIS AND MACRON
       + "??"  // U+01E1: LATIN SMALL LETTER A WITH DOT ABOVE AND MACRON
       + "??"  // U+01FB: LATIN SMALL LETTER A WITH RING ABOVE AND ACUTE
       + "??"  // U+0201: LATIN SMALL LETTER A WITH DOUBLE GRAVE
       + "??"  // U+0203: LATIN SMALL LETTER A WITH INVERTED BREVE
       + "??"  // U+0227: LATIN SMALL LETTER A WITH DOT ABOVE
       + "??"  // U+0250: LATIN SMALL LETTER TURNED A
       + "??"  // U+0259: LATIN SMALL LETTER SCHWA
       + "??"  // U+025A: LATIN SMALL LETTER SCHWA WITH HOOK
       + "???"  // U+1D8F: LATIN SMALL LETTER A WITH RETROFLEX HOOK
       + "???"  // U+1E01: LATIN SMALL LETTER A WITH RING BELOW
       + "???"  // U+1D95: LATIN SMALL LETTER SCHWA WITH RETROFLEX HOOK
       + "???"  // U+1E9A: LATIN SMALL LETTER A WITH RIGHT HALF RING
       + "???"  // U+1EA1: LATIN SMALL LETTER A WITH DOT BELOW
       + "???"  // U+1EA3: LATIN SMALL LETTER A WITH HOOK ABOVE
       + "???"  // U+1EA5: LATIN SMALL LETTER A WITH CIRCUMFLEX AND ACUTE
       + "???"  // U+1EA7: LATIN SMALL LETTER A WITH CIRCUMFLEX AND GRAVE
       + "???"  // U+1EA9: LATIN SMALL LETTER A WITH CIRCUMFLEX AND HOOK ABOVE
       + "???"  // U+1EAB: LATIN SMALL LETTER A WITH CIRCUMFLEX AND TILDE
       + "???"  // U+1EAD: LATIN SMALL LETTER A WITH CIRCUMFLEX AND DOT BELOW
       + "???"  // U+1EAF: LATIN SMALL LETTER A WITH BREVE AND ACUTE
       + "???"  // U+1EB1: LATIN SMALL LETTER A WITH BREVE AND GRAVE
       + "???"  // U+1EB3: LATIN SMALL LETTER A WITH BREVE AND HOOK ABOVE
       + "???"  // U+1EB5: LATIN SMALL LETTER A WITH BREVE AND TILDE
       + "???"  // U+1EB7: LATIN SMALL LETTER A WITH BREVE AND DOT BELOW
       + "???"  // U+2090: LATIN SUBSCRIPT SMALL LETTER A
       + "???"  // U+2094: LATIN SUBSCRIPT SMALL LETTER SCHWA
       + "???"  // U+24D0: CIRCLED LATIN SMALL LETTER A
       + "???"  // U+2C65: LATIN SMALL LETTER A WITH STROKE
       + "???"  // U+2C6F: LATIN CAPITAL LETTER TURNED A
       + "???"  // U+FF41: FULLWIDTH LATIN SMALL LETTER A
      ,"a", // Folded result

       "???"  // U+A732: LATIN CAPITAL LETTER AA
      ,"AA", // Folded result

       "??"  // U+00C6: LATIN CAPITAL LETTER AE
       + "??"  // U+01E2: LATIN CAPITAL LETTER AE WITH MACRON
       + "??"  // U+01FC: LATIN CAPITAL LETTER AE WITH ACUTE
       + "???"  // U+1D01: LATIN LETTER SMALL CAPITAL AE
      ,"AE", // Folded result

       "???"  // U+A734: LATIN CAPITAL LETTER AO
      ,"AO", // Folded result

       "???"  // U+A736: LATIN CAPITAL LETTER AU
      ,"AU", // Folded result

       "???"  // U+A738: LATIN CAPITAL LETTER AV
       + "???"  // U+A73A: LATIN CAPITAL LETTER AV WITH HORIZONTAL BAR
      ,"AV", // Folded result

       "???"  // U+A73C: LATIN CAPITAL LETTER AY
      ,"AY", // Folded result

       "???"  // U+249C: PARENTHESIZED LATIN SMALL LETTER A
      ,"(a)", // Folded result

       "???"  // U+A733: LATIN SMALL LETTER AA
      ,"aa", // Folded result

       "??"  // U+00E6: LATIN SMALL LETTER AE
       + "??"  // U+01E3: LATIN SMALL LETTER AE WITH MACRON
       + "??"  // U+01FD: LATIN SMALL LETTER AE WITH ACUTE
       + "???"  // U+1D02: LATIN SMALL LETTER TURNED AE
      ,"ae", // Folded result

       "???"  // U+A735: LATIN SMALL LETTER AO
      ,"ao", // Folded result

       "???"  // U+A737: LATIN SMALL LETTER AU
      ,"au", // Folded result

       "???"  // U+A739: LATIN SMALL LETTER AV
       + "???"  // U+A73B: LATIN SMALL LETTER AV WITH HORIZONTAL BAR
      ,"av", // Folded result

       "???"  // U+A73D: LATIN SMALL LETTER AY
      ,"ay", // Folded result

       "??"  // U+0181: LATIN CAPITAL LETTER B WITH HOOK
       + "??"  // U+0182: LATIN CAPITAL LETTER B WITH TOPBAR
       + "??"  // U+0243: LATIN CAPITAL LETTER B WITH STROKE
       + "??"  // U+0299: LATIN LETTER SMALL CAPITAL B
       + "???"  // U+1D03: LATIN LETTER SMALL CAPITAL BARRED B
       + "???"  // U+1E02: LATIN CAPITAL LETTER B WITH DOT ABOVE
       + "???"  // U+1E04: LATIN CAPITAL LETTER B WITH DOT BELOW
       + "???"  // U+1E06: LATIN CAPITAL LETTER B WITH LINE BELOW
       + "???"  // U+24B7: CIRCLED LATIN CAPITAL LETTER B
       + "???"  // U+FF22: FULLWIDTH LATIN CAPITAL LETTER B
      ,"B", // Folded result

       "??"  // U+0180: LATIN SMALL LETTER B WITH STROKE
       + "??"  // U+0183: LATIN SMALL LETTER B WITH TOPBAR
       + "??"  // U+0253: LATIN SMALL LETTER B WITH HOOK
       + "???"  // U+1D6C: LATIN SMALL LETTER B WITH MIDDLE TILDE
       + "???"  // U+1D80: LATIN SMALL LETTER B WITH PALATAL HOOK
       + "???"  // U+1E03: LATIN SMALL LETTER B WITH DOT ABOVE
       + "???"  // U+1E05: LATIN SMALL LETTER B WITH DOT BELOW
       + "???"  // U+1E07: LATIN SMALL LETTER B WITH LINE BELOW
       + "???"  // U+24D1: CIRCLED LATIN SMALL LETTER B
       + "???"  // U+FF42: FULLWIDTH LATIN SMALL LETTER B
      ,"b", // Folded result

       "???"  // U+249D: PARENTHESIZED LATIN SMALL LETTER B
      ,"(b)", // Folded result

       "??"  // U+00C7: LATIN CAPITAL LETTER C WITH CEDILLA
       + "??"  // U+0106: LATIN CAPITAL LETTER C WITH ACUTE
       + "??"  // U+0108: LATIN CAPITAL LETTER C WITH CIRCUMFLEX
       + "??"  // U+010A: LATIN CAPITAL LETTER C WITH DOT ABOVE
       + "??"  // U+010C: LATIN CAPITAL LETTER C WITH CARON
       + "??"  // U+0187: LATIN CAPITAL LETTER C WITH HOOK
       + "??"  // U+023B: LATIN CAPITAL LETTER C WITH STROKE
       + "??"  // U+0297: LATIN LETTER STRETCHED C
       + "???"  // U+1D04: LATIN LETTER SMALL CAPITAL C
       + "???"  // U+1E08: LATIN CAPITAL LETTER C WITH CEDILLA AND ACUTE
       + "???"  // U+24B8: CIRCLED LATIN CAPITAL LETTER C
       + "???"  // U+FF23: FULLWIDTH LATIN CAPITAL LETTER C
      ,"C", // Folded result

       "??"  // U+00E7: LATIN SMALL LETTER C WITH CEDILLA
       + "??"  // U+0107: LATIN SMALL LETTER C WITH ACUTE
       + "??"  // U+0109: LATIN SMALL LETTER C WITH CIRCUMFLEX
       + "??"  // U+010B: LATIN SMALL LETTER C WITH DOT ABOVE
       + "??"  // U+010D: LATIN SMALL LETTER C WITH CARON
       + "??"  // U+0188: LATIN SMALL LETTER C WITH HOOK
       + "??"  // U+023C: LATIN SMALL LETTER C WITH STROKE
       + "??"  // U+0255: LATIN SMALL LETTER C WITH CURL
       + "???"  // U+1E09: LATIN SMALL LETTER C WITH CEDILLA AND ACUTE
       + "???"  // U+2184: LATIN SMALL LETTER REVERSED C
       + "???"  // U+24D2: CIRCLED LATIN SMALL LETTER C
       + "???"  // U+A73E: LATIN CAPITAL LETTER REVERSED C WITH DOT
       + "???"  // U+A73F: LATIN SMALL LETTER REVERSED C WITH DOT
       + "???"  // U+FF43: FULLWIDTH LATIN SMALL LETTER C
      ,"c", // Folded result

       "???"  // U+249E: PARENTHESIZED LATIN SMALL LETTER C
      ,"(c)", // Folded result

       "??"  // U+00D0: LATIN CAPITAL LETTER ETH
       + "??"  // U+010E: LATIN CAPITAL LETTER D WITH CARON
       + "??"  // U+0110: LATIN CAPITAL LETTER D WITH STROKE
       + "??"  // U+0189: LATIN CAPITAL LETTER AFRICAN D
       + "??"  // U+018A: LATIN CAPITAL LETTER D WITH HOOK
       + "??"  // U+018B: LATIN CAPITAL LETTER D WITH TOPBAR
       + "???"  // U+1D05: LATIN LETTER SMALL CAPITAL D
       + "???"  // U+1D06: LATIN LETTER SMALL CAPITAL ETH
       + "???"  // U+1E0A: LATIN CAPITAL LETTER D WITH DOT ABOVE
       + "???"  // U+1E0C: LATIN CAPITAL LETTER D WITH DOT BELOW
       + "???"  // U+1E0E: LATIN CAPITAL LETTER D WITH LINE BELOW
       + "???"  // U+1E10: LATIN CAPITAL LETTER D WITH CEDILLA
       + "???"  // U+1E12: LATIN CAPITAL LETTER D WITH CIRCUMFLEX BELOW
       + "???"  // U+24B9: CIRCLED LATIN CAPITAL LETTER D
       + "???"  // U+A779: LATIN CAPITAL LETTER INSULAR D
       + "???"  // U+FF24: FULLWIDTH LATIN CAPITAL LETTER D
      ,"D", // Folded result

       "??"  // U+00F0: LATIN SMALL LETTER ETH
       + "??"  // U+010F: LATIN SMALL LETTER D WITH CARON
       + "??"  // U+0111: LATIN SMALL LETTER D WITH STROKE
       + "??"  // U+018C: LATIN SMALL LETTER D WITH TOPBAR
       + "??"  // U+0221: LATIN SMALL LETTER D WITH CURL
       + "??"  // U+0256: LATIN SMALL LETTER D WITH TAIL
       + "??"  // U+0257: LATIN SMALL LETTER D WITH HOOK
       + "???"  // U+1D6D: LATIN SMALL LETTER D WITH MIDDLE TILDE
       + "???"  // U+1D81: LATIN SMALL LETTER D WITH PALATAL HOOK
       + "???"  // U+1D91: LATIN SMALL LETTER D WITH HOOK AND TAIL
       + "???"  // U+1E0B: LATIN SMALL LETTER D WITH DOT ABOVE
       + "???"  // U+1E0D: LATIN SMALL LETTER D WITH DOT BELOW
       + "???"  // U+1E0F: LATIN SMALL LETTER D WITH LINE BELOW
       + "???"  // U+1E11: LATIN SMALL LETTER D WITH CEDILLA
       + "???"  // U+1E13: LATIN SMALL LETTER D WITH CIRCUMFLEX BELOW
       + "???"  // U+24D3: CIRCLED LATIN SMALL LETTER D
       + "???"  // U+A77A: LATIN SMALL LETTER INSULAR D
       + "???"  // U+FF44: FULLWIDTH LATIN SMALL LETTER D
      ,"d", // Folded result

       "??"  // U+01C4: LATIN CAPITAL LETTER DZ WITH CARON
       + "??"  // U+01F1: LATIN CAPITAL LETTER DZ
      ,"DZ", // Folded result

       "??"  // U+01C5: LATIN CAPITAL LETTER D WITH SMALL LETTER Z WITH CARON
       + "??"  // U+01F2: LATIN CAPITAL LETTER D WITH SMALL LETTER Z
      ,"Dz", // Folded result

       "???"  // U+249F: PARENTHESIZED LATIN SMALL LETTER D
      ,"(d)", // Folded result

       "??"  // U+0238: LATIN SMALL LETTER DB DIGRAPH
      ,"db", // Folded result

       "??"  // U+01C6: LATIN SMALL LETTER DZ WITH CARON
       + "??"  // U+01F3: LATIN SMALL LETTER DZ
       + "??"  // U+02A3: LATIN SMALL LETTER DZ DIGRAPH
       + "??"  // U+02A5: LATIN SMALL LETTER DZ DIGRAPH WITH CURL
      ,"dz", // Folded result

       "??"  // U+00C8: LATIN CAPITAL LETTER E WITH GRAVE
       + "??"  // U+00C9: LATIN CAPITAL LETTER E WITH ACUTE
       + "??"  // U+00CA: LATIN CAPITAL LETTER E WITH CIRCUMFLEX
       + "??"  // U+00CB: LATIN CAPITAL LETTER E WITH DIAERESIS
       + "??"  // U+0112: LATIN CAPITAL LETTER E WITH MACRON
       + "??"  // U+0114: LATIN CAPITAL LETTER E WITH BREVE
       + "??"  // U+0116: LATIN CAPITAL LETTER E WITH DOT ABOVE
       + "??"  // U+0118: LATIN CAPITAL LETTER E WITH OGONEK
       + "??"  // U+011A: LATIN CAPITAL LETTER E WITH CARON
       + "??"  // U+018E: LATIN CAPITAL LETTER REVERSED E
       + "??"  // U+0190: LATIN CAPITAL LETTER OPEN E
       + "??"  // U+0204: LATIN CAPITAL LETTER E WITH DOUBLE GRAVE
       + "??"  // U+0206: LATIN CAPITAL LETTER E WITH INVERTED BREVE
       + "??"  // U+0228: LATIN CAPITAL LETTER E WITH CEDILLA
       + "??"  // U+0246: LATIN CAPITAL LETTER E WITH STROKE
       + "???"  // U+1D07: LATIN LETTER SMALL CAPITAL E
       + "???"  // U+1E14: LATIN CAPITAL LETTER E WITH MACRON AND GRAVE
       + "???"  // U+1E16: LATIN CAPITAL LETTER E WITH MACRON AND ACUTE
       + "???"  // U+1E18: LATIN CAPITAL LETTER E WITH CIRCUMFLEX BELOW
       + "???"  // U+1E1A: LATIN CAPITAL LETTER E WITH TILDE BELOW
       + "???"  // U+1E1C: LATIN CAPITAL LETTER E WITH CEDILLA AND BREVE
       + "???"  // U+1EB8: LATIN CAPITAL LETTER E WITH DOT BELOW
       + "???"  // U+1EBA: LATIN CAPITAL LETTER E WITH HOOK ABOVE
       + "???"  // U+1EBC: LATIN CAPITAL LETTER E WITH TILDE
       + "???"  // U+1EBE: LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND ACUTE
       + "???"  // U+1EC0: LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND GRAVE
       + "???"  // U+1EC2: LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND HOOK ABOVE
       + "???"  // U+1EC4: LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND TILDE
       + "???"  // U+1EC6: LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND DOT BELOW
       + "???"  // U+24BA: CIRCLED LATIN CAPITAL LETTER E
       + "???"  // U+2C7B: LATIN LETTER SMALL CAPITAL TURNED E
       + "???"  // U+FF25: FULLWIDTH LATIN CAPITAL LETTER E
      ,"E", // Folded result

       "??"  // U+00E8: LATIN SMALL LETTER E WITH GRAVE
       + "??"  // U+00E9: LATIN SMALL LETTER E WITH ACUTE
       + "??"  // U+00EA: LATIN SMALL LETTER E WITH CIRCUMFLEX
       + "??"  // U+00EB: LATIN SMALL LETTER E WITH DIAERESIS
       + "??"  // U+0113: LATIN SMALL LETTER E WITH MACRON
       + "??"  // U+0115: LATIN SMALL LETTER E WITH BREVE
       + "??"  // U+0117: LATIN SMALL LETTER E WITH DOT ABOVE
       + "??"  // U+0119: LATIN SMALL LETTER E WITH OGONEK
       + "??"  // U+011B: LATIN SMALL LETTER E WITH CARON
       + "??"  // U+01DD: LATIN SMALL LETTER TURNED E
       + "??"  // U+0205: LATIN SMALL LETTER E WITH DOUBLE GRAVE
       + "??"  // U+0207: LATIN SMALL LETTER E WITH INVERTED BREVE
       + "??"  // U+0229: LATIN SMALL LETTER E WITH CEDILLA
       + "??"  // U+0247: LATIN SMALL LETTER E WITH STROKE
       + "??"  // U+0258: LATIN SMALL LETTER REVERSED E
       + "??"  // U+025B: LATIN SMALL LETTER OPEN E
       + "??"  // U+025C: LATIN SMALL LETTER REVERSED OPEN E
       + "??"  // U+025D: LATIN SMALL LETTER REVERSED OPEN E WITH HOOK
       + "??"  // U+025E: LATIN SMALL LETTER CLOSED REVERSED OPEN E
       + "??"  // U+029A: LATIN SMALL LETTER CLOSED OPEN E
       + "???"  // U+1D08: LATIN SMALL LETTER TURNED OPEN E
       + "???"  // U+1D92: LATIN SMALL LETTER E WITH RETROFLEX HOOK
       + "???"  // U+1D93: LATIN SMALL LETTER OPEN E WITH RETROFLEX HOOK
       + "???"  // U+1D94: LATIN SMALL LETTER REVERSED OPEN E WITH RETROFLEX HOOK
       + "???"  // U+1E15: LATIN SMALL LETTER E WITH MACRON AND GRAVE
       + "???"  // U+1E17: LATIN SMALL LETTER E WITH MACRON AND ACUTE
       + "???"  // U+1E19: LATIN SMALL LETTER E WITH CIRCUMFLEX BELOW
       + "???"  // U+1E1B: LATIN SMALL LETTER E WITH TILDE BELOW
       + "???"  // U+1E1D: LATIN SMALL LETTER E WITH CEDILLA AND BREVE
       + "???"  // U+1EB9: LATIN SMALL LETTER E WITH DOT BELOW
       + "???"  // U+1EBB: LATIN SMALL LETTER E WITH HOOK ABOVE
       + "???"  // U+1EBD: LATIN SMALL LETTER E WITH TILDE
       + "???"  // U+1EBF: LATIN SMALL LETTER E WITH CIRCUMFLEX AND ACUTE
       + "???"  // U+1EC1: LATIN SMALL LETTER E WITH CIRCUMFLEX AND GRAVE
       + "???"  // U+1EC3: LATIN SMALL LETTER E WITH CIRCUMFLEX AND HOOK ABOVE
       + "???"  // U+1EC5: LATIN SMALL LETTER E WITH CIRCUMFLEX AND TILDE
       + "???"  // U+1EC7: LATIN SMALL LETTER E WITH CIRCUMFLEX AND DOT BELOW
       + "???"  // U+2091: LATIN SUBSCRIPT SMALL LETTER E
       + "???"  // U+24D4: CIRCLED LATIN SMALL LETTER E
       + "???"  // U+2C78: LATIN SMALL LETTER E WITH NOTCH
       + "???"  // U+FF45: FULLWIDTH LATIN SMALL LETTER E
      ,"e", // Folded result

       "???"  // U+24A0: PARENTHESIZED LATIN SMALL LETTER E
      ,"(e)", // Folded result

       "??"  // U+0191: LATIN CAPITAL LETTER F WITH HOOK
       + "???"  // U+1E1E: LATIN CAPITAL LETTER F WITH DOT ABOVE
       + "???"  // U+24BB: CIRCLED LATIN CAPITAL LETTER F
       + "???"  // U+A730: LATIN LETTER SMALL CAPITAL F
       + "???"  // U+A77B: LATIN CAPITAL LETTER INSULAR F
       + "???"  // U+A7FB: LATIN EPIGRAPHIC LETTER REVERSED F
       + "???"  // U+FF26: FULLWIDTH LATIN CAPITAL LETTER F
      ,"F", // Folded result

       "??"  // U+0192: LATIN SMALL LETTER F WITH HOOK
       + "???"  // U+1D6E: LATIN SMALL LETTER F WITH MIDDLE TILDE
       + "???"  // U+1D82: LATIN SMALL LETTER F WITH PALATAL HOOK
       + "???"  // U+1E1F: LATIN SMALL LETTER F WITH DOT ABOVE
       + "???"  // U+1E9B: LATIN SMALL LETTER LONG S WITH DOT ABOVE
       + "???"  // U+24D5: CIRCLED LATIN SMALL LETTER F
       + "???"  // U+A77C: LATIN SMALL LETTER INSULAR F
       + "???"  // U+FF46: FULLWIDTH LATIN SMALL LETTER F
      ,"f", // Folded result

       "???"  // U+24A1: PARENTHESIZED LATIN SMALL LETTER F
      ,"(f)", // Folded result

       "???"  // U+FB00: LATIN SMALL LIGATURE FF
      ,"ff", // Folded result

       "???"  // U+FB03: LATIN SMALL LIGATURE FFI
      ,"ffi", // Folded result

       "???"  // U+FB04: LATIN SMALL LIGATURE FFL
      ,"ffl", // Folded result

       "???"  // U+FB01: LATIN SMALL LIGATURE FI
      ,"fi", // Folded result

       "???"  // U+FB02: LATIN SMALL LIGATURE FL
      ,"fl", // Folded result

       "??"  // U+011C: LATIN CAPITAL LETTER G WITH CIRCUMFLEX
       + "??"  // U+011E: LATIN CAPITAL LETTER G WITH BREVE
       + "??"  // U+0120: LATIN CAPITAL LETTER G WITH DOT ABOVE
       + "??"  // U+0122: LATIN CAPITAL LETTER G WITH CEDILLA
       + "??"  // U+0193: LATIN CAPITAL LETTER G WITH HOOK
       + "??"  // U+01E4: LATIN CAPITAL LETTER G WITH STROKE
       + "??"  // U+01E5: LATIN SMALL LETTER G WITH STROKE
       + "??"  // U+01E6: LATIN CAPITAL LETTER G WITH CARON
       + "??"  // U+01E7: LATIN SMALL LETTER G WITH CARON
       + "??"  // U+01F4: LATIN CAPITAL LETTER G WITH ACUTE
       + "??"  // U+0262: LATIN LETTER SMALL CAPITAL G
       + "??"  // U+029B: LATIN LETTER SMALL CAPITAL G WITH HOOK
       + "???"  // U+1E20: LATIN CAPITAL LETTER G WITH MACRON
       + "???"  // U+24BC: CIRCLED LATIN CAPITAL LETTER G
       + "???"  // U+A77D: LATIN CAPITAL LETTER INSULAR G
       + "???"  // U+A77E: LATIN CAPITAL LETTER TURNED INSULAR G
       + "???"  // U+FF27: FULLWIDTH LATIN CAPITAL LETTER G
      ,"G", // Folded result

       "??"  // U+011D: LATIN SMALL LETTER G WITH CIRCUMFLEX
       + "??"  // U+011F: LATIN SMALL LETTER G WITH BREVE
       + "??"  // U+0121: LATIN SMALL LETTER G WITH DOT ABOVE
       + "??"  // U+0123: LATIN SMALL LETTER G WITH CEDILLA
       + "??"  // U+01F5: LATIN SMALL LETTER G WITH ACUTE
       + "??"  // U+0260: LATIN SMALL LETTER G WITH HOOK
       + "??"  // U+0261: LATIN SMALL LETTER SCRIPT G
       + "???"  // U+1D77: LATIN SMALL LETTER TURNED G
       + "???"  // U+1D79: LATIN SMALL LETTER INSULAR G
       + "???"  // U+1D83: LATIN SMALL LETTER G WITH PALATAL HOOK
       + "???"  // U+1E21: LATIN SMALL LETTER G WITH MACRON
       + "???"  // U+24D6: CIRCLED LATIN SMALL LETTER G
       + "???"  // U+A77F: LATIN SMALL LETTER TURNED INSULAR G
       + "???"  // U+FF47: FULLWIDTH LATIN SMALL LETTER G
      ,"g", // Folded result

       "???"  // U+24A2: PARENTHESIZED LATIN SMALL LETTER G
      ,"(g)", // Folded result

       "??"  // U+0124: LATIN CAPITAL LETTER H WITH CIRCUMFLEX
       + "??"  // U+0126: LATIN CAPITAL LETTER H WITH STROKE
       + "??"  // U+021E: LATIN CAPITAL LETTER H WITH CARON
       + "??"  // U+029C: LATIN LETTER SMALL CAPITAL H
       + "???"  // U+1E22: LATIN CAPITAL LETTER H WITH DOT ABOVE
       + "???"  // U+1E24: LATIN CAPITAL LETTER H WITH DOT BELOW
       + "???"  // U+1E26: LATIN CAPITAL LETTER H WITH DIAERESIS
       + "???"  // U+1E28: LATIN CAPITAL LETTER H WITH CEDILLA
       + "???"  // U+1E2A: LATIN CAPITAL LETTER H WITH BREVE BELOW
       + "???"  // U+24BD: CIRCLED LATIN CAPITAL LETTER H
       + "???"  // U+2C67: LATIN CAPITAL LETTER H WITH DESCENDER
       + "???"  // U+2C75: LATIN CAPITAL LETTER HALF H
       + "???"  // U+FF28: FULLWIDTH LATIN CAPITAL LETTER H
      ,"H", // Folded result

       "??"  // U+0125: LATIN SMALL LETTER H WITH CIRCUMFLEX
       + "??"  // U+0127: LATIN SMALL LETTER H WITH STROKE
       + "??"  // U+021F: LATIN SMALL LETTER H WITH CARON
       + "??"  // U+0265: LATIN SMALL LETTER TURNED H
       + "??"  // U+0266: LATIN SMALL LETTER H WITH HOOK
       + "??"  // U+02AE: LATIN SMALL LETTER TURNED H WITH FISHHOOK
       + "??"  // U+02AF: LATIN SMALL LETTER TURNED H WITH FISHHOOK AND TAIL
       + "???"  // U+1E23: LATIN SMALL LETTER H WITH DOT ABOVE
       + "???"  // U+1E25: LATIN SMALL LETTER H WITH DOT BELOW
       + "???"  // U+1E27: LATIN SMALL LETTER H WITH DIAERESIS
       + "???"  // U+1E29: LATIN SMALL LETTER H WITH CEDILLA
       + "???"  // U+1E2B: LATIN SMALL LETTER H WITH BREVE BELOW
       + "???"  // U+1E96: LATIN SMALL LETTER H WITH LINE BELOW
       + "???"  // U+24D7: CIRCLED LATIN SMALL LETTER H
       + "???"  // U+2C68: LATIN SMALL LETTER H WITH DESCENDER
       + "???"  // U+2C76: LATIN SMALL LETTER HALF H
       + "???"  // U+FF48: FULLWIDTH LATIN SMALL LETTER H
      ,"h", // Folded result

       "??"  // U+01F6: LATIN CAPITAL LETTER HWAIR
      ,"HV", // Folded result

       "???"  // U+24A3: PARENTHESIZED LATIN SMALL LETTER H
      ,"(h)", // Folded result

       "??"  // U+0195: LATIN SMALL LETTER HV
      ,"hv", // Folded result

       "??"  // U+00CC: LATIN CAPITAL LETTER I WITH GRAVE
       + "??"  // U+00CD: LATIN CAPITAL LETTER I WITH ACUTE
       + "??"  // U+00CE: LATIN CAPITAL LETTER I WITH CIRCUMFLEX
       + "??"  // U+00CF: LATIN CAPITAL LETTER I WITH DIAERESIS
       + "??"  // U+0128: LATIN CAPITAL LETTER I WITH TILDE
       + "??"  // U+012A: LATIN CAPITAL LETTER I WITH MACRON
       + "??"  // U+012C: LATIN CAPITAL LETTER I WITH BREVE
       + "??"  // U+012E: LATIN CAPITAL LETTER I WITH OGONEK
       + "??"  // U+0130: LATIN CAPITAL LETTER I WITH DOT ABOVE
       + "??"  // U+0196: LATIN CAPITAL LETTER IOTA
       + "??"  // U+0197: LATIN CAPITAL LETTER I WITH STROKE
       + "??"  // U+01CF: LATIN CAPITAL LETTER I WITH CARON
       + "??"  // U+0208: LATIN CAPITAL LETTER I WITH DOUBLE GRAVE
       + "??"  // U+020A: LATIN CAPITAL LETTER I WITH INVERTED BREVE
       + "??"  // U+026A: LATIN LETTER SMALL CAPITAL I
       + "???"  // U+1D7B: LATIN SMALL CAPITAL LETTER I WITH STROKE
       + "???"  // U+1E2C: LATIN CAPITAL LETTER I WITH TILDE BELOW
       + "???"  // U+1E2E: LATIN CAPITAL LETTER I WITH DIAERESIS AND ACUTE
       + "???"  // U+1EC8: LATIN CAPITAL LETTER I WITH HOOK ABOVE
       + "???"  // U+1ECA: LATIN CAPITAL LETTER I WITH DOT BELOW
       + "???"  // U+24BE: CIRCLED LATIN CAPITAL LETTER I
       + "???"  // U+A7FE: LATIN EPIGRAPHIC LETTER I LONGA
       + "???"  // U+FF29: FULLWIDTH LATIN CAPITAL LETTER I
      ,"I", // Folded result

       "??"  // U+00EC: LATIN SMALL LETTER I WITH GRAVE
       + "??"  // U+00ED: LATIN SMALL LETTER I WITH ACUTE
       + "??"  // U+00EE: LATIN SMALL LETTER I WITH CIRCUMFLEX
       + "??"  // U+00EF: LATIN SMALL LETTER I WITH DIAERESIS
       + "??"  // U+0129: LATIN SMALL LETTER I WITH TILDE
       + "??"  // U+012B: LATIN SMALL LETTER I WITH MACRON
       + "??"  // U+012D: LATIN SMALL LETTER I WITH BREVE
       + "??"  // U+012F: LATIN SMALL LETTER I WITH OGONEK
       + "??"  // U+0131: LATIN SMALL LETTER DOTLESS I
       + "??"  // U+01D0: LATIN SMALL LETTER I WITH CARON
       + "??"  // U+0209: LATIN SMALL LETTER I WITH DOUBLE GRAVE
       + "??"  // U+020B: LATIN SMALL LETTER I WITH INVERTED BREVE
       + "??"  // U+0268: LATIN SMALL LETTER I WITH STROKE
       + "???"  // U+1D09: LATIN SMALL LETTER TURNED I
       + "???"  // U+1D62: LATIN SUBSCRIPT SMALL LETTER I
       + "???"  // U+1D7C: LATIN SMALL LETTER IOTA WITH STROKE
       + "???"  // U+1D96: LATIN SMALL LETTER I WITH RETROFLEX HOOK
       + "???"  // U+1E2D: LATIN SMALL LETTER I WITH TILDE BELOW
       + "???"  // U+1E2F: LATIN SMALL LETTER I WITH DIAERESIS AND ACUTE
       + "???"  // U+1EC9: LATIN SMALL LETTER I WITH HOOK ABOVE
       + "???"  // U+1ECB: LATIN SMALL LETTER I WITH DOT BELOW
       + "???"  // U+2071: SUPERSCRIPT LATIN SMALL LETTER I
       + "???"  // U+24D8: CIRCLED LATIN SMALL LETTER I
       + "???"  // U+FF49: FULLWIDTH LATIN SMALL LETTER I
      ,"i", // Folded result

       "??"  // U+0132: LATIN CAPITAL LIGATURE IJ
      ,"IJ", // Folded result

       "???"  // U+24A4: PARENTHESIZED LATIN SMALL LETTER I
      ,"(i)", // Folded result

       "??"  // U+0133: LATIN SMALL LIGATURE IJ
      ,"ij", // Folded result

       "??"  // U+0134: LATIN CAPITAL LETTER J WITH CIRCUMFLEX
       + "??"  // U+0248: LATIN CAPITAL LETTER J WITH STROKE
       + "???"  // U+1D0A: LATIN LETTER SMALL CAPITAL J
       + "???"  // U+24BF: CIRCLED LATIN CAPITAL LETTER J
       + "???"  // U+FF2A: FULLWIDTH LATIN CAPITAL LETTER J
      ,"J", // Folded result

       "??"  // U+0135: LATIN SMALL LETTER J WITH CIRCUMFLEX
       + "??"  // U+01F0: LATIN SMALL LETTER J WITH CARON
       + "??"  // U+0237: LATIN SMALL LETTER DOTLESS J
       + "??"  // U+0249: LATIN SMALL LETTER J WITH STROKE
       + "??"  // U+025F: LATIN SMALL LETTER DOTLESS J WITH STROKE
       + "??"  // U+0284: LATIN SMALL LETTER DOTLESS J WITH STROKE AND HOOK
       + "??"  // U+029D: LATIN SMALL LETTER J WITH CROSSED-TAIL
       + "???"  // U+24D9: CIRCLED LATIN SMALL LETTER J
       + "???"  // U+2C7C: LATIN SUBSCRIPT SMALL LETTER J
       + "???"  // U+FF4A: FULLWIDTH LATIN SMALL LETTER J
      ,"j", // Folded result

       "???"  // U+24A5: PARENTHESIZED LATIN SMALL LETTER J
      ,"(j)", // Folded result

       "??"  // U+0136: LATIN CAPITAL LETTER K WITH CEDILLA
       + "??"  // U+0198: LATIN CAPITAL LETTER K WITH HOOK
       + "??"  // U+01E8: LATIN CAPITAL LETTER K WITH CARON
       + "???"  // U+1D0B: LATIN LETTER SMALL CAPITAL K
       + "???"  // U+1E30: LATIN CAPITAL LETTER K WITH ACUTE
       + "???"  // U+1E32: LATIN CAPITAL LETTER K WITH DOT BELOW
       + "???"  // U+1E34: LATIN CAPITAL LETTER K WITH LINE BELOW
       + "???"  // U+24C0: CIRCLED LATIN CAPITAL LETTER K
       + "???"  // U+2C69: LATIN CAPITAL LETTER K WITH DESCENDER
       + "???"  // U+A740: LATIN CAPITAL LETTER K WITH STROKE
       + "???"  // U+A742: LATIN CAPITAL LETTER K WITH DIAGONAL STROKE
       + "???"  // U+A744: LATIN CAPITAL LETTER K WITH STROKE AND DIAGONAL STROKE
       + "???"  // U+FF2B: FULLWIDTH LATIN CAPITAL LETTER K
      ,"K", // Folded result

       "??"  // U+0137: LATIN SMALL LETTER K WITH CEDILLA
       + "??"  // U+0199: LATIN SMALL LETTER K WITH HOOK
       + "??"  // U+01E9: LATIN SMALL LETTER K WITH CARON
       + "??"  // U+029E: LATIN SMALL LETTER TURNED K
       + "???"  // U+1D84: LATIN SMALL LETTER K WITH PALATAL HOOK
       + "???"  // U+1E31: LATIN SMALL LETTER K WITH ACUTE
       + "???"  // U+1E33: LATIN SMALL LETTER K WITH DOT BELOW
       + "???"  // U+1E35: LATIN SMALL LETTER K WITH LINE BELOW
       + "???"  // U+24DA: CIRCLED LATIN SMALL LETTER K
       + "???"  // U+2C6A: LATIN SMALL LETTER K WITH DESCENDER
       + "???"  // U+A741: LATIN SMALL LETTER K WITH STROKE
       + "???"  // U+A743: LATIN SMALL LETTER K WITH DIAGONAL STROKE
       + "???"  // U+A745: LATIN SMALL LETTER K WITH STROKE AND DIAGONAL STROKE
       + "???"  // U+FF4B: FULLWIDTH LATIN SMALL LETTER K
      ,"k", // Folded result

       "???"  // U+24A6: PARENTHESIZED LATIN SMALL LETTER K
      ,"(k)", // Folded result

       "??"  // U+0139: LATIN CAPITAL LETTER L WITH ACUTE
       + "??"  // U+013B: LATIN CAPITAL LETTER L WITH CEDILLA
       + "??"  // U+013D: LATIN CAPITAL LETTER L WITH CARON
       + "??"  // U+013F: LATIN CAPITAL LETTER L WITH MIDDLE DOT
       + "??"  // U+0141: LATIN CAPITAL LETTER L WITH STROKE
       + "??"  // U+023D: LATIN CAPITAL LETTER L WITH BAR
       + "??"  // U+029F: LATIN LETTER SMALL CAPITAL L
       + "???"  // U+1D0C: LATIN LETTER SMALL CAPITAL L WITH STROKE
       + "???"  // U+1E36: LATIN CAPITAL LETTER L WITH DOT BELOW
       + "???"  // U+1E38: LATIN CAPITAL LETTER L WITH DOT BELOW AND MACRON
       + "???"  // U+1E3A: LATIN CAPITAL LETTER L WITH LINE BELOW
       + "???"  // U+1E3C: LATIN CAPITAL LETTER L WITH CIRCUMFLEX BELOW
       + "???"  // U+24C1: CIRCLED LATIN CAPITAL LETTER L
       + "???"  // U+2C60: LATIN CAPITAL LETTER L WITH DOUBLE BAR
       + "???"  // U+2C62: LATIN CAPITAL LETTER L WITH MIDDLE TILDE
       + "???"  // U+A746: LATIN CAPITAL LETTER BROKEN L
       + "???"  // U+A748: LATIN CAPITAL LETTER L WITH HIGH STROKE
       + "???"  // U+A780: LATIN CAPITAL LETTER TURNED L
       + "???"  // U+FF2C: FULLWIDTH LATIN CAPITAL LETTER L
      ,"L", // Folded result

       "??"  // U+013A: LATIN SMALL LETTER L WITH ACUTE
       + "??"  // U+013C: LATIN SMALL LETTER L WITH CEDILLA
       + "??"  // U+013E: LATIN SMALL LETTER L WITH CARON
       + "??"  // U+0140: LATIN SMALL LETTER L WITH MIDDLE DOT
       + "??"  // U+0142: LATIN SMALL LETTER L WITH STROKE
       + "??"  // U+019A: LATIN SMALL LETTER L WITH BAR
       + "??"  // U+0234: LATIN SMALL LETTER L WITH CURL
       + "??"  // U+026B: LATIN SMALL LETTER L WITH MIDDLE TILDE
       + "??"  // U+026C: LATIN SMALL LETTER L WITH BELT
       + "??"  // U+026D: LATIN SMALL LETTER L WITH RETROFLEX HOOK
       + "???"  // U+1D85: LATIN SMALL LETTER L WITH PALATAL HOOK
       + "???"  // U+1E37: LATIN SMALL LETTER L WITH DOT BELOW
       + "???"  // U+1E39: LATIN SMALL LETTER L WITH DOT BELOW AND MACRON
       + "???"  // U+1E3B: LATIN SMALL LETTER L WITH LINE BELOW
       + "???"  // U+1E3D: LATIN SMALL LETTER L WITH CIRCUMFLEX BELOW
       + "???"  // U+24DB: CIRCLED LATIN SMALL LETTER L
       + "???"  // U+2C61: LATIN SMALL LETTER L WITH DOUBLE BAR
       + "???"  // U+A747: LATIN SMALL LETTER BROKEN L
       + "???"  // U+A749: LATIN SMALL LETTER L WITH HIGH STROKE
       + "???"  // U+A781: LATIN SMALL LETTER TURNED L
       + "???"  // U+FF4C: FULLWIDTH LATIN SMALL LETTER L
      ,"l", // Folded result

       "??"  // U+01C7: LATIN CAPITAL LETTER LJ
      ,"LJ", // Folded result

       "???"  // U+1EFA: LATIN CAPITAL LETTER MIDDLE-WELSH LL
      ,"LL", // Folded result

       "??"  // U+01C8: LATIN CAPITAL LETTER L WITH SMALL LETTER J
      ,"Lj", // Folded result

       "???"  // U+24A7: PARENTHESIZED LATIN SMALL LETTER L
      ,"(l)", // Folded result

       "??"  // U+01C9: LATIN SMALL LETTER LJ
      ,"lj", // Folded result

       "???"  // U+1EFB: LATIN SMALL LETTER MIDDLE-WELSH LL
      ,"ll", // Folded result

       "??"  // U+02AA: LATIN SMALL LETTER LS DIGRAPH
      ,"ls", // Folded result

       "??"  // U+02AB: LATIN SMALL LETTER LZ DIGRAPH
      ,"lz", // Folded result

       "??"  // U+019C: LATIN CAPITAL LETTER TURNED M
       + "???"  // U+1D0D: LATIN LETTER SMALL CAPITAL M
       + "???"  // U+1E3E: LATIN CAPITAL LETTER M WITH ACUTE
       + "???"  // U+1E40: LATIN CAPITAL LETTER M WITH DOT ABOVE
       + "???"  // U+1E42: LATIN CAPITAL LETTER M WITH DOT BELOW
       + "???"  // U+24C2: CIRCLED LATIN CAPITAL LETTER M
       + "???"  // U+2C6E: LATIN CAPITAL LETTER M WITH HOOK
       + "???"  // U+A7FD: LATIN EPIGRAPHIC LETTER INVERTED M
       + "???"  // U+A7FF: LATIN EPIGRAPHIC LETTER ARCHAIC M
       + "???"  // U+FF2D: FULLWIDTH LATIN CAPITAL LETTER M
      ,"M", // Folded result

       "??"  // U+026F: LATIN SMALL LETTER TURNED M
       + "??"  // U+0270: LATIN SMALL LETTER TURNED M WITH LONG LEG
       + "??"  // U+0271: LATIN SMALL LETTER M WITH HOOK
       + "???"  // U+1D6F: LATIN SMALL LETTER M WITH MIDDLE TILDE
       + "???"  // U+1D86: LATIN SMALL LETTER M WITH PALATAL HOOK
       + "???"  // U+1E3F: LATIN SMALL LETTER M WITH ACUTE
       + "???"  // U+1E41: LATIN SMALL LETTER M WITH DOT ABOVE
       + "???"  // U+1E43: LATIN SMALL LETTER M WITH DOT BELOW
       + "???"  // U+24DC: CIRCLED LATIN SMALL LETTER M
       + "???"  // U+FF4D: FULLWIDTH LATIN SMALL LETTER M
      ,"m", // Folded result

       "???"  // U+24A8: PARENTHESIZED LATIN SMALL LETTER M
      ,"(m)", // Folded result

       "??"  // U+00D1: LATIN CAPITAL LETTER N WITH TILDE
       + "??"  // U+0143: LATIN CAPITAL LETTER N WITH ACUTE
       + "??"  // U+0145: LATIN CAPITAL LETTER N WITH CEDILLA
       + "??"  // U+0147: LATIN CAPITAL LETTER N WITH CARON
       + "??"  // U+014A: LATIN CAPITAL LETTER ENG
       + "??"  // U+019D: LATIN CAPITAL LETTER N WITH LEFT HOOK
       + "??"  // U+01F8: LATIN CAPITAL LETTER N WITH GRAVE
       + "??"  // U+0220: LATIN CAPITAL LETTER N WITH LONG RIGHT LEG
       + "??"  // U+0274: LATIN LETTER SMALL CAPITAL N
       + "???"  // U+1D0E: LATIN LETTER SMALL CAPITAL REVERSED N
       + "???"  // U+1E44: LATIN CAPITAL LETTER N WITH DOT ABOVE
       + "???"  // U+1E46: LATIN CAPITAL LETTER N WITH DOT BELOW
       + "???"  // U+1E48: LATIN CAPITAL LETTER N WITH LINE BELOW
       + "???"  // U+1E4A: LATIN CAPITAL LETTER N WITH CIRCUMFLEX BELOW
       + "???"  // U+24C3: CIRCLED LATIN CAPITAL LETTER N
       + "???"  // U+FF2E: FULLWIDTH LATIN CAPITAL LETTER N
      ,"N", // Folded result

       "??"  // U+00F1: LATIN SMALL LETTER N WITH TILDE
       + "??"  // U+0144: LATIN SMALL LETTER N WITH ACUTE
       + "??"  // U+0146: LATIN SMALL LETTER N WITH CEDILLA
       + "??"  // U+0148: LATIN SMALL LETTER N WITH CARON
       + "??"  // U+0149: LATIN SMALL LETTER N PRECEDED BY APOSTROPHE
       + "??"  // U+014B: LATIN SMALL LETTER ENG
       + "??"  // U+019E: LATIN SMALL LETTER N WITH LONG RIGHT LEG
       + "??"  // U+01F9: LATIN SMALL LETTER N WITH GRAVE
       + "??"  // U+0235: LATIN SMALL LETTER N WITH CURL
       + "??"  // U+0272: LATIN SMALL LETTER N WITH LEFT HOOK
       + "??"  // U+0273: LATIN SMALL LETTER N WITH RETROFLEX HOOK
       + "???"  // U+1D70: LATIN SMALL LETTER N WITH MIDDLE TILDE
       + "???"  // U+1D87: LATIN SMALL LETTER N WITH PALATAL HOOK
       + "???"  // U+1E45: LATIN SMALL LETTER N WITH DOT ABOVE
       + "???"  // U+1E47: LATIN SMALL LETTER N WITH DOT BELOW
       + "???"  // U+1E49: LATIN SMALL LETTER N WITH LINE BELOW
       + "???"  // U+1E4B: LATIN SMALL LETTER N WITH CIRCUMFLEX BELOW
       + "???"  // U+207F: SUPERSCRIPT LATIN SMALL LETTER N
       + "???"  // U+24DD: CIRCLED LATIN SMALL LETTER N
       + "???"  // U+FF4E: FULLWIDTH LATIN SMALL LETTER N
      ,"n", // Folded result

       "??"  // U+01CA: LATIN CAPITAL LETTER NJ
      ,"NJ", // Folded result

       "??"  // U+01CB: LATIN CAPITAL LETTER N WITH SMALL LETTER J
      ,"Nj", // Folded result

       "???"  // U+24A9: PARENTHESIZED LATIN SMALL LETTER N
      ,"(n)", // Folded result

       "??"  // U+01CC: LATIN SMALL LETTER NJ
      ,"nj", // Folded result

       "??"  // U+00D2: LATIN CAPITAL LETTER O WITH GRAVE
       + "??"  // U+00D3: LATIN CAPITAL LETTER O WITH ACUTE
       + "??"  // U+00D4: LATIN CAPITAL LETTER O WITH CIRCUMFLEX
       + "??"  // U+00D5: LATIN CAPITAL LETTER O WITH TILDE
       + "??"  // U+00D6: LATIN CAPITAL LETTER O WITH DIAERESIS
       + "??"  // U+00D8: LATIN CAPITAL LETTER O WITH STROKE
       + "??"  // U+014C: LATIN CAPITAL LETTER O WITH MACRON
       + "??"  // U+014E: LATIN CAPITAL LETTER O WITH BREVE
       + "??"  // U+0150: LATIN CAPITAL LETTER O WITH DOUBLE ACUTE
       + "??"  // U+0186: LATIN CAPITAL LETTER OPEN O
       + "??"  // U+019F: LATIN CAPITAL LETTER O WITH MIDDLE TILDE
       + "??"  // U+01A0: LATIN CAPITAL LETTER O WITH HORN
       + "??"  // U+01D1: LATIN CAPITAL LETTER O WITH CARON
       + "??"  // U+01EA: LATIN CAPITAL LETTER O WITH OGONEK
       + "??"  // U+01EC: LATIN CAPITAL LETTER O WITH OGONEK AND MACRON
       + "??"  // U+01FE: LATIN CAPITAL LETTER O WITH STROKE AND ACUTE
       + "??"  // U+020C: LATIN CAPITAL LETTER O WITH DOUBLE GRAVE
       + "??"  // U+020E: LATIN CAPITAL LETTER O WITH INVERTED BREVE
       + "??"  // U+022A: LATIN CAPITAL LETTER O WITH DIAERESIS AND MACRON
       + "??"  // U+022C: LATIN CAPITAL LETTER O WITH TILDE AND MACRON
       + "??"  // U+022E: LATIN CAPITAL LETTER O WITH DOT ABOVE
       + "??"  // U+0230: LATIN CAPITAL LETTER O WITH DOT ABOVE AND MACRON
       + "???"  // U+1D0F: LATIN LETTER SMALL CAPITAL O
       + "???"  // U+1D10: LATIN LETTER SMALL CAPITAL OPEN O
       + "???"  // U+1E4C: LATIN CAPITAL LETTER O WITH TILDE AND ACUTE
       + "???"  // U+1E4E: LATIN CAPITAL LETTER O WITH TILDE AND DIAERESIS
       + "???"  // U+1E50: LATIN CAPITAL LETTER O WITH MACRON AND GRAVE
       + "???"  // U+1E52: LATIN CAPITAL LETTER O WITH MACRON AND ACUTE
       + "???"  // U+1ECC: LATIN CAPITAL LETTER O WITH DOT BELOW
       + "???"  // U+1ECE: LATIN CAPITAL LETTER O WITH HOOK ABOVE
       + "???"  // U+1ED0: LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND ACUTE
       + "???"  // U+1ED2: LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND GRAVE
       + "???"  // U+1ED4: LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND HOOK ABOVE
       + "???"  // U+1ED6: LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND TILDE
       + "???"  // U+1ED8: LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND DOT BELOW
       + "???"  // U+1EDA: LATIN CAPITAL LETTER O WITH HORN AND ACUTE
       + "???"  // U+1EDC: LATIN CAPITAL LETTER O WITH HORN AND GRAVE
       + "???"  // U+1EDE: LATIN CAPITAL LETTER O WITH HORN AND HOOK ABOVE
       + "???"  // U+1EE0: LATIN CAPITAL LETTER O WITH HORN AND TILDE
       + "???"  // U+1EE2: LATIN CAPITAL LETTER O WITH HORN AND DOT BELOW
       + "???"  // U+24C4: CIRCLED LATIN CAPITAL LETTER O
       + "???"  // U+A74A: LATIN CAPITAL LETTER O WITH LONG STROKE OVERLAY
       + "???"  // U+A74C: LATIN CAPITAL LETTER O WITH LOOP
       + "???"  // U+FF2F: FULLWIDTH LATIN CAPITAL LETTER O
      ,"O", // Folded result

       "??"  // U+00F2: LATIN SMALL LETTER O WITH GRAVE
       + "??"  // U+00F3: LATIN SMALL LETTER O WITH ACUTE
       + "??"  // U+00F4: LATIN SMALL LETTER O WITH CIRCUMFLEX
       + "??"  // U+00F5: LATIN SMALL LETTER O WITH TILDE
       + "??"  // U+00F6: LATIN SMALL LETTER O WITH DIAERESIS
       + "??"  // U+00F8: LATIN SMALL LETTER O WITH STROKE
       + "??"  // U+014D: LATIN SMALL LETTER O WITH MACRON
       + "??"  // U+014F: LATIN SMALL LETTER O WITH BREVE
       + "??"  // U+0151: LATIN SMALL LETTER O WITH DOUBLE ACUTE
       + "??"  // U+01A1: LATIN SMALL LETTER O WITH HORN
       + "??"  // U+01D2: LATIN SMALL LETTER O WITH CARON
       + "??"  // U+01EB: LATIN SMALL LETTER O WITH OGONEK
       + "??"  // U+01ED: LATIN SMALL LETTER O WITH OGONEK AND MACRON
       + "??"  // U+01FF: LATIN SMALL LETTER O WITH STROKE AND ACUTE
       + "??"  // U+020D: LATIN SMALL LETTER O WITH DOUBLE GRAVE
       + "??"  // U+020F: LATIN SMALL LETTER O WITH INVERTED BREVE
       + "??"  // U+022B: LATIN SMALL LETTER O WITH DIAERESIS AND MACRON
       + "??"  // U+022D: LATIN SMALL LETTER O WITH TILDE AND MACRON
       + "??"  // U+022F: LATIN SMALL LETTER O WITH DOT ABOVE
       + "??"  // U+0231: LATIN SMALL LETTER O WITH DOT ABOVE AND MACRON
       + "??"  // U+0254: LATIN SMALL LETTER OPEN O
       + "??"  // U+0275: LATIN SMALL LETTER BARRED O
       + "???"  // U+1D16: LATIN SMALL LETTER TOP HALF O
       + "???"  // U+1D17: LATIN SMALL LETTER BOTTOM HALF O
       + "???"  // U+1D97: LATIN SMALL LETTER OPEN O WITH RETROFLEX HOOK
       + "???"  // U+1E4D: LATIN SMALL LETTER O WITH TILDE AND ACUTE
       + "???"  // U+1E4F: LATIN SMALL LETTER O WITH TILDE AND DIAERESIS
       + "???"  // U+1E51: LATIN SMALL LETTER O WITH MACRON AND GRAVE
       + "???"  // U+1E53: LATIN SMALL LETTER O WITH MACRON AND ACUTE
       + "???"  // U+1ECD: LATIN SMALL LETTER O WITH DOT BELOW
       + "???"  // U+1ECF: LATIN SMALL LETTER O WITH HOOK ABOVE
       + "???"  // U+1ED1: LATIN SMALL LETTER O WITH CIRCUMFLEX AND ACUTE
       + "???"  // U+1ED3: LATIN SMALL LETTER O WITH CIRCUMFLEX AND GRAVE
       + "???"  // U+1ED5: LATIN SMALL LETTER O WITH CIRCUMFLEX AND HOOK ABOVE
       + "???"  // U+1ED7: LATIN SMALL LETTER O WITH CIRCUMFLEX AND TILDE
       + "???"  // U+1ED9: LATIN SMALL LETTER O WITH CIRCUMFLEX AND DOT BELOW
       + "???"  // U+1EDB: LATIN SMALL LETTER O WITH HORN AND ACUTE
       + "???"  // U+1EDD: LATIN SMALL LETTER O WITH HORN AND GRAVE
       + "???"  // U+1EDF: LATIN SMALL LETTER O WITH HORN AND HOOK ABOVE
       + "???"  // U+1EE1: LATIN SMALL LETTER O WITH HORN AND TILDE
       + "???"  // U+1EE3: LATIN SMALL LETTER O WITH HORN AND DOT BELOW
       + "???"  // U+2092: LATIN SUBSCRIPT SMALL LETTER O
       + "???"  // U+24DE: CIRCLED LATIN SMALL LETTER O
       + "???"  // U+2C7A: LATIN SMALL LETTER O WITH LOW RING INSIDE
       + "???"  // U+A74B: LATIN SMALL LETTER O WITH LONG STROKE OVERLAY
       + "???"  // U+A74D: LATIN SMALL LETTER O WITH LOOP
       + "???"  // U+FF4F: FULLWIDTH LATIN SMALL LETTER O
      ,"o", // Folded result

       "??"  // U+0152: LATIN CAPITAL LIGATURE OE
       + "??"  // U+0276: LATIN LETTER SMALL CAPITAL OE
      ,"OE", // Folded result

       "???"  // U+A74E: LATIN CAPITAL LETTER OO
      ,"OO", // Folded result

       "??"  // U+0222: LATIN CAPITAL LETTER OU
       + "???"  // U+1D15: LATIN LETTER SMALL CAPITAL OU
      ,"OU", // Folded result

       "???"  // U+24AA: PARENTHESIZED LATIN SMALL LETTER O
      ,"(o)", // Folded result

       "??"  // U+0153: LATIN SMALL LIGATURE OE
       + "???"  // U+1D14: LATIN SMALL LETTER TURNED OE
      ,"oe", // Folded result

       "???"  // U+A74F: LATIN SMALL LETTER OO
      ,"oo", // Folded result

       "??"  // U+0223: LATIN SMALL LETTER OU
      ,"ou", // Folded result

       "??"  // U+01A4: LATIN CAPITAL LETTER P WITH HOOK
       + "???"  // U+1D18: LATIN LETTER SMALL CAPITAL P
       + "???"  // U+1E54: LATIN CAPITAL LETTER P WITH ACUTE
       + "???"  // U+1E56: LATIN CAPITAL LETTER P WITH DOT ABOVE
       + "???"  // U+24C5: CIRCLED LATIN CAPITAL LETTER P
       + "???"  // U+2C63: LATIN CAPITAL LETTER P WITH STROKE
       + "???"  // U+A750: LATIN CAPITAL LETTER P WITH STROKE THROUGH DESCENDER
       + "???"  // U+A752: LATIN CAPITAL LETTER P WITH FLOURISH
       + "???"  // U+A754: LATIN CAPITAL LETTER P WITH SQUIRREL TAIL
       + "???"  // U+FF30: FULLWIDTH LATIN CAPITAL LETTER P
      ,"P", // Folded result

       "??"  // U+01A5: LATIN SMALL LETTER P WITH HOOK
       + "???"  // U+1D71: LATIN SMALL LETTER P WITH MIDDLE TILDE
       + "???"  // U+1D7D: LATIN SMALL LETTER P WITH STROKE
       + "???"  // U+1D88: LATIN SMALL LETTER P WITH PALATAL HOOK
       + "???"  // U+1E55: LATIN SMALL LETTER P WITH ACUTE
       + "???"  // U+1E57: LATIN SMALL LETTER P WITH DOT ABOVE
       + "???"  // U+24DF: CIRCLED LATIN SMALL LETTER P
       + "???"  // U+A751: LATIN SMALL LETTER P WITH STROKE THROUGH DESCENDER
       + "???"  // U+A753: LATIN SMALL LETTER P WITH FLOURISH
       + "???"  // U+A755: LATIN SMALL LETTER P WITH SQUIRREL TAIL
       + "???"  // U+A7FC: LATIN EPIGRAPHIC LETTER REVERSED P
       + "???"  // U+FF50: FULLWIDTH LATIN SMALL LETTER P
      ,"p", // Folded result

       "???"  // U+24AB: PARENTHESIZED LATIN SMALL LETTER P
      ,"(p)", // Folded result

       "??"  // U+024A: LATIN CAPITAL LETTER SMALL Q WITH HOOK TAIL
       + "???"  // U+24C6: CIRCLED LATIN CAPITAL LETTER Q
       + "???"  // U+A756: LATIN CAPITAL LETTER Q WITH STROKE THROUGH DESCENDER
       + "???"  // U+A758: LATIN CAPITAL LETTER Q WITH DIAGONAL STROKE
       + "???"  // U+FF31: FULLWIDTH LATIN CAPITAL LETTER Q
      ,"Q", // Folded result

       "??"  // U+0138: LATIN SMALL LETTER KRA
       + "??"  // U+024B: LATIN SMALL LETTER Q WITH HOOK TAIL
       + "??"  // U+02A0: LATIN SMALL LETTER Q WITH HOOK
       + "???"  // U+24E0: CIRCLED LATIN SMALL LETTER Q
       + "???"  // U+A757: LATIN SMALL LETTER Q WITH STROKE THROUGH DESCENDER
       + "???"  // U+A759: LATIN SMALL LETTER Q WITH DIAGONAL STROKE
       + "???"  // U+FF51: FULLWIDTH LATIN SMALL LETTER Q
      ,"q", // Folded result

       "???"  // U+24AC: PARENTHESIZED LATIN SMALL LETTER Q
      ,"(q)", // Folded result

       "??"  // U+0239: LATIN SMALL LETTER QP DIGRAPH
      ,"qp", // Folded result

       "??"  // U+0154: LATIN CAPITAL LETTER R WITH ACUTE
       + "??"  // U+0156: LATIN CAPITAL LETTER R WITH CEDILLA
       + "??"  // U+0158: LATIN CAPITAL LETTER R WITH CARON
       + "??"  // U+0210: LATIN CAPITAL LETTER R WITH DOUBLE GRAVE
       + "??"  // U+0212: LATIN CAPITAL LETTER R WITH INVERTED BREVE
       + "??"  // U+024C: LATIN CAPITAL LETTER R WITH STROKE
       + "??"  // U+0280: LATIN LETTER SMALL CAPITAL R
       + "??"  // U+0281: LATIN LETTER SMALL CAPITAL INVERTED R
       + "???"  // U+1D19: LATIN LETTER SMALL CAPITAL REVERSED R
       + "???"  // U+1D1A: LATIN LETTER SMALL CAPITAL TURNED R
       + "???"  // U+1E58: LATIN CAPITAL LETTER R WITH DOT ABOVE
       + "???"  // U+1E5A: LATIN CAPITAL LETTER R WITH DOT BELOW
       + "???"  // U+1E5C: LATIN CAPITAL LETTER R WITH DOT BELOW AND MACRON
       + "???"  // U+1E5E: LATIN CAPITAL LETTER R WITH LINE BELOW
       + "???"  // U+24C7: CIRCLED LATIN CAPITAL LETTER R
       + "???"  // U+2C64: LATIN CAPITAL LETTER R WITH TAIL
       + "???"  // U+A75A: LATIN CAPITAL LETTER R ROTUNDA
       + "???"  // U+A782: LATIN CAPITAL LETTER INSULAR R
       + "???"  // U+FF32: FULLWIDTH LATIN CAPITAL LETTER R
      ,"R", // Folded result

       "??"  // U+0155: LATIN SMALL LETTER R WITH ACUTE
       + "??"  // U+0157: LATIN SMALL LETTER R WITH CEDILLA
       + "??"  // U+0159: LATIN SMALL LETTER R WITH CARON
       + "??"  // U+0211: LATIN SMALL LETTER R WITH DOUBLE GRAVE
       + "??"  // U+0213: LATIN SMALL LETTER R WITH INVERTED BREVE
       + "??"  // U+024D: LATIN SMALL LETTER R WITH STROKE
       + "??"  // U+027C: LATIN SMALL LETTER R WITH LONG LEG
       + "??"  // U+027D: LATIN SMALL LETTER R WITH TAIL
       + "??"  // U+027E: LATIN SMALL LETTER R WITH FISHHOOK
       + "??"  // U+027F: LATIN SMALL LETTER REVERSED R WITH FISHHOOK
       + "???"  // U+1D63: LATIN SUBSCRIPT SMALL LETTER R
       + "???"  // U+1D72: LATIN SMALL LETTER R WITH MIDDLE TILDE
       + "???"  // U+1D73: LATIN SMALL LETTER R WITH FISHHOOK AND MIDDLE TILDE
       + "???"  // U+1D89: LATIN SMALL LETTER R WITH PALATAL HOOK
       + "???"  // U+1E59: LATIN SMALL LETTER R WITH DOT ABOVE
       + "???"  // U+1E5B: LATIN SMALL LETTER R WITH DOT BELOW
       + "???"  // U+1E5D: LATIN SMALL LETTER R WITH DOT BELOW AND MACRON
       + "???"  // U+1E5F: LATIN SMALL LETTER R WITH LINE BELOW
       + "???"  // U+24E1: CIRCLED LATIN SMALL LETTER R
       + "???"  // U+A75B: LATIN SMALL LETTER R ROTUNDA
       + "???"  // U+A783: LATIN SMALL LETTER INSULAR R
       + "???"  // U+FF52: FULLWIDTH LATIN SMALL LETTER R
      ,"r", // Folded result

       "???"  // U+24AD: PARENTHESIZED LATIN SMALL LETTER R
      ,"(r)", // Folded result

       "??"  // U+015A: LATIN CAPITAL LETTER S WITH ACUTE
       + "??"  // U+015C: LATIN CAPITAL LETTER S WITH CIRCUMFLEX
       + "??"  // U+015E: LATIN CAPITAL LETTER S WITH CEDILLA
       + "??"  // U+0160: LATIN CAPITAL LETTER S WITH CARON
       + "??"  // U+0218: LATIN CAPITAL LETTER S WITH COMMA BELOW
       + "???"  // U+1E60: LATIN CAPITAL LETTER S WITH DOT ABOVE
       + "???"  // U+1E62: LATIN CAPITAL LETTER S WITH DOT BELOW
       + "???"  // U+1E64: LATIN CAPITAL LETTER S WITH ACUTE AND DOT ABOVE
       + "???"  // U+1E66: LATIN CAPITAL LETTER S WITH CARON AND DOT ABOVE
       + "???"  // U+1E68: LATIN CAPITAL LETTER S WITH DOT BELOW AND DOT ABOVE
       + "???"  // U+24C8: CIRCLED LATIN CAPITAL LETTER S
       + "???"  // U+A731: LATIN LETTER SMALL CAPITAL S
       + "???"  // U+A785: LATIN SMALL LETTER INSULAR S
       + "???"  // U+FF33: FULLWIDTH LATIN CAPITAL LETTER S
      ,"S", // Folded result

       "??"  // U+015B: LATIN SMALL LETTER S WITH ACUTE
       + "??"  // U+015D: LATIN SMALL LETTER S WITH CIRCUMFLEX
       + "??"  // U+015F: LATIN SMALL LETTER S WITH CEDILLA
       + "??"  // U+0161: LATIN SMALL LETTER S WITH CARON
       + "??"  // U+017F: LATIN SMALL LETTER LONG S
       + "??"  // U+0219: LATIN SMALL LETTER S WITH COMMA BELOW
       + "??"  // U+023F: LATIN SMALL LETTER S WITH SWASH TAIL
       + "??"  // U+0282: LATIN SMALL LETTER S WITH HOOK
       + "???"  // U+1D74: LATIN SMALL LETTER S WITH MIDDLE TILDE
       + "???"  // U+1D8A: LATIN SMALL LETTER S WITH PALATAL HOOK
       + "???"  // U+1E61: LATIN SMALL LETTER S WITH DOT ABOVE
       + "???"  // U+1E63: LATIN SMALL LETTER S WITH DOT BELOW
       + "???"  // U+1E65: LATIN SMALL LETTER S WITH ACUTE AND DOT ABOVE
       + "???"  // U+1E67: LATIN SMALL LETTER S WITH CARON AND DOT ABOVE
       + "???"  // U+1E69: LATIN SMALL LETTER S WITH DOT BELOW AND DOT ABOVE
       + "???"  // U+1E9C: LATIN SMALL LETTER LONG S WITH DIAGONAL STROKE
       + "???"  // U+1E9D: LATIN SMALL LETTER LONG S WITH HIGH STROKE
       + "???"  // U+24E2: CIRCLED LATIN SMALL LETTER S
       + "???"  // U+A784: LATIN CAPITAL LETTER INSULAR S
       + "???"  // U+FF53: FULLWIDTH LATIN SMALL LETTER S
      ,"s", // Folded result

       "???"  // U+1E9E: LATIN CAPITAL LETTER SHARP S
      ,"SS", // Folded result

       "???"  // U+24AE: PARENTHESIZED LATIN SMALL LETTER S
      ,"(s)", // Folded result

       "??"  // U+00DF: LATIN SMALL LETTER SHARP S
      ,"ss", // Folded result

       "???"  // U+FB06: LATIN SMALL LIGATURE ST
      ,"st", // Folded result

       "??"  // U+0162: LATIN CAPITAL LETTER T WITH CEDILLA
       + "??"  // U+0164: LATIN CAPITAL LETTER T WITH CARON
       + "??"  // U+0166: LATIN CAPITAL LETTER T WITH STROKE
       + "??"  // U+01AC: LATIN CAPITAL LETTER T WITH HOOK
       + "??"  // U+01AE: LATIN CAPITAL LETTER T WITH RETROFLEX HOOK
       + "??"  // U+021A: LATIN CAPITAL LETTER T WITH COMMA BELOW
       + "??"  // U+023E: LATIN CAPITAL LETTER T WITH DIAGONAL STROKE
       + "???"  // U+1D1B: LATIN LETTER SMALL CAPITAL T
       + "???"  // U+1E6A: LATIN CAPITAL LETTER T WITH DOT ABOVE
       + "???"  // U+1E6C: LATIN CAPITAL LETTER T WITH DOT BELOW
       + "???"  // U+1E6E: LATIN CAPITAL LETTER T WITH LINE BELOW
       + "???"  // U+1E70: LATIN CAPITAL LETTER T WITH CIRCUMFLEX BELOW
       + "???"  // U+24C9: CIRCLED LATIN CAPITAL LETTER T
       + "???"  // U+A786: LATIN CAPITAL LETTER INSULAR T
       + "???"  // U+FF34: FULLWIDTH LATIN CAPITAL LETTER T
      ,"T", // Folded result

       "??"  // U+0163: LATIN SMALL LETTER T WITH CEDILLA
       + "??"  // U+0165: LATIN SMALL LETTER T WITH CARON
       + "??"  // U+0167: LATIN SMALL LETTER T WITH STROKE
       + "??"  // U+01AB: LATIN SMALL LETTER T WITH PALATAL HOOK
       + "??"  // U+01AD: LATIN SMALL LETTER T WITH HOOK
       + "??"  // U+021B: LATIN SMALL LETTER T WITH COMMA BELOW
       + "??"  // U+0236: LATIN SMALL LETTER T WITH CURL
       + "??"  // U+0287: LATIN SMALL LETTER TURNED T
       + "??"  // U+0288: LATIN SMALL LETTER T WITH RETROFLEX HOOK
       + "???"  // U+1D75: LATIN SMALL LETTER T WITH MIDDLE TILDE
       + "???"  // U+1E6B: LATIN SMALL LETTER T WITH DOT ABOVE
       + "???"  // U+1E6D: LATIN SMALL LETTER T WITH DOT BELOW
       + "???"  // U+1E6F: LATIN SMALL LETTER T WITH LINE BELOW
       + "???"  // U+1E71: LATIN SMALL LETTER T WITH CIRCUMFLEX BELOW
       + "???"  // U+1E97: LATIN SMALL LETTER T WITH DIAERESIS
       + "???"  // U+24E3: CIRCLED LATIN SMALL LETTER T
       + "???"  // U+2C66: LATIN SMALL LETTER T WITH DIAGONAL STROKE
       + "???"  // U+FF54: FULLWIDTH LATIN SMALL LETTER T
      ,"t", // Folded result

       "??"  // U+00DE: LATIN CAPITAL LETTER THORN
       + "???"  // U+A766: LATIN CAPITAL LETTER THORN WITH STROKE THROUGH DESCENDER
      ,"TH", // Folded result

       "???"  // U+A728: LATIN CAPITAL LETTER TZ
      ,"TZ", // Folded result

       "???"  // U+24AF: PARENTHESIZED LATIN SMALL LETTER T
      ,"(t)", // Folded result

       "??"  // U+02A8: LATIN SMALL LETTER TC DIGRAPH WITH CURL
      ,"tc", // Folded result

       "??"  // U+00FE: LATIN SMALL LETTER THORN
       + "???"  // U+1D7A: LATIN SMALL LETTER TH WITH STRIKETHROUGH
       + "???"  // U+A767: LATIN SMALL LETTER THORN WITH STROKE THROUGH DESCENDER
      ,"th", // Folded result

       "??"  // U+02A6: LATIN SMALL LETTER TS DIGRAPH
      ,"ts", // Folded result

       "???"  // U+A729: LATIN SMALL LETTER TZ
      ,"tz", // Folded result

       "??"  // U+00D9: LATIN CAPITAL LETTER U WITH GRAVE
       + "??"  // U+00DA: LATIN CAPITAL LETTER U WITH ACUTE
       + "??"  // U+00DB: LATIN CAPITAL LETTER U WITH CIRCUMFLEX
       + "??"  // U+00DC: LATIN CAPITAL LETTER U WITH DIAERESIS
       + "??"  // U+0168: LATIN CAPITAL LETTER U WITH TILDE
       + "??"  // U+016A: LATIN CAPITAL LETTER U WITH MACRON
       + "??"  // U+016C: LATIN CAPITAL LETTER U WITH BREVE
       + "??"  // U+016E: LATIN CAPITAL LETTER U WITH RING ABOVE
       + "??"  // U+0170: LATIN CAPITAL LETTER U WITH DOUBLE ACUTE
       + "??"  // U+0172: LATIN CAPITAL LETTER U WITH OGONEK
       + "??"  // U+01AF: LATIN CAPITAL LETTER U WITH HORN
       + "??"  // U+01D3: LATIN CAPITAL LETTER U WITH CARON
       + "??"  // U+01D5: LATIN CAPITAL LETTER U WITH DIAERESIS AND MACRON
       + "??"  // U+01D7: LATIN CAPITAL LETTER U WITH DIAERESIS AND ACUTE
       + "??"  // U+01D9: LATIN CAPITAL LETTER U WITH DIAERESIS AND CARON
       + "??"  // U+01DB: LATIN CAPITAL LETTER U WITH DIAERESIS AND GRAVE
       + "??"  // U+0214: LATIN CAPITAL LETTER U WITH DOUBLE GRAVE
       + "??"  // U+0216: LATIN CAPITAL LETTER U WITH INVERTED BREVE
       + "??"  // U+0244: LATIN CAPITAL LETTER U BAR
       + "???"  // U+1D1C: LATIN LETTER SMALL CAPITAL U
       + "???"  // U+1D7E: LATIN SMALL CAPITAL LETTER U WITH STROKE
       + "???"  // U+1E72: LATIN CAPITAL LETTER U WITH DIAERESIS BELOW
       + "???"  // U+1E74: LATIN CAPITAL LETTER U WITH TILDE BELOW
       + "???"  // U+1E76: LATIN CAPITAL LETTER U WITH CIRCUMFLEX BELOW
       + "???"  // U+1E78: LATIN CAPITAL LETTER U WITH TILDE AND ACUTE
       + "???"  // U+1E7A: LATIN CAPITAL LETTER U WITH MACRON AND DIAERESIS
       + "???"  // U+1EE4: LATIN CAPITAL LETTER U WITH DOT BELOW
       + "???"  // U+1EE6: LATIN CAPITAL LETTER U WITH HOOK ABOVE
       + "???"  // U+1EE8: LATIN CAPITAL LETTER U WITH HORN AND ACUTE
       + "???"  // U+1EEA: LATIN CAPITAL LETTER U WITH HORN AND GRAVE
       + "???"  // U+1EEC: LATIN CAPITAL LETTER U WITH HORN AND HOOK ABOVE
       + "???"  // U+1EEE: LATIN CAPITAL LETTER U WITH HORN AND TILDE
       + "???"  // U+1EF0: LATIN CAPITAL LETTER U WITH HORN AND DOT BELOW
       + "???"  // U+24CA: CIRCLED LATIN CAPITAL LETTER U
       + "???"  // U+FF35: FULLWIDTH LATIN CAPITAL LETTER U
      ,"U", // Folded result

       "??"  // U+00F9: LATIN SMALL LETTER U WITH GRAVE
       + "??"  // U+00FA: LATIN SMALL LETTER U WITH ACUTE
       + "??"  // U+00FB: LATIN SMALL LETTER U WITH CIRCUMFLEX
       + "??"  // U+00FC: LATIN SMALL LETTER U WITH DIAERESIS
       + "??"  // U+0169: LATIN SMALL LETTER U WITH TILDE
       + "??"  // U+016B: LATIN SMALL LETTER U WITH MACRON
       + "??"  // U+016D: LATIN SMALL LETTER U WITH BREVE
       + "??"  // U+016F: LATIN SMALL LETTER U WITH RING ABOVE
       + "??"  // U+0171: LATIN SMALL LETTER U WITH DOUBLE ACUTE
       + "??"  // U+0173: LATIN SMALL LETTER U WITH OGONEK
       + "??"  // U+01B0: LATIN SMALL LETTER U WITH HORN
       + "??"  // U+01D4: LATIN SMALL LETTER U WITH CARON
       + "??"  // U+01D6: LATIN SMALL LETTER U WITH DIAERESIS AND MACRON
       + "??"  // U+01D8: LATIN SMALL LETTER U WITH DIAERESIS AND ACUTE
       + "??"  // U+01DA: LATIN SMALL LETTER U WITH DIAERESIS AND CARON
       + "??"  // U+01DC: LATIN SMALL LETTER U WITH DIAERESIS AND GRAVE
       + "??"  // U+0215: LATIN SMALL LETTER U WITH DOUBLE GRAVE
       + "??"  // U+0217: LATIN SMALL LETTER U WITH INVERTED BREVE
       + "??"  // U+0289: LATIN SMALL LETTER U BAR
       + "???"  // U+1D64: LATIN SUBSCRIPT SMALL LETTER U
       + "???"  // U+1D99: LATIN SMALL LETTER U WITH RETROFLEX HOOK
       + "???"  // U+1E73: LATIN SMALL LETTER U WITH DIAERESIS BELOW
       + "???"  // U+1E75: LATIN SMALL LETTER U WITH TILDE BELOW
       + "???"  // U+1E77: LATIN SMALL LETTER U WITH CIRCUMFLEX BELOW
       + "???"  // U+1E79: LATIN SMALL LETTER U WITH TILDE AND ACUTE
       + "???"  // U+1E7B: LATIN SMALL LETTER U WITH MACRON AND DIAERESIS
       + "???"  // U+1EE5: LATIN SMALL LETTER U WITH DOT BELOW
       + "???"  // U+1EE7: LATIN SMALL LETTER U WITH HOOK ABOVE
       + "???"  // U+1EE9: LATIN SMALL LETTER U WITH HORN AND ACUTE
       + "???"  // U+1EEB: LATIN SMALL LETTER U WITH HORN AND GRAVE
       + "???"  // U+1EED: LATIN SMALL LETTER U WITH HORN AND HOOK ABOVE
       + "???"  // U+1EEF: LATIN SMALL LETTER U WITH HORN AND TILDE
       + "???"  // U+1EF1: LATIN SMALL LETTER U WITH HORN AND DOT BELOW
       + "???"  // U+24E4: CIRCLED LATIN SMALL LETTER U
       + "???"  // U+FF55: FULLWIDTH LATIN SMALL LETTER U
      ,"u", // Folded result

       "???"  // U+24B0: PARENTHESIZED LATIN SMALL LETTER U
      ,"(u)", // Folded result

       "???"  // U+1D6B: LATIN SMALL LETTER UE
      ,"ue", // Folded result

       "??"  // U+01B2: LATIN CAPITAL LETTER V WITH HOOK
       + "??"  // U+0245: LATIN CAPITAL LETTER TURNED V
       + "???"  // U+1D20: LATIN LETTER SMALL CAPITAL V
       + "???"  // U+1E7C: LATIN CAPITAL LETTER V WITH TILDE
       + "???"  // U+1E7E: LATIN CAPITAL LETTER V WITH DOT BELOW
       + "???"  // U+1EFC: LATIN CAPITAL LETTER MIDDLE-WELSH V
       + "???"  // U+24CB: CIRCLED LATIN CAPITAL LETTER V
       + "???"  // U+A75E: LATIN CAPITAL LETTER V WITH DIAGONAL STROKE
       + "???"  // U+A768: LATIN CAPITAL LETTER VEND
       + "???"  // U+FF36: FULLWIDTH LATIN CAPITAL LETTER V
      ,"V", // Folded result

       "??"  // U+028B: LATIN SMALL LETTER V WITH HOOK
       + "??"  // U+028C: LATIN SMALL LETTER TURNED V
       + "???"  // U+1D65: LATIN SUBSCRIPT SMALL LETTER V
       + "???"  // U+1D8C: LATIN SMALL LETTER V WITH PALATAL HOOK
       + "???"  // U+1E7D: LATIN SMALL LETTER V WITH TILDE
       + "???"  // U+1E7F: LATIN SMALL LETTER V WITH DOT BELOW
       + "???"  // U+24E5: CIRCLED LATIN SMALL LETTER V
       + "???"  // U+2C71: LATIN SMALL LETTER V WITH RIGHT HOOK
       + "???"  // U+2C74: LATIN SMALL LETTER V WITH CURL
       + "???"  // U+A75F: LATIN SMALL LETTER V WITH DIAGONAL STROKE
       + "???"  // U+FF56: FULLWIDTH LATIN SMALL LETTER V
      ,"v", // Folded result

       "???"  // U+A760: LATIN CAPITAL LETTER VY
      ,"VY", // Folded result

       "???"  // U+24B1: PARENTHESIZED LATIN SMALL LETTER V
      ,"(v)", // Folded result

       "???"  // U+A761: LATIN SMALL LETTER VY
      ,"vy", // Folded result

       "??"  // U+0174: LATIN CAPITAL LETTER W WITH CIRCUMFLEX
       + "??"  // U+01F7: LATIN CAPITAL LETTER WYNN
       + "???"  // U+1D21: LATIN LETTER SMALL CAPITAL W
       + "???"  // U+1E80: LATIN CAPITAL LETTER W WITH GRAVE
       + "???"  // U+1E82: LATIN CAPITAL LETTER W WITH ACUTE
       + "???"  // U+1E84: LATIN CAPITAL LETTER W WITH DIAERESIS
       + "???"  // U+1E86: LATIN CAPITAL LETTER W WITH DOT ABOVE
       + "???"  // U+1E88: LATIN CAPITAL LETTER W WITH DOT BELOW
       + "???"  // U+24CC: CIRCLED LATIN CAPITAL LETTER W
       + "???"  // U+2C72: LATIN CAPITAL LETTER W WITH HOOK
       + "???"  // U+FF37: FULLWIDTH LATIN CAPITAL LETTER W
      ,"W", // Folded result

       "??"  // U+0175: LATIN SMALL LETTER W WITH CIRCUMFLEX
       + "??"  // U+01BF: LATIN LETTER WYNN
       + "??"  // U+028D: LATIN SMALL LETTER TURNED W
       + "???"  // U+1E81: LATIN SMALL LETTER W WITH GRAVE
       + "???"  // U+1E83: LATIN SMALL LETTER W WITH ACUTE
       + "???"  // U+1E85: LATIN SMALL LETTER W WITH DIAERESIS
       + "???"  // U+1E87: LATIN SMALL LETTER W WITH DOT ABOVE
       + "???"  // U+1E89: LATIN SMALL LETTER W WITH DOT BELOW
       + "???"  // U+1E98: LATIN SMALL LETTER W WITH RING ABOVE
       + "???"  // U+24E6: CIRCLED LATIN SMALL LETTER W
       + "???"  // U+2C73: LATIN SMALL LETTER W WITH HOOK
       + "???"  // U+FF57: FULLWIDTH LATIN SMALL LETTER W
      ,"w", // Folded result

       "???"  // U+24B2: PARENTHESIZED LATIN SMALL LETTER W
      ,"(w)", // Folded result

       "???"  // U+1E8A: LATIN CAPITAL LETTER X WITH DOT ABOVE
       + "???"  // U+1E8C: LATIN CAPITAL LETTER X WITH DIAERESIS
       + "???"  // U+24CD: CIRCLED LATIN CAPITAL LETTER X
       + "???"  // U+FF38: FULLWIDTH LATIN CAPITAL LETTER X
      ,"X", // Folded result

       "???"  // U+1D8D: LATIN SMALL LETTER X WITH PALATAL HOOK
       + "???"  // U+1E8B: LATIN SMALL LETTER X WITH DOT ABOVE
       + "???"  // U+1E8D: LATIN SMALL LETTER X WITH DIAERESIS
       + "???"  // U+2093: LATIN SUBSCRIPT SMALL LETTER X
       + "???"  // U+24E7: CIRCLED LATIN SMALL LETTER X
       + "???"  // U+FF58: FULLWIDTH LATIN SMALL LETTER X
      ,"x", // Folded result

       "???"  // U+24B3: PARENTHESIZED LATIN SMALL LETTER X
      ,"(x)", // Folded result

       "??"  // U+00DD: LATIN CAPITAL LETTER Y WITH ACUTE
       + "??"  // U+0176: LATIN CAPITAL LETTER Y WITH CIRCUMFLEX
       + "??"  // U+0178: LATIN CAPITAL LETTER Y WITH DIAERESIS
       + "??"  // U+01B3: LATIN CAPITAL LETTER Y WITH HOOK
       + "??"  // U+0232: LATIN CAPITAL LETTER Y WITH MACRON
       + "??"  // U+024E: LATIN CAPITAL LETTER Y WITH STROKE
       + "??"  // U+028F: LATIN LETTER SMALL CAPITAL Y
       + "???"  // U+1E8E: LATIN CAPITAL LETTER Y WITH DOT ABOVE
       + "???"  // U+1EF2: LATIN CAPITAL LETTER Y WITH GRAVE
       + "???"  // U+1EF4: LATIN CAPITAL LETTER Y WITH DOT BELOW
       + "???"  // U+1EF6: LATIN CAPITAL LETTER Y WITH HOOK ABOVE
       + "???"  // U+1EF8: LATIN CAPITAL LETTER Y WITH TILDE
       + "???"  // U+1EFE: LATIN CAPITAL LETTER Y WITH LOOP
       + "???"  // U+24CE: CIRCLED LATIN CAPITAL LETTER Y
       + "???"  // U+FF39: FULLWIDTH LATIN CAPITAL LETTER Y
      ,"Y", // Folded result

       "??"  // U+00FD: LATIN SMALL LETTER Y WITH ACUTE
       + "??"  // U+00FF: LATIN SMALL LETTER Y WITH DIAERESIS
       + "??"  // U+0177: LATIN SMALL LETTER Y WITH CIRCUMFLEX
       + "??"  // U+01B4: LATIN SMALL LETTER Y WITH HOOK
       + "??"  // U+0233: LATIN SMALL LETTER Y WITH MACRON
       + "??"  // U+024F: LATIN SMALL LETTER Y WITH STROKE
       + "??"  // U+028E: LATIN SMALL LETTER TURNED Y
       + "???"  // U+1E8F: LATIN SMALL LETTER Y WITH DOT ABOVE
       + "???"  // U+1E99: LATIN SMALL LETTER Y WITH RING ABOVE
       + "???"  // U+1EF3: LATIN SMALL LETTER Y WITH GRAVE
       + "???"  // U+1EF5: LATIN SMALL LETTER Y WITH DOT BELOW
       + "???"  // U+1EF7: LATIN SMALL LETTER Y WITH HOOK ABOVE
       + "???"  // U+1EF9: LATIN SMALL LETTER Y WITH TILDE
       + "???"  // U+1EFF: LATIN SMALL LETTER Y WITH LOOP
       + "???"  // U+24E8: CIRCLED LATIN SMALL LETTER Y
       + "???"  // U+FF59: FULLWIDTH LATIN SMALL LETTER Y
      ,"y", // Folded result

       "???"  // U+24B4: PARENTHESIZED LATIN SMALL LETTER Y
      ,"(y)", // Folded result

       "??"  // U+0179: LATIN CAPITAL LETTER Z WITH ACUTE
       + "??"  // U+017B: LATIN CAPITAL LETTER Z WITH DOT ABOVE
       + "??"  // U+017D: LATIN CAPITAL LETTER Z WITH CARON
       + "??"  // U+01B5: LATIN CAPITAL LETTER Z WITH STROKE
       + "??"  // U+021C: LATIN CAPITAL LETTER YOGH
       + "??"  // U+0224: LATIN CAPITAL LETTER Z WITH HOOK
       + "???"  // U+1D22: LATIN LETTER SMALL CAPITAL Z
       + "???"  // U+1E90: LATIN CAPITAL LETTER Z WITH CIRCUMFLEX
       + "???"  // U+1E92: LATIN CAPITAL LETTER Z WITH DOT BELOW
       + "???"  // U+1E94: LATIN CAPITAL LETTER Z WITH LINE BELOW
       + "???"  // U+24CF: CIRCLED LATIN CAPITAL LETTER Z
       + "???"  // U+2C6B: LATIN CAPITAL LETTER Z WITH DESCENDER
       + "???"  // U+A762: LATIN CAPITAL LETTER VISIGOTHIC Z
       + "???"  // U+FF3A: FULLWIDTH LATIN CAPITAL LETTER Z
      ,"Z", // Folded result

       "??"  // U+017A: LATIN SMALL LETTER Z WITH ACUTE
       + "??"  // U+017C: LATIN SMALL LETTER Z WITH DOT ABOVE
       + "??"  // U+017E: LATIN SMALL LETTER Z WITH CARON
       + "??"  // U+01B6: LATIN SMALL LETTER Z WITH STROKE
       + "??"  // U+021D: LATIN SMALL LETTER YOGH
       + "??"  // U+0225: LATIN SMALL LETTER Z WITH HOOK
       + "??"  // U+0240: LATIN SMALL LETTER Z WITH SWASH TAIL
       + "??"  // U+0290: LATIN SMALL LETTER Z WITH RETROFLEX HOOK
       + "??"  // U+0291: LATIN SMALL LETTER Z WITH CURL
       + "???"  // U+1D76: LATIN SMALL LETTER Z WITH MIDDLE TILDE
       + "???"  // U+1D8E: LATIN SMALL LETTER Z WITH PALATAL HOOK
       + "???"  // U+1E91: LATIN SMALL LETTER Z WITH CIRCUMFLEX
       + "???"  // U+1E93: LATIN SMALL LETTER Z WITH DOT BELOW
       + "???"  // U+1E95: LATIN SMALL LETTER Z WITH LINE BELOW
       + "???"  // U+24E9: CIRCLED LATIN SMALL LETTER Z
       + "???"  // U+2C6C: LATIN SMALL LETTER Z WITH DESCENDER
       + "???"  // U+A763: LATIN SMALL LETTER VISIGOTHIC Z
       + "???"  // U+FF5A: FULLWIDTH LATIN SMALL LETTER Z
      ,"z", // Folded result

       "???"  // U+24B5: PARENTHESIZED LATIN SMALL LETTER Z
      ,"(z)", // Folded result

       "???"  // U+2070: SUPERSCRIPT ZERO
       + "???"  // U+2080: SUBSCRIPT ZERO
       + "???"  // U+24EA: CIRCLED DIGIT ZERO
       + "???"  // U+24FF: NEGATIVE CIRCLED DIGIT ZERO
       + "???"  // U+FF10: FULLWIDTH DIGIT ZERO
      ,"0", // Folded result

       "??"  // U+00B9: SUPERSCRIPT ONE
       + "???"  // U+2081: SUBSCRIPT ONE
       + "???"  // U+2460: CIRCLED DIGIT ONE
       + "???"  // U+24F5: DOUBLE CIRCLED DIGIT ONE
       + "???"  // U+2776: DINGBAT NEGATIVE CIRCLED DIGIT ONE
       + "???"  // U+2780: DINGBAT CIRCLED SANS-SERIF DIGIT ONE
       + "???"  // U+278A: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT ONE
       + "???"  // U+FF11: FULLWIDTH DIGIT ONE
      ,"1", // Folded result

       "???"  // U+2488: DIGIT ONE FULL STOP
      ,"1.", // Folded result

       "???"  // U+2474: PARENTHESIZED DIGIT ONE
      ,"(1)", // Folded result

       "??"  // U+00B2: SUPERSCRIPT TWO
       + "???"  // U+2082: SUBSCRIPT TWO
       + "???"  // U+2461: CIRCLED DIGIT TWO
       + "???"  // U+24F6: DOUBLE CIRCLED DIGIT TWO
       + "???"  // U+2777: DINGBAT NEGATIVE CIRCLED DIGIT TWO
       + "???"  // U+2781: DINGBAT CIRCLED SANS-SERIF DIGIT TWO
       + "???"  // U+278B: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT TWO
       + "???"  // U+FF12: FULLWIDTH DIGIT TWO
      ,"2", // Folded result

       "???"  // U+2489: DIGIT TWO FULL STOP
      ,"2.", // Folded result

       "???"  // U+2475: PARENTHESIZED DIGIT TWO
      ,"(2)", // Folded result

       "??"  // U+00B3: SUPERSCRIPT THREE
       + "???"  // U+2083: SUBSCRIPT THREE
       + "???"  // U+2462: CIRCLED DIGIT THREE
       + "???"  // U+24F7: DOUBLE CIRCLED DIGIT THREE
       + "???"  // U+2778: DINGBAT NEGATIVE CIRCLED DIGIT THREE
       + "???"  // U+2782: DINGBAT CIRCLED SANS-SERIF DIGIT THREE
       + "???"  // U+278C: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT THREE
       + "???"  // U+FF13: FULLWIDTH DIGIT THREE
      ,"3", // Folded result

       "???"  // U+248A: DIGIT THREE FULL STOP
      ,"3.", // Folded result

       "???"  // U+2476: PARENTHESIZED DIGIT THREE
      ,"(3)", // Folded result

       "???"  // U+2074: SUPERSCRIPT FOUR
       + "???"  // U+2084: SUBSCRIPT FOUR
       + "???"  // U+2463: CIRCLED DIGIT FOUR
       + "???"  // U+24F8: DOUBLE CIRCLED DIGIT FOUR
       + "???"  // U+2779: DINGBAT NEGATIVE CIRCLED DIGIT FOUR
       + "???"  // U+2783: DINGBAT CIRCLED SANS-SERIF DIGIT FOUR
       + "???"  // U+278D: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT FOUR
       + "???"  // U+FF14: FULLWIDTH DIGIT FOUR
      ,"4", // Folded result

       "???"  // U+248B: DIGIT FOUR FULL STOP
      ,"4.", // Folded result

       "???"  // U+2477: PARENTHESIZED DIGIT FOUR
      ,"(4)", // Folded result

       "???"  // U+2075: SUPERSCRIPT FIVE
       + "???"  // U+2085: SUBSCRIPT FIVE
       + "???"  // U+2464: CIRCLED DIGIT FIVE
       + "???"  // U+24F9: DOUBLE CIRCLED DIGIT FIVE
       + "???"  // U+277A: DINGBAT NEGATIVE CIRCLED DIGIT FIVE
       + "???"  // U+2784: DINGBAT CIRCLED SANS-SERIF DIGIT FIVE
       + "???"  // U+278E: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT FIVE
       + "???"  // U+FF15: FULLWIDTH DIGIT FIVE
      ,"5", // Folded result

       "???"  // U+248C: DIGIT FIVE FULL STOP
      ,"5.", // Folded result

       "???"  // U+2478: PARENTHESIZED DIGIT FIVE
      ,"(5)", // Folded result

       "???"  // U+2076: SUPERSCRIPT SIX
       + "???"  // U+2086: SUBSCRIPT SIX
       + "???"  // U+2465: CIRCLED DIGIT SIX
       + "???"  // U+24FA: DOUBLE CIRCLED DIGIT SIX
       + "???"  // U+277B: DINGBAT NEGATIVE CIRCLED DIGIT SIX
       + "???"  // U+2785: DINGBAT CIRCLED SANS-SERIF DIGIT SIX
       + "???"  // U+278F: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT SIX
       + "???"  // U+FF16: FULLWIDTH DIGIT SIX
      ,"6", // Folded result

       "???"  // U+248D: DIGIT SIX FULL STOP
      ,"6.", // Folded result

       "???"  // U+2479: PARENTHESIZED DIGIT SIX
      ,"(6)", // Folded result

       "???"  // U+2077: SUPERSCRIPT SEVEN
       + "???"  // U+2087: SUBSCRIPT SEVEN
       + "???"  // U+2466: CIRCLED DIGIT SEVEN
       + "???"  // U+24FB: DOUBLE CIRCLED DIGIT SEVEN
       + "???"  // U+277C: DINGBAT NEGATIVE CIRCLED DIGIT SEVEN
       + "???"  // U+2786: DINGBAT CIRCLED SANS-SERIF DIGIT SEVEN
       + "???"  // U+2790: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT SEVEN
       + "???"  // U+FF17: FULLWIDTH DIGIT SEVEN
      ,"7", // Folded result

       "???"  // U+248E: DIGIT SEVEN FULL STOP
      ,"7.", // Folded result

       "???"  // U+247A: PARENTHESIZED DIGIT SEVEN
      ,"(7)", // Folded result

       "???"  // U+2078: SUPERSCRIPT EIGHT
       + "???"  // U+2088: SUBSCRIPT EIGHT
       + "???"  // U+2467: CIRCLED DIGIT EIGHT
       + "???"  // U+24FC: DOUBLE CIRCLED DIGIT EIGHT
       + "???"  // U+277D: DINGBAT NEGATIVE CIRCLED DIGIT EIGHT
       + "???"  // U+2787: DINGBAT CIRCLED SANS-SERIF DIGIT EIGHT
       + "???"  // U+2791: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT EIGHT
       + "???"  // U+FF18: FULLWIDTH DIGIT EIGHT
      ,"8", // Folded result

       "???"  // U+248F: DIGIT EIGHT FULL STOP
      ,"8.", // Folded result

       "???"  // U+247B: PARENTHESIZED DIGIT EIGHT
      ,"(8)", // Folded result

       "???"  // U+2079: SUPERSCRIPT NINE
       + "???"  // U+2089: SUBSCRIPT NINE
       + "???"  // U+2468: CIRCLED DIGIT NINE
       + "???"  // U+24FD: DOUBLE CIRCLED DIGIT NINE
       + "???"  // U+277E: DINGBAT NEGATIVE CIRCLED DIGIT NINE
       + "???"  // U+2788: DINGBAT CIRCLED SANS-SERIF DIGIT NINE
       + "???"  // U+2792: DINGBAT NEGATIVE CIRCLED SANS-SERIF DIGIT NINE
       + "???"  // U+FF19: FULLWIDTH DIGIT NINE
      ,"9", // Folded result

       "???"  // U+2490: DIGIT NINE FULL STOP
      ,"9.", // Folded result

       "???"  // U+247C: PARENTHESIZED DIGIT NINE
      ,"(9)", // Folded result

       "???"  // U+2469: CIRCLED NUMBER TEN
       + "???"  // U+24FE: DOUBLE CIRCLED NUMBER TEN
       + "???"  // U+277F: DINGBAT NEGATIVE CIRCLED NUMBER TEN
       + "???"  // U+2789: DINGBAT CIRCLED SANS-SERIF NUMBER TEN
       + "???"  // U+2793: DINGBAT NEGATIVE CIRCLED SANS-SERIF NUMBER TEN
      ,"10", // Folded result

       "???"  // U+2491: NUMBER TEN FULL STOP
      ,"10.", // Folded result

       "???"  // U+247D: PARENTHESIZED NUMBER TEN
      ,"(10)", // Folded result

       "???"  // U+246A: CIRCLED NUMBER ELEVEN
       + "???"  // U+24EB: NEGATIVE CIRCLED NUMBER ELEVEN
      ,"11", // Folded result

       "???"  // U+2492: NUMBER ELEVEN FULL STOP
      ,"11.", // Folded result

       "???"  // U+247E: PARENTHESIZED NUMBER ELEVEN
      ,"(11)", // Folded result

       "???"  // U+246B: CIRCLED NUMBER TWELVE
       + "???"  // U+24EC: NEGATIVE CIRCLED NUMBER TWELVE
      ,"12", // Folded result

       "???"  // U+2493: NUMBER TWELVE FULL STOP
      ,"12.", // Folded result

       "???"  // U+247F: PARENTHESIZED NUMBER TWELVE
      ,"(12)", // Folded result

       "???"  // U+246C: CIRCLED NUMBER THIRTEEN
       + "???"  // U+24ED: NEGATIVE CIRCLED NUMBER THIRTEEN
      ,"13", // Folded result

       "???"  // U+2494: NUMBER THIRTEEN FULL STOP
      ,"13.", // Folded result

       "???"  // U+2480: PARENTHESIZED NUMBER THIRTEEN
      ,"(13)", // Folded result

       "???"  // U+246D: CIRCLED NUMBER FOURTEEN
       + "???"  // U+24EE: NEGATIVE CIRCLED NUMBER FOURTEEN
      ,"14", // Folded result

       "???"  // U+2495: NUMBER FOURTEEN FULL STOP
      ,"14.", // Folded result

       "???"  // U+2481: PARENTHESIZED NUMBER FOURTEEN
      ,"(14)", // Folded result

       "???"  // U+246E: CIRCLED NUMBER FIFTEEN
       + "???"  // U+24EF: NEGATIVE CIRCLED NUMBER FIFTEEN
      ,"15", // Folded result

       "???"  // U+2496: NUMBER FIFTEEN FULL STOP
      ,"15.", // Folded result

       "???"  // U+2482: PARENTHESIZED NUMBER FIFTEEN
      ,"(15)", // Folded result

       "???"  // U+246F: CIRCLED NUMBER SIXTEEN
       + "???"  // U+24F0: NEGATIVE CIRCLED NUMBER SIXTEEN
      ,"16", // Folded result

       "???"  // U+2497: NUMBER SIXTEEN FULL STOP
      ,"16.", // Folded result

       "???"  // U+2483: PARENTHESIZED NUMBER SIXTEEN
      ,"(16)", // Folded result

       "???"  // U+2470: CIRCLED NUMBER SEVENTEEN
       + "???"  // U+24F1: NEGATIVE CIRCLED NUMBER SEVENTEEN
      ,"17", // Folded result

       "???"  // U+2498: NUMBER SEVENTEEN FULL STOP
      ,"17.", // Folded result

       "???"  // U+2484: PARENTHESIZED NUMBER SEVENTEEN
      ,"(17)", // Folded result

       "???"  // U+2471: CIRCLED NUMBER EIGHTEEN
       + "???"  // U+24F2: NEGATIVE CIRCLED NUMBER EIGHTEEN
      ,"18", // Folded result

       "???"  // U+2499: NUMBER EIGHTEEN FULL STOP
      ,"18.", // Folded result

       "???"  // U+2485: PARENTHESIZED NUMBER EIGHTEEN
      ,"(18)", // Folded result

       "???"  // U+2472: CIRCLED NUMBER NINETEEN
       + "???"  // U+24F3: NEGATIVE CIRCLED NUMBER NINETEEN
      ,"19", // Folded result

       "???"  // U+249A: NUMBER NINETEEN FULL STOP
      ,"19.", // Folded result

       "???"  // U+2486: PARENTHESIZED NUMBER NINETEEN
      ,"(19)", // Folded result

       "???"  // U+2473: CIRCLED NUMBER TWENTY
       + "???"  // U+24F4: NEGATIVE CIRCLED NUMBER TWENTY
      ,"20", // Folded result

       "???"  // U+249B: NUMBER TWENTY FULL STOP
      ,"20.", // Folded result

       "???"  // U+2487: PARENTHESIZED NUMBER TWENTY
      ,"(20)", // Folded result

       "??"  // U+00AB: LEFT-POINTING DOUBLE ANGLE QUOTATION MARK
       + "??"  // U+00BB: RIGHT-POINTING DOUBLE ANGLE QUOTATION MARK
       + "???"  // U+201C: LEFT DOUBLE QUOTATION MARK
       + "???"  // U+201D: RIGHT DOUBLE QUOTATION MARK
       + "???"  // U+201E: DOUBLE LOW-9 QUOTATION MARK
       + "???"  // U+2033: DOUBLE PRIME
       + "???"  // U+2036: REVERSED DOUBLE PRIME
       + "???"  // U+275D: HEAVY DOUBLE TURNED COMMA QUOTATION MARK ORNAMENT
       + "???"  // U+275E: HEAVY DOUBLE COMMA QUOTATION MARK ORNAMENT
       + "???"  // U+276E: HEAVY LEFT-POINTING ANGLE QUOTATION MARK ORNAMENT
       + "???"  // U+276F: HEAVY RIGHT-POINTING ANGLE QUOTATION MARK ORNAMENT
       + "???"  // U+FF02: FULLWIDTH QUOTATION MARK
      ,"\"", // Folded result

       "???"  // U+2018: LEFT SINGLE QUOTATION MARK
       + "???"  // U+2019: RIGHT SINGLE QUOTATION MARK
       + "???"  // U+201A: SINGLE LOW-9 QUOTATION MARK
       + "???"  // U+201B: SINGLE HIGH-REVERSED-9 QUOTATION MARK
       + "???"  // U+2032: PRIME
       + "???"  // U+2035: REVERSED PRIME
       + "???"  // U+2039: SINGLE LEFT-POINTING ANGLE QUOTATION MARK
       + "???"  // U+203A: SINGLE RIGHT-POINTING ANGLE QUOTATION MARK
       + "???"  // U+275B: HEAVY SINGLE TURNED COMMA QUOTATION MARK ORNAMENT
       + "???"  // U+275C: HEAVY SINGLE COMMA QUOTATION MARK ORNAMENT
       + "???"  // U+FF07: FULLWIDTH APOSTROPHE
      ,"'", // Folded result

       "???"  // U+2010: HYPHEN
       + "???"  // U+2011: NON-BREAKING HYPHEN
       + "???"  // U+2012: FIGURE DASH
       + "???"  // U+2013: EN DASH
       + "???"  // U+2014: EM DASH
       + "???"  // U+207B: SUPERSCRIPT MINUS
       + "???"  // U+208B: SUBSCRIPT MINUS
       + "???"  // U+FF0D: FULLWIDTH HYPHEN-MINUS
      ,"-", // Folded result

       "???"  // U+2045: LEFT SQUARE BRACKET WITH QUILL
       + "???"  // U+2772: LIGHT LEFT TORTOISE SHELL BRACKET ORNAMENT
       + "???"  // U+FF3B: FULLWIDTH LEFT SQUARE BRACKET
      ,"[", // Folded result

       "???"  // U+2046: RIGHT SQUARE BRACKET WITH QUILL
       + "???"  // U+2773: LIGHT RIGHT TORTOISE SHELL BRACKET ORNAMENT
       + "???"  // U+FF3D: FULLWIDTH RIGHT SQUARE BRACKET
      ,"]", // Folded result

       "???"  // U+207D: SUPERSCRIPT LEFT PARENTHESIS
       + "???"  // U+208D: SUBSCRIPT LEFT PARENTHESIS
       + "???"  // U+2768: MEDIUM LEFT PARENTHESIS ORNAMENT
       + "???"  // U+276A: MEDIUM FLATTENED LEFT PARENTHESIS ORNAMENT
       + "???"  // U+FF08: FULLWIDTH LEFT PARENTHESIS
      ,"(", // Folded result

       "???"  // U+2E28: LEFT DOUBLE PARENTHESIS
      ,"((", // Folded result

       "???"  // U+207E: SUPERSCRIPT RIGHT PARENTHESIS
       + "???"  // U+208E: SUBSCRIPT RIGHT PARENTHESIS
       + "???"  // U+2769: MEDIUM RIGHT PARENTHESIS ORNAMENT
       + "???"  // U+276B: MEDIUM FLATTENED RIGHT PARENTHESIS ORNAMENT
       + "???"  // U+FF09: FULLWIDTH RIGHT PARENTHESIS
      ,")", // Folded result

       "???"  // U+2E29: RIGHT DOUBLE PARENTHESIS
      ,"))", // Folded result

       "???"  // U+276C: MEDIUM LEFT-POINTING ANGLE BRACKET ORNAMENT
       + "???"  // U+2770: HEAVY LEFT-POINTING ANGLE BRACKET ORNAMENT
       + "???"  // U+FF1C: FULLWIDTH LESS-THAN SIGN
      ,"<", // Folded result

       "???"  // U+276D: MEDIUM RIGHT-POINTING ANGLE BRACKET ORNAMENT
       + "???"  // U+2771: HEAVY RIGHT-POINTING ANGLE BRACKET ORNAMENT
       + "???"  // U+FF1E: FULLWIDTH GREATER-THAN SIGN
      ,">", // Folded result

       "???"  // U+2774: MEDIUM LEFT CURLY BRACKET ORNAMENT
       + "???"  // U+FF5B: FULLWIDTH LEFT CURLY BRACKET
      ,"{", // Folded result

       "???"  // U+2775: MEDIUM RIGHT CURLY BRACKET ORNAMENT
       + "???"  // U+FF5D: FULLWIDTH RIGHT CURLY BRACKET
      ,"}", // Folded result

       "???"  // U+207A: SUPERSCRIPT PLUS SIGN
       + "???"  // U+208A: SUBSCRIPT PLUS SIGN
       + "???"  // U+FF0B: FULLWIDTH PLUS SIGN
      ,"+", // Folded result

       "???"  // U+207C: SUPERSCRIPT EQUALS SIGN
       + "???"  // U+208C: SUBSCRIPT EQUALS SIGN
       + "???"  // U+FF1D: FULLWIDTH EQUALS SIGN
      ,"=", // Folded result

       "???"  // U+FF01: FULLWIDTH EXCLAMATION MARK
      ,"!", // Folded result

       "???"  // U+203C: DOUBLE EXCLAMATION MARK
      ,"!!", // Folded result

       "???"  // U+2049: EXCLAMATION QUESTION MARK
      ,"!?", // Folded result

       "???"  // U+FF03: FULLWIDTH NUMBER SIGN
      ,"#", // Folded result

       "???"  // U+FF04: FULLWIDTH DOLLAR SIGN
      ,"$", // Folded result

       "???"  // U+2052: COMMERCIAL MINUS SIGN
       + "???"  // U+FF05: FULLWIDTH PERCENT SIGN
      ,"%", // Folded result

       "???"  // U+FF06: FULLWIDTH AMPERSAND
      ,"&", // Folded result

       "???"  // U+204E: LOW ASTERISK
       + "???"  // U+FF0A: FULLWIDTH ASTERISK
      ,"*", // Folded result

       "???"  // U+FF0C: FULLWIDTH COMMA
      ,",", // Folded result

       "???"  // U+FF0E: FULLWIDTH FULL STOP
      ,".", // Folded result

       "???"  // U+2044: FRACTION SLASH
       + "???"  // U+FF0F: FULLWIDTH SOLIDUS
      ,"/", // Folded result

       "???"  // U+FF1A: FULLWIDTH COLON
      ,":", // Folded result

       "???"  // U+204F: REVERSED SEMICOLON
       + "???"  // U+FF1B: FULLWIDTH SEMICOLON
      ,";", // Folded result

       "???"  // U+FF1F: FULLWIDTH QUESTION MARK
      ,"?", // Folded result

       "???"  // U+2047: DOUBLE QUESTION MARK
      ,"??", // Folded result

       "???"  // U+2048: QUESTION EXCLAMATION MARK
      ,"?!", // Folded result

       "???"  // U+FF20: FULLWIDTH COMMERCIAL AT
      ,"@", // Folded result

       "???"  // U+FF3C: FULLWIDTH REVERSE SOLIDUS
      ,"\\", // Folded result

       "???"  // U+2038: CARET
       + "???"  // U+FF3E: FULLWIDTH CIRCUMFLEX ACCENT
      ,"^", // Folded result

       "???"  // U+FF3F: FULLWIDTH LOW LINE
      ,"_", // Folded result

       "???"  // U+2053: SWUNG DASH
       + "???"  // U+FF5E: FULLWIDTH TILDE
      ,"~", // Folded result
    };

    // Construct input text and expected output tokens
    List<String> expectedUnfoldedTokens = new ArrayList<>();
    List<String> expectedFoldedTokens = new ArrayList<>();
    StringBuilder inputText = new StringBuilder();
    for (int n = 0 ; n < foldings.length ; n += 2) {
      if (n > 0) {
        inputText.append(' ');  // Space between tokens
      }
      inputText.append(foldings[n]);

      // Construct the expected output tokens: both the unfolded and folded string,
      // with the folded duplicated as many times as the number of characters in
      // the input text.
      StringBuilder expected = new StringBuilder();
      int numChars = foldings[n].length();
      for (int m = 0 ; m < numChars; ++m) {
        expected.append(foldings[n + 1]);
      }
      expectedUnfoldedTokens.add(foldings[n]);
      expectedFoldedTokens.add(expected.toString());
    }

    TokenStream stream = whitespaceMockTokenizer(inputText.toString());
    ASCIIFoldingFilter filter = new ASCIIFoldingFilter(stream, random().nextBoolean());
    CharTermAttribute termAtt = filter.getAttribute(CharTermAttribute.class);
    Iterator<String> unfoldedIter = expectedUnfoldedTokens.iterator();
    Iterator<String> foldedIter = expectedFoldedTokens.iterator();
    filter.reset();
    while (foldedIter.hasNext()) {
      assertNextTerms(unfoldedIter.next(), foldedIter.next(), filter, termAtt);
    }
    assertFalse(filter.incrementToken());
  }
  
  /** blast some random strings through the analyzer */
  public void testRandomStrings() throws Exception {
    Analyzer a = new Analyzer() {

      @Override
      protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new MockTokenizer(MockTokenizer.WHITESPACE, false);
        return new TokenStreamComponents(tokenizer,
          new ASCIIFoldingFilter(tokenizer, random().nextBoolean()));
      } 
    };
    checkRandomData(random(), a, 1000*RANDOM_MULTIPLIER);
    a.close();
  }
  
  public void testEmptyTerm() throws IOException {
    Analyzer a = new Analyzer() {
      @Override
      protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new KeywordTokenizer();
        return new TokenStreamComponents(tokenizer,
          new ASCIIFoldingFilter(tokenizer, random().nextBoolean()));
      }
    };
    checkOneTerm(a, "", "");
    a.close();
  }
}
