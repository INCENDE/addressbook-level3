package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.ui.Gui.DISPLAYED_INDEX_OFFSET;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected AddressBook addressBook;
    protected List<? extends ReadOnlyPerson> relevantPersons;
    private int targetIndex = -1;

    /**
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForPersonListShownSummary(List<? extends ReadOnlyPerson> personsDisplayed) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, personsDisplayed.size());
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute();

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
        this.relevantPersons = relevantPersons;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyPerson getTargetPerson() throws IndexOutOfBoundsException {
        return relevantPersons.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public abstract Command prepare(String commandWord, String args);

    public static Command getType(String commandWord) {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommand(null);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(0);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommand(null);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommand(0);

        case ViewAllCommand.COMMAND_WORD:
            return new ViewAllCommand(0);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }
}
