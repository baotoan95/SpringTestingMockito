package com.btit95.sample.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.btit95.sample.utils.DateFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "todo")
public class ToDo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	@Column(name = "description", length = 500)
	private String description;
	@Column(name = "creation_time", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	private Date creationTime;
	@Version
	private long version;

	public ToDo() {
		this.title = "";
		this.description = "";
	}

	public ToDo(int id, String title, String description, Date creationTime, long version) {
		super();
		this.id = id;
		this.creationTime = creationTime;
		this.description = description;
		this.title = title;
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ToDo) {
			ToDo another = (ToDo) obj;
			return this.id == another.getId()
					&& DateFormat.dateToString(this.creationTime).equals(DateFormat.dateToString(another.getCreationTime()))
					&& this.description.equals(another.getDescription()) && this.title.equals(another.getTitle())
					&& this.version == another.getVersion();
		}
		return false;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", title=" + title + ", description=" + description + ", creationTime="
				+ DateFormat.dateToString(creationTime) + ", version=" + version + "]";
	}

}
