package com.sbn.web.rest;

import com.sbn.service.AuditEventService;
import com.sbn.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * REST controller for getting the audit events.
 */
@RestController
@RequestMapping("/simulateur")
public class SimulateurResource {

    @PostMapping(path = "/lmnp")
   // @Timed
    public void simulateLmnp(@RequestBody String input) {
            System.out.println(input);
       
    }
}
