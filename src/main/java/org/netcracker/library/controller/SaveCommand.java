package org.netcracker.library.controller;

import org.netcracker.library.model.Code;
import org.netcracker.library.model.Library;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SaveCommand extends Command {
    protected SaveCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public MessageInformation execute() {
        try {
            saveToFile(args[0]);
        } catch (JAXBException e) {
            e.printStackTrace();
            return new MessageInformation(Code.SAVE_ERROR); //any bad code
        }

        return new MessageInformation(Code.SAVE_COMPLETE);
    }
    
    private MessageInformation saveToFile(String path) throws JAXBException {
        File file = new File(path);

        JAXBContext context = JAXBContext.newInstance(Library.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.marshal(Library.getInstance(), file);

        return new MessageInformation(Code.SAVE_COMPLETE);
    }
}
