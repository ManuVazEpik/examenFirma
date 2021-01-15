/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmaexamen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.itextpdf.text.Document;

/**
 *
 * @author manua
 */
public class datos {
    private String nombre;
    private String boleta;
    private String mensaje;

    private static Cipher cifrado;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBoleta() {
        return boleta;
    }

    public void setBoleta(String boleta) {
        this.boleta = boleta;
    }

    public String getMensaje() {
        return mensaje; 
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
        
    
    public void GenerarLlaves() throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, Exception{
        
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        
        KeyPair keys = kg.generateKeyPair();
        
        PublicKey llavepublica = keys.getPublic();
        PrivateKey llaveprivada = keys.getPrivate();
        
        guardarKey(llavepublica,"publickey.key");
        llavepublica = cargarLlavePublica("publickey.key");
        
        guardarKey(llaveprivada,"privatekey.key");
        llaveprivada = cargarLlavePrivada("privatekey.key");
        
        //obtener el cifrado 
        System.out.println("Se hace");
     
    }
    private static void guardarKey(Key key, String archivo) throws FileNotFoundException, IOException {
        //se cifra el contenido de la llave

        byte[] keybytes = key.getEncoded();
        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(keybytes);
        System.out.println(archivo);
        fos.close();
    }

    public static PublicKey cargarLlavePublica(String archivo) throws FileNotFoundException, IOException, Exception {
        //generar archivo
        FileInputStream fis = new FileInputStream(archivo);
        int nb = fis.available();
        byte[] bytes = new byte[nb];
        fis.read(bytes);
        fis.close();
        
        KeyFactory keyFactor = KeyFactory.getInstance("RSA");
        KeySpec keySpec= new X509EncodedKeySpec(bytes);
        PublicKey llavepublic = keyFactor.generatePublic(keySpec);
        return llavepublic;
    }

    public PrivateKey cargarLlavePrivada(String archivo) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        
        FileInputStream fis = new FileInputStream(archivo);
        int nb = fis.available();
        byte[] bytes = new byte[nb];
        fis.read(bytes);
        fis.close();
        
        KeyFactory keyFactor = KeyFactory.getInstance("RSA");
        KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey llaveprivada = keyFactor.generatePrivate(keySpec);
        return llaveprivada;
    }
}