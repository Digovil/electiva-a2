
import { Revista } from "../models/revista.models.js";

export class RevistaController {

    async getAllRevista(req,res) {

        try {
            const revista = await Revista.findAll();
            res.status(200).json(revista);
        } catch (error) {
            
        }
    }

    async getOneRevista( req,res) {
        const {id: idParam}= req.params;
        try {
            const revista = await Revista.findOne(
                {
                    where: {id: idParam}
                }
            );

            if(revista === null){
                res.status(400).json({
                    ok: false,
                    message: "No se encontro la revista"
                });

            }

            res.status(200).json(revista);
        } catch (error) {
            res.status(500).json({msg:"Error internal"});
            
        }
    }

    async createRevista( req,res) {
        const {

            titulo,
            issn,
            numero,
            anio,
        } = req.body;

        try {
            let body = {
                
                titulo,
                issn,
                numero,
                anio,
            }
            const revista = await Revista.create({...body});
            res.status(200).json({
                ok: true,
                revista: {
                    id: revista.id,
                    titulo: revista.dataValues.titulo,
                    issn: revista.dataValues.issn,
                    numero: revista.dataValues.numero,
                    anio: revista.dataValues.anio,
                    
                }
            });
        } catch (error){
            return res.status(500).json({
                ok: false, 
                message: "Error del servidor...",
                error
            });
        }
    }

    async updateRevista( req,res) {
        const id = req.params.id;
        const {
            issn,
            numero,
            titulo,
            anio
        
        } = req.body;
        try {
            let body = {
                
                issn,
                numero,
                titulo,
                anio
        
            
            }


            const revistaExist = await Revista.findByPk(id);

            if(!revistaExist) return res.status(400).json({mns:"Revista no existe"});
            
            const objectRevista = await Revista.update(body,{where: {id:id}});

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
    
    async deleteRevista( req,res) {
        const id = req.params.id;
        try {

            const autorExist = await Revista.findByPk(id);
            if(autorExist === null) return res.status(400).json({mns:"Revista no existe"});
            await Revista.destroy({
                where: {id}
            })
            return res.status(200).json({msg:"Revista Eliminado"})
        }catch (error){

        }

    }
}