package com.example.sharesphere.repo;

import com.example.sharesphere.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface ToolRepo extends JpaRepository<Tool, Integer> {


    }

