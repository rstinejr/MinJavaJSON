package net.waltonstine.json.test;

import net.waltonstine.json.piped.PipeWriter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestPipeWriter
{
    @Test
	public void execCtor()
	{
		{
		    PipeWriter src = new PipeWriter();
		}

		assertEquals(1,1);
    }
}
