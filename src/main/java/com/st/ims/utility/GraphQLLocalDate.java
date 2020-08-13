package com.st.ims.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;


@Component
public class GraphQLLocalDate extends GraphQLScalarType{

	@SuppressWarnings("deprecation")
	public GraphQLLocalDate() {
		super("LocalDateTime", "LocalDateTime Type",new Coercing<LocalDateTime, String>() {

			@Override
			public String serialize(Object result) {
			  	if (result instanceof LocalDateTime) {
					return ((LocalDateTime)result).toString();
					}
				return null;
				
			}

			@Override
			public LocalDateTime parseValue(Object input) {
			    if (input instanceof String) {
			    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					return LocalDateTime.parse((String)input, formatter);
					
				}
				return null;
			}

			@Override
			public LocalDateTime parseLiteral(Object input) {
				if (!(input instanceof StringValue)) return null;
				return LocalDateTime.parse(((StringValue)input).getValue());
			}
		});

	}

}
