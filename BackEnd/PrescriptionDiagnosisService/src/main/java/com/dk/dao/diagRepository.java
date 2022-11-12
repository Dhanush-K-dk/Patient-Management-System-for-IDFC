package com.dk.dao;

import com.dk.model.Diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;

public interface diagRepository extends JpaRepository<Diagnosis, Long> {

}
