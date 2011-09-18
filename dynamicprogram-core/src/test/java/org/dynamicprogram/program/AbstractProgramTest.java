package org.dynamicprogram.program;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class AbstractProgramTest {

	@Test
	public void testExecution() {
		// define root program
		final Boolean executed[] = new Boolean[] { false };
		class ProgramRoot extends AbstractProgram {
			@Override
			protected void internalExecute() {
				executed[0] = true;
			}

		}
		ProgramRoot program = new ProgramRoot();

		// tests execution
		assertArrayEquals(new Boolean[] { false }, executed);
		program.execute();
		assertArrayEquals(new Boolean[] { true }, executed);

		Arrays.fill(executed, false);
		assertArrayEquals(new Boolean[] { false }, executed);
		program.execute();
		assertArrayEquals(new Boolean[] { true }, executed);
	}

	@Test
	public void testListeners() {
		// define root program
		final Boolean executed[] = new Boolean[] { false, false, false };
		class ProgramRoot extends AbstractProgram {
			@Override
			protected void internalExecute() {
				assertArrayEquals(new Boolean[] { true, false, false },
						executed);
				executed[1] = true;
			}

		}
		ProgramRoot program = new ProgramRoot();
		program.addExecutionListener(new ExecutionListener() {

			@Override
			public void preExecution() {
				assertArrayEquals(new Boolean[] { false, false, false },
						executed);
				executed[0] = true;
			}

			@Override
			public void postExecution() {
				assertArrayEquals(new Boolean[] { true, true, false }, executed);
				executed[2] = true;
			}
		});

		// tests execution
		assertArrayEquals(new Boolean[] { false, false, false }, executed);
		program.execute();
		assertArrayEquals(new Boolean[] { true, true, true }, executed);

		Arrays.fill(executed, false);
		assertArrayEquals(new Boolean[] { false, false, false }, executed);
		program.execute();
		assertArrayEquals(new Boolean[] { true, true, true }, executed);
	}

}
