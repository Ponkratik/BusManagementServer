package com.ponkratov.busmanagementserver.controller.impl;

import com.ponkratov.busmanagementserver.controller.TripController;
import com.ponkratov.busmanagementserver.model.Trip;
import com.ponkratov.busmanagementserver.payload.response.MessageResponse;
import com.ponkratov.busmanagementserver.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TripControllerImpl implements TripController {
    @Autowired
    TripRepository tripRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<Trip> trips = tripRepository.findAll();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Trip> temp = tripRepository.findById(id);
        Trip tempTrip;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этот рейс"), HttpStatus.NO_CONTENT);
        } else {
            tempTrip = temp.get();
        }

        return new ResponseEntity<>(tempTrip, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getByDriver(@PathVariable Long id) {
        List<Trip> trips = tripRepository.findAllByUserByUserId_UserId(id);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        tripRepository.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Рейс удален успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> add(Trip trip) {
        tripRepository.save(trip);

        return new ResponseEntity<>(new MessageResponse("Рейс создан успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Long id, Trip trip) {
        Optional<Trip> temp = tripRepository.findById(id);
        Trip tempTrip;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этот рейс"), HttpStatus.NO_CONTENT);
        } else {
            tempTrip = temp.get();
        }

        trip.setTripId(id);

        tripRepository.save(trip);
        return new ResponseEntity<>(new MessageResponse("Рейс обновлён успешно"), HttpStatus.OK);
    }
}
