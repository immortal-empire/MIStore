package com.neu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Image {
	
	public static List<String> getImgurl(String path) {
		
		List<String> list = new ArrayList<String>();

		File file = new File(path);//�ļ���·��
	    File[] files = file.listFiles();//�������ļ���
        if(null!=files){
	        for (int i = 0; i < files.length; i++){
	            File file1 = files[i];
	            String name = file1.getName();//��ȡͼƬ����
	            list.add(name);
	        }
	        return list;
        }else{
        	return null;
        }
		
	}

}
