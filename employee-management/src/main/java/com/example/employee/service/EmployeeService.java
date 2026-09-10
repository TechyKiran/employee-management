package com.example.employee.service;
import com.example.employee.dto.*;
import java.util.List;
public interface EmployeeService {
 EmployeeResponse create(EmployeeRequest r);
 List<EmployeeResponse> getAll();
 EmployeeResponse getById(Long id);
 EmployeeResponse update(Long id,EmployeeRequest r);
 void delete(Long id);
}