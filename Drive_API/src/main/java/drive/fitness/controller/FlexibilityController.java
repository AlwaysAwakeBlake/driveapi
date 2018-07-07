package drive.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.FlexibilityDao;

@RestController
public class FlexibilityController {
	@Autowired
	private FlexibilityDao flexibilityDao;

}
