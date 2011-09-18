package org.dynamicprogram.program;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.dynamicprogram.program.DynamicProgram;
import org.dynamicprogram.program.Program;
import org.junit.Test;

public class DynamicProgramTest {

	@Test
	public void testUpdate() {
		// define internal programs
		class SubProgram extends Program {
			@Override
			protected void process() {
				// nothing to do
			}
		}

		// define root program
		final SubProgram subProgram = new SubProgram();
		class ProgramRoot extends DynamicProgram {
			private SubProgram p1 = subProgram;
			private SubProgram p2 = new SubProgram();

			@Override
			protected void process() {
				p1.execute();
				p2.execute();
			}

			@Override
			public Collection<? extends Program> getUpdatableParts() {
				return Arrays.asList(p1);
			}

			public <DP extends Program> void replace(DP current, DP replacement) {
				if (p1 == current) {
					p1 = (SubProgram) replacement;
				}
			};
		}
		ProgramRoot program = new ProgramRoot();

		// tests
		assertTrue(program.hasUpdatableParts());

		Collection<Program> subprograms = new HashSet<Program>();
		subprograms.add(subProgram);
		assertTrue(program.getUpdatableParts().containsAll(subprograms));
		assertTrue(subprograms.containsAll(program.getUpdatableParts()));

		SubProgram program1B = new SubProgram();
		program.replace(subProgram, program1B);
		subprograms.clear();
		subprograms.add(program1B);
		assertTrue(program.getUpdatableParts().containsAll(subprograms));
		assertTrue(subprograms.containsAll(program.getUpdatableParts()));
	}

}
