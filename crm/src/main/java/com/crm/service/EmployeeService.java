package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
private EmployeeRepository emprep;
private ModelMapper mm;
public EmployeeService(EmployeeRepository emprep,ModelMapper mm) {
        this.emprep = emprep;
        this.mm = mm;
    }
    public EmployeeDto addemployee(EmployeeDto dto) {
             Employee e = mapToEntity(dto);
             Employee emp = emprep.save(e);
             EmployeeDto employeeDto = mapToDto(emp);
             return employeeDto;

    }

    public void deleteEmployee(long id) {
     emprep.deleteById(id);
    }

    public EmployeeDto updateEmployee(long id, EmployeeDto dto) {

        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee e = emprep.save(employee);
        EmployeeDto employeeDto = mapToDto(e);
        return employeeDto;
    }

    public List<EmployeeDto> getEmployee() {
        List<Employee> all = emprep.findAll();
        List<EmployeeDto> collect = all.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return collect;
    }
    EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = mm.map(employee, EmployeeDto.class);
        return dto;

    }
    Employee mapToEntity(EmployeeDto dto){
        Employee employee = mm.map(dto, Employee.class);
        return employee;

    }

    public EmployeeDto getEmployeeById(long empId) {
        Employee employee = emprep.findById(empId).orElseThrow(
                () -> new ResourceNotFound("Employee not found with id " + empId)
        );
        EmployeeDto dto = mapToDto(employee);
        return dto;
    }
}
