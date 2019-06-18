package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;
import kr.or.ddit.board.model.ReplyVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplyServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(ReplyServiceTest.class);
	
	private IReplyService service;
	
	@Before
	public void setup(){
		service = ReplyServiceImpl.getInstance();
				
	}
	
	@Test
	public void insertReply() {
		/***Given***/
		ReplyVO replyVO = new ReplyVO(13, "댓글2", "brown", "2019-06-07");

		/***When***/
		int inResult = service.insertReply(replyVO);
		
		/***Then***/
		assertEquals(1, inResult);

	
	}

	@Test
	public void deleteReply(){
		/***Given***/
		int re_seq  = 2;
		
		/***When***/
		int delResult = service.deleteReply(re_seq);
		
		/***Then***/
		assertEquals(1, delResult);

	}
	
}
