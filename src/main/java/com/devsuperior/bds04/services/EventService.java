package com.devsuperior.bds04.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAllPaged(Pageable pageable){
		Page<Event> page = eventRepository.findAll(pageable);
		
		return page.map(event -> new EventDTO(event));
	}

	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event event = dtoToEntity(dto);
		event = eventRepository.save(event);
		return new EventDTO(event);
	}

	private Event dtoToEntity(EventDTO dto) {
		Event event = new Event();
		event.setId(null);
		event.setName(dto.getName());
		event.setDate(dto.getDate());
		event.setUrl(dto.getUrl());
		
		Optional<City> city = cityRepository.findById(dto.getCityId());
		event.setCity(city.get());
		
		return event;
	}

}
