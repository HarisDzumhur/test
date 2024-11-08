package com.example.app.group.hunt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lovacko_drustvo")
public class HuntingGroup {
	
	@Id
	@Column(name="IdDrustva")
	private Integer id;
	
	public HuntingGroup()
	{
		
	}
	
	public HuntingGroup(Integer id)
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
