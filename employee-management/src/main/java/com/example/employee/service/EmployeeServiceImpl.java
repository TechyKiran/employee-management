package com.example.employee.service;
import com.example.employee.dto.*;
import com.example.employee.entity.Employee;
import com.example.employee.exception.*;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
 private final EmployeeRepository repo;
 public EmployeeServiceImpl(EmployeeRepository repo){this.repo=repo;}
 private EmployeeResponse dto(Employee e){return new EmployeeResponse(e.getId(),e.getName(),e.getEmail(),e.getDepartment(),e.getSalary());}
 @Override @CacheEvict(value="employees",allEntries=true)
 public EmployeeResponse create(EmployeeRequest r){
  if(repo.existsByEmail(r.email())) throw new EmailAlreadyExistsException(r.email());
  Employee e=new Employee(); e.setName(r.name());e.setEmail(r.email());e.setDepartment(r.department());e.setSalary(r.salary());return dto(repo.save(e));
 }
 @Override public List<EmployeeResponse> getAll(){return repo.findAll().stream().map(this::dto).toList();}
 @Override @Cacheable(value="employees",key="#id")
 public EmployeeResponse getById(Long id){return dto(repo.findById(id).orElseThrow(()->new EmployeeNotFoundException(id)));}
 @Override @CacheEvict(value="employees",key="#id")
 public EmployeeResponse update(Long id,EmployeeRequest r){
  Employee e=repo.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
  if(!e.getEmail().equals(r.email()) && repo.existsByEmail(r.email())) throw new EmailAlreadyExistsException(r.email());
  e.setName(r.name());e.setEmail(r.email());e.setDepartment(r.department());e.setSalary(r.salary());return dto(repo.save(e));
 }
 @Override @CacheEvict(value="employees",key="#id")
 public void delete(Long id){if(!repo.existsById(id))throw new EmployeeNotFoundException(id);repo.deleteById(id);}
}