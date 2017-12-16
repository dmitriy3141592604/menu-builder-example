package com.github.menubuilder;

import java.util.function.Consumer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.github.typemarkup.Responsibility;

@Responsibility("Предоставляет вытягивающий интерфейс для панели меню")
public class JMenuBarBuilder<ContextType> extends JMenuBuilderBase<ContextType> {

	private final JMenuBar menuBar;

	public JMenuBarBuilder(JMenuBar menuBar, LabelResolver labelResolver, CommandRegistry<ContextType> commandRegistry, ContextType context) {
		super(labelResolver, commandRegistry, context);
		this.menuBar = menuBar;

	}

	public JMenuBarBuilder<ContextType> menu(String string, Consumer<JMenuBuilder<ContextType>> menuBuilderCallback) {
		final JMenu newMenu = newJMenu(string);
		menuBar.add(newMenu);
		menuBuilderCallback.accept(newJMenuBuilder(newMenu));
		return this;
	}

}
