package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.board.model.ReplyVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplyDaoTest {

	
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyDaoTest.class);
	
	private IReplyDao dao;
	
	@Before
	public void setup(){
		dao = ReplyDaoImpl.getInstance();
		
	}
	
	/**
	* Method : insertReply
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 :	댓글 등록
	*/
	@Test
	public void insertReply() {
		/***Given***/
		ReplyVO replyVO = new ReplyVO(13, "댓글1", "brown", "2019-06-07");

		/***When***/
		int inResult = dao.insertReply(replyVO);
		
		/***Then***/
		assertEquals(1, inResult);

		
	}
	
	@Test
	public void getReplys(){
		/***Given***/
		int po_seq = 13;

		/***When***/
		List<ReplyVO> list = dao.getReplys(po_seq);
		
		/***Then***/
		logger.debug("댓글 수 : {}" , list.size());

	}
	
	
	@Test
	public void deleteReply(){
		/***Given***/
		int re_seq = 1;
		
		/***When***/
		int delResult = dao.deleteReply(re_seq);
		
		/***Then***/
		assertEquals(1, delResult);

	}

}
