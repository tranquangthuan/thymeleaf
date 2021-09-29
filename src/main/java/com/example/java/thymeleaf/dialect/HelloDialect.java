package com.example.java.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import com.example.java.thymeleaf.processor.SayToAttributeTagProcessor;
import com.example.java.thymeleaf.processor.SayToPlanetAttributeTagProcessor;

public class HelloDialect extends AbstractProcessorDialect {

	public HelloDialect() {
		super("Hello Dialect", "hello", 1000);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		// TODO Auto-generated method stub
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new SayToAttributeTagProcessor(dialectPrefix));
		processors.add(new SayToPlanetAttributeTagProcessor(dialectPrefix));
		return processors;
	}

}
