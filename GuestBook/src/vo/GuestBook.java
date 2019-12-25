package vo;

import java.util.Date;

public class GuestBook {
	protected String	email;
	protected String	password;
	protected String	contents;
	protected Date		createdDate;
	protected Date		modifiedDate;
	protected int		gno;
	
	public String getEmail() {
		return email;
	}
	
	public GuestBook setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public GuestBook setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String getContents() {
		return contents;
	}
	
	public GuestBook setContents(String contents) {
		this.contents = contents;
		return this;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public GuestBook setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	public GuestBook setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
	
	public int getNo() {
		return gno;
	}
	
	public GuestBook setNo(int gno) {
		this.gno = gno;
		return this;
	}
}