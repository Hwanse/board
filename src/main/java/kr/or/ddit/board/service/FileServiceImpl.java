package kr.or.ddit.board.service;

import java.util.Map;

import kr.or.ddit.board.dao.FileDaoImpl;
import kr.or.ddit.board.dao.IFileDao;
import kr.or.ddit.board.model.AttachFileVO;

public class FileServiceImpl implements IFileService{

	private static IFileService service;
	private IFileDao dao;
	
	private FileServiceImpl() {
		dao = FileDaoImpl.getInstance();
	}
	
	public static IFileService getInstance(){
		if(service == null){
			service = new FileServiceImpl();
		}
		return service;
	}
	
	@Override
	public AttachFileVO getFileInfo(int file_seq) {
		AttachFileVO fileVO = dao.getFileInfo(file_seq);		
		return fileVO;
	}

	@Override
	public int deleteExistingFile(Map<String, Object> param) {
		int delResult = dao.deleteExistingFile(param);
		return delResult;
	}

}
