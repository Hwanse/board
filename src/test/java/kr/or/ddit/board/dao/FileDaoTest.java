package kr.or.ddit.board.dao;

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

public class FileDaoTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(FileDaoTest.class);
	
	private IFileDao dao;
	
	@Before
	public void setup(){
		dao = FileDaoImpl.getInstance();
	}
	
	/**
	* Method : getFileInfoTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 파일 조회기능 테스트
	*/
	@Test
	public void getFileInfoTest() {
		/***Given***/
		int file_seq = 24;

		/***When***/
		AttachFileVO fileVO = dao.getFileInfo(file_seq);
		
		/***Then***/
		assertNotNull(fileVO);
		logger.debug("fileVO : {}", fileVO);
	
	}
	
	
	/**
	* Method : deleteExistingFileTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 게시글 수정시 기존의 첨부파일목록에서 삭제된 파일데이터를 DB에서 삭제하기 위한 메서드 테스트
	*/
	@Test
	public void deleteExistingFileTest(){
		/***Given***/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("po_seq", 14);
		List<Integer> fileList = new ArrayList<Integer>();
		fileList.add(25);
		param.put("existingFilesList", fileList);

		
		/***When***/
		int delResult = dao.deleteExistingFile(param);
		
		/***Then***/
		assertNotEquals(0, delResult);
		
	}

}
