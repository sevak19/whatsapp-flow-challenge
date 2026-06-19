const wppconnect = require('@wppconnect-team/wppconnect');
const express = require('express');
const app = express();
app.use(express.json());

let client = null;

wppconnect.create({
    session: 'minha-sessao',
    puppeteerOptions: {
        executablePath: '/mnt/c/Program Files/Google/Chrome/Application/chrome.exe',
        args: ['--no-sandbox', '--disable-setuid-sandbox', '--disable-dev-shm-usage']
    }
}).then((c) => {
    client = c;
    console.log('[WPP] Sessão iniciada!');
}).catch(console.error);

app.post('/api/minha-sessao/send-message', async (req, res) => {
    const { phone, message } = req.body;
    try {
        await client.sendText(phone, message);
        console.log('[WPP] Enviado para ' + phone + ': ' + message);
        res.json({ success: true });
    } catch (e) {
        console.error('[WPP] Erro:', e.message);
        res.status(500).json({ error: e.message });
    }
});

app.listen(21465, () => console.log('[WPP] Servidor rodando na porta 21465'));
