package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.board.model.PostPageVO;
import kr.or.ddit.board.model.PostVO;

public interface IPostDao {

	/**
	* Method : insertBoard
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 등록하기
	*/
	public int insertPost(PostVO postVO);
	
	
	/**
	* Method : getPostSeq
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 방금 생성한 게시글번호 조회
	*/
	public int getPostSeq();
	
	/**
	* Method : insertAttachFile
	* 작성자 : PC14
	* 변경이력 :
	* @param fileVO
	* @return
	* Method 설명 : 첨부파일 정보를 추가하기
	*/
	public int insertAttachFile(AttachFileVO fileVO);

	
	/**
	* Method : getPostInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param param (po_seq, seq) - 게시글 번호, 게시판 id
	* @return
	* Method 설명 : 선택한 게시글의 정보를 조회
	*/
	public PostVO getPostInfo(Map<String, Integer> param);
	
	
	/**
	* Method : getAttatchFiles
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 선택한 게시글의 첨부파일 리스트를 조회
	*/
	public List<AttachFileVO> getAttatchFiles(int po_seq);

	
	/**
	* Method : postPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 페이징리스트 조회
	*/
	public List<PostVO> postPagingList(PostPageVO postPageVO);

	
	/**
	* Method : postsCnt
	* 작성자 : PC14
	* 변경이력 :
	* @param board_seq
	* @return
	* Method 설명 : 해당 게시판의 전체 게시글 수 조회
	*/
	public int postsCnt(PostPageVO postPageVO);
	
	
	/**
	* Method : updatePostInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시글 정보 수정
	*/
	public int updatePostInfo(PostVO postVO);
	
	/**
	* Method : deleteAttachFile
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 수정시 해당 게시글에 첨부된 파일정보 모두 삭제
	*/
	//public int deleteAttachFile(int po_seq);
	
	
	/**
	* Method : insertAnswer
	* 작성자 : PC14
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 답글 등록
	*/
	public int insertAnswer(PostVO postVO);
	
	
	/**
	* Method : deletePost
	* 작성자 : PC14
	* 변경이력 :
	* @param po_seq
	* @return
	* Method 설명 : 게시글 삭제처리
	*/
	public int deletePost(int po_seq);
	
}
