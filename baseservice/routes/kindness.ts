import * as express from 'express';
const app = express();

const saludos = ["hola", "buen día","pura vida", "matice", "pasela ak 6+1", "buena vibra"];

app.get("/saludos", (req, res,next) => {
    res.json({ message: saludos[Math.trunc(Math.random()*saludos.length)]});
});

app.post("/saludosTo", (req, res,next) => {
    res.json({ message: saludos[Math.trunc(Math.random()*saludos.length)]+ " "+req.body.quien});
});

export { app as kindnessrouter };