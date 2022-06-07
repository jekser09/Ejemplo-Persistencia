/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


import java.util.ArrayList;
import datos.Contacto;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import util.GestorPersistencia;
/**
 *
 * @author JEKSER
 */
public class Libreta implements Serializable{
    private ArrayList<Contacto> contactos;
    
    public Libreta() {
        this.buscarContactos();
        if(contactos==null){
            contactos=new ArrayList();
        }
    }
    
    public boolean adicionarContacto(Contacto contacto){
        if(contacto==null){
            return false;
        }
        contacto.setIdentificador(this.consecutivo());
        if(this.contactos.add(contacto)){
            return this.actualizarPersistencia();
        }                
        return false;
    }
    
    public boolean borrarContacto(Contacto contacto){
        if(contacto==null){
            return false;
        }
        if(contactos.remove(contacto)){
            return actualizarPersistencia();
        }
        return false;
    }
    
    public void buscarContactos(){
        GestorPersistencia gestor=new GestorPersistencia();
        this.contactos=gestor.abrirArchivo();             
    }
    
    public boolean editarContacto(Contacto contacto){
        if(contacto==null){
            return false;
        }
        for (Contacto elcontacto : contactos) {
            if(elcontacto.getIdentificador()==contacto.getIdentificador()){
                elcontacto.setNombre(contacto.getNombre());
                elcontacto.setCorreo(contacto.getCorreo());
                elcontacto.setTelefono(contacto.getTelefono());
                elcontacto.setFavorito(contacto.isFavorito());
                return this.actualizarPersistencia();
            }           
        }
        return false;
    }
    
    public Contacto buscarContacto(Long idtf){
        if(idtf==null){
            return null;
        }
        for (Contacto elcontacto : contactos) {
            if(elcontacto.getIdentificador()==idtf) return elcontacto;                
        }
        return null;
    }  
    
    private boolean actualizarPersistencia(){        
        GestorPersistencia gestor=new GestorPersistencia();
        return gestor.guardarArchivo(contactos);        
    }
    
    private Long consecutivo(){
        if(contactos.isEmpty()){
            return 1L;
        }
        
        Collections.sort(contactos,new Comparator<Contacto>(){
            @Override
            public int compare(Contacto c1, Contacto c2) {                
                if(c1.getIdentificador()>c2.getIdentificador())return 1;           
                if(c1.getIdentificador()==c2.getIdentificador()) return 0;         
                return -1;
                //retunr c1.getIdentificador().compareTo(c2.getIdentificador());
            }   
        });
        return contactos.get(this.contactos.size()-1).getIdentificador()+1;       
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }   
}
