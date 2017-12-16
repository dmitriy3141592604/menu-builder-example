package com.github.menubuilder;

import javax.swing.JFrame;

import com.github.typemarkup.Responsibility;

@Responsibility("Отвечает за конфигуреирование и запуск приложения")
public class Window implements Runnable {

	public static void main(String... args) {
		new Window().run();
	}

	@Override
	public void run() {
		final JFrameAdapter fa = new JFrameAdapter(new JFrame());

		fa.width(300).height(200).center().exitOnClose();

		fa.menuBar(this::buildMenuBar);

		fa.commandRegistry(newCommandRegistry());
		fa.context(new ApplicationContext());

		final JFrame frame = fa.build();

		frame.setVisible(true);
	}

	private CommandRegistry<ApplicationContext> newCommandRegistry() {
		final CommandRegistry<ApplicationContext> cr = new CommandRegistry<ApplicationContext>();
		cr.registry(FileCommands.class);
		return cr;
	}

	private void buildMenuBar(JMenuBarBuilder<ApplicationContext> mbb) {
		mbb.menu("file", file -> {
			file.cmd("newFile");
			file.cmd("openFile");
			file.cmd("openProjectFromFileSystem");
			file.separator();
			file.cmd("close");
			file.cmd("closeAll");
			file.separator();
			file.cmd("exit");
		});
		mbb.menu("edit", edit -> {
			edit.cmd("cut");
			edit.cmd("paste");
			edit.menu("removing", removing -> {
				removing.cmd("delete");
			});
		});
		mbb.menu("source", source -> {
			source.cmd("format");
			source.cmd("togleComments");
		});
		mbb.menu("window", window -> {
			window.menu("showView", showView -> {
				showView.cmd("ant");
				showView.cmd("task");
				showView.separator();
			});
		});

	}
}
