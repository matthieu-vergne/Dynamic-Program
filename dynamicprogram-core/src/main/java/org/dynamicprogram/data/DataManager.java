package org.dynamicprogram.data;

import java.util.Collection;

/**
 * This manager allows to manage dynamically a set of data. Each piece of data
 * is identified through a a given ID.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <Data>
 *            The data which are wrapped by this manager. In a general case it
 *            will be Object (to manage several kind of data) but it is not a
 *            mandatory.
 * @param <ID>
 *            The type of the IDs used to identify each piece of data. It can be
 *            the data themselves
 */
public interface DataManager<Data, ID> {

	/**
	 * This method should return all the usable IDs.
	 * 
	 * @return the IDs of each piece of data
	 */
	public Collection<ID> getDataIDs();

	/**
	 * This method should implement a way to set a specific piece of data.
	 * 
	 * @param id
	 *            the ID of the data
	 * @param data
	 *            the data to use
	 */
	public void setData(ID id, Data data);

	/**
	 * This method should implement a way to get the piece of data corresponding
	 * to the given ID.
	 * 
	 * @param id
	 *            the ID of the data
	 * @return the identified data
	 */
	public Data getData(ID id);
}
