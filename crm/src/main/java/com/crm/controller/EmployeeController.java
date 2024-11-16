package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService service;
    public EmployeeController(EmployeeService service) {

        this.service = service;

    }

    @PostMapping("/addEmp")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto,
                                                   BindingResult result)
    {
        if (result.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            result.getFieldErrors().forEach(fieldError -> {
                errorMessages.append(fieldError.getField())
                        .append(": ")
                        .append(fieldError.getDefaultMessage())
                        .append("\n");
            });
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        EmployeeDto e = service.addemployee(dto);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteEmployee(@RequestParam long id) {
        service.deleteEmployee(id);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam long id,@RequestBody EmployeeDto dto) {
        EmployeeDto employeeDto = service.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK)  ;

    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee() {
         List<EmployeeDto> emp = service.getEmployee();
         return new ResponseEntity<>(emp, HttpStatus.OK)  ;
    }
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long empId) {
        EmployeeDto emp = service.getEmployeeById(empId);
        return new ResponseEntity<>(emp, HttpStatus.OK) ;
    }
}
