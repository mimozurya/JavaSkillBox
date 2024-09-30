package org.example;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.domain.Command;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    public String executeCommand(String inputData){
        if (inputData.isEmpty()) {
            return "Invalid command";
        }
        String[] params = inputData.split(" ",2);
        Command command = null;
        Command[] commands = Command.values();
        for(Command comm:commands){
            if (params[0].equals(comm.getCommand())){
                command = comm;
            }
        }


        assert command != null;
        return switch (command) {
            case ADD -> userService.add(params[1].trim());
            case DELETE -> userService.delete(params[1].trim());
            case GET -> userService.getAll();
            case HELP -> userService.help();
            default -> "Invalid command";
        };
    }
}

