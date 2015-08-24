package net.waltonstine.json.test;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
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
    public void readFile() throws IOException, InterruptedException
    {
        final String FNAME = "sample.json";
        PipeWriter wtr = new PipeWriter();
        PipedOutputStream ostrm = wtr.openStream(FNAME);
        PipedInputStream  istrm = new PipedInputStream(ostrm);

        Thread t = new Thread(wtr);
        t.start();

        final int BUFF_LN = 10240;

        byte[] iBuffer = new byte[BUFF_LN];
        int cnt = istrm.read(iBuffer, 0, BUFF_LN);
        System.out.printf("Read %d bytes from pipe.\n", cnt);

        ostrm.close();
        istrm.close();

        t.join();

        assertTrue((new File(FNAME)).length() == cnt);
    }
}
