package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.PostVO;

public class BoardServiceImpl implements IBoardService{

	private static IBoardService service;
	private IBoardDao dao;
	
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance(){
		if(service == null){
			service = new BoardServiceImpl();
		}
		return service;
	}
	
	
	/**
	* Method : getAllBoardList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 게시판의 전체 리스트 조회
	*/
	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = dao.getAllBoardList();
		return boardList;
	}

	/**
	* Method : getBoardInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param seq
	* @return
	* Method 설명 : 선택한 게시판의 정보를 조회
	*/
	@Override
	public BoardVO getBoardInfo(int seq) {
		BoardVO boardVO = dao.getBoardInfo(seq);
		return boardVO;
	}
	
	/**
	* Method : insertBoard
	* 작성자 : PC14
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 추가하기
	*/
	@Override
	public int insertBoard(BoardVO boardVO) {
		int result = dao.insertBoard(boardVO);
		return result;
	}

	
	/**
	* Method : updateBoard
	* 작성자 : PC14
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 수정하기
	*/
	@Override
	public int updateBoard(BoardVO boardVO) {
		int upResult = dao.updateBoard(boardVO);
		return upResult;
	}

	/**
	* Method : selectedAllPostsList
	* 작성자 : PC14
	* 변경이력 :
	* @param seq
	* @return
	* Method 설명 : 게시판의 모든 게시글을 조회
	*/
	@Override
	public List<PostVO> selectedAllPostsList(int seq) {
		List<PostVO> postsList = dao.selectedAllPostsList(seq);
		return postsList;
	}

	
	
}
