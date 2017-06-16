package com.thinkgem.jeesite.utils;

import groovyjarjarantlr.TokenStream;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.AttributeSource;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 
 */
public class TokenSpliter {
    private static Analyzer analyzer = new IKAnalyzer();

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // System.out.println(showToken(text));

    }

    /**
     * 分词及打印分词结果的方法
     * 
     * @param analyzer 分词器名称
     * @param text 要分词的字符串
     * @throws IOException 抛出的异常
     */
    public static List<String> showToken(String text) throws IOException {
        List<String> result = new ArrayList<String>();
        Reader reader = new StringReader(text);
        TokenStream stream = (TokenStream) analyzer.tokenStream("", reader);
        TermAttribute termAtt = ((AttributeSource) stream).addAttribute(TermAttribute.class);
        // OffsetAttribute offAtt = stream.addAttribute(OffsetAttribute.class);
        // 循环打印出分词的结果，及分词出现的位置
        while (((org.apache.lucene.analysis.TokenStream) stream).incrementToken()) {
            // System.out.print(termAtt.term() + "|(" + offAtt.startOffset() +
            // " " + offAtt.endOffset() + ")");
            result.add(termAtt.term());
        }
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o2.length() - o1.length();
            }
        });
        return result;
    }

}
