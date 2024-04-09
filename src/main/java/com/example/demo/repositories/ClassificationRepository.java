package com.example.demo.repositories;

import com.example.demo.models.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Integer> {
}
