import { RevistaController } from "../controllers/revista.controllers.js";

export class RevistaRouter {
    revistaController = new RevistaController();

    routes(app) {
        
        app.get("/mostrar/revistas",this.revistaController.getAllRevista);
        app.get("/mostrar/revista/:id",this.revistaController.getOneRevista);
        app.post("/crear/revista",this.revistaController.createRevista);
        app.put("/update/revista/:id",this.revistaController.updateRevista);
        app.delete("/delete/revista/:id",this.revistaController.deleteRevista);

    }
}