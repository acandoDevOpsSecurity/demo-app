package de.secdevops.user_mgmt;

public class UserModel {
	private String name;
	private String password;
	private String oldPassword;
	private String icon;
	private String website;
	private String color;
	private String privateSnippet;
	boolean admin;
	boolean author;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrivateSnippet() {
		return privateSnippet;
	}
	public void setPrivateSnippet(String privateSnippet) {
		this.privateSnippet = privateSnippet;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isAuthor() {
		return author;
	}
	public void setAuthor(boolean author) {
		this.author = author;
	}


}
