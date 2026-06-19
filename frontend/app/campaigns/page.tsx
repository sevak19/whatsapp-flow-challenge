"use client";
import { useState, useEffect } from "react";

type Campaign = { id: number; name: string; message: string; minDelaySeconds: number; maxDelaySeconds: number };

export default function CampaignsPage() {
  const [campaigns, setCampaigns] = useState<Campaign[]>([]);
  const [name, setName] = useState("");
  const [message, setMessage] = useState("");
  const [minDelay, setMinDelay] = useState(5);
  const [maxDelay, setMaxDelay] = useState(15);
  const [result, setResult] = useState("");

  async function load() {
    const res = await fetch("http://localhost:8080/campaigns");
    setCampaigns(await res.json());
  }

  useEffect(() => { load(); }, []);

  async function handleCreate() {
    await fetch("http://localhost:8080/campaigns", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, message, minDelaySeconds: minDelay, maxDelaySeconds: maxDelay }),
    });
    setName(""); setMessage("");
    load();
  }

  async function handleDispatch(id: number) {
    const res = await fetch(`http://localhost:8080/campaigns/${id}/dispatch`, { method: "POST" });
    setResult(await res.text());
  }

  return (
    <div className="max-w-2xl">
      <h1 className="text-2xl font-bold mb-6">Campanhas</h1>

      <div className="bg-black p-4 rounded shadow mb-6 flex flex-col gap-3">
        <h2 className="font-semibold">Nova campanha</h2>
        <input placeholder="Nome" value={name} onChange={e => setName(e.target.value)} className="border p-2 rounded" />
        <textarea placeholder="Mensagem" value={message} onChange={e => setMessage(e.target.value)} className="border p-2 rounded" />
        <div className="flex gap-3">
          <input type="number" placeholder="Delay mín (s)" value={minDelay} onChange={e => setMinDelay(+e.target.value)} className="border p-2 rounded w-full" />
          <input type="number" placeholder="Delay máx (s)" value={maxDelay} onChange={e => setMaxDelay(+e.target.value)} className="border p-2 rounded w-full" />
        </div>
        <button onClick={handleCreate} className="bg-green-600 text-white px-4 py-2 rounded">Criar</button>
      </div>

      {result && <p className="mb-4 text-green-700">{result}</p>}

      <div className="flex flex-col gap-3">
        {campaigns.map(c => (
          <div key={c.id} className="bg-black p-4 rounded shadow flex justify-between items-center">
            <div>
              <p className="font-semibold">{c.name}</p>
              <p className="text-sm text-gray-500">{c.message}</p>
            </div>
            <button onClick={() => handleDispatch(c.id)} className="bg-green-600 text-white px-3 py-1 rounded text-sm">
              Disparar
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
