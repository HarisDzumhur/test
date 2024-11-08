package com.example.app.reply;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="odgovor")
public class ForumReply {

	
	@Id
	@Column(name="id_odgovora")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Sadrzaj",length=500)
	private String content;
	
	@Column(name="odgovara_id_korisnika")
	private Integer idResponseUser;
	
	@Column(name="id_teme")
	private Integer idTopic;
	
	@Column(name="id_replike")
	private Integer idReply;
	
	@Column(name="datum_odgovora")
	private LocalDateTime date;
	
	@Column(name="prijavljen")
	private Boolean reported;
	
	private String userName;
	private String userSurname;
	private String topicName;
	private byte[] userImage;
	
	public ForumReply()
	{
		super();
	}

	public ForumReply(Integer id, String content, Integer idResponseUser, Integer idTopic, Integer idReply,
			LocalDateTime date,Boolean reported) {
		super();
		this.id = id;
		this.content = content;
		this.idResponseUser = idResponseUser;
		this.idTopic = idTopic;
		this.idReply = idReply;
		this.date = date;
		this.reported=reported;
	}

	public Boolean getReported() {
		return reported;
	}

	public void setReported(Boolean reported) {
		this.reported = reported;
	}

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserSurname() {
		return userSurname;
	}



	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}



	public byte[] getUserImage() {
		return userImage;
	}



	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIdResponseUser() {
		return idResponseUser;
	}

	public void setIdResponseUser(Integer idResponseUser) {
		this.idResponseUser = idResponseUser;
	}

	public Integer getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(Integer idTopic) {
		this.idTopic = idTopic;
	}

	public Integer getIdReply() {
		return idReply;
	}

	public void setIdReply(Integer idReply) {
		this.idReply = idReply;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	
	
	
	
	
}
