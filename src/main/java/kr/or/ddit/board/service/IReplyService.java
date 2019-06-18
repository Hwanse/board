package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.ReplyVO;

public interface IReplyService {

	/**
	* Method : insertReply
	* 작성자 : PC14
	* 변경이력 :
	* @param replyVO
	* @return
	* Method 설명 : 댓글 등록
	*/
	public int insertReply(ReplyVO replyVO);
	
	
	
	/**
	* Method : getReplys
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 해당 게시글의 댓글리스트 조회
	*/
	public List<ReplyVO> getReplys(int po_seq);
	
	/**
	* Method : updateReply
	* 작성자 : PC14
	* 변경이력 :
	* @param re_seq
	* @return
	* Method 설명 : 댓글 삭제 기능
	*/
	public int deleteReply(int re_seq);
	
}
