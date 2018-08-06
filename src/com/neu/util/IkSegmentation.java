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
 * ���ķִʹ�����
 * ���������������ؼ��ֽ��зִ�
 * @author ������
 *
 */
public class IkSegmentation {
 
	@SuppressWarnings("resource")
	public static List<String> Seg(String sentence) throws IOException {
		List<String> result = new ArrayList<String>();
		
		/*
		 * �����ִʶ���
		 */
		Analyzer anal = new IKAnalyzer(true);
		StringReader reader = new StringReader(sentence);
		
		/*
		 * �ִ�
		 * TokenStream�����а�����text�ķִʽ��
		 * CharTermAttribute�Ǿ���Ĵ�Ԫ����ÿ�ε���incrementToken()�����󣬸ö��������еĴ�Ԫ����¡�
		 */
		TokenStream ts = anal.tokenStream("", reader);
		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
		
		/*
		 * �����ִ�����
		 * TokenStream.incrementToken()�Ĺ����൱�ڵ���������������ÿ����Ԫ�� 
		 */
		while (ts.incrementToken()) {
			if(!term.toString().equals("��")){
				result.add(term.toString());				
			}
		}
		reader.close();
		return result;
	}
 
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		List<String> words = IkSegmentation.Seg("AI˫��С���ֻ���С��8");
		System.out.print("�ִʽ��Ϊ��");
		for(String s:words){
			System.out.print(s+" | ");				
		}
	}
}
