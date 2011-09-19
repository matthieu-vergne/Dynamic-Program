package org.dynamicprogram.program;

import org.dynamicprogram.program.input.AbstractInputableProgram;
import org.dynamicprogram.program.input.InputableProgram;
import org.dynamicprogram.program.output.AbstractOutputableProgram;
import org.dynamicprogram.program.output.OutputableProgram;
import org.dynamicprogram.program.update.AbstractUpdatableProgram;
import org.dynamicprogram.program.update.UpdatableProgram;

/**
 * A program is an instance able to execute a process. It is possible to
 * implement a program extending {@link AbstractProgram}. It is also possible to
 * improve a program management implementing {@link InputableProgram},
 * {@link OutputableProgram} and {@link UpdatableProgram}. For these last cases,
 * you can also extend their abstract classes {@link AbstractInputableProgram},
 * {@link AbstractOutputableProgram} or {@link AbstractUpdatableProgram}, but
 * only one of them as you cannot extends several classes in Java. In order to
 * use all the possibilities of several of these abstract classes (basic
 * implementation, listeners, etc.), you can just implement the corresponding
 * interfaces and use an instance of each needed abstract class to implement the
 * corresponding methods.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 */
public interface Program {

	/**
	 * This method should describe the process to execute.
	 */
	public void execute();
}
