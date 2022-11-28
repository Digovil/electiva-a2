package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articulo {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("paginainicio")
    @Expose
    private int paginainicio;

    @SerializedName("paginafin")
    @Expose
    private int paginafin;


    @SerializedName("idrevista")
    @Expose
    private int idrevista;

    public Articulo(int id, String titulo, int paginainicio, int paginafin, int idrevista) {
        this.id = id;
        this.titulo = titulo;
        this.paginainicio = paginainicio;
        this.paginafin = paginafin;
        this.idrevista = idrevista;
    }
    public Articulo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPaginainicio() {
        return paginainicio;
    }

    public void setPaginainicio(int paginainicio) {
        this.paginainicio = paginainicio;
    }

    public int getPaginafin() {
        return paginafin;
    }

    public void setPaginafin(int paginafin) {
        this.paginafin = paginafin;
    }

    public int getIdrevista() {
        return idrevista;
    }

    public void setIdrevista(int idrevista) {
        this.idrevista = idrevista;
    }
}