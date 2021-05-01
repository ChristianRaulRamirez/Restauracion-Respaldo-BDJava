package com.pe.tecnologia.respaldo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Respaldo{
    
    private static Respaldo instancia;
    
    //Creamos el método para generar la copia
    public void crearBackup() throws IOException{ //puede llamarse crearRespaldo()
        Process proceso = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqldump -u Christian -pholamundo clientes"); //aqui pones tu ruta de Mysql , usuario y password
        InputStream entrada = proceso.getInputStream();
        FileOutputStream archivo = new FileOutputStream("RespaldoBD-clientes.sql");
        
        byte[] buffer = new byte[1000];
        int byteLeido = entrada.read(buffer);
        
        while(byteLeido > 0){
            archivo.write(buffer,0, byteLeido);
            byteLeido = entrada.read(buffer);
        }
        
        archivo.close();
    }
    
    
    public static Respaldo getInstance(){
        if(instancia == null){
            instancia = new Respaldo();
        }
        return instancia;
    }
}
