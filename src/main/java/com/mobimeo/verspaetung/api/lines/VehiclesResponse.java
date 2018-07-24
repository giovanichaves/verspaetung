package com.mobimeo.verspaetung.api.lines;

import lombok.Getter;

import java.util.List;

@Getter
public class VehiclesResponse {

    private List<String> vehicles;

    public VehiclesResponse(List<String> vehicles) {
        this.vehicles = vehicles;
    }
}
