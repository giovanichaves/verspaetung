package com.mobimeo.verspaetung.api.lines;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@Getter
public class VehiclesResponse {

    private List<String> vehicles;

    public VehiclesResponse(List<String> vehicles) {
        this.vehicles = vehicles;
    }
}
