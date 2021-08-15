package com.example.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.dto.CompanyDto;
import com.example.crm.entity.Company;
import com.example.crm.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Optional<Company> getCompanyByName(final String name) {
        log.info("find company with name: {}", name);
        return companyRepository.findByName(name);
    }

    @Transactional
    public Company createOrUpdate(final CompanyDto companyDto) {
        final Optional<Company> companyOpt = getCompanyByName(companyDto.getName());
        if (companyOpt.isPresent()) {
            final Company company = companyOpt.get();
            updateCompany(companyDto, company);
            return company;
        } else {
            final Company company = new Company();
            updateCompany(companyDto, company);
            return  companyRepository.save(company);
        }
    }

    @Transactional
    public List<Company> deleteByName(final String name) {
        return companyRepository.deleteByName(name);
    }

    private void updateCompany(final CompanyDto source, final Company target) {
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setIndustry(source.getIndustry());
    }
}
