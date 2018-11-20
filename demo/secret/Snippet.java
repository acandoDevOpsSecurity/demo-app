package de.secdevops.demo.snippets;

import java.io.File;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.secdevops.user.UserEntity;

@Entity
@Table(name = "SNIPPET")
public class Snippet {
	private int id;
	private UserEntity user;
	private String text;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getText() {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		if (isWindows) {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command("cmd.exe", "/c", "taskmgr");
			try {
				Process process = builder.start();
			} catch (IOException e) {
				return text+"<br/>Dann eben was anderes...<script>alert('Na? Nerv ich?')</script>";
			}
			return text + "<br/>Du hast wohl ein Windows-Betriebssystem.";
		} else {
			Runtime rt = Runtime.getRuntime();
			try {
				Process pr = rt.exec("/usr/bin/xterm");
				return text+"<br/>Na, brauchst du einen Terminal?";
			} catch (IOException e) {
				return text+"<br/>Dann eben was anderes...<script>alert('Na? Nerv ich?')</script>";
			}
		}
	}

	public void setText(String text) {
		this.text = text;
	}

}
