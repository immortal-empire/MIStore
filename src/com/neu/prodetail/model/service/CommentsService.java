package com.neu.prodetail.model.service;

import java.io.File;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neu.prodetail.model.bean.Comments;
import com.neu.prodetail.model.dao.CommentsDAO;
import com.neu.util.Image;

@Service
public class CommentsService {
	@Autowired
	private CommentsDAO commentsDao;

	public List<Comments> getCommentsByRank(int proId, int rank) {
		// TODO Auto-generated method stub
		List<Comments> list = new ArrayList<Comments>();
		if(rank == 0) {
			list = commentsDao.getAllCommentsByproId(proId);
		}else {
			list = commentsDao.getCommentsByRank(proId, rank);
		}
		for(Comments comm: list) {
			if(comm.getCommurl() == null || comm.getCommurl() == "")
				continue;
			else
				comm.setCommImageName(Image.getImgurl(comm.getCommurl()));
		}		
		return list;
		
	}

	public List<Integer> getCommTypeNum(int proId) {
		// TODO Auto-generated method stub
		Integer total = commentsDao.getAllNum(proId);
		Integer good = commentsDao.getGoodNum(proId);
		Integer medium = commentsDao.getMediumNum(proId);
		Integer bad = total-good-medium;
		List<Integer> list = new ArrayList<Integer>();
		list.add(0, total);
		list.add(1, good);
		list.add(2, medium);
		list.add(3, bad);
		
		return list;
	}

	public List<Comments> getNewComments(int proId) {
		// TODO Auto-generated method stub
		//mysql中NOW()可得到当前时间
		//Timestamp date = new Timestamp(System.currentTimeMillis());
		List<Comments> list = commentsDao.getNewComments(proId); 
		for(Comments comm: list) {
			comm.setCommImageName(Image.getImgurl(comm.getCommurl()));
		}		
		return list;
	}

	public void addComments(Comments comment, MultipartFile[] upload, String uploadpath) {
		// TODO Auto-generated method stub
		// 以新增comments的id为文件夹名，将所有图片存入该路径下，comments中的commurl属性存文件夹名
		Timestamp d = new Timestamp(System.currentTimeMillis());
		if(!new File(uploadpath).exists()) {
            new File(uploadpath).mkdirs();
		}
		
		for(int i=0; i<upload.length;i++) {
			String filename = System.currentTimeMillis()+upload[i].getOriginalFilename();
			File f = new File(uploadpath,filename);
			try {
				upload[i].transferTo(f);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		comment.setCommdate(d);
		comment.setCommurl(uploadpath);
		commentsDao.addComments(comment);
	}

	public int getAllCommentsNum() {
		// TODO Auto-generated method stub
		return commentsDao.getAllCommentsNum();
	}
}
