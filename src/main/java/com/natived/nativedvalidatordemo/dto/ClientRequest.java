package com.natived.nativedvalidatordemo.dto;

import com.natived.nativedvalidatordemo.validation.ValidAdminRoleSpecies;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@ValidAdminRoleSpecies
public class ClientRequest {
    @NotBlank
    private String userName;

    @NotBlank
    private String role;

    @NotBlank
    private String appId;

    private String species;

    @NotBlank
    private String location;

}
