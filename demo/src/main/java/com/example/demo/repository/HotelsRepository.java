package com.example.demo.repository;

import com.example.demo.models.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelsRepository extends JpaRepository<Hotels,Long> {
}
