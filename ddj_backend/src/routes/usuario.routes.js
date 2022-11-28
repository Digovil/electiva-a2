import { UsuarioController } from "../controllers/usuario.controllers.js";

export class UsuarioRouter {
    usuarioController = new UsuarioController();

    routes(app) {
        
        app.post("/login",this.usuarioController.login);
        app.post("/register",this.usuarioController.register);


    }
}