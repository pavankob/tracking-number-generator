package com.assessment.trackingnumbergenerator.service.impl;

import com.assessment.trackingnumbergenerator.dto.TrackingNumberGeneratorRequest;
import com.assessment.trackingnumbergenerator.dto.TrackingNumberGeneratorResponse;
import com.assessment.trackingnumbergenerator.service.ITrackingNumberGeneratorService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TrackingNumberGeneratorServiceImpl implements ITrackingNumberGeneratorService {

    private final ConcurrentHashMap<String, Boolean> trackingNumbers = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(0);

    @Override
    public TrackingNumberGeneratorResponse getNextTrackingNumber(TrackingNumberGeneratorRequest request) {

        String trackingNumber;
        do {
            trackingNumber = generateUniqueTrackingNumber();
        } while (trackingNumbers.putIfAbsent(trackingNumber, true) != null);

        String timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now());

        return new TrackingNumberGeneratorResponse(trackingNumber, timestamp);
    }

    private String generateUniqueTrackingNumber() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        return uuid.substring(0, Math.min(16, uuid.length())).replaceAll("[^A-Z0-9]", "");
    }
}
