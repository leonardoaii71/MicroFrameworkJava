package org.rd.barcamp.sparkbarcamp.main;

import org.rd.barcamp.sparkbarcamp.encapsulacion.Estudiante;
import org.rd.barcamp.sparkbarcamp.servicios.FakeServices;

import static spark.Spark.*;

/**
 * Ejemplos de uso de rutas.
 */
public class ManejoRutas {

    /**
     *
     */
    public void ejemplosRutas(){

        /**
         * El sistema de ruta se determina por tres factores:
         * El verbo o el metodo de protocolo http: get, put, post...
         * La ruta: "/rutas/"
         */
        get("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas...";
        });

        post("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas...";
        });

        put("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas...";
        });

        delete("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas...";
        });

        options("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas...";
        });



        /**
         * Ejemplos de patrones de rutas
         * http://localhost:4567/rutas/20011126
         */
        get("/rutas/:matricula", (request, response)->{
            int matricula=Integer.parseInt(request.params("matricula"));
            //Consulta Fake a la base de datos...
            Estudiante estudiante = FakeServices.getInstancia().getEstudianteMatricula(matricula);

            return estudiante;
        });

        /**
         * Ejemplo de uso de comodines en la ruta.
         * http://localhost:4567/rutas/20011126/transferirMonto/100.00/a/20011287
         */
        get("/rutas/:matricula/transferirMonto/*/a/*", (request, response)->{
            //obtiendo los parametros vía comodines:
            String[] comodines=request.splat();

            //
            String montoOrigen = comodines[0];
            String matriculaDestino = comodines[1];

            //Omitiendo validaciones....
            Estudiante origen= FakeServices.getInstancia().getEstudianteMatricula(Integer.parseInt(request.params("matricula")));
            Estudiante destino = FakeServices.getInstancia().getEstudianteMatricula(Integer.parseInt(matriculaDestino));

            return String.format("Monto Transferido: $s, del Estudiante %s al %s, realizado con éxito", montoOrigen, origen.getNombre(), destino.getNombre());
        });

    }
}