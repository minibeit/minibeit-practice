package com.miniprac.school.service;

import com.miniprac.school.domain.School;
import com.miniprac.school.domain.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<School> getList() {
        return schoolRepository.findAll();
    }
}
