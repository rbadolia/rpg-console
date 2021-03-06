package in.ramakant.rpg.common.utils;

import in.ramakant.rpg.common.exceptions.UserInputParseException;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import static in.ramakant.rpg.common.utils.RandomIntegerUtils.isNotBetweenZeroAndMaxExclusive;

public class InputParser {
    private static final int MAX_RETRY = 5;

    private final OutputWriter outputWriter;
    private final Scanner scanner;

    public InputParser(OutputWriter outputWriter, InputStream source) {
        this.outputWriter = outputWriter;
        this.scanner = new Scanner(source);
    }

    public String readUserInputAsString(String message) {
        outputWriter.showMessage(message);
        return readUserInputAsString();
    }

    public String readUserInputAsString() {
        return scanner.nextLine();
    }

    public int tryReadingMenuChoice(int optionsSize) {
        return tryReadingInputAsInt(optionsSize + 1) - 1;
    }

    public int tryReadingInputAsInt(String message) {
        outputWriter.showMessage(message);
        return tryReadingInputAsInt(Integer.MAX_VALUE);
    }

    private int tryReadingInputAsInt(int optionsSize) {
        for (int i = 1; i <= MAX_RETRY; i++) {
            try {
                return readInt(optionsSize);
            } catch (Exception e) {
                if (MAX_RETRY == i) {
                    throw e;
                } else {
                    outputWriter.showMessage(e.getMessage());
                }
            }
        }

        return -1;
    }

    private int readInt(int optionsSize) {
        try {
            int result = Integer.parseInt(scanner.nextLine());
            if (isNotBetweenZeroAndMaxExclusive(result, optionsSize)) {
                throw new UserInputParseException("Sorry, but command number is either below 0 or too high. Please try again.");
            } else {
                return result;
            }
        } catch (NumberFormatException | InputMismatchException e) {
            throw new UserInputParseException("Sorry, but command number you entered is not even a number. Please try harder next time.");
        }
    }

}
