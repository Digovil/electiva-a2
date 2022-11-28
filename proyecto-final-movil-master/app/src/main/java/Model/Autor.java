package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Autor {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("adscripcion")
    @Expose
    private String adscripcion;

    @SerializedName("email")
    @Expose
    private String email;



    public Autor(int id, String nombre, String adscripcion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.adscripcion = adscripcion;
        this.email = email;
    }

    public Autor() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id;  }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre;  }


    public String getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}