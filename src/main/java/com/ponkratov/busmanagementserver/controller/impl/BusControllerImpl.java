package com.ponkratov.busmanagementserver.controller.impl;

import com.ponkratov.busmanagementserver.controller.BusController;
import com.ponkratov.busmanagementserver.model.Bus;
import com.ponkratov.busmanagementserver.payload.response.MessageResponse;
import com.ponkratov.busmanagementserver.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BusControllerImpl implements BusController {

    @Autowired
    BusRepository busRepository;

    @Override
    public ResponseEntity<?> getAllBuses() {
        List<Bus> buses = busRepository.findAll();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getBusById(Long id) {
        Optional<Bus> temp = busRepository.findById(id);
        Bus tempBus;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Could not find this bus"), HttpStatus.NO_CONTENT);
        } else {
            tempBus = temp.get();
        }

        return new ResponseEntity<>(tempBus, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBus(Long id) {
        busRepository.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Bus deleted successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addBus(Bus bus) {
        if (busRepository.existsBusByNumber(bus.getNumber())) {
            return new ResponseEntity<>(new MessageResponse("Bus with this number exists"), HttpStatus.BAD_REQUEST);
        }

        if (busRepository.existsBusByVin(bus.getVin())) {
            return new ResponseEntity<>(new MessageResponse("Bus with this VIN exists"), HttpStatus.BAD_REQUEST);
        }

        busRepository.save(bus);
        return new ResponseEntity<>(new MessageResponse("Bus added successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateBus(Long id, Bus bus) {
        Optional<Bus> temp = busRepository.findById(id);
        Bus tempBus;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Could not find this bus"), HttpStatus.NO_CONTENT);
        } else {
            tempBus = temp.get();
        }

        if (!bus.getNumber().equals(tempBus.getNumber()) && busRepository.existsBusByNumber(bus.getNumber())) {
            return new ResponseEntity<>(new MessageResponse("Bus with this number exists"), HttpStatus.BAD_REQUEST);
        }

        if (!bus.getVin().equals(tempBus.getVin()) && busRepository.existsBusByVin(bus.getVin())) {
            return new ResponseEntity<>(new MessageResponse("Bus with this VIN exists"), HttpStatus.BAD_REQUEST);
        }

        bus.setBusId(id);

        busRepository.save(bus);
        return new ResponseEntity<>(new MessageResponse("Bus added successfully"), HttpStatus.OK);
    }
}
