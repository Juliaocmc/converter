package com.converter.repository;

import com.converter.model.UnitConverter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitConverterRepository extends JpaRepository<UnitConverter,String> {
    
    @Query(value="SELECT si FROM unit_conversion_factors WHERE name = :unit OR symbol = :unit", nativeQuery = true)
    String getSi(String unit);

    @Query(value="SELECT si_conversion FROM unit_conversion_factors WHERE name = :unit OR symbol = :unit", nativeQuery = true)
    Double getSiConverter(String unit);
    
}