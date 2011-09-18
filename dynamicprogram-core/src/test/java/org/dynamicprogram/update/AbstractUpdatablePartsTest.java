package org.dynamicprogram.update;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.dynamicprogram.program.AbstractProgram;
import org.dynamicprogram.program.Program;
import org.junit.Test;

public class AbstractUpdatablePartsTest {

	class SubProgram extends AbstractProgram {

		@Override
		protected void internalExecute() {
			// nothing to do
		}

	}

	@Test
	public void testUpdate() {
		final SubProgram program0 = new SubProgram();
		final SubProgram program1 = new SubProgram();
		final SubProgram program2 = new SubProgram();
		AbstractUpdatableParts<Integer> parts = new AbstractUpdatableParts<Integer>() {
			private final Program[] parts = new Program[] { program0, program1,
					program2 };

			@Override
			public Collection<Integer> getPartIDs() {
				return Arrays.asList(0, 1, 2);
			}

			@Override
			public Program getPart(Integer id) {
				return parts[id];
			}

			@Override
			protected void internalSetPart(Integer id, Program program) {
				parts[id] = program;
			}

		};

		assertEquals(program0, parts.getPart(0));
		assertEquals(program1, parts.getPart(1));
		assertEquals(program2, parts.getPart(2));

		parts.setPart(0, program2);
		parts.setPart(1, program0);
		parts.setPart(2, program2);
		assertEquals(program2, parts.getPart(0));
		assertEquals(program0, parts.getPart(1));
		assertEquals(program2, parts.getPart(2));
	}

	@Test
	public void testListeners() {
		final SubProgram program0 = new SubProgram();
		final SubProgram program1 = new SubProgram();
		final SubProgram program2 = new SubProgram();
		final Boolean executed[] = new Boolean[] { false, false };
		AbstractUpdatableParts<Integer> parts = new AbstractUpdatableParts<Integer>() {
			private final Program[] parts = new Program[] { program0, program1,
					program2 };

			@Override
			public Collection<Integer> getPartIDs() {
				return Arrays.asList(0, 1, 2);
			}

			@Override
			public Program getPart(Integer id) {
				return parts[id];
			}

			@Override
			protected void internalSetPart(Integer id, Program program) {
				parts[id] = program;
				assertArrayEquals(new Boolean[] { false, false }, executed);
				executed[0] = true;
			}

		};
		final Object[] updated = new Object[3];
		parts.addUpdateListener(new UpdateListener<Integer>() {

			@Override
			public void partUpdated(Integer id, Program oldPart, Program newPart) {
				assertArrayEquals(updated,
						new Object[] { id, oldPart, newPart });
				assertArrayEquals(new Boolean[] { true, false }, executed);
				executed[1] = true;
			}
		});

		Arrays.fill(executed, false);
		updated[0] = 0;
		updated[1] = program0;
		updated[2] = program2;
		assertArrayEquals(new Boolean[] { false, false }, executed);
		parts.setPart((Integer) updated[0], (Program) updated[2]);
		assertArrayEquals(new Boolean[] { true, true }, executed);

		Arrays.fill(executed, false);
		updated[0] = 1;
		updated[1] = program1;
		updated[2] = program0;
		assertArrayEquals(new Boolean[] { false, false }, executed);
		parts.setPart((Integer) updated[0], (Program) updated[2]);
		assertArrayEquals(new Boolean[] { true, true }, executed);
	}

}
