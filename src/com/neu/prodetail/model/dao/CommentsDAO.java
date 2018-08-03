package com.neu.prodetail.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neu.prodetail.model.bean.Comments;

public interface CommentsDAO {

	public List<Comments> getCommentsByRank(@Param("proId")int proId, @Param("rank")int rank);

	public Integer getAllNum(int proId);

	public Integer getGoodNum(int proId);

	public Integer getMediumNum(int proId);

	public List<Comments> getNewComments(int proId);

	public List<Comments> getAllCommentsByproId(int proId);

	public void addComments(Comments comment);

	public int getAllCommentsNum();
}
