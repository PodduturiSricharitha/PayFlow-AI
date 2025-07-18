package com.aptpath.payflowapi.service;

import com.aptpath.payflowapi.dto.EmployeeDTO;
import com.aptpath.payflowapi.entity.Employee;
import com.aptpath.payflowapi.entity.User;
import com.aptpath.payflowapi.repository.EmployeeRepository;
import com.aptpath.payflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptpath.payflowapi.*;
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private UserRepository userRepo;

    public Employee createEmployee(EmployeeDTO dto) {
    	if (dto.getCreatedByUserId() == null) {
            throw new IllegalArgumentException("CreatedBy user ID is missing");
        }
        User createdBy = userRepo.findById(dto.getCreatedByUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Employee employee = new Employee();
                employee.setFullName(dto.getFullName());
                employee.setAge(dto.getAge());
                employee.setTotalExperience(dto.getTotalExperience());
                employee.setPastExperience(dto.getPastExperience());
                employee.setCreatedBy(createdBy);
                employee.setStatus(Employee.Status.ACTIVE);

        return employeeRepo.save(employee);
    }
}

