package com.company.U1M4_city_activity.controller;

import com.company.U1M4_city_activity.model.City;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class CityController {
    //create City list
    List<City> cityList = new ArrayList<>();

    /*
    Add a city
    URI: /city
    Method: POST
    Request Body: City
    Response Body: City
     */
    @RequestMapping(value = "/city", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public City addCity (@RequestBody @Valid City city){
        cityList.add(city);
        return city;
    }


    /*
    Delete a city
    URI: /city/{name}
    Method: DELETE
    Request body: none
    Response body: none
     */

    @RequestMapping(value = "/city/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable @Valid String name){
        if (name == null) {
            throw new IllegalArgumentException("Please enter a city name");
        }
        cityList.removeIf(c -> c.equals(name));
    }

    /*
    Retrieve all the cities
    URI: /city
    Method: GET
    Request body: none
    Response body: city list
     */

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<City> retreiveAll (){
        return cityList;
    }

    /*
    Retrieve one city by name
    URI: /city/{name}
    Method: GET
    Request body: none
    Response body: City
     */
    @RequestMapping(value = "/city/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public City retrieveCity (@PathVariable @Valid String name){
        if (name == null) {
            throw new IllegalArgumentException("Please enter a city name");
        }
        return cityList
                .stream()
                .filter(city -> city.getName().equals(name))
                .findAny()
                .get();

    }
}
