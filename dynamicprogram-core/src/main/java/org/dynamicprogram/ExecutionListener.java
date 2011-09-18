package org.dynamicprogram;

/**
 * This listener allows to execute some operations just before/after the
 * execution of a program.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 */
public interface ExecutionListener {

	public void preExecution();

	public void postExecution();
}
