package com.popcorn.controller;

import com.popcorn.model.ComplexRequestObject;
import com.popcorn.model.ComplexResponseObject;
import com.popcorn.service.DummyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy-client")
@RequiredArgsConstructor
@Slf4j
public class DummyController {
    private final DummyService dummyService;

    @PutMapping(
            path = "/update",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ComplexResponseObject> update(@RequestBody ComplexRequestObject request) {
        log.info("DummyController::update {}", request);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(dummyService.update(request));
    }
}
