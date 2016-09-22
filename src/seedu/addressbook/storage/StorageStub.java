package seedu.addressbook.storage;

import java.nio.file.Paths;

import seedu.addressbook.data.AddressBook;

public class StorageStub extends Storage {

    public StorageStub() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }
    
    public StorageStub(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    @Override
    public AddressBook load() throws StorageOperationException {

            final AddressBook empty = new AddressBook();
            save(empty);
            return empty;
    }
    @Override
    public void save(AddressBook addressBook){
    }

}
