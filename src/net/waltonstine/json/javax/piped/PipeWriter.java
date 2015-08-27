package net.waltonstine.json.javax.piped;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

public class PipeWriter implements Runnable
{
    private FileInputStream   fstrm;
    private PipedOutputStream pstrm;

    public PipeWriter()
    {
    }
    
    public PipedOutputStream openStream(String jsonFile) throws IOException
    {
        fstrm = new FileInputStream(jsonFile);
        pstrm = new PipedOutputStream();

        return pstrm;
    }

    public void run()
    {
        final int BUFF_LN = 11;
        byte[] readBuffer = new byte[BUFF_LN];

        try
        {
            for ( ; ; )
            {
                int cnt = fstrm.read(readBuffer, 0, BUFF_LN);
                if (cnt == -1)
                {
                    System.out.println("EOF on json file.");
                    pstrm.flush();                    
                    pstrm.close();
                    fstrm.close();
                    break;
                }

                pstrm.write(readBuffer, 0, cnt); 
                try
                {
                    // This sleep and the crazy buffer length of 11
                    // are used to demonstrate that the JSON parser is in
                    // fact a streaming parser, and does not need to wait
                    // for the entire file to be loaded into memory.
                    Thread.sleep(500);
                }
                catch (InterruptedException ex)
                {
                   ; // ignore
                }
            }
        }
        catch (IOException ioex)
        {
            System.err.printf("Exception reading json file: %s\n", ioex.toString());
        }
    }
}
