package com.assessment.trackingnumbergenerator.controller;

import com.assessment.trackingnumbergenerator.dto.TrackingNumberGeneratorRequest;
import com.assessment.trackingnumbergenerator.dto.TrackingNumberGeneratorResponse;
import com.assessment.trackingnumbergenerator.service.ITrackingNumberGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1")
public class TrackingNumberGeneratorController {



    @Autowired
    ITrackingNumberGeneratorService trackingNumberGeneratorService;

    @GetMapping("/next-tracking-number")
    public TrackingNumberGeneratorResponse getNextTrackingNumber(
            @RequestParam String origin_country_id,
            @RequestParam String destination_country_id,
            @RequestParam double weight,
            @RequestParam String created_at,
            @RequestParam UUID customer_id,
            @RequestParam String customer_name,
            @RequestParam String customer_slug
    ){
        TrackingNumberGeneratorRequest request = new TrackingNumberGeneratorRequest(origin_country_id,
                destination_country_id,
                weight,
                created_at,
                customer_id,
                customer_name,
                customer_slug
                );

        return trackingNumberGeneratorService.getNextTrackingNumber(request);
    }


}
