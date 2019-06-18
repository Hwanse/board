package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.AttachFileVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(FileServiceTest.class);
	
	private IFileService service;
	
	@Before
	public void setup(){
		service = FileServiceImpl.getInstance();
		
	}
	
	
	/**
	* Method : getFileInfo
	* 작성자 : PC14
	* 변경이력 :
	* @param file_seq
	* @return
	* Method 설명 : 파일 정보를 조회
	*/
	@Test
	public void getFileInfoTest() {
		/***Given***/
		int file_seq = 41;

		/***When***/
		AttachFileVO fileVO = service.getFileInfo(file_seq);
		
		/***Then***/
		assertNotNull(fileVO);
		logger.debug("fileVO : {}", fileVO);
	
	}


	/**
	* Method : deleteExistingFile
	* 작성자 : PC14
	* 변경이력 :
	* @param param
	* @return
	* Method 설명 : 게시글 수정시 기존의 첨부파일목록에서 삭제된 파일데이터를 DB에서 삭제하기 위한 메서드
	*/
	@Test
	public void deleteExistingFileTest(){
		/***Given***/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("po_seq", 26);
		List<Integer> fileList = new ArrayList<Integer>();
		fileList.add(41);
		param.put("existingFilesList", fileList);

		
		/***When***/
		int delResult = service.deleteExistingFile(param);
		
		/***Then***/
		assertNotEquals(0, delResult);
		logger.debug("delResult : {}", delResult);
		
	}
	
}
