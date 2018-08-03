package com.neu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Image {
	
	public static List<String> getImgurl(String path) {
		
		List<String> list = new ArrayList<String>();

		File file = new File(path);//文件夹路径
	    File[] files = file.listFiles();//遍历该文件夹
        if(null!=files){
	        for (int i = 0; i < files.length; i++){
	            File file1 = files[i];
	            String name = file1.getName();//获取图片名称
	            list.add(name);
	        }
	        return list;
        }else{
        	return null;
        }
		
	}

}
