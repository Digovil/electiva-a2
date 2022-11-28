package Utils;



public class Apis {

    public static final String URL_001="http://172.18.48.1:5000/";

    public static RevistaServices getRevistaService(){

        return Revista.getRevista(URL_001).create(RevistaServices.class);
    }

    public static UsuarioService getUsuarioService(){

        return Usuario.getUsuario(URL_001).create(UsuarioService.class);
    }

    public static AutorServices getAutorService(){
        return Autor.getAutor(URL_001).create(AutorServices.class);
    }
}
