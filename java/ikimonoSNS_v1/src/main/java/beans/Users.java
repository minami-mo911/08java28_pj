package beans;

import java.io.Serializable;

public class Users implements Serializable{
	private String userId;
	private String userName;
	private String email;
	private String password;
	
	public Users() {}
	
/*	
	public Users(String name,String pass) {
		this.name = name;
		this.pass = pass;
	}
*/
	
	public Users(String userId, String userName, String email, String password) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	// @getter
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
	
	
	// @setter
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
