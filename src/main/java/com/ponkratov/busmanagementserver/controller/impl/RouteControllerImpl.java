package com.ponkratov.busmanagementserver.controller.impl;

import com.ponkratov.busmanagementserver.controller.RouteController;
import com.ponkratov.busmanagementserver.model.Route;
import com.ponkratov.busmanagementserver.payload.response.MessageResponse;
import com.ponkratov.busmanagementserver.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RouteControllerImpl implements RouteController {
    @Autowired
    RouteRepository routeRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<Route> cities = routeRepository.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Route> temp = routeRepository.findById(id);
        Route tempRoute;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этот маршрут"), HttpStatus.NO_CONTENT);
        } else {
            tempRoute = temp.get();
        }

        return new ResponseEntity<>(tempRoute, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        routeRepository.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Маршрут удален успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> add(Route route) {
        routeRepository.save(route);
        return new ResponseEntity<>(new MessageResponse("Маршрут создан успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Long id, Route route) {
        //проверка на то, что ид уже занят
        Optional<Route> temp = routeRepository.findById(id);
        Route tempRoute;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этот маршрут"), HttpStatus.NO_CONTENT);
        } else {
            tempRoute = temp.get();
        }

        route.setRouteId(id);

        routeRepository.save(route);
        return new ResponseEntity<>(new MessageResponse("Маршрут обновлён успешно"), HttpStatus.OK);
    }
}
