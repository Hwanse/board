package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.PostVO;
import kr.or.ddit.mybatis.MyBatisUtil;

public class BoardDaoImpl implements IBoardDao{

	public static IBoardDao dao;
	
	private BoardDaoImpl() {

	}
	
	public static IBoardDao getInstance(){
		if(dao == null){
			dao = new BoardDaoImpl();
		}
		return dao;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> boardList = sqlSession.selectList("board.getAllBoardList");
		sqlSession.close();
		return boardList;
	}

	@Override
	public BoardVO getBoardInfo(int seq) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardVO boardVO = sqlSession.selectOne("board.getBoardInfo",seq);
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int inResult = sqlSession.insert("board.insertBoard", boardVO);
		
		if(inResult == 0){
			sqlSession.rollback();
		}
		sqlSession.commit();
		sqlSession.close();
		return inResult;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int upResult = sqlSession.update("board.updateBoard",boardVO);

		if(upResult > 0){
			sqlSession.commit();
		}
		sqlSession.close();
		return upResult;
	}

	/**
	* Method : selectedAllPostsList
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 선택된 게시판 전체 게시글 조회
	*/
	@Override
	public List<PostVO> selectedAllPostsList(int seq) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVO> postsList = sqlSession.selectList("board.selectedAllPostsList", seq); 
		sqlSession.close();
		return postsList;
	}

	

}
