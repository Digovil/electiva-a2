
import { Autor } from "../models/autor.models.js";

export class AutorController {

    async getAllAutor(req,res) {

        try {
            const autor = await Autor.findAll();
            res.status(200).json(autor);
        } catch (error) {
            
        }
    }

    async getOneAutor( req,res) {
        const {id: idParam}= req.params;
        try {
            const autor = await Autor.findOne(
                {
                    where: {id: idParam}
                }
            );
            res.status(200).json({autor});
        } catch (error) {
            res.status(500).json({msg:"Error internal"});
            
        }
    }

    async createAutor( req,res) {
        const {

                
            nombre,
            email,
            adscripcion
        } = req.body;

        try {
            let body = {
                
                nombre,
                email,
                adscripcion
            
            }
            const autor = await Autor.create({...body});
            res.status(200).json({
                ok: true,
                autor: {
                    id: autor.id,
                    nombre: autor.dataValues.nombre,
                    email: autor.dataValues.email,
                    adscripcion: autor.dataValues.adscripcion
                    
                }
            });
        } catch (error){

        }
    }

    async updateAutor( req,res) {
        const id = req.params.id;
        const {
            nombre,
            email,
            adscripcion
        
        } = req.body;
        try {
            let body = {
                nombre,
                email,
                adscripcion
            
            }


            const autorExist = await Autor.findByPk(id);

            if(!autorExist) return res.status(400).json({mns:"Autor no existe"});
            
            const objectAutor = await Autor.update(body,{where: {id:id}});

            return res.status(200).json({
                ok:true,
                message: "Actualizacion exitosa..."
            });
        }catch (error){
            return res.status(500).json({
                ok: false, 
                message: "Error del servidor...",
                error
            });
        }
    }
    
    async deleteAutor( req,res) {
        const id = req.params.id;
        try {

            const autorExist = await Autor.findByPk(id);
            if(autorExist === null) return res.status(400).json({mns:"Autor no existe"});
            await Autor.destroy({
                where: {id}
            })
            return res.status(200).json({msg:"Autor Eliminado"})
        }catch (error){

        }

    }
}