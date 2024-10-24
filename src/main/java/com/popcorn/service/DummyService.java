package com.popcorn.service;

import com.popcorn.model.ComplexRequestObject;
import com.popcorn.model.ComplexResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class DummyService {
    public ComplexResponseObject update(ComplexRequestObject request) {
        log.info("DummyService::update");
        ComplexResponseObject response = ComplexResponseObject.builder()
                .age((byte) 25)
                .salary((short) 5000)
                .married(true)
                .gender('M')
                .count(100)
                .height(5.9f)
                .serialNumber(123456789L)
                .amount(2500.75)
                .name("John Doe")
                .uuid(UUID.fromString("fb427153-cdb0-471f-aa39-59f0fb458d45"))
                .revenue(new BigDecimal("100000.50"))
                .additionalProperties(Map.of())
                .build();
        return response;
    }

    public ComplexResponseObject get() {
        log.info("DummyService::get");
        return ComplexResponseObject.builder()
                .age((byte) 25)
                .salary((short) 5000)
                .married(true)
                .gender('M')
                .count(100)
                .height(5.9f)
                .serialNumber(123456789L)
                .amount(2500.75)
                .name("John Doe")
                .uuid(UUID.fromString("fb427153-cdb0-471f-aa39-59f0fb458d45"))
                .revenue(new BigDecimal("100000.50"))
                .additionalProperties(Map.of())
                .build();
    }
}
