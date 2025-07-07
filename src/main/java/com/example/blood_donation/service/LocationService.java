package com.example.blood_donation.service;

import com.example.blood_donation.entity.Location;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Integer id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + id));
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Integer id, Location updatedLocation) {
        Location location = getLocationById(id);
        if (updatedLocation.getName() != null && !updatedLocation.getName().isBlank()) {
            location.setName(updatedLocation.getName());
        }
        if (updatedLocation.getAddress() != null && !updatedLocation.getAddress().isBlank()) {
            location.setAddress(updatedLocation.getAddress());
        }
        if (updatedLocation.getCity() != null && !updatedLocation.getCity().isBlank()) {
            location.setCity(updatedLocation.getCity());
        }
        if (updatedLocation.getLatitude() != null) {
            location.setLatitude(updatedLocation.getLatitude());
        }
        if (updatedLocation.getLongitude() != null) {
            location.setLongitude(updatedLocation.getLongitude());
        }
        return locationRepository.save(location);
    }

    public void deleteLocation(Integer id) {
        locationRepository.deleteById(id);
    }
}

