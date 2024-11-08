package com.example.app.group.fish;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ribolovacko_drustvo")
public class FishingGroup {
	@Id
	@Column(name="IdDrustva")
	private Integer id;
	
	public FishingGroup()
	{
		
	}
	
	public FishingGroup(Integer id)
	{
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
