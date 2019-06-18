package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(BoardServiceTest.class);
	
	private IBoardService service;
	
	@Before
	public void setup(){
		service = BoardServiceImpl.getInstance();
		
	}
	
	/**
	* Method : getAllBoardList
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 게시글 전체 리스트 조회
	*/
	@Test
	public void getAllBoardList() {
		/***Given***/
		

		/***When***/
		List<BoardVO> boardList = service.getAllBoardList();
		
		/***Then***/
		assertEquals(0, boardList.size());

	
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
		BoardVO boardVO = new BoardVO("brown", "Q&A게시판", "Y", "2019-06-04 20:36:22");

		/***When***/
		int result = service.insertBoard(boardVO);
		
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
		BoardVO boardVO = new BoardVO("brown", "test9", "N", "2019-06-05 12:40:22");
		boardVO.setSeq(27);
		
		/***When***/
		int upResult = service.updateBoard(boardVO);
		
		/***Then***/
		logger.debug("boardVO : {}" , boardVO);
		assertEquals(1, upResult);

	}
	
	
	
}
