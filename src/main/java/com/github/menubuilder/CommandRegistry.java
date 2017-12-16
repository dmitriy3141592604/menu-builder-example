package com.github.menubuilder;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.typemarkup.Responsibility;

@Responsibility("Предоставляет механизм доступа к командам из разных библиотек")
public class CommandRegistry<ContextType> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Logger commandLogger = LoggerFactory.getLogger("notExistsCommandJavaCode");

	private final Map<String, Command<ContextType>> commands = new HashMap<>();

	public CommandRegistry<ContextType> registry(Class<?> commandLibraryClass) {
		final Field[] fields = commandLibraryClass.getDeclaredFields();
		try {
			final Object commandLibrary = commandLibraryClass.newInstance();
			for (final Field field : fields) {
				final String commandId = field.getName();
				final Class<?> type = field.getType();
				if (type.isAssignableFrom(Command.class)) {
					field.setAccessible(true);
					final Object commandCandidate = field.get(commandLibrary);
					if (!(commandCandidate instanceof Command)) {
						throw new IllegalStateException();
					}
					@SuppressWarnings("unchecked")
					final Command<ContextType> commandFromLibrary = (Command<ContextType>) commandCandidate;
					final Command<ContextType> previousCommand = commands.put(commandId, commandFromLibrary);

					if (previousCommand != null) {
						logger.warn("151342073: Redifine command {} from library {}", commandId, commandLibraryClass);
					}
					logger.debug("151342071: Command {} registered", commandId);
				} else {
					logger.debug("151342076: Ignore field: {}", commandId);
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	public Command<ContextType> getCommand(String cmdId) {
		if (!commands.containsKey(cmdId)) {
			logger.error("151342159: Not registred command {} requested", cmdId);
			commands.put(cmdId, ctx -> {
				logger.error("151342162: Not registred command {} called", cmdId);
			});
			commandLogger
					.debug("Command " + cmdId + " = ctx -> {\r\n" + "\tSystem.out.println(\"not implemented command: " + cmdId + "\");\r\n" + "	};");
		}
		return commands.get(cmdId);
	}

}
