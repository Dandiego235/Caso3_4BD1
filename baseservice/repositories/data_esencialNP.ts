import { getEnabledCategories } from 'trace_events';
import { Logger } from '../common'

export class data_esencialNP {
    private static instance: data_esencialNP;
    private log: Logger;

    private constructor()
    {
        this.log = new Logger();
        this.log.info("creando objeto");
        // via singleton, accediendo a un solo pool tengo una conexiona la base de datos
    }

    private sleep(milliseconds) {
        const date = Date.now();
        let currentDate = null;
        do {
          currentDate = Date.now();
        } while (currentDate - date < milliseconds);
    }

    public static getInstance() : data_esencialNP
    {
        if (!this.instance)
        {
            this.instance = new data_esencialNP();
        }
        return this.instance;
    }

    public async getLotes() : Promise<any>{

        const sqlNP = require('tedious')
        
        const sqlNPConfig = {
            server: 'localhost',
            authentication: {
                type: 'default',
                options: {
                    userName: 'sa',
                    password: 'Twinsociety235'
                }
            },
            options: {
                database: 'ev34',
                port: 1433,
                encrypt: true,
                trustServerCertificate: true,
                rowCollectionOnDone: true,
                rowCollectionOnRequestCompletion: true,
                useColumnNames: true
            }
        }

        return new Promise<any> ((resolve, reject) => {
            const connection = new sqlNP.Connection(sqlNPConfig);

            connection.on("connect", (err:any) => {
                if (err) {
                    console.error(err.message);
                } else {
                    const result = [];
                    const request = new sqlNP.Request('SP_getLotes', (err:any, rowCount: number, rows:any) => {
                        if (err) {
                            reject(err);
                        } else {
                            const dictionary = {};
                            dictionary["recordsets"] = result;
                            resolve({rows});
                            console.log('DONE');
                            console.log({rows});
                            //this.sleep(20000);
                            connection.close();
                        }
                    });
                    
                    /*
                    request.on("row", (columns:any) => {
                        const entry = {};
                        columns.forEach((column:any) => {
                          entry[column.metadata.colName] = column.value;
                        });
                        result.push(entry);
                    });*/
                    connection.callProcedure(request);
                };
            });

            connection.connect();
        });
    }
}

