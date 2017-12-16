package com.github.menubuilder;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.typemarkup.Responsibility;

@Responsibility("Создает на лету несуществующие метки на основе id метки")
public class DefaultIdLabelAsLabelResolver implements LabelResolver {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Logger notRegistredLabelsJavaCode = LoggerFactory.getLogger("notRegistredLabelsJavaCode");

	private final Map<String, String> idToLabel = new HashMap<>();

	@Override
	public String getLabel(String labelId) {
		if (!idToLabel.containsKey(labelId)) {
			logger.warn("labelId: {} is not registred", labelId);
			notRegistredLabelsJavaCode.info("put(\"" + labelId + "\", \"" + labelId + "\");");
			idToLabel.put(labelId, "#" + labelId);
		}
		return idToLabel.get(labelId);
	}

	public void put(String id, String label) {
		idToLabel.put(id, label);
	};

}
