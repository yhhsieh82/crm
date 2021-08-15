package com.example.crm.service;

import java.util.Optional;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.crm.entity.Company;
import com.example.crm.repository.CompanyRepository;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void testGetCompanyByName() {
        final Company company = new Company();
        company.setName("apple");
        company.setDescription("desc");
        company.setIndustry("tech");
        Mockito.when(companyRepository.findByName("apple"))
                .thenReturn(Optional.of(company));

        final Optional<Company> companyOpt = companyService.getCompanyByName("apple");
        Assert.assertTrue(companyOpt.isPresent());
        final Company result = companyOpt.get();
        Assert.assertEquals(result.getName(), company.getName());
        Assert.assertEquals(result.getDescription(), company.getDescription());
    }

}
