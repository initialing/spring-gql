package com.gql.springcloud.gqlException;

import graphql.ExecutionResult;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionContext;
import graphql.execution.ExecutionStrategyParameters;
import graphql.execution.NonNullableFieldWasNullException;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class QueryExecutionStrategy extends AsyncExecutionStrategy {
    public QueryExecutionStrategy() {
        super(new GraphQLExceptionHandler());
    }

    @Override
    public CompletableFuture<ExecutionResult> execute(ExecutionContext executionContext,
                                                      ExecutionStrategyParameters parameters)
            throws NonNullableFieldWasNullException {
        System.out.print("error");
        return super.execute(executionContext, parameters);
    }
}