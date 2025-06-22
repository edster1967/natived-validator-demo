package com.natived.nativedvalidatordemo.controllers;

import com.natived.nativedvalidatordemo.dto.ClientRequest;
import com.natived.nativedvalidatordemo.dto.ClientResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/client")
public class DemoController {

    @PostMapping
    public ResponseEntity<ClientResponse> getClientInfo(@Valid @RequestBody ClientRequest request) {
        ClientResponse response = new ClientResponse(
                "124455",
                LocalDate.of(2025, 12, 3).toString(),
                "active"
        );
        return ResponseEntity.ok(response);
    }

}
