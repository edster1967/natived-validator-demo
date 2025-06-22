package com.natived.nativedvalidatordemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientResponse {
    private String clientId;
    private String lastUpdated;
    private String status;
}
