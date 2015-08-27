package net.waltonstine.json.jackson.minparser;

import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class Main
{

    public static void main(String[] args) throws IOException, JsonParserException
    {
        if (args.length != 1)
        {
            System.err.printf("Need exactly one argument, the name of a JSON source file.\n");
            System.exit(1);
        }
        String fname = args[0];
        
        JsonParser jsonParser = new JsonFactory().createParser(new File(fname));

        System.out.printf("Jackson lib parsinging file %s:\n", fname);

        for ( ; ; )
        {
            JsonToken toke = jsonParser.nextToken();
            System.out.println("Token: " toke.toString());
            if (toke == JsonToken.END_OBJECT)
            {
                break;
            }
        }
        
        System.out.println("JSON parser done.");
        
        jsonParser.close();
    }
}
