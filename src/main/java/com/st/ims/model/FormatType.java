package com.st.ims.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FormatType")
@Getter @Setter 
@NoArgsConstructor
public class FormatType implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "\"formatTypeID\"" , nullable = false)
	private int id;
	private String title;

}
