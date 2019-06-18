package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class UserModifyController
 */
@WebServlet("/userModify")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class UserModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IUserService userService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserModifyController.class);
	
	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVO vo = (UserVO) request.getSession().getAttribute("vo");
		
		String birth = null;

		if(vo.getBirth() == null){
			birth = "";
		} else{
			birth = sdf.format(vo.getBirth());
		}
		request.setAttribute("birth", birth);
		request.getRequestDispatcher("/user/userModify.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String userId 	= request.getParameter("userId");
		String name 	= request.getParameter("name");
		String alias 	= request.getParameter("alias");
		String addr1 	= request.getParameter("addr1");
		String addr2 	= request.getParameter("addr2");
		String zipcd 	= request.getParameter("zipcd");
		String birth 	= request.getParameter("birth");
		
		// 사용자가 보낸 평문 비밀번호 데이터
		String pass 	= request.getParameter("pass");

		// 암호화(SHA256방식) API가 적용된 비밀번호 
		String encrytedPass = KISA_SHA256.encrypt(pass);
		
		logger.debug("name : {} ", name);
		Part profile = request.getPart("profile");
		
		UserVO userVO = null;
		try {
			if( profile.getSize() > 0){
				String contentDisposition = profile.getHeader("content-disposition");
				String fileName = PartUtil.getFileName(contentDisposition);
				String ext = PartUtil.getExt(fileName);
				
				String uploadPath = PartUtil.getUploadPath();
				File uploadFolder = new File(uploadPath);
				if(uploadFolder.exists()){
					String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;

					profile.write(filePath);
					profile.delete();
					userVO = new UserVO(name, userId, alias, encrytedPass, addr1, addr2, zipcd, sdf.parse(birth), filePath, fileName);
				}
				
			} else{
				UserVO lookupUserVO = userService.getUser(userId);
				if(lookupUserVO != null){
					userVO = new UserVO(name, userId, alias, encrytedPass, addr1, addr2, zipcd
									, sdf.parse(birth), lookupUserVO.getPath(), lookupUserVO.getFilename());
				
				}
				
			}
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(userService.updateUser(userVO) > 0){
			request.getSession().removeAttribute("vo");
			response.sendRedirect(request.getContextPath() + "/userPagingList");
		} else{
			request.getSession().setAttribute("vo", userVO);
			request.getRequestDispatcher("/user/userModify.jsp");
		}
		
	}

}
