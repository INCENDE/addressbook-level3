package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

public class StorageStub extends Storage {

    public StorageStub(String filePat) {
    }
    
    @Override
    public AddressBook load() throws StorageOperationException {
        return null;
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
    }

}
