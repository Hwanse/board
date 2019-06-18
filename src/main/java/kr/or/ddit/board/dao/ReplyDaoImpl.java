package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.mybatis.MyBatisUtil;

public class ReplyDaoImpl implements IReplyDao{

	private static IReplyDao dao;
	
	private ReplyDaoImpl() {

	}
	
	public static IReplyDao getInstance(){
		if(dao == null){
			dao = new ReplyDaoImpl();
		}
		return dao;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int inResult = sqlSession.insert("reply.insertReply",replyVO);
		sqlSession.commit();
		sqlSession.close();
		return inResult;
	}

	/**
	* Method : deleteReply
	* 작성자 : PC14
	* 변경이력 :
	* @param  re_seq
	* @return
	* Method 설명 : 댓글 삭제
	*/
	@Override
	public int deleteReply(int re_seq) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int delResult = sqlSession.update("reply.deleteReply", re_seq);
		sqlSession.commit();
		sqlSession.close();
		return delResult;
	}

	/**
	* Method : getReplys
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 해당 게시글 댓글리스트 조회
	*/
	@Override
	public List<ReplyVO> getReplys(int po_seq) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ReplyVO> replyList = sqlSession.selectList("reply.getReplys", po_seq);
		sqlSession.close();
		return replyList;
	}
	
	
}
