package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.board.model.PostPageVO;
import kr.or.ddit.board.model.PostVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDaoTest {

	private static final Logger logger = LoggerFactory
			.getLogger(PostDaoTest.class);
	
	private IPostDao dao;
	
	@Before
	public void setup(){
		dao = PostDaoImpl.getInstance();
	}
	
	/**
	* Method : insertBoardTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 게시글 추가하기
	*/
	@Test
	public void insertPostTest(){
		/***Given***/
		PostVO postVO = new PostVO( 1,"brown", "게시글이당2", "에에에에", "2019-06-05 20:42:22");

		logger.debug("postVO : {}", postVO);
		/***When***/
		int inResult = dao.insertPost(postVO);
		
		/***Then***/
		assertEquals(1, inResult);

	}
	
	
	/**
	* Method : getPostSeq
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 방금 생성한 게시글번호 조회
	*/
	@Test
	public void getPostSeq(){
		/***Given***/
		

		/***When***/
		int post_seq = dao.getPostSeq();
		
		/***Then***/
		logger.debug("post_seq : {}", post_seq);
		assertEquals(2, post_seq);
	}
	
	/**
	* Method : insertAttachFileTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 첨부파일 정보를 등록하기
	*/
	@Test
	public void insertAttachFileTest(){
		/***Given***/
		int po_seq = dao.getPostSeq();
		AttachFileVO fileVO1 = new AttachFileVO(po_seq,"d:\\upload\\", "sally.png");
		AttachFileVO fileVO2 = new AttachFileVO(po_seq,"d:\\upload\\", "sally.png");
		AttachFileVO fileVO3 = new AttachFileVO(po_seq,"d:\\upload\\", "sally.png");

		List<AttachFileVO> list = new ArrayList<AttachFileVO>();
		list.add(fileVO1);
		list.add(fileVO2);
		list.add(fileVO3);
		/***When***/
		for(AttachFileVO fileVO : list){
			int inResult = dao.insertAttachFile(fileVO);
			
			/***Then***/
			assertEquals(1, inResult);
		}
		
	}

	
	/**
	* Method : getPostInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param param (po_seq, seq) - 게시글 번호, 게시판 id
	* @return
	* Method 설명 : 선택한 게시글의 정보를 조회
	*/
	@Test
	public void getPostInfo(){
		/***Given***/
		int po_seq = 4;
		int seq = 1;
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("po_seq", po_seq);
		param.put("seq", seq);
		
		/***When***/
		PostVO postVO = dao.getPostInfo(param);
		
		/***Then***/
		logger.debug("postVO : {}", postVO);
		assertNotNull(postVO);
	}
	
	/**
	* Method : getAttatchFiles
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 선택한 게시글의 첨부파일 리스트를 조회
	*/
	@Test
	public void getAttachFiles(){
		/***Given***/
		int po_seq = 4;

		/***When***/
		List<AttachFileVO> list = dao.getAttatchFiles(po_seq);
		
		/***Then***/
		assertEquals(3, list.size());

	}
	
	
	/**
	* Method : postPagingList
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 게시글 페이징리스트 조회
	*/
	@Test
	public void postPagingList(){
		/***Given***/
		PostPageVO postPageVO = new PostPageVO(1, 10, 1);

		/***When***/
		List<PostVO> list = dao.postPagingList(postPageVO);
		
		/***Then***/
		for(PostVO post : list){
			logger.debug("postVO : {}", post);
		}

	}
	
	
	@Test
	public void postsCntTest(){
		/***Given***/
		PostPageVO postPageVO = new PostPageVO(1, 10, 1);

		/***When***/
		int cnt= dao.postsCnt(postPageVO);
		
		/***Then***/
		logger.debug("게시글 전체 수 : {}",  cnt);

	}
	
	
	/**
	* Method : updatePostInfo
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 게시글 수정
	*/
	@Test
	public void updatePostInfo(){
		/***Given***/
		PostVO postVO = new PostVO();
		postVO.setPo_seq(13);
		postVO.setTitle("수정된 게시글");
		postVO.setContent("ㅎ호호안하왛ㅇㄴ한ㅇ");

		/***When***/
		int upResult = dao.updatePostInfo(postVO);

		/***Then***/
		assertEquals(1, upResult);

	}
	
	/**
	* Method : deleteAttachFile
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 수정시 해당 게시글의 모든 첨부파일정보 삭제
	*/
	/*@Test
	public void deleteAttachFile(){
		*//***Given***//*
		int po_seq = 13;

		*//***When***//*
		int delResult = dao.deleteAttachFile(po_seq);
		
		*//***Then***//*
		assertEquals(1, delResult);

	}*/

	
	/**
	* Method : insertAnswer
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 답글 등록
	*/
	@Test
	public void insertAnswer(){
		/***Given***/
		PostVO postVO = new PostVO(1, 13, "brown", "13번 게시글의 답글", "답글 테스트", "2019-06-08", 13);
		
		/***When***/
		int inResult = dao.insertAnswer(postVO);
		
		/***Then***/
		assertEquals(1, inResult);

	}
	
	
	/**
	* Method : deletePostTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 게시글 삭제처리
	*/
	@Test
	public void deletePostTest(){
		/***Given***/
		int po_seq = 14;

		/***When***/
		int upResult = dao.deletePost(po_seq);
		
		/***Then***/
		assertEquals(1, upResult);

	}
	
}
