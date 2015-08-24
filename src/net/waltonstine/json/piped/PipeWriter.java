package net.waltonstine.json.piped;

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
        final int BUFF_LN = 80;
        byte[] readBuffer = new byte[BUFF_LN];

        try
        {
            for ( ; ; )
            {
                int cnt = fstrm.read(readBuffer, 0, BUFF_LN);
                if (cnt == -1)
                {
                    System.out.println("EOF on json file.");
                    fstrm.close();
                    break;
                }
            }
        }
        catch (IOException ioex)
        {
            System.err.printf("Exception reading json file: %s\n", ioex.toString());
        }
    }
}
