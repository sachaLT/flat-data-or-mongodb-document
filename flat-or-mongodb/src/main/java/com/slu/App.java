package com.slu;

import com.slu.mongodb.ClientFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ClientFactory
            .getInstance("mongodb://localHost:27017")
            .close();
    }
}
