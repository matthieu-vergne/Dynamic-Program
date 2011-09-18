package org.dynamicprogram.program;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ProgramTest {

	@Test
	public void testExecution() {
		// define root program
		final Boolean executed[] = new Boolean[] { false };
		class ProgramRoot extends Program {
			@Override
			protected void process() {
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

}
