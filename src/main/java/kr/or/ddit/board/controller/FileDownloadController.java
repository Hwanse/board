package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.board.service.FileServiceImpl;
import kr.or.ddit.board.service.IFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/file")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(FileDownloadController.class);
	
	private IFileService fileService;
	
	@Override
	public void init() throws ServletException {
		fileService = FileServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int file_seq = Integer.parseInt(request.getParameter("fileSeq"));
		
		AttachFileVO fileVO = fileService.getFileInfo(file_seq);
		
		if(fileVO != null){
			
			File file = new File(fileVO.getPath());
			FileInputStream fin = new FileInputStream(file);
			ServletOutputStream sout = response.getOutputStream();
			
			byte[] buffer = new byte[512];
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream charset=\"UTF-8\"");
			response.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(fileVO.getFilename(), "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			int length = 0;
			while( (length = fin.read(buffer)) != -1){
				sout.write(buffer, 0, length);
			}
			fin.close();
			sout.flush();
			sout.close();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
