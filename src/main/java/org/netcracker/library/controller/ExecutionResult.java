package org.netcracker.library.controller;

public class ExecutionResult {

    private ResultCode resultCode;
    private String commandName;
    private String commandKey;
    private String[] commandArgs;

    public ExecutionResult(ResultCode resultCode, String commandName, String commandKey, String[] commandArgs) {
        this.resultCode = resultCode;
        this.commandName = commandName;
        this.commandKey = commandKey;
        this.commandArgs = commandArgs;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandKey() {
        return commandKey;
    }

    public void setCommandKey(String commandKey) {
        this.commandKey = commandKey;
    }

    public String[] getCommandArgs() {
        return commandArgs;
    }

    public void setCommandArgs(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }
}
