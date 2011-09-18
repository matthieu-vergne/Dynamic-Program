package org.dynamicprogram;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class ExecutionListenerTest {

	@Test
	public void test() {
		final Boolean[] steps = new Boolean[] { false, false, false };
		ExecutionListener listener = new ExecutionListener() {

			@Override
			public void preExecution() {
				assertArrayEquals(new Boolean[] { false, false, false }, steps);
				steps[0] = true;
			}

			@Override
			public void postExecution() {
				assertArrayEquals(new Boolean[] { true, true, false }, steps);
				steps[2] = true;
			}
		};
		DynamicProgram program = new DynamicProgram() {

			@Override
			protected void process() {
				assertArrayEquals(new Boolean[] { true, false, false }, steps);
				steps[1] = true;
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return null;
			}
		};
		program.addExecutionListener(listener);

		Arrays.fill(steps, false);
		assertArrayEquals(new Boolean[] { false, false, false }, steps);
		program.execute();
		assertArrayEquals(new Boolean[] { true, true, true }, steps);

		Arrays.fill(steps, false);
		assertArrayEquals(new Boolean[] { false, false, false }, steps);
		program.execute();
		assertArrayEquals(new Boolean[] { true, true, true }, steps);
	}

}
