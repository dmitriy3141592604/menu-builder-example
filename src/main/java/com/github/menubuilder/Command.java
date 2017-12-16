package com.github.menubuilder;

import com.github.typemarkup.Responsibility;

@Responsibility("Инкапсулирует команду, выполняемую в контексте приложения")
public interface Command<ContextType> {

	void execute(ContextType ctx);

}
