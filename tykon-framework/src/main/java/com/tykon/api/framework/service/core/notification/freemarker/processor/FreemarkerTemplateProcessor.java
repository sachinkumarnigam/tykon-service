/**
 * 
 */
package com.tykon.api.framework.service.core.notification.freemarker.processor;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author sachin
 *
 */
@Service
public class FreemarkerTemplateProcessor {

	protected final Logger logger = LoggerFactory.getLogger(FreemarkerTemplateProcessor.class);

	@Autowired
	private Configuration configuration;
	
	@PostConstruct
	public void init() {
//		FileTemplateLoader ftl1 = new FileTemplateLoader(new File("/tmp/templates"));
//		FileTemplateLoader ftl2 = new FileTemplateLoader(new File("/usr/data/templates"));
		ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), "/");
		TemplateLoader[] loaders = new TemplateLoader[] {ctl};//{ ftl1, ftl2, ctl };
		MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);

		configuration.setTemplateLoader(mtl);
	}

	public String processTemplate(Map<String, Object> dataModel, String templateName) {
		/* Merge data model with template */
		Writer out = new StringWriter();
		try {
			Template template = configuration.getTemplate(templateName);
			template.process(dataModel, out);
		} catch (IOException e) {
			logger.error("Error processing notification template: "+templateName, e);
		} catch (TemplateException e) {
			logger.error("Error processing notification template: "+templateName, e);
		}

		return out.toString();
	}
}
