package org.dynamicprogram;

import static org.junit.Assert.*;

import org.junit.Test;

public class IOLinkerTest {

	@Test
	public void testLink() {
		final Integer[] dataWrapper = new Integer[] { 0, 1 };
		IOLinker<Integer> linker = new IOLinker<Integer>() {

			@Override
			public void setInput(Integer data) {
				dataWrapper[1] = data;
			}

			@Override
			public Integer getOutput() {
				return dataWrapper[0];
			}
		};

		assertArrayEquals(new Integer[] { 0, 1 }, dataWrapper);
		linker.link();
		assertArrayEquals(new Integer[] { 0, 0 }, dataWrapper);
		dataWrapper[0] = 5;
		assertArrayEquals(new Integer[] { 5, 0 }, dataWrapper);
		linker.link();
		assertArrayEquals(new Integer[] { 5, 5 }, dataWrapper);
	}

}
