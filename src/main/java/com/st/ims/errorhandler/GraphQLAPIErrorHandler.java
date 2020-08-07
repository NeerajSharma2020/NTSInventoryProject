package com.st.ims.errorhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;

@Component
public class GraphQLAPIErrorHandler implements GraphQLErrorHandler{

	private final Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public List<GraphQLError> processErrors(List<GraphQLError> errors) {
		List<GraphQLError> clientErrors = errors.stream()
                .filter(this::isClientError)
                .collect(Collectors.toList());
       log.info("size of clientErrors List "+clientErrors.size());
        List<GraphQLError> serverErrors = errors.stream()
                .filter(e -> !isClientError(e))
                .map(GraphQLErrorAdapter::new)
                .collect(Collectors.toList());
        log.info("size of serverErrors List "+serverErrors.size());
        List<GraphQLError> e = new ArrayList<>();
        e.addAll(clientErrors);
        e.addAll(serverErrors);
        return e;
	}
	protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
	    return errors.stream()
	            .filter(this::isClientError)
	            .collect(Collectors.toList());
	}

	protected boolean isClientError(GraphQLError error) {
	    return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
	}

}


