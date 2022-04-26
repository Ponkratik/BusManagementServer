package com.ponkratov.busmanagementserver.controller.impl;

import com.ponkratov.busmanagementserver.controller.BusStopController;
import com.ponkratov.busmanagementserver.model.Busstop;
import com.ponkratov.busmanagementserver.payload.response.MessageResponse;
import com.ponkratov.busmanagementserver.repository.BusStopRepository;
import com.ponkratov.busmanagementserver.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BusStopControllerImpl implements BusStopController {
    @Autowired
    BusStopRepository busStopRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<Busstop> busstops = busStopRepository.findAll();
        return new ResponseEntity<>(busstops, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Busstop> temp = busStopRepository.findById(id);
        Busstop tempBusStop;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этот остановочный пункт"), HttpStatus.NO_CONTENT);
        } else {
            tempBusStop = temp.get();
        }

        return new ResponseEntity<>(tempBusStop, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        busStopRepository.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Остановочный пункт удален успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> add(Busstop busstop) {
        busStopRepository.save(busstop);
        return new ResponseEntity<>(new MessageResponse("Остановочный пункт добавлен успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Long id, Busstop busstop) {
        Optional<Busstop> temp = busStopRepository.findById(id);
        Busstop tempBusStop;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этот остановочный пункт"), HttpStatus.NO_CONTENT);
        } else {
            tempBusStop = temp.get();
        }

        busstop.setStopId(id);

        busStopRepository.save(busstop);
        return new ResponseEntity<>(new MessageResponse("Остановочный пункт обновлён успешно"), HttpStatus.OK);
    }
}
