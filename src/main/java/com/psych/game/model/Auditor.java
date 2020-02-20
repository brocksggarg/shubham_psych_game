package com.psych.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data
public abstract class Auditor {
	
	@Id
	private Long id;
	
	
	@Column
	private String audi;
}
