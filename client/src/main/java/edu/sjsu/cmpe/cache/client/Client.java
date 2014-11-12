package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws Exception {

        Map<Integer, String> lookUpMap = new HashMap<Integer, String>();
        lookUpMap.put(1,"One");
        lookUpMap.put(2,"Two");
        lookUpMap.put(3,"Three");
        lookUpMap.put(4,"Four");
        lookUpMap.put(5,"Five");
        lookUpMap.put(6,"Six");
        lookUpMap.put(7,"Seven");
        lookUpMap.put(8,"Eight");
        lookUpMap.put(9,"Nine");
        lookUpMap.put(10,"Ten");


        System.out.println("Starting Cache Client...");
        List<String> servers = new ArrayList<String>();
        servers.add("http://localhost:3000");
        servers.add("http://localhost:3001");
        servers.add("http://localhost:3002");

        ConsistentHash consistentHash = new ConsistentHash(15, servers);
        System.out.println("Storing Values");
        for (int i = 1; i < 11; i++) {

         String serverAddress = (String)consistentHash.get(new Integer(i).toString());
            System.out.println("Storing value of i= "+i+" into Server = "+serverAddress);
            CacheServiceInterface cache = new DistributedCacheService(
                    serverAddress);
            cache.put(i, lookUpMap.get(i));

        }
System.out.println("Retrieving Values from different locations...");
        for (int i = 1; i < 11; i++) {

            String serverAddress = (String)consistentHash.get(new Integer(i).toString());
            CacheServiceInterface cache = new DistributedCacheService(
                    serverAddress);
            String value = cache.get(i);
            System.out.println(i+" => "+value);


        }




    }

}
