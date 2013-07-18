package kz.enu.epam.azimkhan.auth.helper;

import kz.enu.epam.azimkhan.auth.command.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Finds command
 */
public enum RequestHelper {

    INSTANCE;

    private final Logger logger = Logger.getRootLogger();
    /**
     * Request parameter name for command
     */
    public static final String COMMAND_PARAMETER = "c";

    /**
     * action commands
     */
    private HashMap<String, ActionCommand> commands = new HashMap<String, ActionCommand>();
    {
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("change_locale", new ChangeLocaleCommand());
    }

    /**
     * Find command from request
     * @param request
     * @return
     */
    public ActionCommand getCommand(HttpServletRequest request){
        String action = request.getParameter(COMMAND_PARAMETER);
        return getCommand(action);
    }

    /**
     *  Find command by name
     * @param action
     * @return
     */
    public ActionCommand getCommand(String action){

        ActionCommand command = commands.get(action);

        logger.info("Command requested: "+ action);

        if (command == null){
            command = new EmptyCommand();
            logger.error("No such command, returning "+ command);
        } else {
            logger.info("Command found: " + command);
        }

        return command;
    }
}
