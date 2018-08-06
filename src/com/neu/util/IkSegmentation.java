package com.neu.util;

import java.io.IOException;
import java.io.StringReader;
 
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;
 
/**
 * 中文分词工具类
 * 对搜索栏的搜索关键字进行分词
 * @author 刘星星
 *
 */
public class IkSegmentation {
 
	@SuppressWarnings("resource")
	public static List<String> Seg(String sentence) throws IOException {
		List<String> result = new ArrayList<String>();
		
		/*
		 * 创建分词对象
		 */
		Analyzer anal = new IKAnalyzer(true);
		StringReader reader = new StringReader(sentence);
		
		/*
		 * 分词
		 * TokenStream对象中包含了text的分词结果
		 * CharTermAttribute是具体的词元对象，每次调用incrementToken()方法后，该对象所持有的词元会更新。
		 */
		TokenStream ts = anal.tokenStream("", reader);
		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
		
		/*
		 * 遍历分词数据
		 * TokenStream.incrementToken()的功能相当于迭代器，用来遍历每个词元。 
		 */
		while (ts.incrementToken()) {
			if(!term.toString().equals("的")){
				result.add(term.toString());				
			}
		}
		reader.close();
		return result;
	}
 
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		List<String> words = IkSegmentation.Seg("AI双摄小米手机、小米8");
		System.out.print("分词结果为：");
		for(String s:words){
			System.out.print(s+" | ");				
		}
	}
}
