package net.waltonstine.json.piped;

import java.io.InputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

public class PipeWriter implements Runnable
{
    private String            jsonFile;
    private PipedOutputStream strm;

    public PipeWriter(String jsonFile) throws IOException
    {
        this.jsonFile = jsonFile;
    }
    
    public PipedOutputStream openStream()
    {
        strm = new PipedOutputStream();
        return strm;
    }

    public void run()
    {
    }
}
