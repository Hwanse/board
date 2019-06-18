package kr.or.ddit.board.model;

public class PostPageVO {
	private int page;		// 페이지 번호
	private int pageSize;	// 페이징 갯수
	private int seq;		// 해당 게시판의 id

	public PostPageVO(int page, int pageSize, int seq){
		this.page = page;
		this.pageSize = pageSize;
		this.seq = seq;
	}
	
	public PostPageVO(){
		
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
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

}	
