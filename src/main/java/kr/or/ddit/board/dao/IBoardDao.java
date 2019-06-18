package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.PostVO;

public interface IBoardDao {
	
	/**
	* Method : getAllBoardList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 게시판의 전체 리스트 조회
	*/
	public List<BoardVO> getAllBoardList();

	
	/**
	* Method : getBoardInfo
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 선택한 게시판의 정보를 조회
	*/
	public BoardVO getBoardInfo(int seq);
	
	/**
	* Method : insertBoard
	* 작성자 : PC14
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 추가하기
	*/
	public int insertBoard(BoardVO boardVO);

			
	/**
	* Method : updateBoard
	* 작성자 : PC14
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 수정하기
	*/
	public int updateBoard(BoardVO boardVO);

	
	/**
	* Method : selectedAllPostsList
	* 작성자 : PC14
	* 변경이력 :
	* @param seq
	* @return
	* Method 설명 : 선택된 게시판의 모든 게시글을 조회
	*/
	public List<PostVO> selectedAllPostsList(int seq);
	
}
