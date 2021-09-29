package com.example.java.thymeleaf.processor;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SayToPlanetAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "saytoplanet";
	private static final int PRECEDENCE = 10000;
	private static final String SAYTO_PLANET_MESSAGE = "msg.helloplanet";

	public SayToPlanetAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		final IEngineConfiguration configuration = context.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);

		final String planet = (String) expression.execute(context);
		log.info(attributeName);

		// structureHandler.setBody("Hello, planet, " + HtmlEscape.escapeHtml5(planet) +
		// " !", false);
		final String i18nMessage = context.getMessage(SayToPlanetAttributeTagProcessor.class, SAYTO_PLANET_MESSAGE,
				new Object[] { planet }, true);

		/*
		 * Set the computed message as the body of the tag, HTML-escaped and
		 * non-processable (hence the 'false' argument)
		 */
		structureHandler.setBody(HtmlEscape.escapeHtml5(i18nMessage), false);
	}
}
