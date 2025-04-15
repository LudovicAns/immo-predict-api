package fr.ludovicanselin.immopredict.core.usecase;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * An interface designed to handle the asynchronous execution of use cases in the application.
 * The UseCaseExecutor provides a mechanism to execute a specific use case, process its input,
 * and map its output to a desired result type using a mapping function.
 *
 * This abstraction facilitates decoupling the execution logic from the rest of the application,
 * promoting testing, reusability, and separation of concerns. Implementors of this interface
 * can provide specific execution strategies tailored to the application's needs.
 */
public interface UseCaseExecutor {

    /**
     * Executes a given use case with the specified input and maps the output using a provided mapping function.
     *
     * @param <RX> the type of the result returned after mapping the output
     * @param <I> the type of the input required by the use case, must extend {@link UseCase.InputValues}
     * @param <O> the type of the output returned by the use case, must extend {@link UseCase.OutputValues}
     * @param useCase the use case to be executed
     * @param input the input to execute the given use case
     * @param outputMapper the function to map the output of the use case execution into the desired result type
     * @return a {@link CompletableFuture} that resolves to the mapped result of the executed use case
     */
    <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> CompletableFuture<RX> execute(
            UseCase<I, O> useCase,
            I input,
            Function<O, RX> outputMapper
    );

}
