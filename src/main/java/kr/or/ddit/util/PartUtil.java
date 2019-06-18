package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartUtil {

	private static final String UPLOAD_PATH = "d:\\upload\\";

	/**
	* Method : getFileName
	* 작성자 : PC14
	* 변경이력 :
	* @param contentDisposition
	* @return
	* Method 설명 : contentDisposition에서 파일명을 반환한다.
	*/
	public static String getFileName(String contentDisposition) {
		//  form-data; name="profile"; filename="sally.png"
		String[] tags = contentDisposition.split(";");

		String fileName = null;
		for(String tag : tags){
			if(tag.contains("filename")){
				int startIdx = tag.indexOf("\"");
				int endIdx = tag.lastIndexOf("\"");

				fileName = tag.substring(startIdx+1, endIdx); 
				break;
			}
		}
		return fileName;
	}

	/**
	* Method : getExt
	* 작성자 : PC14
	* 변경이력 :
	 * @param fileName 
	* @return
	* Method 설명 : 파일명으로부터 파일 확장자를 반환
	*/
	public static String getExt(String fileName) {
		
		int idx = fileName.lastIndexOf(".");
		String ext  = "";
		if(idx != -1){
			ext = fileName.substring(idx+1);
		}
		
		return ext.equals("") ? "" : "." + ext;
		
		// 방법2
//		String[] splited = fileName.split("\\.");
//		if(splited.length == 1){
//			return "";
//		} else{
//			return splited[splited.length-1];
//		}
	
	}
	
	/**
	* Method : checkUploadFolder
	* 작성자 : PC14
	* 변경이력 :
	* @param yyyy
	* @param mm
	* Method 설명 : 년도, 월 업로드 폴더가 존재하는지 체크, 없을 경우엔 폴더를 생성
	*/
	public static void checkUploadFolder(String yyyy, String mm){
		
		File yyyyFolder = new File(UPLOAD_PATH + yyyy);
		// 신규 년도로 넘어갔을 때 해당년도의 폴더를 생성한다.
		if (!yyyyFolder.exists()) {
			yyyyFolder.mkdir();
		}
		// 월에 해당하는 폴더가 있는지 확인
		// File.separator : 해당 컴퓨터의 Os를 파악하고 해당 os에서 호환이되는
		// 파일경로 구분자를 넣어준다.
		File mmFolder = new File(UPLOAD_PATH + yyyy + File.separator + mm);
		if (!mmFolder.exists()) {
			mmFolder.mkdir();
		}
		
	}
	
	
	/**
	* Method : getUploadPath
	* 작성자 : PC14
	* 변경이력 :
	* @param yyyy
	* @param mm
	* @return
	* Method 설명 : 업로드 경로를 반환
	*/
	public static String getUploadPath(){
		// 업로드 폴더 유무 확인
		Date dt = new Date();
		SimpleDateFormat yyyyMMSdf = new SimpleDateFormat("yyyyMM");

		String yyyyMM = yyyyMMSdf.format(dt);
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6);
		
		PartUtil.checkUploadFolder(yyyy, mm);
		return UPLOAD_PATH + yyyy + File.separator + mm;
	}
	
}

