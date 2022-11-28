package Utils;



public class Apis {

    public static final String URL_001="http://172.18.48.1:4500/";

    public static RevistaServices getRevistaService(){

        return Revista.getRevista(URL_001).create(RevistaServices.class);
    }

    public static UsuarioService getUsuarioService(){

        return Usuario.getUsuario(URL_001).create(UsuarioService.class);
    }

    public static ArticuloServices getArticuloService(){
        return Articulo.getArticulo(URL_001).create(ArticuloServices.class);
    }
}
