package com.assessment.TrackingNumberGenerator.controller;


import com.assessment.TrackingNumberGenerator.dto.TrackingNumberResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TrackingNumberGeneratorController {

    private final ConcurrentHashMap<String, String> generatedNumbers = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(0);

    @GetMapping("/next-tracking-number")
    public TrackingNumberResponse getNextTrackingNumber(
            @RequestParam String origin_country_id,
            @RequestParam String destination_country_id,
            @RequestParam double weight,
            @RequestParam String created_at,
            @RequestParam UUID customer_id,
            @RequestParam String customer_name,
            @RequestParam String customer_slug
    ){

        String uniquePart = Long.toHexString(counter.incrementAndGet()).toUpperCase();
        String trackingNumber = String.format("%s%s%s", origin_country_id, destination_country_id, uniquePart);

        if (trackingNumber.length() > 16) {
            trackingNumber = trackingNumber.substring(0, 16);
        }

        while (generatedNumbers.putIfAbsent(trackingNumber, trackingNumber) != null) {
            uniquePart = Long.toHexString(counter.incrementAndGet()).toUpperCase();
            trackingNumber = String.format("%s%s%s", origin_country_id, destination_country_id, uniquePart);
            if (trackingNumber.length() > 16) {
                trackingNumber = trackingNumber.substring(0, 16);
            }
        }

        String timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now());

        return new TrackingNumberResponse(trackingNumber, timestamp);
    }


}
