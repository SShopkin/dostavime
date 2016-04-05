package org.elsysbg.ip.todo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name=Notice.QUERY_ALL,
		query = "SELECT t from Task t"),
	@NamedQuery(name=Notice.QUERY_BY_AUTHOR,
		query = "SELECT t from Task t WHERE t.author=:author")
})
public class Notice {
	public static final String QUERY_ALL = "noticesAll";
	public static final String QUERY_BY_AUTHOR = "noticesByAuthor";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	@ManyToOne
	private NeedTransportUser author;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NeedTransportUser getAuthor() {
		return author;
	}

	public void setAuthor(NeedTransportUser author) {
		this.author = author;
	}

}
