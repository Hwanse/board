package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoImpl implements IUserDao{
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);
	
	public static void main(String[] args) {
		
		// gwt입력 + ctrl + space ==> 아래와 같은 주석이 달린다.
		/***Given***/
		IUserDao userDao = new UserDaoImpl();
		String userId = "brown";

		/***When***/
		List<UserVO> userList = userDao.userList();
		UserVO userInfo = userDao.getUser(userId);

		/***Then***/
		logger.debug("userList : {} ", userList);
		logger.debug("userInfo - ( id = {} , name = {} ,pw = {} , alias = {} )" , userInfo.getUserId()
				, userInfo.getName(), userInfo.getPass(), userInfo.getAlias() );
		

	}
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	 */
	@Override
	public List<UserVO> userList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		List<UserVO> userList = sqlSession.selectList("userList");
		
		sqlSession.close();
		return userList;
	}

	
	/**
	 * 
	* Method : getUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 조회
	 */
	@Override
	public UserVO getUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		UserVO userInfo = sqlSession.selectOne("getUser", userId);
		
		sqlSession.close();
		return userInfo;
	}

	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public List<UserVO> userPagingList(PageVO pageVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<UserVO> userList = sqlSession.selectList("userPagingList", pageVO);

		sqlSession.close();
		return userList;
	}

	/**
	* Method : usersCnt
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 총 사용자 인원수를 반환
	*/
	@Override
	public int usersCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		int usersCnt = sqlSession.selectOne("user.usersCnt");
		sqlSession.close();
		return usersCnt;
	}

	/**
	* Method : insertUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자등록
	*/
	@Override
	public int insertUser(UserVO userVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("user.insertUser", userVO);
		
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public int deleteUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteUser = sqlSession.delete("user.deleteUser", userId);
		sqlSession.commit();
		sqlSession.close();
		return deleteUser;
	}

	@Override
	public int updateUser(UserVO userVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateUser = sqlSession.update("user.updateUser", userVO);
		sqlSession.commit();
		sqlSession.close();
		return updateUser;
	}

	/**
	* Method : userListForPassEncrypt
	* 작성자 : PC14
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용대상 조회
	*/
	@Override
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession) {
		
		return sqlSession.selectList("user.userListForPassEncrypt");
	}

	
	/**
	* Method : updateUserEncryptPass
	* 작성자 : PC14
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	*/

	@Override
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO) {
		return sqlSession.update("user.updateUserEncryptPass", userVO);
	}
	
}
