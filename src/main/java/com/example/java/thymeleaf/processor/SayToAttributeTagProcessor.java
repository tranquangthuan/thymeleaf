package com.example.java.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

public class SayToAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "sayto";
	private static final int PRECEDENCE = 10000;

	public SayToAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		// TODO Auto-generated method stub
		structureHandler.setBody("Server Hello, " + HtmlEscape.escapeHtml5(attributeValue) + " !", false);
	}
}
