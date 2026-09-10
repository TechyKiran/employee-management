package com.example.employee.controller;
import com.example.employee.dto.*;
import com.example.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/employees")
public class EmployeeController {
 private final EmployeeService service;
 public EmployeeController(EmployeeService s){service=s;}
 @PostMapping ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest r){return ResponseEntity.status(201).body(service.create(r));}
 @GetMapping java.util.List<EmployeeResponse> all(){return service.getAll();}
 @GetMapping("/{id}") EmployeeResponse one(@PathVariable Long id){return service.getById(id);}
 @PutMapping("/{id}") EmployeeResponse update(@PathVariable Long id,@Valid @RequestBody EmployeeRequest r){return service.update(id,r);}
 @DeleteMapping("/{id}") ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}