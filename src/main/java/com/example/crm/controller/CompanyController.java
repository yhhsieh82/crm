package com.example.crm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.dto.CompanyDto;
import com.example.crm.entity.Company;
import com.example.crm.exception.DocumentNotFoundException;
import com.example.crm.service.CompanyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    private static final String COMPANY_NOT_FOUND = "the requested company with name = %s does not exist.";


    @ApiOperation(value = "create a new company or update an exist company", response = Company.class)
    @PostMapping
    public Company createCompany(@ApiParam(value = "companyDto") @RequestParam CompanyDto companyDto) {
        return companyService.createOrUpdate(companyDto);
    }

    @ApiOperation(value = "get a company by company name", response = Company.class)
    @GetMapping
    public Company getCompanyByName(String name) throws DocumentNotFoundException {
        return companyService.getCompanyByName(name)
            .orElseThrow(() -> new DocumentNotFoundException(String.format(COMPANY_NOT_FOUND, name)));
    }

    @DeleteMapping
    public List<Company> deleteCompanyByName(String name) {
        return companyService.deleteByName(name);
    }

}
