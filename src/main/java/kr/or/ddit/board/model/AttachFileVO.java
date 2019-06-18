package kr.or.ddit.board.model;

public class AttachFileVO {
	
	private int file_seq;
	private int po_seq;
	private String path;
	private String filename;

	public AttachFileVO(int po_seq, String path, String filename) {
		this.po_seq = po_seq;
		this.path = path;
		this.filename = filename;
	}

	public AttachFileVO() {

	}
	
	public int getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}
	public int getPo_seq() {
		return po_seq;
	}
	public void setPo_seq(int po_seq) {
		this.po_seq = po_seq;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "AttachFileVO [file_seq=" + file_seq + ", po_seq=" + po_seq
				+ ", path=" + path + ", filename=" + filename + "]";
	}
	
}
