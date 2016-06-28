package shafin.sustord.dao;

import java.util.Collection;

public interface BasicCRUD<T> {

	public T findOne(int id);

	public Collection<T> findAll();

	public boolean insertOne(T object);

	public boolean updateOne(T object);

	public boolean deleteOne(T object);
}
