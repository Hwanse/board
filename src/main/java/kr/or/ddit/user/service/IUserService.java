package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

public interface IUserService {

	/**
	 * 
	* Method : userList
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체리스트 조회
	 */
	public List<UserVO> userList();
	
	
	/**
	 * 
	* Method : getUser
	* 작성자 : PC14
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 사용자 정보 조회
	 */
	public UserVO getUser(String id);
	
	/**
	 * 
	* Method : userPagingListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	public Map<String, Object> userPagingList(PageVO pageVO);
	
	
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
	* Method : updateUser
	* 작성자 : PC14
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	int updateUser(UserVO userVO);
	
	
	/**
	* Method : encryptPassAllUser
	* 작성자 : PC14
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 비밀번호를 암호화 일괄 적용 배치
	*/
	int encryptPassAllUser();
	
	
}
