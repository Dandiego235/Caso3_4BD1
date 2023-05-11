import { Logger } from '../common'

const sql = require('mssql')

const sqlConfig = {
    user: "sa",
    password: "Twinsociety235",
    database: "ev34",
    server: "localhost",
    pool: {
      max: 1,
      min: 1,
      idleTimeoutMillis: 30000
    },
    options: {
      encrypt: true, 
      trustServerCertificate: true 
    }
}

export class data_esencial {
    private static instance: data_esencial;
    private log: Logger;
    private pool;

    private constructor()
    {
        this.log = new Logger();
        try
        {  
            sql.connect(sqlConfig).then((pool:any) => {
                this.pool = pool;
            })
        } catch (e)
        {
            this.log.error(e);
        }
        // via singleton, accediendo a un solo pool tengo una conexiona la base de datos
    }

    public static getInstance() : data_esencial
    {
        if (!this.instance)
        {
            this.instance = new data_esencial();
        }
        return this.instance;
    }

    public getLotes() : Promise<any>
    {
        // return this.pool.request().execute("SP_getLotes")
    
        return sql.connect(sqlConfig).then((pool:any) => {
            return pool.request()
                .execute("SP_getLotes")
        })
    }

}
