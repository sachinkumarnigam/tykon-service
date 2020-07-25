package com.tykon.persistence.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;

public abstract class BaseCrudDAO<T, ID extends Serializable> extends BaseDAO<T, ID> {

}
