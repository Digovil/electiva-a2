package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("correo")
    @Expose
    private String correo;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("clave")
    @Expose
    private String clave;


    public Usuario(int id, String nombre, String correo, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
    }

    public Usuario() {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre;  }


    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;

    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}