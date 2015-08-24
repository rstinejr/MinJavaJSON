package net.waltonstine.json.test;

import java.io.IOException;
import java.io.PipedOutputStream;

import net.waltonstine.json.piped.PipeWriter;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestPipeWriter
{
    @Test
    public void execCtor()
    {
        {
            new PipeWriter();
        }

        assertTrue(true);
    }

    @Test
    public void readFile() throws IOException
    {
        PipeWriter wtr = new PipeWriter();
        PipedOutputStream ostrm = wtr.openStream("sample.json");
        assertTrue(true);
    }
}
