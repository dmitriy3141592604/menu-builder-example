package com.github.menubuilder;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.github.typemarkup.Responsibility;

@Responsibility("Предоставляет набор общих функций для работы с меню приложения")
public abstract class JMenuBuilderBase<ContextType> {

	private final LabelResolver labelResolver;

	private final CommandRegistry<ContextType> commandRegistry;

	private final ContextType context;

	public JMenuBuilderBase(LabelResolver labelResolver, CommandRegistry<ContextType> commandRegistry, ContextType context) {
		this.labelResolver = labelResolver;
		this.commandRegistry = commandRegistry;
		this.context = context;
	}

	protected JMenuBuilder<ContextType> newJMenuBuilder(final JMenu newMenu) {
		return new JMenuBuilder<ContextType>(newMenu, labelResolver, commandRegistry, context);
	}

	protected JMenu newJMenu(String labelId) {
		final JMenu jMenu = new JMenu(labelFromId(labelId));
		return jMenu;
	}

	protected JMenuItem newJMenuItem(String cmdId) {
		final JMenuItem jMenuItem = new JMenuItem(labelFromId(cmdId));

		final Command<ContextType> command = commandRegistry.getCommand(cmdId);

		jMenuItem.addActionListener(event -> {
			command.execute(context);
		});
		return jMenuItem;
	}

	private String labelFromId(String labelId) {
		return labelResolver.getLabel(labelId);
	}

}
