package com.practice.recruitmentservice.controller;

import com.practice.recruitmentservice.dto.RecruitmentDto;
import com.practice.recruitmentservice.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/recruitment")
public class RecruitmentController {

  private final RecruitmentService service;

  @Autowired
  public RecruitmentController(RecruitmentService service) {
    this.service = service;
  }

  @GetMapping("/list")
  @ResponseStatus(HttpStatus.OK)
  public List<RecruitmentDto> list(){
    return service.list();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RecruitmentDto get(@PathVariable Long id){
    return service.get(id);
  }

  @GetMapping("/actual")
  @ResponseStatus(HttpStatus.OK)
  public RecruitmentDto getActual(){
    return service.getActual();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RecruitmentDto create(@RequestBody @Valid RecruitmentDto dto){
    return service.create(dto);
  }


  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RecruitmentDto update(@PathVariable Long id, @RequestBody @Valid RecruitmentDto dto){
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RecruitmentDto delete(@PathVariable Long id){
    return service.delete(id);
  }
}
