package com.example.crm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.entity.CrmUser;
import com.example.crm.service.CrmUserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/crmUsers")
@RequiredArgsConstructor
public class CrmUserController {
    private final CrmUserService crmUserService;

    @ApiOperation(value = "create a new user", response = CrmUser.class)
    @PostMapping
    public CrmUser createUser(@RequestBody final CrmUser user) {
        return crmUserService.createUser(user);
    }
}
