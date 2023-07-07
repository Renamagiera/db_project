package com.platform.platformapi.api.controller;

import com.platform.platformapi.api.model.Section;
import com.platform.platformapi.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SectionController {

    private SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/section")
    public Section getSectionID(@RequestParam String id) {
        Optional<Section> section = sectionService.getSectionID(id);
        if (section.isPresent()) {
            return section.get();
        }
        return null;
    }

}
