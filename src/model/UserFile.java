package model;

import java.sql.Timestamp;

public class UserFile {
	private int id;
	private String filename;
	private String path;
	private Timestamp createTime;
	private int isShared;
	private int ownerId;
	private String fileSize;
	private int count;
	private User owner;
	private String filepassword;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getIsShared() {
		return isShared;
	}
	public void setIsShared(int isShared) {
		this.isShared = isShared;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	//分享文件的用户是否已关注的参数
	private int followed = 0;
	
	public int getFollowed() {
		return followed;
	}
	public void setFollowed(int followed) {
		this.followed = followed;
	}
	public String getFilepassword() {
		return filepassword;
	}
	public void setFilepassword(String filepassword) {
		this.filepassword = filepassword;
	}
	@Override
	public String toString() {
		return "UserFile [id=" + id + ", filename=" + filename + ", path="
				+ path + ", createTime=" + createTime + ", isShared="
				+ isShared + ", ownerId=" + ownerId + ", fileSize=" + fileSize
				+ ", count=" + count + ", owner=" + owner +", filepassword=" + filepassword + "]" ;
	}
	
	
}
