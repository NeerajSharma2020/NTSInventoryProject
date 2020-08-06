package com.st.ims.errorhandler;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class GraphQLErrorAdapter implements GraphQLError{

	private GraphQLError error;

    public GraphQLErrorAdapter(GraphQLError error) {
        this.error = error;
    }

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return (error instanceof ExceptionWhileDataFetching) 
                ? ((ExceptionWhileDataFetching) error).getException().getMessage() : error.getMessage();
	}

	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return error.getLocations();
	}

	@Override
	public ErrorType getErrorType() {
		// TODO Auto-generated method stub
		return (ErrorType)error.getErrorType();
	}
	
	@Override
    public Map<String, Object> getExtensions() {
        return error.getExtensions();
    }
	
	@Override
    public List<Object> getPath() {
        return error.getPath();
    }
	
	@Override
    public Map<String, Object> toSpecification() {
        return error.toSpecification();
    }
    
}
