package drive.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.HistoryDao;

@RestController
public class HistoryController {
	@Autowired
	private HistoryDao historyDao;

}
