package org.netcracker.library.view;

import org.netcracker.library.controller.ExecutionResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OutputHandler {

    public OutputHandler() {

    }

    public void explainExecutionResult(ExecutionResult result){

        String outputMsg = "\n" + "Execution result: " + result.getResultCode().getDesc() +
                "\n" + "Command: " + result.getCommandName() +
                "\n" + "Key: " + result.getCommandKey() +
                "\n" + "Args: " + Arrays.toString(result.getCommandArgs());

        System.out.println(outputMsg);
    }
}
