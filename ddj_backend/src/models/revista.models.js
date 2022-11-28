import { Model, DataTypes } from 'sequelize';
import { database } from '../config/config.js';

export class Revista extends Model {
    id;
    titulo;
    issn;
    numero;
    anio;
}



Revista.init(
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
        titulo: {
            type: DataTypes.STRING,
            allowNull: false,
        },

        issn: {
            type: DataTypes.STRING,
            allowNull: false,
        },
  
        numero: {
            type: DataTypes.INTEGER,
            allowNull: false,
        },

        anio: {
            type: DataTypes.STRING,
            allowNull: false,
        },
    },
    {
        tableName: "Revista",
        sequelize: database,
        timestamps: true
    }
)
