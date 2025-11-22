package com.example.sharesphere.service;

import com.example.sharesphere.model.Tool;
import com.example.sharesphere.repo.ToolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ToolService {

    @Autowired
    private ToolRepo repo;

    public List<Tool> getAllTools() {

        return repo.findAll();
    }

    public Tool getToolbyId(int id) {
        return repo.findById(id).orElse(null);
    }

    public Tool addTool(Tool tool) {

        return repo.save(tool);
    }

    public Tool updateTool(int id, Tool tool) {
        return repo.save(tool);
    }

    public void deletetool(int id) {
        repo.deleteById(id);
    }
}
