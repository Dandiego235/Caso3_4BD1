import { data_esencialNP } from '../repositories/data_esencialNP'
import { Logger } from '../common'


export class EsencialControllerNP {
    private static instance: EsencialControllerNP;
    private log: Logger;

    private constructor()
    {
        this.log = new Logger();
        try
        {
        } catch (e)
        {
            this.log.error(e);
        }
    }

    public static getInstance() : EsencialControllerNP
    {
        if (!this.instance)
        {
            this.instance = new EsencialControllerNP();
        }
        return this.instance;
    }


    
                    // request.on('row', function(columns) {
                    //     columns.forEach(function(column) {
                    //         console.log(column.metadata.colName + ' = ' + column.value);
                    //     })
                        
                    // })
        /*
        const connection = new sqlNP.Connection(sqlNPConfig);
    
        connection.on("connect", (err:any) => {
            if (err) {
                console.error(err.message);
            } else {
                getLotesSP();
            }
        })


        connection.on('debug', function(err:any) { console.log('debug:', err);});
        
        await connection.connect();

        async function getLotesSP() {
            res = await new Promise<any> ((resolve, reject) => {
                const result = [];
                const request = new sqlNP.Request('SP_getLotes', (err:any, rowCount: number, rows:any) => {
                    if (err) {
                        reject(err);
                    } else {
                        const dictionary = {};
                        dictionary["recordsets"] = result;
                        resolve(dictionary);
                        console.log('DONE');
                        console.log({rows});
                        connection.close();
                    }
                });

                request.on("row", (columns:any) => {
                    const entry = {};
                    columns.forEach((column:any) => {
                      entry[column.metadata.colName] = column.value;
                    });
                    result.push(entry);
                });

                // request.on('row', function(columns) {
                //     columns.forEach(function(column) {
                //         console.log(column.metadata.colName + ' = ' + column.value);
                //     })
                    
                // })
            
                connection.callProcedure(request);
            }
            )
            console.log("End request" + res);
        }

        await console.log("End res" + res);
        return res;
        */
    
    public getLotes() : Promise<any> 
    {
        const esencialNPdata = data_esencialNP.getInstance();
        return esencialNPdata.getLotes();
    }

    /*
    public listArticles() : Promise<any> 
    {
        const dynamo = new articles_data();
        return dynamo.getAllArticles();
    }
    */
}