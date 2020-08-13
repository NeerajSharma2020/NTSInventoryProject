package com.st.ims.errorhandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException implements GraphQLError{

	private final transient Map<String, Object> extensions = new HashMap<>();
	private final List<SourceLocation> sourceLocation = new ArrayList<>();

    public ProductNotFoundException(String message, int invalidProducttId) {
        super(message);
        extensions.put("invalidProductId", invalidProducttId);
    }
    
	@Override
	public List<SourceLocation> getLocations() {
		return sourceLocation;
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
