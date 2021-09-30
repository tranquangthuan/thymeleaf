package com.example.java.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IOpenElementTag;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.example.java.thymeleaf.utils.UtilString;

public class PagingElementTagProcessor extends AbstractElementTagProcessor {

	private static final String TAG_NAME = "paging";
	private static final int PRECEDENCE = 10000;
	private static final int SHOWPAGES = 5;

	public PagingElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	public void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		final int page = UtilString.parseInt(tag.getAttributeValue("page"), 1);
		final int rows = UtilString.parseInt(tag.getAttributeValue("rows"), 10);
		final int total = UtilString.parseInt(tag.getAttributeValue("total"), 10);
		final int totPage = total / rows + (total > total / rows * rows ? 1 : 0);

		int minPage = (page / SHOWPAGES) * SHOWPAGES + 1;
		if (minPage > page) {
			minPage = ((page - 1) / SHOWPAGES) * SHOWPAGES + 1;
		}
		int maxPage = minPage + SHOWPAGES;
		if (maxPage > totPage) {
			maxPage = totPage + 1;
		}

		final IModelFactory modelFactory = context.getModelFactory();

		final IModel model = modelFactory.createModel();
		IOpenElementTag firstDiv = modelFactory.createOpenElementTag("div", "class", "w3-center w3-padding-32");
		firstDiv = modelFactory.setAttribute(firstDiv, "style", "text-align: center");
		model.add(firstDiv);
		model.add(modelFactory.createOpenElementTag("div", "class", "w3-bar"));
		if (minPage > SHOWPAGES) {
			IOpenElementTag aTag = modelFactory.createOpenElementTag("a", "style", "w3-button w3-hover-black");
			aTag = modelFactory.setAttribute(aTag, "href", String.format("?page=%d", minPage - 1));
			model.add(aTag);
			model.add(modelFactory.createText("◀"));
			model.add(modelFactory.createCloseElementTag("a"));
		}
		for (int i = minPage; i < maxPage; i++) {
			IOpenElementTag aTag = modelFactory.createOpenElementTag("a");
			aTag = modelFactory.setAttribute(aTag, "style", "margin:10px");
			if (i == page) {
				aTag = modelFactory.setAttribute(aTag, "style", "background-color:blue; margin:10px");
			} else {
				aTag = modelFactory.setAttribute(aTag, "class", "w3-button w3-hover-black");
			}
			aTag = modelFactory.setAttribute(aTag, "href", String.format("?page=%d", i));
			model.add(aTag);
			model.add(modelFactory.createText("" + i));
			model.add(modelFactory.createCloseElementTag("a"));
		}
		if (maxPage < totPage) {
			IOpenElementTag aTag = modelFactory.createOpenElementTag("a", "class", "w3-button w3-hover-black");
			aTag = modelFactory.setAttribute(aTag, "href", String.format("?page=%d", maxPage));
			model.add(aTag);
			model.add(modelFactory.createText("▶"));
			model.add(modelFactory.createCloseElementTag("a"));
		}
		model.add(modelFactory.createCloseElementTag("div"));
		model.add(modelFactory.createCloseElementTag("div"));

		structureHandler.replaceWith(model, false);
	}

}
