import express from 'express';
import morgan from 'morgan';
import cors from 'cors';
import {
  Index
} from './routes/index.js';


export class App {

  constructor(port) {
    this.app = express();
    this.port = port;
    this.route = new Index();
    this.settings();
    this.middlewares();
    this.routes();
  }

  settings() {
    this.app.set('port', this.port || process.env.PORT || 5000);
  }

  async listen() {
    await this.app.listen(this.app.get('port'));
    console.log('Server on port ', this.app.get('port'));
  }

  middlewares() {
    this.app.use(morgan('dev'));
    this.app.use(express.json());
    this.app.use(express.urlencoded({
      extended: false
    }));
    this.app.use(cors());
  }

  routes() {
    this.route.autorRouter.routes(this.app);
    this.route.revistaRouter.routes(this.app);
    this.route.usuarioRouter.routes(this.app);
  }
}

(async()=>{
  const app = new App();
  await app.listen();
})()