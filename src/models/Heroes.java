package models;

import java.sql.Date;

import javafx.scene.image.Image;



public class Heroes {

    int id;
    String editor;
    String nombre;
    String alterEgo;
    String personaje;
    Date fechaCreacion;
    Image image;
   



    public Image getImage() {
        return image;
    }



    public void setImage(Image image) {
        this.image = image;
    }



    public Heroes(int id, String editor, String nombre, String alterEgo, String personaje, Date fechaCreacion) {
        this.id = id;
        this.editor = editor;
        this.nombre = nombre;
        this.alterEgo = alterEgo;
        this.personaje = personaje;
        this.fechaCreacion = fechaCreacion;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getEditor() {
        return editor;
    }



    public void setEditor(String editor) {
        this.editor = editor;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getAlterEgo() {
        return alterEgo;
    }



    public void setAlterEgo(String alterEgo) {
        this.alterEgo = alterEgo;
    }



    public String getPersonaje() {
        return personaje;
    }



    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }



    public Date getFechaCreacion() {
        return fechaCreacion;
    }



    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
    
    


    
}
