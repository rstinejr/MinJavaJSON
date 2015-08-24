package net.waltonstine.json.minparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class Main
{

    public static void main(String[] args) throws IOException 
    {
        if (args.length != 1)
        {
            System.err.printf("Need exactly one argument, the name of a JSON source file.\n");
            System.exit(1);
        }
        String fname = args[0];
        
        InputStream fis = new FileInputStream(fname);

        JsonParser jsonParser = Json.createParser(fis);

        /**
         * We can create JsonParser from JsonParserFactory also with below code
         * JsonParserFactory factory = Json.createParserFactory(null);
         * jsonParser = factory.createParser(fis);
         */

        System.out.printf("Parsinging file %s:\n", fname);

        while (jsonParser.hasNext()) 
        {
            Event event = jsonParser.next();
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
                System.out.printf("Key name '%s'\n", jsonParser.getString());
                break;
            case VALUE_STRING:
                System.out.printf("String value: '%s'\n", jsonParser.getString());
                break;
            case VALUE_NUMBER:
                System.out.printf("Number value: %d\n", jsonParser.getLong());
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
        fis.close();
        jsonParser.close();
    }
}
