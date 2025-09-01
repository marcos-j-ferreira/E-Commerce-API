package com.marcos.ecommerce.account.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponse {

    private int status;
    private String message; 
    private String path;
}