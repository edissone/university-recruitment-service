package com.practice.recruitmentservice.controller;

import com.practice.recruitmentservice.dto.enrollee.EnrolleeDto;
import com.practice.recruitmentservice.service.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/enrollee")
public class EnrolleeController {

  private final EnrolleeService service;

  @Autowired
  public EnrolleeController(EnrolleeService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EnrolleeDto get(@PathVariable Long id){
    return service.get(id);
  }

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public List<EnrolleeDto> list(){
    return service.getAll();
  }

  @GetMapping("/list")
  @ResponseStatus(HttpStatus.OK)
  public List<EnrolleeDto> listFromRecruitment(@RequestParam Long recruitmentId){
    return service.getAllFromRecruitment(recruitmentId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EnrolleeDto post(@RequestBody @Valid EnrolleeDto dto){
    return service.create(dto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EnrolleeDto put(@PathVariable Long id, @RequestBody @Valid EnrolleeDto dto){
    return service.update(id, dto);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public EnrolleeDto put(@RequestBody @Valid EnrolleeDto dto){
    return service.update(dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EnrolleeDto delete(@PathVariable Long id){
    return service.delete(id);
  }
}
