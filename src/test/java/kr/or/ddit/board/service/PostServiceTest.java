package kr.or.ddit.board.service;

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

public class PostServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(PostServiceTest.class);
	
	private IPostService service;
	
	@Before
	public void setup(){
		service = PostServiceImpl.getInstance();
	}
	
	
	/**
	* Method : insertPostTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명: 게시글 추가하기
	*/
	@Test
	public void insertPostTest(){
		/***Given***/
		PostVO postVO = new PostVO(1, "brown", "test4", "테스테스테트ㅔㅌ세ㅡ", "2019-06-05 19:57:21");

		/***When***/
		int inResult = service.insertPost(postVO);
		
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
		int post_seq = service.getPostSeq();
		
		/***Then***/
		logger.debug("post_seq : {}", post_seq);
		assertEquals(3, post_seq);
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
		int po_seq = service.getPostSeq();
		AttachFileVO fileVO1 = new AttachFileVO(po_seq,"d:\\upload\\", "sally.png");
		AttachFileVO fileVO2 = new AttachFileVO(po_seq,"d:\\upload\\", "sally.png");
		AttachFileVO fileVO3 = new AttachFileVO(po_seq,"d:\\upload\\", "sally.png");

		List<AttachFileVO> list = new ArrayList<AttachFileVO>();
		list.add(fileVO1);
		list.add(fileVO2);
		list.add(fileVO3);
		/***When***/
		for(AttachFileVO fileVO : list){
			int inResult = service.insertAttachFile(fileVO);
			
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
		PostVO postVO = service.getPostInfo(param);
		
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
		List<AttachFileVO> list = service.getAttatchFiles(po_seq);
		
		/***Then***/
		assertEquals(3, list.size());

	}
	
	
	/**
	* Method : postPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 페이징리스트 조회
	*/
	@Test
	public void postPagingListTest(){
		/***Given***/
		PostPageVO postPageVO = new PostPageVO(1, 10, 1);

		/***When***/
		Map<String, Object> resultMap = service.postPagingList(postPageVO);
		List<PostVO> postList = (List<PostVO>) resultMap.get("postList");
		int postsCnt = (int) resultMap.get("postsCnt");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		/***Then***/
		for(PostVO postVO : postList){
			logger.debug("postVO : {}", postVO);
		}
		assertEquals(11, postsCnt);
		assertEquals(2, paginationSize);
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
		postVO.setTitle("두번째 수정된 게시글");
		postVO.setContent("냉뇨욘애녀ㅛㅇ내애ㅕ");

		/***When***/
		int upResult = service.updatePostInfo(postVO);
			
		/***Then***/
		assertEquals(1, upResult);

		
	}
	
	/**
	* Method : deleteAttachFile
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 수정시 해당 게시글의 모든 첨부파일정보 삭제
	*/
	@Test
	public void deleteAttachFile(){
		/***Given***/
		int po_seq = 13;

		/***When***/
		//int delResult = service.deleteAttachFile(po_seq);
		
		/***Then***/
		//assertEquals(1, delResult);

	}
	

	/**
	* Method : insertAnswerTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 답글 등록
	*/
	@Test
	public void insertAnswerTest(){
		/***Given***/
		PostVO postVO = new PostVO(1, 17, "brown", "17번 게시글의 답글", "service Test실행 테스트", "2019-06-08", 13);

		/***When***/
		int inResult = service.insertAnswer(postVO);
		
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
		int po_seq = 16;

		/***When***/
		int upResult = service.deletePost(po_seq);
		
		/***Then***/
		assertEquals(1, upResult);

	}
	
}
