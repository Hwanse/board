package kr.or.ddit.board.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.mybatis.MyBatisUtil;

public class FileDaoImpl implements IFileDao{

	private static IFileDao dao;
	
	private FileDaoImpl() {

	}
		
	public static IFileDao getInstance(){
		if(dao == null){
			dao = new FileDaoImpl();
		}
		return dao;
	}
	@Override
	public AttachFileVO getFileInfo(int file_seq) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		AttachFileVO fileVO = sqlSession.selectOne("file.getFileInfo", file_seq);
		sqlSession.close();
		return fileVO;
	}

	@Override
	public int deleteExistingFile(Map<String, Object> param) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int delResult = sqlSession.delete("file.deleteExistingFile", param);
		sqlSession.commit();
		sqlSession.close();
		return delResult;
	}

}
