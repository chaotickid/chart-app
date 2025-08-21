/**
 * Copyright © 2024 Mavenir Systems
 */
package com.dep.ordermanagement.pojo.dto;

import lombok.Data;

/***
 * @author Aditya Patil
 * @date 18-06-2024
 */
@Data
public class TenantDto {

    private int id;

    private String email;

    private String password;

    private String tenantName;
}
