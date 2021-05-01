package com.pe.tecnologia.restauracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Restauracion{
    
    private static Restauracion instancia;
    
    //Creamos el método para generar la restauracion
    public void crearRestauracion() throws IOException{ 
        Process proceso = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysql -u Christian -pholamundo restauracionclientes"); //aqui tienes que poner el nombre de la base de datos en la cual vas a restaurar el respaldo
        OutputStream salida = proceso.getOutputStream();
        FileInputStream archivo = new FileInputStream("RespaldoBD-clientes.sql");
        
        byte[] buffer = new byte[1000];
        int byteLeido = archivo.read(buffer);
        
        while(byteLeido > 0){
            salida.write(buffer,0, byteLeido);
            byteLeido = archivo.read(buffer);
        }
        
        salida.flush();
        salida.close();
        archivo.close();
    }
    
    
    public static Restauracion getInstance(){
        if(instancia == null){
            instancia = new Restauracion();
        }
        return instancia;
    }
}
