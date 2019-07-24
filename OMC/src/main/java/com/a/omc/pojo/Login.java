package com.a.omc.pojo;

import java.io.Serializable;

public class Login implements Serializable{

	private static final long serialVersionUID = 580173718225090442L;

	private Integer id;

    private String user;

    private String password;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

	@Override
	public String toString() {
		return "Login [id=" + id + ", user=" + user + ", password=" + password + ", type=" + type + "]";
	}
    
}