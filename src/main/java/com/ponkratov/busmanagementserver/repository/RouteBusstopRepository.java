package com.ponkratov.busmanagementserver.repository;

import com.ponkratov.busmanagementserver.model.Route;
import com.ponkratov.busmanagementserver.model.Routebusstop;
import com.ponkratov.busmanagementserver.model.RoutebusstopPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteBusstopRepository extends JpaRepository<Routebusstop, RoutebusstopPK> {
    List<Routebusstop> findAllByRouteByRouteId_RouteId(long routeByRouteId_routeId);
    long deleteAllByRouteByRouteId_RouteId(long routeByRouteId_routeId);
}
