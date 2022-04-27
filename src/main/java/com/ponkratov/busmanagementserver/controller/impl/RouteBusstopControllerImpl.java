package com.ponkratov.busmanagementserver.controller.impl;

import com.ponkratov.busmanagementserver.controller.RouteBusstopController;
import com.ponkratov.busmanagementserver.model.Routebusstop;
import com.ponkratov.busmanagementserver.payload.response.MessageResponse;
import com.ponkratov.busmanagementserver.repository.RouteBusstopRepository;
import com.ponkratov.busmanagementserver.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RouteBusstopControllerImpl implements RouteBusstopController {
    @Autowired
    RouteBusstopRepository routeBusstopRepository;

    @Autowired
    RouteRepository routeRepository;

    @Override
    public ResponseEntity<?> getAllByRouteId(Long id) {
        List<Routebusstop> routebusstops = routeBusstopRepository.findAllByRouteByRouteId_RouteId(id);
        return new ResponseEntity<>(routebusstops, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAllByRouteId(Long id) {
        routeBusstopRepository.deleteAllByRouteByRouteId_RouteId(id);

        return new ResponseEntity<>(new MessageResponse("Остановочные пункты по маршруту успешно удалены"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Long id, List<Routebusstop> routebusstops) {
        routeBusstopRepository.deleteAllByRouteByRouteId_RouteId(id);
        List<Routebusstop> routebusstops1 = new ArrayList<>();
        for(Routebusstop routebusstop: routebusstops) {
            routebusstop.setRouteId(routebusstop.getRouteByRouteId().getRouteId());
            routebusstop.setStopId(routebusstop.getBusstopByStopId().getStopId());
            routebusstops1.add(routebusstop);
        }
        routeBusstopRepository.saveAll(routebusstops1);

        return new ResponseEntity<>(new MessageResponse("Маршрут успешно сохранен"), HttpStatus.OK);
    }
}
