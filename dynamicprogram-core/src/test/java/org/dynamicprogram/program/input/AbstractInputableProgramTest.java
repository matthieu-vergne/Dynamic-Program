package org.dynamicprogram.program.input;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class AbstractInputableProgramTest {

	@Test
	public void testUpdate() {
		AbstractInputableProgram<String, Integer> program = new AbstractInputableProgram<String, Integer>() {

			@Override
			public void execute() {
				// nothing to do
			}

			@Override
			public Collection<Integer> getInputIDs() {
				return Arrays.asList(0, 1);
			}
		};

		assertNull(program.getInput(0));
		assertNull(program.getInput(1));

		String s0 = "aze";
		program.setInput(0, s0);
		assertEquals(s0, program.getInput(0));
		assertNull(program.getInput(1));

		String s1 = "test";
		program.setInput(1, s1);
		assertEquals(s0, program.getInput(0));
		assertEquals(s1, program.getInput(1));
	}

	@Test
	public void testListeners() {
		AbstractInputableProgram<String, Integer> program = new AbstractInputableProgram<String, Integer>() {

			@Override
			public void execute() {
				// nothing to do
			}

			@Override
			public Collection<Integer> getInputIDs() {
				return Arrays.asList(0, 1);
			}
		};
		final Object[] update = new Object[3];
		final Boolean[] updated = new Boolean[] { false };
		program.addInputListener(new InputListener<String, Integer>() {

			@Override
			public void inputUpdated(Integer id, String oldInput,
					String newInput) {
				assertArrayEquals(update,
						new Object[] { id, oldInput, newInput });
				updated[0] = true;
			}
		});

		update[0] = 0;
		update[1] = null;
		update[2] = "aze";
		updated[0] = false;
		program.setInput((Integer) update[0], (String) update[2]);
		assertTrue(updated[0]);

		update[0] = 0;
		update[1] = "aze";
		update[2] = "aze";
		updated[0] = false;
		program.setInput((Integer) update[0], (String) update[2]);
		assertTrue(updated[0]);

		update[0] = 1;
		update[1] = null;
		update[2] = "test";
		updated[0] = false;
		program.setInput((Integer) update[0], (String) update[2]);
		assertTrue(updated[0]);
	}

}
