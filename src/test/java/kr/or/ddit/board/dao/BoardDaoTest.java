package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.PostVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDaoTest {

	private static final Logger logger = LoggerFactory
			.getLogger(BoardDaoTest.class);
	
	private IBoardDao dao;
	
	@Before
	public void setup() {
		dao = BoardDaoImpl.getInstance();
		logger.debug("setup");
	}
	
	/**
	* Method : getAllBoardList
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 게시판 전체 리스트를 조회
	*/
	@Test
	public void getAllBoardList() {
		/***Given***/
		

		/***When***/
		List<BoardVO> boardList = dao.getAllBoardList();
		
		/***Then***/
		logger.debug("boarList.size() : {}",boardList.size() ); 
	}
	
	/**
	* Method : insertBoard
	* 작성자 : PC14
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 추가하기
	*/
	@Test
	public void inserBoard(){
		/***Given***/
		BoardVO boardVO = new BoardVO("brown", "자유게시판", "Y", "2019-06-05 09:47:22");

		/***When***/
		int result = dao.insertBoard(boardVO);
		
		/***Then***/
		assertEquals(1, result);

	}
	
	/**
	* Method : updateBoard
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 게시판 수정하기
	*/
	@Test
	public void updateBoard(){
		/***Given***/
		BoardVO boardVO = new BoardVO();
		boardVO.setSeq(23);
		boardVO.setReg_dt("2019-06-05");
		boardVO.setUserId("brown");
		boardVO.setName("test5");
		boardVO.setUse_yn("N");
		/***When***/
		int upResult = dao.updateBoard(boardVO);
		
		/***Then***/
		assertEquals(1, upResult);

	}
	
	
	/**
	* Method : selectedAllPostsList
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 선택된 게시판의 모든 게시글 조회
	*/
	@Test
	public void selectedAllPostsList(){
		/***Given***/
		int seq = 3;
	
		/***When***/
		List<PostVO> postsList = dao.selectedAllPostsList(seq);
		
		
		/***Then***/
		logger.debug("postList : {}" , postsList.get(0));
		assertEquals(1, postsList.size());

	}
	
	
	
	
}
