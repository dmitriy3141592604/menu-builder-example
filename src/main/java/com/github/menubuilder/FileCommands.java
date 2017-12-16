package com.github.menubuilder;

import com.github.typemarkup.Responsibility;

// TODO Нужно указать категорию комманд или их классификациАЫ
@Responsibility("Группирует команды для категории File")
public class FileCommands<ContextType> {

	Command<ContextType> exit = ctx -> {
		System.exit(0);
	};

	Command<ContextType> newFile = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> openFile = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> openProjectFromFileSystem = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> close = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> closeAll = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> cut = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> paste = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> delete = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> format = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> togleComments = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> ant = ctx -> {
		System.out.println("not implemented command");
	};

	Command<ContextType> task = ctx -> {
		System.out.println("not implemented command");
	};

}
