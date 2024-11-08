package com.example.app.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
	
	private final ConfigurationRepository rp;
	
	@Autowired
	public ConfigurationService(ConfigurationRepository rp)
	{
		this.rp=rp;
	}

	public Configuration add(Configuration conf)
	{
		return rp.save(conf);
	}
	
	public List<Configuration> getByIdUser(Integer idUser)
	{
		return rp.findByidUser(idUser);
	}

	public void deleteConfiguration(Integer idConfiguration) {
		
		Configuration conf=rp.findById(idConfiguration).get();
		conf.setDeleted(true);
		rp.save(conf);
	}
	
}
