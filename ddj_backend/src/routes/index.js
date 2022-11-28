import { AutorRouter } from "./autor.routes.js";
import { RevistaRouter } from "./revista.routes.js";
import { UsuarioRouter } from "./usuario.routes.js";

export class Index {
    autorRouter = new AutorRouter();
    revistaRouter = new RevistaRouter();
    usuarioRouter = new UsuarioRouter();
    
}