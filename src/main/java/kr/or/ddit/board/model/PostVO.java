package kr.or.ddit.board.model;


/**
* PostVO.java
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
public class PostVO {
	
	private int po_seq;
	private int seq;
	private int parent_seq;
	private int group_seq;
	private String userId;
	private String title;
	private String content;
	private String wt_dt;
	private String delete_yn;
	private int level;

	// 게시글 등록할 때
	public PostVO(int seq, String userId, String title, String content, String wt_dt) {
		this.seq = seq;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.wt_dt = wt_dt;
	}

	
	// 답글 등록할 때
	public PostVO(int seq, int parent_seq, String userId, String title,
			String content, String wt_dt, int group_seq) {
		this.seq = seq;
		this.parent_seq = parent_seq;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.wt_dt = wt_dt;
		this.group_seq = group_seq;
	}
	
	public PostVO(){
		
	}
	
	public int getPo_seq() {
		return po_seq;
	}
	public void setPo_seq(int po_seq) {
		this.po_seq = po_seq;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	public int getGroup_seq() {
		return group_seq;
	}
	
	public void setGroup_seq(int group_seq) {
		this.group_seq = group_seq;
	}

	@Override
	public String toString() {
		return "PostVO [po_seq=" + po_seq + ", seq=" + seq + ", parent_seq="
				+ parent_seq + ", group_seq=" + group_seq + ", userId="
				+ userId + ", title=" + title + ", content=" + content
				+ ", wt_dt=" + wt_dt + ", delete_yn=" + delete_yn + ", level="
				+ level + "]";
	}
	
	
}
