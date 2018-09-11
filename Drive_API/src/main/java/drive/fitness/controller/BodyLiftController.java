package drive.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.BodyLiftDao;

@RestController
public class BodyLiftController {
	@Autowired
	private BodyLiftDao bodyLiftDao;

}