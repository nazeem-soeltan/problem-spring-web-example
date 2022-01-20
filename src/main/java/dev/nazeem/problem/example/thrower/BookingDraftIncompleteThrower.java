package dev.nazeem.problem.example.thrower;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import dev.nazeem.problem.example.exceptions.BookingDraftIncompleteException;

@Component
public class BookingDraftIncompleteThrower implements Thrower {

    private static final List<String> DETAIL_MESSAGES = List.of("Missing commodity code", "Missing gross weight", "Missing packaging amount");

    @Override
    public void throwNow() {
        final int messageIndex = new Random().ints(0, DETAIL_MESSAGES.size())
                .findFirst()
                .getAsInt();

        throw new BookingDraftIncompleteException(DETAIL_MESSAGES.get(messageIndex));
    }

    @Override
    public String getKey() {
        return "booking-draft-incomplete";
    }
}
