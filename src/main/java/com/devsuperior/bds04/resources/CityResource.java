package com.devsuperior.bds04.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityResource {
	
	@Autowired
	private CityService cityService;
	

	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(){
		List<CityDTO> list = cityService.findAll();
		return ResponseEntity.ok().body(list);
	}

}
