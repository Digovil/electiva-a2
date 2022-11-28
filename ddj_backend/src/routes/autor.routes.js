import { AutorController } from "../controllers/autor.controllers.js";

export class AutorRouter {
    autorController = new AutorController();

    routes(app) {
        
        app.get("/mostrar/autores",this.autorController.getAllAutor);
        app.get("/mostrar/autor/:id",this.autorController.getOneAutor);
        app.post("/crear/autor",this.autorController.createAutor);
        app.put("/update/autor/:id",this.autorController.updateAutor);
        app.delete("/delete/autor/:id",this.autorController.deleteAutor);

    }
}