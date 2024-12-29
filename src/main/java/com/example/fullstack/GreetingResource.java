package com.example.fullstack;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// /hello yolu altında bulunan HTTP isteklerini karşılamak için kullanılan sınıf
@Path("/hello")
public class GreetingResource {

    // GET isteği alındığında "Hello from RESTEasy Reactive" yanıtını döner
    @GET
    @Produces(MediaType.TEXT_PLAIN) // Yanıtın düz metin (plain text) formatında olacağını belirtir
    public String hello() {
        return "Hello from RESTEasy Reactive"; // Yanıt metni
    }

    // /hello/world yolunda gelen GET isteği alındığında "Hello world!!" yanıtını döner
    @GET
    @Produces(MediaType.TEXT_PLAIN) // Yanıtın düz metin formatında olacağını belirtir
    @Path("/world") // Bu metodun /hello/world yolunda çalışmasını sağlar
    public String helloWorld() {
        return "Hello world!!"; // Yanıt metni
    }
}
