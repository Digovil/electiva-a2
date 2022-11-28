
import { Usuario } from "../models/usuario.models.js";
import bcrypt from 'bcrypt';

export class UsuarioController {

    async login(req,res) {
        try {

            const { correo, clave} = req.body;

            if(!correo || !clave){
                return res.status(201).json({
                    ok: false, 
                    message: "Campos incompletos"

                });
            }

            const usuario = await Usuario.findOne(
                {
                    where: {correo}
                }
            );


            if(!usuario){
                return res.status(404).json({
                    ok: false,
                    message: "Usuario no encontrado"
                });
            }

            const compare = await bcrypt.compare(clave,usuario.dataValues.clave);
            

            if(!compare) {
                return res.status(404).json({
                    ok: false,
                    message: "Credencial incorrecta"
                });
            }
            
            delete usuario.dataValues.clave
            return res.status(200).json({
                ok: true,
                usuario
            });




        } catch (error) {
            return res.status(500).json({
                ok: false, 
                message: "Error del servidor...",
                error
            });
        
        }
    }

    async register(req, res) {
        try {

            const {correo, nombre, clave} = req.body;

            if(!correo || !nombre || !clave){
                return res.status(201).json({
                    ok: false, 
                    message: "Campos incompletos"

                });
            }

            const encriptada = await bcrypt.hash( clave,10);
            
            //const compare = await bcrypt.compare("12345",encriptada);
            let body = {correo,nombre, clave: encriptada}
            let usuario = await Usuario.create({...body});

            if(usuario.id){
                return res.status(200).json({
                    ok: true
                });
            }

        

        } catch (error) {
            return res.status(500).json({
                ok: false, 
                message: "Error del servidor...",
                error
            });
        
        }
    }
}