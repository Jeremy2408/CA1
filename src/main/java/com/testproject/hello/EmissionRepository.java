package com.testproject.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmissionRepository extends JpaRepository<EmissionEntity, Long> {
}
    

