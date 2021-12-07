package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		List<City> list = cityRepository.findAllByOrderByNameAsc();	
		return list.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
	}

	@Transactional
	public CityDTO insert(CityDTO dto) {
		City city = dtoToEntity(dto);
		city = cityRepository.save(city);
		return new CityDTO(city);
	}

	private City dtoToEntity(CityDTO dto) {
		City city = new City();
		city.setId(null);
		city.setName(dto.getName());
		return city;
	}

}
