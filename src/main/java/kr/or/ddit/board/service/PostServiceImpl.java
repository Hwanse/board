package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.IPostDao;
import kr.or.ddit.board.dao.PostDaoImpl;
import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.board.model.PostPageVO;
import kr.or.ddit.board.model.PostVO;

public class PostServiceImpl implements IPostService{

	private static IPostService service;
	private IPostDao dao;
	
	private PostServiceImpl() {
		dao = PostDaoImpl.getInstance();
	}
	
	public static IPostService getInstance(){
		if(service == null){
			service = new PostServiceImpl();
		}
		return service;
	}
	/**
	* Method : insertPost
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 추가하기
	*/
	@Override
	public int insertPost(PostVO postVO) {
		int inResult = dao.insertPost(postVO);
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
		int po_seq = dao.getPostSeq();
		return po_seq;
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
		int inResult = dao.insertAttachFile(fileVO);
		return inResult;
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
		PostVO postVO = dao.getPostInfo(param);
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
		List<AttachFileVO> files = dao.getAttatchFiles(po_seq);
		return files;
	}
	
	
	/**
	* Method : postPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 페이징리스트 조회
	*/
	public Map<String, Object> postPagingList(PostPageVO postPageVO){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<PostVO> postList = dao.postPagingList(postPageVO);
		int postsCnt = dao.postsCnt(postPageVO);
		
		int paginationSize = 0;
		if(postsCnt % postPageVO.getPageSize() == 0){
			paginationSize = postsCnt / postPageVO.getPageSize();
		} else{
			paginationSize = postsCnt / postPageVO.getPageSize() + 1;
		}
		
		resultMap.put("postsCnt", postsCnt);
		resultMap.put("postList", postList);
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	
	
	/**
	* Method : deleteAttachFile
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 수정시 해당 게시글의 모든 첨부파일 정보를 삭제
	*/
	/*@Override
	public int deleteAttachFile(int po_seq) {
		int delResult = dao.deleteAttachFile(po_seq);
		return delResult;
	}*/

	
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
		int upResult = dao.updatePostInfo(postVO);
		return upResult;
	}

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
		int inResult = dao.insertAnswer(postVO);
		return inResult;
	}

	/**
	* Method : deletePost
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 게시글 삭제처리
	*/
	@Override
	public int deletePost(int po_seq) {
		int upResult = dao.deletePost(po_seq);
		return upResult;
	}
	
}
