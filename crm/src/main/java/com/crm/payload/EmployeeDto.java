package com.crm.payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    @NotEmpty
    @Size(min=3,message = "Atleast 3 chars required")
    private String name;
    @Email
    private String email;
    @Size(min=10,max=10,message = "enter a 10 digit number")
    private String mobile;


}