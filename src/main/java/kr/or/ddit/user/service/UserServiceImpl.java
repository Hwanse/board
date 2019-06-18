package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVO;

public class UserServiceImpl implements IUserService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	/**
	 * 
	* Method : userList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	 */
	@Override
	public List<UserVO> userList() {
		IUserDao userDao = new UserDaoImpl();
		List<UserVO> userList = userDao.userList();
		return userList;
	}

	@Override
	public UserVO getUser(String id) {
		IUserDao userDao = new UserDaoImpl();
		UserVO vo = userDao.getUser(id);
		
		return vo;
	}

	
	/**
	 * 
	* Method : userPagingListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	public Map<String, Object> userPagingList(PageVO pageVO){
		IUserDao userDao = new UserDaoImpl();
		
		// 1. List<UserVO>, userCnt 를 필드로 하는 VO
		
		// 2. List<Object>, resultList = new ArrayList<Object>();
		// 	  resultList.add(userList);
		//	  rusultList.add(usersCnt);
		
		// 3. Map<String, Object> resultMap = new hashMap<String, Object>();
		//	  resultMap.put("userList", userList);
		//	  resultMap.put("usersCnt, usersCnt);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
//		resultMap.put("userList", userDao.userPagingList(pageVO));
//		resultMap.put("usersCnt", userDao.usersCnt());
		
		List<UserVO> userList = userDao.userPagingList(pageVO);
		int usersCnt = userDao.usersCnt();
		resultMap.put("userList", userList);
		resultMap.put("usersCnt", usersCnt);
		
		// usersCnt --> paginationSize 변경
		// pageSize --> pageVO.getPageSize();
		
//		int paginationSize = (int)Math.ceil( (double)usersCnt/ pageVO.getPageSize() );
		int paginationSize = 0;
		if(usersCnt % pageVO.getPageSize() == 0){
			paginationSize = usersCnt / pageVO.getPageSize();
		} else{
			paginationSize = usersCnt / pageVO.getPageSize() + 1;
		}
		
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	@Override
	public int insertUser(UserVO userVO) {
		IUserDao dao = new UserDaoImpl();
		int result = dao.insertUser(userVO);
		
		return result;
	}

	@Override
	public int updateUser(UserVO userVO) {
		IUserDao dao = new UserDaoImpl();
		int result = dao.updateUser(userVO);
		return result;
	}

	
	/**
	* Method : encryptPassAllUser
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 비밀번호를 암호화 일괄 적용 배치
	*/
	@Override
	public int encryptPassAllUser() {
		
		// 사용 금지 , 이미 암호화가 적용이 되어 있음
		if(1 == 1){
			return 0;
		}
		// 0. sql실행에 필용한 sqlSession객체를 생성
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		IUserDao userDao = new UserDaoImpl();
		
		// 1. 모든 사용자 정보를 조회 ( 단, 기존 암호화 적용 사용자 제외)
		List<UserVO> userList = userDao.userListForPassEncrypt(sqlSession);
		
		int updateCntSum = 0;
		// 2. 조회된 사용자의 비밀번호를 암호화 적용후 사용자 업데이트
		for(UserVO userVO : userList){
			String encryptPass = KISA_SHA256.encrypt(userVO.getPass());
			userVO.setPass(encryptPass);
		
			int updateCnt = userDao.updateUserEncryptPass(sqlSession, userVO);
			updateCntSum += updateCnt;
			// 비정상(정상적으로 처리되지 않았을 경우)
			if(updateCnt != 1){
				sqlSession.rollback();
				break;
			}
		}
		// 3. sqlSession 객체를 commit, close
		sqlSession.commit();
		sqlSession.close();

		return updateCntSum;
	}
	
	public static void main(String[] args) {
		IUserService userService = new UserServiceImpl();
		int updateCnt = userService.encryptPassAllUser();
		logger.debug("updateCnt : {}", updateCnt);
	}
	
}
