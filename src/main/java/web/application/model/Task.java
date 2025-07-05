package web.application.model;

import java.sql.Timestamp;

public class Task {

	private int id;
	private String title;
	private String description;
	private Timestamp createdAt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		if (title == null || title.isBlank())
			throw new IllegalArgumentException("Title cannot be null or empty");
		
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {		
		this.description = description;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
