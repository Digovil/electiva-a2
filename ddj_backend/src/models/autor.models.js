import { Model, DataTypes } from 'sequelize';
import { database } from '../config/config.js';

export class Autor extends Model {
    id;
    nombre;
    email;
    adscripcion;
}



Autor.init(
    {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            allowNull: true,
            validate: {
                isNumeric: true
            }
        },
        nombre: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        email: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        adscripcion: {
            type: DataTypes.STRING,
            allowNull: false,
        },
    },
    {
        tableName: "Autor",
        sequelize: database,
        timestamps: true
    }
)

