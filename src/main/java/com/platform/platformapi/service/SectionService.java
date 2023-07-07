package com.platform.platformapi.service;

import com.platform.platformapi.api.model.Section;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private List<Section> sectionList;

    public SectionService() {
        sectionList = new ArrayList<>();
        Section section1 = new Section("A");
        Section section2 = new Section("B");
        Section section3 = new Section("C");
        Section section4 = new Section("D");
        sectionList.addAll(Arrays.asList(section1,section2,section3,section4));
    }

    public Optional<Section> getSectionID(String sectionID) {
        Optional optional = Optional.empty();
        for (Section section : sectionList) {
            if (sectionID.equals(section.getSection())) {
                optional = Optional.of(section);
                return optional;
            }
        }
        return optional;
    }

}
