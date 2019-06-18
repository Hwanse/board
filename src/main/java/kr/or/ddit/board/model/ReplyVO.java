package kr.or.ddit.board.model;

public class ReplyVO {
	private int re_seq;
	private int po_seq;
	private String content;
	private String wt_dt;
	private String userId;
	private String delete_yn;
	
	
	public ReplyVO(int po_seq, String content, String userId, String wt_dt){
		this.po_seq = po_seq;
		this.content = content;
		this.userId = userId;
		this.wt_dt = wt_dt;
	}

	public ReplyVO(){
		
	}
	
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	public int getPo_seq() {
		return po_seq;
	}
	public void setPo_seq(int po_seq) {
		this.po_seq = po_seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWt_dt() {
		return wt_dt;
	}
	public void setWt_dt(String wt_dt) {
		this.wt_dt = wt_dt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}

	@Override
	public String toString() {
		return "ReplyVO [re_seq=" + re_seq + ", po_seq=" + po_seq
				+ ", content=" + content + ", wt_dt=" + wt_dt + ", userId="
				+ userId + ", delete_yn=" + delete_yn + "]";
	}
	
	
	
}
