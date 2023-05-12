import { Logger } from '../common'

const sqlNP = require('mssql')

const sqlNPConfig = {
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

export class data_esencialNP {
    private static instance: data_esencialNP;
    private log: Logger;
    private pool;

    public constructor()
    {
        this.log = new Logger();
        try
        {  
            this.pool = new sqlNP.ConnectionPool(sqlNPConfig);
        } catch (e)
        {
            this.log.error(e);
        }
        // via singleton, accediendo a un solo pool tengo una conexiona la base de datos
    }

    public static getInstance() : data_esencialNP
    {
        if (!this.instance)
        {
            this.instance = new data_esencialNP();
        }
        return this.instance;
    }

    public async getLotes() : Promise<any>
    {
        this.pool = await this.pool.connect();
        var res:any = await this.pool.request().execute('SP_getLotes');
        //await setTimeout(() => {this.pool.close();}, 25000);
        this.pool.close();
        return res;
    }

    public getPool():any {
        return this.pool;
    }

}
