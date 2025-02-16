package com.DDN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DDN.entity.NotifiEntity;

@Repository
public interface NotifiRepository extends JpaRepository<NotifiEntity,Long> {
    
}
