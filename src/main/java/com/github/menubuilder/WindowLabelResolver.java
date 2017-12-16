package com.github.menubuilder;

import com.github.typemarkup.Responsibility;

@Responsibility("Отвечает за преобразование id метки в ее текстовое представление")
public class WindowLabelResolver extends DefaultIdLabelAsLabelResolver {

	{
		put("file", "File");
		put("newFile", "newFile");
		put("openFile", "Open file");
		put("openProjectFromFileSystem", "Open project from file system");
		put("close", "Close");
		put("closeAll", "Close All");
		put("exit", "Exit");
		put("edit", "Edit");
		put("cut", "Cut");
		put("paste", "Paste");
		put("removing", "Removing");
		put("delete", "Delete");
		put("source", "Source");
		put("format", "Format");
		put("togleComments", "Togle Comments");
		put("window", "Window");
		put("showView", "Show View");
		put("ant", "Ant");
		put("task", "Task");
	}

}
