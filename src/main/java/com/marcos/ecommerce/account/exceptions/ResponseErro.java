package com.marcos.ecommerce.account.exceptions;


import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseErro {
    private int status;
    private Map<String, String> erro = new HashMap<>();
    private String path;
}



