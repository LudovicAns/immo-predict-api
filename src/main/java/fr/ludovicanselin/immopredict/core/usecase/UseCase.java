package fr.ludovicanselin.immopredict.core.usecase;

/**
 * Represents an abstract base class for creating use cases in an application.
 * A use case is a specific business logic operation that takes input, processes it,
 * and produces output. This class works as a template, dictating that subclasses
 * define their specific input and output types and provide an implementation for
 * the execution logic.
 *
 * @param <I> the type of input required, must extend {@link UseCase.InputValues}
 * @param <O> the type of output produced, must extend {@link UseCase.OutputValues}
 */
public abstract class UseCase<I extends UseCase.InputValues, O extends UseCase.OutputValues> {

    /**
     * Executes the use case with the provided input and returns the output.
     *
     * @param input the input required to execute the use case, must implement {@link UseCase.InputValues}
     * @return the result of executing the use case, must implement {@link UseCase.OutputValues}
     */
    public abstract O execute(I input);

    public interface InputValues {}
    public interface OutputValues {}

}
