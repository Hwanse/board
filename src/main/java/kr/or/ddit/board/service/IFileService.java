package kr.or.ddit.board.service;

import java.util.Map;

import kr.or.ddit.board.model.AttachFileVO;

public interface IFileService {

	/**
	* Method : getFileInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param file_seq
	* @return
	* Method 설명 : 파일 정보를 조회
	*/
	public AttachFileVO getFileInfo(int file_seq);

	
	/**
	* Method : deleteExistingFile
	* 작성자 : PC14
	* 변경이력 :
	* @param param
	* @return
	* Method 설명 : 게시글 수정시 기존의 첨부파일목록에서 삭제된 파일데이터를 DB에서 삭제하기 위한 메서드
	*/
	public int deleteExistingFile(Map<String, Object> param);
	
}
