package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.board.model.PostPageVO;
import kr.or.ddit.board.model.PostVO;
import kr.or.ddit.mybatis.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

/**
* PostDaoImpl.java
*
* @author PC14
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC14 최초 생성
*
* </pre>
*/
public class PostDaoImpl implements IPostDao{

	private static IPostDao dao;
	
	private PostDaoImpl(){
		
	}
	
	public static IPostDao getInstance(){
		if(dao == null){
			dao = new PostDaoImpl();
		}
		
		return dao;
	}
	
	/**
	* Method : insertBoard
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 추가하기
	*/
	@Override
	public int insertPost(PostVO postVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int inResult = sqlSession.insert("post.insertPost", postVO);
		sqlSession.commit();
		sqlSession.close();
		return inResult;
	}

	/**
	* Method : insertAttachFile
	* 작성자 : PC14
	* 변경이력 :
	* @param fileVO
	* @return
	* Method 설명 : 첨부파일 정보를 추가하기
	*/
	@Override
	public int insertAttachFile(AttachFileVO fileVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int inResult = sqlSession.insert("post.insertAttachFile",fileVO);
		
		if(inResult != 1){
			sqlSession.rollback();
			sqlSession.close();
			return 0;
		}
		sqlSession.commit();
		sqlSession.close();
		return inResult;
	}

	/**
	* Method : getPostSeq
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 방금 생성한 게시글번호 조회
	*/
	@Override
	public int getPostSeq() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int post_seq = sqlSession.selectOne("post.getPostSeq");
		sqlSession.close();
		return post_seq;
	}

	/**
	* Method : getPostInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param param (po_seq, seq) - 게시글 번호, 게시판 id
	* @return
	* Method 설명 : 선택한 게시글의 정보를 조회
	*/
	@Override
	public PostVO getPostInfo(Map<String, Integer> param) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		PostVO postVO = sqlSession.selectOne("post.getPostInfo", param);
		sqlSession.close();
		return postVO;
	}

	/**
	* Method : getAttatchFiles
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 선택한 게시글의 첨부파일 리스트를 조회
	*/
	@Override
	public List<AttachFileVO> getAttatchFiles(int po_seq) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<AttachFileVO> files = sqlSession.selectList("post.getAttatchFiles", po_seq);
		sqlSession.close();
		return files;
	}


	
	/**
	* Method : postPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 페이징 리스트 조회
	*/
	@Override
	public List<PostVO> postPagingList(PostPageVO postPageVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVO> pagingList = sqlSession.selectList("post.postPagingList", postPageVO);
		sqlSession.close();
		return pagingList;
	}

	
	/**
	* Method : postsCnt
	* 작성자 : PC14
	* 변경이력 :
	* @param board_seq
	* @return
	* Method 설명 : 해당 게시판의 전체 게시글 수 조회
	*/
	@Override
	public int postsCnt(PostPageVO postPageVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.selectOne("post.postsCnt", postPageVO);
		sqlSession.close();
		return cnt;
	}

	
	/**
	* Method : updatePostInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 수정
	*/
	@Override
	public int updatePostInfo(PostVO postVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int upResult = sqlSession.update("post.updatePostInfo", postVO);
		sqlSession.commit();
		sqlSession.close();
		return upResult;
	}
	
	/**
	* Method : deleteAttachFile
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 수정 시 해당 게시글의 모든 첨부파일 정보를 삭제
	*/
	/*@Override
	public int deleteAttachFile(int po_seq) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int delResult = sqlSession.delete("post.deleteAttachFile", po_seq);
		sqlSession.commit();
		sqlSession.close();
		return delResult;
	}*/

	/**
	* Method : insertAnswer
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 답글 등록
	*/
	@Override
	public int insertAnswer(PostVO postVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int inResult = sqlSession.insert("post.insertAnswer", postVO);
		sqlSession.commit();
		sqlSession.close();
		return inResult;
	}

	@Override
	public int deletePost(int po_seq) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int upResult = sqlSession.update("post.deletePost",po_seq);
		sqlSession.commit();
		sqlSession.close();
		return upResult;
	}

	
}
