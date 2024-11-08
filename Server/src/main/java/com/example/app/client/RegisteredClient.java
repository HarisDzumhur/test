package com.example.app.client;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.GeneratorType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.servlet.annotation.MultipartConfig;

@Entity
@Table(name="registrovani_klijent")
public class RegisteredClient implements UserDetails{

	@Id
	@Column(name="id_klijenta")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	

	
	
	@Column(name="Lozinka")
	private String password;
	
	@Column(name="Email")
	private String mail;
	
	
	@Column(name="Ime")
	private String name;
	
	@Column(name="Prezime")
	private String surname;
	
	
	@Column(name="Slika",length=16777215)
	 @Lob
	 @Basic(fetch = FetchType.LAZY)
	private byte[] image;

	@Column(name="blokiran")
	private Boolean blocked;
	

	@Column(name="obrisan")
	private Boolean deleted;
	
	@Column(name="google_signin")
	private Boolean google_signin;
	
	@Column(name="fcm_token")
	private String fcmToken;
	
	@Column(name="je_verifikovan")
	private Boolean isVerified;
	
	
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	public RegisteredClient()
	{
		super();
	}
	
	public RegisteredClient(Integer id, String password, String mail, String name, String surname,
			byte[] image, Boolean blocked, Boolean deleted, Boolean google_signin,String fcmToken,Boolean isVerified) {
		super();
		this.id = id;
		this.password = password;
		this.mail = mail;
		this.name = name;
		this.surname = surname;
		this.image = image;
		this.blocked = blocked;
		this.deleted = deleted;
		this.google_signin = google_signin;
		this.fcmToken=fcmToken;
		this.isVerified=isVerified;
	}
	public String getFcmToken() {
		return fcmToken;
	}
	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Boolean getGoogle_signin() {
		return google_signin;
	}
	public void setGoogle_signin(Boolean google_signin) {
		this.google_signin = google_signin;
	}
	public Boolean getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, mail, name, password, surname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisteredClient other = (RegisteredClient) obj;
		return Objects.equals(id, other.id) && Objects.equals(mail, other.mail) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		return List.of(new SimpleGrantedAuthority("CLIENT"));
	}
	
	
	
	
	
	
}
