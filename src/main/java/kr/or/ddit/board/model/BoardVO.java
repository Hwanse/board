package kr.or.ddit.board.model;

public class BoardVO {
	
	private int seq;
	private String userId;
	private String name;
	private String use_yn;
	private String reg_dt;
	
	public BoardVO( String userId, String name, String use_yn,
			String reg_dt) {
		this.userId = userId;
		this.name = name;
		this.use_yn = use_yn;
		this.reg_dt = reg_dt;
	}
	
	public BoardVO() {

	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", userId=" + userId + ", name=" + name
				+ ", use_yn=" + use_yn + ", reg_dt=" + reg_dt + "]";
	}
	
	
}
