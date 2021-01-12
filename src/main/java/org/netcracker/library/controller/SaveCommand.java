package org.netcracker.library.controller;

import org.netcracker.library.model.Library;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveCommand extends Command {

    public SaveCommand(Library library, String name, String key, String[] args) {
        super(library, name, key, args);
    }

    @Override
    public ExecutionResult execute() {
        ResultCode resultCode;

        try {
            saveToFile(args[0]);
        } catch (JAXBException e) {
            resultCode = ResultCode.UNEXPECTED_SAVE_ERROR;
            return new ExecutionResult(resultCode, name, key, args);
        }

        resultCode = ResultCode.SUCCESS;

        return new ExecutionResult(resultCode, name, key, args);
    }
    
    private ResultCode saveToFile(String path) throws JAXBException {
        File fileToSave;

        try {
            fileToSave = Paths.get(path).toFile();
        } catch (InvalidPathException e) {
            return ResultCode.INVALID_PATH;
        }

        JAXBContext context = JAXBContext.newInstance(Library.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.marshal(Library.getInstance(), fileToSave);

        return ResultCode.SUCCESS;
    }


}
