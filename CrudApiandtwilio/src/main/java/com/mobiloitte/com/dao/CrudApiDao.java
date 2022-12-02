package com.mobiloitte.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobiloitte.com.model.CrudApiModel;

public interface CrudApiDao extends JpaRepository<CrudApiModel,Long> {

CrudApiModel getByUserid(Long userid);



}
