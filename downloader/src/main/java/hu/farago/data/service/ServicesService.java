package hu.farago.data.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hu.farago.data.model.dao.mongo.AutomaticServiceErrorRepository;
import hu.farago.data.model.entity.mongo.AutomaticServiceError;

@Controller
public class ServicesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServicesService.class);
	
	@Autowired
	private AutomaticServiceErrorRepository repo;
	
//	@RequestMapping(value = "/getErrors", method = RequestMethod.GET, produces = {
//			MediaType.APPLICATION_JSON_VALUE })
	public List<AutomaticServiceError> getErrors() {
		LOGGER.info("getErrors");
		return repo.findAll();
	}
	
}
