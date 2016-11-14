package com.whenling.bbs.website.dialect;

import java.util.Set;

import org.thymeleaf.dialect.IProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class BbsDialect implements IProcessorDialect {
	
	public static final String PREFIX = "bbs";
	public static final String NAME = "BbsDialect";
	public static final int PRECEDENCE = 1000;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getDialectProcessorPrecedence() {
		return PRECEDENCE;
	}

	@Override
	public String getPrefix() {
		return PREFIX;
	}

	@Override
	public Set<IProcessor> getProcessors(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
