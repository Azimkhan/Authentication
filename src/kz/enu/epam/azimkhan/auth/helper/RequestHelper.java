package kz.enu.epam.azimkhan.auth.helper;

import com.sun.deploy.net.HttpRequest;
import kz.enu.epam.azimkhan.auth.command.ActionCommand;
import kz.enu.epam.azimkhan.auth.command.EmptyCommand;

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

    /**
     * Find command from request
     * @param request
     * @return
     */
    public ActionCommand getCommand(HttpServletRequest request){
        String action = request.getParameter(COMMAND_PARAMETER);
        ActionCommand command = commands.get(action);

        if (command == null){
            command = new EmptyCommand();
        }

        return command;
    }
}
