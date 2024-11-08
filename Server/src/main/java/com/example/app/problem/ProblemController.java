package com.example.app.problem;

import java.util.List;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/problem")
public class ProblemController {
	public final ProblemService ps;
	
	@Autowired
	public ProblemController(ProblemService ps)
	{
		this.ps = ps;
	}
	
	@GetMapping("/unsolved")
	public List<Problem> getAllUnsolved()
	{
		return ps.getAllUnsolved();
	}
	
	@GetMapping("/all")
	public List<Problem> getAll()
	{
		return ps.getAll();
	}
	
	@PutMapping("/solve")
	public ResponseEntity<String> markAsSolved(@RequestParam Integer id)
	{
		ps.solve(id);
		return new ResponseEntity<>("Successfully marked a problem!", HttpStatus.OK);
	}
	
	@PutMapping("/unsolve")
	public ResponseEntity<String> markAsUnSolved(@RequestParam Integer id)
	{
		ps.unsolve(id);
		return new ResponseEntity<>("Successfully unmarked a problem!", HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addNewProblem(@RequestBody Problem problem)
	{
		ps.addProblem(problem);
		return new ResponseEntity<>("Successfully created a problem!", HttpStatus.OK);
	}
	
}
