package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;

public interface IUserDao {

	
	/**
	 * 
	* Method : userList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	 */
	public List<UserVO> userList();
	
	/**
	 * 
	* Method : getUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	 */
	public UserVO getUser(String userId);

	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	public List<UserVO> userPagingList(PageVO pageVO);

	/**
	 * 
	* Method : userCnt
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 수 조회
	 */
	int usersCnt();
	
	/**
	* Method : insertUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(UserVO userVO);

	
	
	/**
	* Method : deleteUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭세
	*/
	int deleteUser(String userId);

	
	/**
	* Method : updateUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	int updateUser(UserVO userVO);

	
	/**
	* Method : userListForPassEncrypt
	* 작성자 : PC14
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용대상 조회
	*/
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession);

	
	
	/**
	* Method : updateUserEncryptPass
	* 작성자 : PC14
	* 변경이력 :
	* @param sqlSession
	* @param userVO 
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	*/
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO);


}
