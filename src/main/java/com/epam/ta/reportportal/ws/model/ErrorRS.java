/*
 * Copyright 2016 EPAM Systems
 * 
 * 
 * This file is part of EPAM Report Portal.
 * https://github.com/reportportal/commons-model
 * 
 * Report Portal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Report Portal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Report Portal.  If not, see <http://www.gnu.org/licenses/>.
 */ 

package com.epam.ta.reportportal.ws.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.io.Serializable;

/**
 * Base Error response body for all Report Portal exceptions
 * 
 * @author Andrei Varabyeu
 * 
 */
@JsonPropertyOrder({ "errorCode", "message", "stackTrace" })
@JsonInclude(Include.NON_NULL)
public class ErrorRS implements Serializable {
	/**
	 * Generated SVUID
	 */
	private static final long serialVersionUID = -3717290684860161862L;

	@JsonSerialize(using = ErrorTypeSerializer.class)
	@JsonDeserialize(using = ErrorTypeDeserializer.class)
	@JsonProperty("errorCode")
	private ErrorType errorType;

	@JsonProperty("stackTrace")
	private String stackTrace;

	@JsonProperty("message")
	private String message;

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorType == null) ? 0 : errorType.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((stackTrace == null) ? 0 : stackTrace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorRS other = (ErrorRS) obj;
		if (errorType != other.errorType)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (stackTrace == null) {
			if (other.stackTrace != null)
				return false;
		} else if (!stackTrace.equals(other.stackTrace))
			return false;
		return true;
	}

	private static class ErrorTypeDeserializer extends JsonDeserializer<ErrorType> {

		@Override
		public ErrorType deserialize(JsonParser parser, DeserializationContext context) throws IOException {
			ObjectCodec oc = parser.getCodec();
			JsonNode node = oc.readTree(parser);
			return ErrorType.getByCode(node.asInt());

		}

	}

	private static class ErrorTypeSerializer extends JsonSerializer<ErrorType> {

		@Override
		public void serialize(ErrorType error, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeNumber(error.getCode());
		}

	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ErrorRS{");
		sb.append("errorType=").append(errorType);
		sb.append(", stackTrace='").append(stackTrace).append('\'');
		sb.append(", message='").append(message).append('\'');
		sb.append('}');
		return sb.toString();
	}
}