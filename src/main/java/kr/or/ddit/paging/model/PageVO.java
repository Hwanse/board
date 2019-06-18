package kr.or.ddit.paging.model;

/**
* PageVO.java
*
* @author PC14
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC14 최초 생성
*
* </pre>
*/
public class PageVO {

	private int page;		// 페이지 번호
	private int pageSize;	// 페이징 갯수
	

	public PageVO(int page, int pageSize){
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public PageVO(){
		
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
	
}
