package io.javabrains.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.javabrains.coronavirustracker.CoronaVirusDataService;
import io.javabrains.coronavirustracker.models.LocationStats;

@Controller
public class HomeController {
    @Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String Home(Model model) {
		List<LocationStats>allStats=coronaVirusDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats",coronaVirusDataService.getAllStats());
		model.addAttribute("totalReportedCases",totalCases);
		model.addAttribute("totalNewCases",totalNewCases);
		

		return "home";
	}
	
	
	
}
