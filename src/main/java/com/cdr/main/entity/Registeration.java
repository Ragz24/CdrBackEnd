package com.cdr.main.entity;

import org.springframework.stereotype.Component;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "reg")
@AllArgsConstructor
@NoArgsConstructor
public class Registeration 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
		
	private String phoneNumber;
	
	private String address;

	private String planCategory;
	
	private String email;
	
	private String password;
	
	

}

