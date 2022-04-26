package com.ponkratov.busmanagementserver.controller.impl;

import com.ponkratov.busmanagementserver.controller.CityController;
import com.ponkratov.busmanagementserver.model.City;
import com.ponkratov.busmanagementserver.payload.response.MessageResponse;
import com.ponkratov.busmanagementserver.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CityControllerImpl implements CityController {
    @Autowired
    CityRepository cityRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<City> cities = cityRepository.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<City> temp = cityRepository.findById(id);
        City tempCity;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этот город"), HttpStatus.NO_CONTENT);
        } else {
            tempCity = temp.get();
        }

        return new ResponseEntity<>(tempCity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        cityRepository.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Город удален успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> add(City city) {
        cityRepository.save(city);
        return new ResponseEntity<>(new MessageResponse("Город добавлен успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Long id, City city) {
        Optional<City> temp = cityRepository.findById(id);
        City tempCity;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этот город"), HttpStatus.NO_CONTENT);
        } else {
            tempCity = temp.get();
        }

        city.setCityId(id);

        cityRepository.save(city);
        return new ResponseEntity<>(new MessageResponse("Город обновлён успешно"), HttpStatus.OK);
    }
}
