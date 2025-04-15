package fr.ludovicanselin.immopredict.core.domain;

public class EmailAlreadyUsedException extends DomainException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
