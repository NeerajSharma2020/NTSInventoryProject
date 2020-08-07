package com.st.ims.errorhandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class ProductNotFoundException extends RuntimeException implements GraphQLError{

	private Map<String, Object> extensions = new HashMap<>();

    public ProductNotFoundException(String message, int invalidProducttId) {
        super(message);
        extensions.put("invalidProductId", invalidProducttId);
    }
    
	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.DataFetchingException;
	}
	
	@Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

}
