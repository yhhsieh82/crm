package com.example.crm.controller;

import java.util.List;


import org.springframework.web.bind.annotation.*;

import com.example.crm.dto.CompanyDto;
import com.example.crm.entity.Company;
import com.example.crm.exception.DocumentNotFoundException;
import com.example.crm.service.CompanyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    private static final String COMPANY_NOT_FOUND = "the requested company with name = %s does not exist.";

    @ApiOperation(value = "create a new company", response = Company.class)
    @PostMapping
    public Company createCompany(@ApiParam(value = "companyDto")
                                 @RequestBody final CompanyDto companyDto) {
        return companyService.createOrUpdate(companyDto);
    }

    @ApiOperation(value = "get a company by company name", response = Company.class)
    @GetMapping
    public Company getCompanyByName(@RequestParam(value = "name") final String name) throws DocumentNotFoundException {
        return companyService.getCompanyByName(name)
                .orElseThrow(() -> {
                    log.debug("company: {} not found", name);
                    return new DocumentNotFoundException(String.format(COMPANY_NOT_FOUND, name));
                });
    }

    @ApiOperation(value = "update a company", response = Company.class)
    @PatchMapping
    public Company updateCompany(@ApiParam(value = "companyDto")
                                 @RequestBody final CompanyDto companyDto) {
        return companyService.createOrUpdate(companyDto);
    }

    @DeleteMapping
    public List<Company> deleteCompanyByName(final String name) {
        return companyService.deleteByName(name);
    }

}
