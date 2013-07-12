package kz.enu.epam.azimkhan.auth.helper;

import kz.enu.epam.azimkhan.auth.command.ActionCommand;
import kz.enu.epam.azimkhan.auth.command.EmptyCommand;
import kz.enu.epam.azimkhan.auth.command.LoginCommand;
import kz.enu.epam.azimkhan.auth.command.LogoutCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Finds command
 */
public enum RequestHelper {

    INSTANCE;

    /**
     * Request parameter name for command
     */
    public static final String COMMAND_PARAMETER = "command";

    /**
     * action commands
     */
    private HashMap<String, ActionCommand> commands = new HashMap<String, ActionCommand>();
    {
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
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

        if (command == null){
            command = new EmptyCommand();
        }

        return command;
    }
}
