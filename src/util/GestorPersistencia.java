/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import datos.Contacto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JEKSER
 */
public class GestorPersistencia {
    
    private final String NOMBREAP="loscontactos.asi";

    public GestorPersistencia() {
        
    }
    
    public boolean guardarArchivo(ArrayList<Contacto> contactos){
        FileOutputStream fo=null;
        
        try{
            fo=new FileOutputStream(NOMBREAP);
            ObjectOutputStream oo=new ObjectOutputStream(fo);
            oo.writeObject(contactos);
            oo.close();
            return true;
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            try{
                fo.close();
            }catch(IOException ex){
                
            }
        }
        return false;
    }
    
    public ArrayList<Contacto> abrirArchivo(){
        ArrayList<Contacto> contactosArchivo=null;
        FileInputStream fi=null;
        
        try{
            fi=new FileInputStream(NOMBREAP);
            ObjectInputStream oi=new ObjectInputStream(fi);
            contactosArchivo=(ArrayList<Contacto>)oi.readObject();            
            oi.close();
            fi.close();
            return contactosArchivo;
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo de contactos no encontrado.\nse creera uno nuevo","Error",JOptionPane.ERROR_MESSAGE);
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }finally{            
        }    
        return null;
    }
}

