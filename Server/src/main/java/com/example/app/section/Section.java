package com.example.app.section;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="sekcija")
public class Section {
	
	@Id
	@Column(name="IdSekcije")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Naziv")
	private String name;
	
	@Column(name="IdDrustva")
	private Integer idGroup;
	
	@Column(name="BrojClanova")
	private Integer membersCount;
	
	@Column(name="Opis")
	private String description;
	
	@Column(name="Slika",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	
	public Section() {
		
	}
	
	public Section(Integer id, String name, Integer idGroup, Integer membersCount, String description, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.idGroup = idGroup;
		this.membersCount = membersCount;
		this.description = description;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	public Integer getMembersCount() {
		return membersCount;
	}
	public void setMembersCount(Integer membersCount) {
		this.membersCount = membersCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
