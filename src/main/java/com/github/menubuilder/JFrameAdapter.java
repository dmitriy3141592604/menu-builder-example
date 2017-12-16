package com.github.menubuilder;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.github.typemarkup.Responsibility;

@Responsibility("Предоставляет вытягивающий интерфейс для настройки JFrame")
public class JFrameAdapter {

	private Integer defaultCloseOperation;

	private Integer width;

	private Integer height;

	private String windowAlign;

	private final JFrame frame;

	private Consumer<JMenuBarBuilder<ApplicationContext>> menuBarBuilder;

	private CommandRegistry<ApplicationContext> commandRegistry;

	private ApplicationContext context;

	public JFrameAdapter(JFrame frame) {
		this.frame = frame;
	}

	public JFrame build() {
		if ("center".equals(windowAlign)) {
			final Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setBounds(ss.width / 2 - width / 2, ss.height / 2 - height / 2, width, height);
		}
		{
			frame.setDefaultCloseOperation(defaultCloseOperation);
		}
		if (menuBarBuilder != null) {
			final JMenuBar menuBar = new JMenuBar();
			frame.setJMenuBar(menuBar);
			if (commandRegistry == null) {
				throw new IllegalStateException();
			}
			// TODO Проверить на null
			menuBarBuilder.accept(new JMenuBarBuilder<ApplicationContext>(menuBar, new WindowLabelResolver(), commandRegistry, context));
		}
		return frame;
	}

	public JFrameAdapter commandRegistry(CommandRegistry<ApplicationContext> commandRegistry) {
		this.commandRegistry = commandRegistry;
		return this;
	}

	public JFrameAdapter width(int width) {
		this.width = width;
		return this;
	}

	public JFrameAdapter height(int height) {
		this.height = height;
		return this;
	}

	public JFrameAdapter center() {
		this.windowAlign = "center";
		return this;
	}

	public JFrameAdapter exitOnClose() {
		this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
		return this;
	}

	public JFrameAdapter menuBar(Consumer<JMenuBarBuilder<ApplicationContext>> menuBarBuilder) {
		this.menuBarBuilder = menuBarBuilder;
		return this;
	}

	public JFrameAdapter context(ApplicationContext applicationContext) {
		this.context = applicationContext;
		return this;
	}

}
