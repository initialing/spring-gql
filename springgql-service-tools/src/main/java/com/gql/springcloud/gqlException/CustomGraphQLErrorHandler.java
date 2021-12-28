package com.gql.springcloud.gqlException;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import graphql.validation.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> graphQLErrors) {
        return graphQLErrors.stream().map(this::handleGraphQLError).collect(Collectors.toList());
    }

    private GraphQLError handleGraphQLError(GraphQLError error) {
        if (error instanceof GraphQLException) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "GraphQLException as GraphQLError...", (GraphQLException) error);
        } else if (error instanceof ValidationError){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ValidationError: " + error.getMessage());
        } else if (error instanceof ExceptionWhileDataFetching) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
        } else {
            return error;
        }
    }
}
