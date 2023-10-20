package com.cdr.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdr.main.entity.Registeration;

@Repository
public interface CdrRepository extends JpaRepository<Registeration, Integer> 
{
	Registeration findByphoneNumber(String phoneNumber);

}
