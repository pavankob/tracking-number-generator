package com.assessment.trackingnumbergenerator.service;

import com.assessment.trackingnumbergenerator.dto.TrackingNumberGeneratorRequest;
import com.assessment.trackingnumbergenerator.dto.TrackingNumberGeneratorResponse;

public interface ITrackingNumberGeneratorService {

    TrackingNumberGeneratorResponse getNextTrackingNumber(TrackingNumberGeneratorRequest trackingNumberGeneratorRequest);

}
