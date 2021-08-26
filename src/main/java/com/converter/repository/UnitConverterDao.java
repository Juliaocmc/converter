package com.converter.repository;

import com.converter.model.UnitConverter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitConverterDao extends JpaRepository<UnitConverter,String> {
    
}
