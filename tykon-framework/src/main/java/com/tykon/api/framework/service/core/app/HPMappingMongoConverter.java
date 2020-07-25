package com.tykon.api.framework.service.core.app;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;

public class HPMappingMongoConverter extends MappingMongoConverter {

	protected final MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContextCopy;

	@Value("${mongo.nested.type}")
	private String[] typeClasses;

	public HPMappingMongoConverter(DbRefResolver dbRefResolver,
			MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext) {
		super(dbRefResolver, mappingContext);
		mappingContextCopy = mappingContext;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void write(Object obj, Bson bson) {
		super.write(obj, bson);
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.get(obj) instanceof List<?>) {
					List<?> nestedObjects = (List<?>) field.get(obj);
					int index = 0;
					for (Object nestedObject : nestedObjects) {
						if (nestedObject != null && nestedObject.getClass().getTypeName() != null
								&& Arrays.asList(typeClasses).contains(nestedObject.getClass().getTypeName())) {
							for (Field subfield : nestedObject.getClass().getDeclaredFields()) {
								subfield.setAccessible(true);
								if (subfield.get(nestedObject) == null) {
									((List<Document>) ((Document) bson)
											.get(field.getName().substring(0, 1).toUpperCase()
													+ field.getName().substring(1))).get(index)
															.put(subfield.getName().substring(0, 1).toUpperCase()
																	+ subfield.getName().substring(1), null);
								}
							}
						}
						index++;
					}
				}
				if (field.get(obj) != null && field.get(obj).getClass().getTypeName() != null
						&& Arrays.asList(typeClasses).contains(field.get(obj).getClass().getTypeName())) {
					for (Field subfield : field.get(obj).getClass().getDeclaredFields()) {
						subfield.setAccessible(true);
						if (subfield.get(field.get(obj)) == null) {
							((Document) ((Document) bson)
									.get(field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)))
											.put(subfield.getName().substring(0, 1).toUpperCase()
													+ subfield.getName().substring(1), null);
						}
					}
				}
				if (field.get(obj) == null) {
					((Document) bson)
							.append(field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), null);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return;
	}

}
