package com.example.app.equipment;

import java.util.Objects;

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
@Table(name="oprema")
public class Equipment {
	
	@Id
	@Column(name="idOpreme")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Naziv")
	private String name;
	
	@Column(name="Slika",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	
	@Column(name="idTipa")
	private Integer typeId;
	
	@Column(name="je_lovacka")
	private Boolean isHuntingGear;

	public Equipment() {
		
	}
	
	public Equipment(Integer id) {
	this.id=id;
	}
	
	public Equipment(Integer id, String name, byte[] image, Integer typeId,Boolean isHuntingGear) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.typeId = typeId;
		this.isHuntingGear=isHuntingGear;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Boolean getIsHuntingGear() {
		return isHuntingGear;
	}

	public void setIsHuntingGear(Boolean isHuntingGear) {
		this.isHuntingGear = isHuntingGear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
