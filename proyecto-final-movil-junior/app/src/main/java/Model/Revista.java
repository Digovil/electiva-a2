package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Revista {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("numero")
    @Expose
    private int numero;

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("issn")
    @Expose
    private String issn;

    @SerializedName("anio")
    @Expose
    private String anio;



    public Revista(int id, int numero, String titulo, String issn, String anio) {
        this.id = id;
        this.numero = numero;
        this.titulo = titulo;
        this.issn = issn;
        this.anio = anio;
    }

    public Revista() {

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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
