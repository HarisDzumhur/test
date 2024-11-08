package com.example.app.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;

@Service
public class ProblemService {
	private final ProblemRepository pr;
	private final RegisteredClientRepository rpClient;
	
	@Autowired
	public ProblemService(ProblemRepository pr,RegisteredClientRepository rpClient)
	{
		this.pr = pr;
		this.rpClient=rpClient;
	}
	
	public List<Problem> getAllUnsolved()
	{
		List<Problem> problems=pr.findBySolved(false);
		return checkClient(problems);
	}

	public List<Problem> getAll() 
	{
		List<Problem> problems=pr.findAll();
		return checkClient(problems);
	}
	
	private List<Problem> checkClient(List<Problem> problems)
	{
		List<Problem> copy=new ArrayList<Problem>();
		
		for(Problem problem:problems)
		{
			RegisteredClient client=rpClient.findById(problem.getIdClient()).get();
			if(client.getBlocked()==false && client.getDeleted()==false) {
				problem.setUserName(client.getName());
				problem.setUserSurname(client.getSurname());
				copy.add(problem);
			}
		}
		return copy;
	}
	public void solve(Integer id)
	{
		Optional<Problem> problem = pr.findById(id);
		Problem prob = problem.get();
		prob.setSolved(true);
		pr.save(prob);
	}
	
	public void addProblem(Problem p)
	{
		pr.save(p);
	}

	public void unsolve(Integer id) {
		Optional<Problem> problem = pr.findById(id);
		Problem prob = problem.get();
		prob.setSolved(false);
		pr.save(prob);
		
	}
	
}
