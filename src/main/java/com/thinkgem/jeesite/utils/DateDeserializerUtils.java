package com.thinkgem.jeesite.utils;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateDeserializerUtils implements JsonDeserializer<java.util.Date>{

	@Override
	public Date deserialize(JsonElement json, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
	}

}
