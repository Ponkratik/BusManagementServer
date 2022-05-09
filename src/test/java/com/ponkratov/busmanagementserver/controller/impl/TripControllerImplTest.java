package com.ponkratov.busmanagementserver.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ponkratov.busmanagementserver.repository.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TripControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusStopRepository busStopRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RouteBusstopRepository routeBusstopRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Test()
    @Order(1)
    void getAll() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/trip/get/all").contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Test
    @Order(2)
    void getById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/trip/get/1").contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.tripId").isNumber())
                .andExpect(jsonPath("$.depTime").exists())
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(jsonPath("$.busByBusId").exists())
                .andExpect(jsonPath("$.routeByRouteId").exists())
                .andExpect(jsonPath("$.userByUserId").exists())
                .andReturn();
    }

    @Test
    @Order(3)
    void getByDriver() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/trip/get/driver/1").contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Test()
    @Order(4)
    void add() throws Exception {
        String body = "{\"tripId\":0,\"depTime\":\"2022-05-15T18:00:00\",\"price\":22.0,\"routeByRouteId\":{\"routeId\":620,\"routeName\":\"Минск-Витебск\"},\"userByUserId\":{\"userId\":1,\"login\":\"ponkratov\",\"password\":\"$2a$10$QunRQlqI1QY.f1JDAdFHvexwCjHbni4pD3pVMNb5ksdYWYIwZpXua\",\"email\":\"ponkratov@tuttrans.by\",\"roleId\":1,\"lastName\":\"Понкратов\",\"firstName\":\"Алексей\",\"surName\":\"Михайлович\",\"phone\":\"+375298993407\",\"blocked\":false,\"roleByRoleId\":{\"roleId\":1,\"roleName\":\"ROLE_SYSADMIN\"}},\"busByBusId\":{\"busId\":2,\"busModel\":\"Mercedes Sprinter\",\"number\":\"AM3333-2\",\"seatsQty\":21,\"vin\":\"W1W4ECHY5MT077826\"}};";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/trip/add").content(body).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(5)
    void update() throws Exception {
        String body = "{\"tripId\":4,\"depTime\":\"2022-05-15T19:00:00\",\"price\":20.0,\"routeByRouteId\":{\"routeId\":620,\"routeName\":\"Минск-Витебск\"},\"userByUserId\":{\"userId\":1,\"login\":\"ponkratov\",\"password\":\"$2a$10$QunRQlqI1QY.f1JDAdFHvexwCjHbni4pD3pVMNb5ksdYWYIwZpXua\",\"email\":\"ponkratov@tuttrans.by\",\"roleId\":1,\"lastName\":\"Понкратов\",\"firstName\":\"Алексей\",\"surName\":\"Михайлович\",\"phone\":\"+375298993407\",\"blocked\":false,\"roleByRoleId\":{\"roleId\":1,\"roleName\":\"ROLE_SYSADMIN\"}},\"busByBusId\":{\"busId\":2,\"busModel\":\"Mercedes Sprinter\",\"number\":\"AM3333-2\",\"seatsQty\":21,\"vin\":\"W1W4ECHY5MT077826\"}};";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/trip/update/4").content(body).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(6)
    void delete() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/trip/delete/4").contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
    }
}