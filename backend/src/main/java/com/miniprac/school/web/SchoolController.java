package com.miniprac.school.web;

import com.miniprac.school.dto.SchoolResponse;
import com.miniprac.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/school")
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping("/list")
    public List<SchoolResponse.GetList> getList(){
        return schoolService.getList().stream().map(SchoolResponse.GetList::build).collect(Collectors.toList());
    }
}
