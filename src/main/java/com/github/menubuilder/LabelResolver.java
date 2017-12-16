package com.github.menubuilder;

import com.github.typemarkup.Responsibility;

@Responsibility("Предоставляет контракт досупа к метке элемента по id метки")
public interface LabelResolver {

	String getLabel(String labelId);
}
