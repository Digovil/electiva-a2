import { Model, DataTypes } from 'sequelize';
import { database } from '../config/config.js';

export class Usuario extends Model {
    id;
    nombre;
    correo;
    clave;
}



Usuario.init(
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
            validate: {
                is: /^[a-z]+$/i
            }
        },
  
        correo: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                isEmail: true
            }
        },

        clave: {
            type: DataTypes.STRING,
            allowNull: false,
        },
    },
    {
        tableName: "Usuario",
        sequelize: database,
        timestamps: true
    }
)