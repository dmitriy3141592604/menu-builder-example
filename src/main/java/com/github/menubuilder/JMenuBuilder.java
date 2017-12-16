package com.github.menubuilder;

import java.util.function.Consumer;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.github.typemarkup.Responsibility;

@Responsibility("Предоставляет вытягивающий интерфейс для меню")
public class JMenuBuilder<ContextType> extends JMenuBuilderBase<ContextType> {

	private final JMenu menu;

	public JMenuBuilder(JMenu menu, LabelResolver labelResolver, CommandRegistry<ContextType> commandRegistry, ContextType context) {
		super(labelResolver, commandRegistry, context);
		this.menu = menu;
	}

	public JMenuBuilder<ContextType> menu(String labelId, Consumer<JMenuBuilder<ContextType>> menuBuilderCallback) {
		final JMenu newMenu = newJMenu(labelId);
		menu.add(newMenu);
		menuBuilderCallback.accept(newJMenuBuilder(newMenu));
		return this;
	}

	public JMenuBuilder<ContextType> cmd(String cmdId) {
		final JMenuItem item = newJMenuItem(cmdId);
		menu.add(item);
		return this;
	}

	public JMenuBuilder<ContextType> separator() {
		menu.addSeparator();
		return this;
	}
}