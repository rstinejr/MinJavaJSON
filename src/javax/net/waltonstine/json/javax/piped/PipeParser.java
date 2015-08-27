package net.waltonstine.json.javax.piped;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import net.waltonstine.json.javax.piped.PipeWriter;

public class PipeParser
{

    public static void main(String[] args) throws IOException,InterruptedException
    {
        System.out.printf("Start PipeParser test.\n");
        if (args.length != 1)
        {
            System.err.printf("Need exactly one argument, the name of a JSON source file.\n");
            System.exit(1);
        }
        String fname = args[0];
        
        PipeWriter pw         = new PipeWriter();
        PipedOutputStream src = pw.openStream(fname);

        PipedInputStream  snk = new PipedInputStream(src);

        System.out.printf("Hooked up output and input streams, source file is '%s'\n", fname);
        System.out.flush();
        Thread t = new Thread(pw);
        t.start();

        JsonParser jp = Json.createParser(snk);

        System.out.printf("Get items from parser...\n"); 

        while (jp.hasNext()) 
        {
            Event event = jp.next();
            System.out.printf("Event: %s\n", event.toString());
            switch (event) 
            {
            case START_OBJECT:
                System.out.printf("\nStart Object.\n");
                break;
            case END_OBJECT:
                System.out.printf("End Object.\n");
                break;
            case START_ARRAY:
                System.out.printf("Start Array.\n");
                break;
            case END_ARRAY:
                System.out.printf("End Array.\n");
                break;
            case KEY_NAME:
                System.out.printf("Key name '%s'\n", jp.getString());
                break;
            case VALUE_STRING:
                System.out.printf("String value: '%s'\n", jp.getString());
                break;
            case VALUE_NUMBER:
                System.out.printf("Number value: %d\n", jp.getLong());
                break;
            case VALUE_FALSE:
                System.out.printf("Boolean value FALSE\n");
                break;
            case VALUE_TRUE:
                System.out.printf("Boolean value TRUE\n");
                break;
            case VALUE_NULL:
                System.out.printf("NULL value.\n");
                break;
            default:
                // we are not looking for other events
            }
        }
        
        System.out.println("JSON parser done.");
        
        //close resources
        t.join();
        snk.close();
        jp.close();
    }
}
