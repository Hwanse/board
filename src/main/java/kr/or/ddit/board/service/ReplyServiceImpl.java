package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IReplyDao;
import kr.or.ddit.board.dao.ReplyDaoImpl;
import kr.or.ddit.board.model.ReplyVO;

public class ReplyServiceImpl implements IReplyService{

	private static IReplyService service;
	private IReplyDao dao;
	
	private ReplyServiceImpl() {
		dao = ReplyDaoImpl.getInstance();
	}
	
	public static IReplyService getInstance(){
		if(service == null){
			service = new ReplyServiceImpl();
		}
		return service;
	}
	
	/**
	* Method : insertReply
	* 작성자 : PC14
	* 변경이력 :
	* @param replyVO
	* @return
	* Method 설명 : 댓글 등록
	*/
	@Override
	public int insertReply(ReplyVO replyVO) {
		int inResult = dao.insertReply(replyVO);
		return inResult;
	}

	/**
	* Method : deleteReply
	* 작성자 : PC14
	* 변경이력 :
	* @param re_seq
	* @return
	* Method 설명 : 댓글 삭제
	*/
	@Override
	public int deleteReply(int re_seq) {
		int delResult = dao.deleteReply(re_seq);
		return delResult;
	}

	/**
	* Method : getReplys
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 해당 게시글의 댓글 리스트 조회
	*/
	@Override
	public List<ReplyVO> getReplys(int po_seq) {
		List<ReplyVO> replyList = dao.getReplys(po_seq);
		return replyList;
	}

	
	
}
