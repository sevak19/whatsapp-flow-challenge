"use client";
import { useState } from "react";

export default function FlowsPage() {
  const [flowName, setFlowName] = useState("");
  const [flowId, setFlowId] = useState<number | null>(null);
  const [steps, setSteps] = useState([{ message: "", delayMinutes: 0 }]);
  const [result, setResult] = useState("");

  async function handleCreateFlow() {
    const res = await fetch("http://localhost:8080/flows", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name: flowName }),
    });
    const data = await res.json();
    setFlowId(data.id);
    setResult(`Fluxo criado com ID ${data.id}. Agora adicione as etapas e inicie.`);
  }

  async function handleSaveSteps() {
    if (!flowId) return;
    for (let i = 0; i < steps.length; i++) {
      await fetch("http://localhost:8080/flow-steps", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ flow: { id: flowId }, stepOrder: i + 1, message: steps[i].message, delayMinutes: steps[i].delayMinutes }),
      });
    }
    setResult(`${steps.length} etapa(s) salva(s).`);
  }

  async function handleStart() {
    if (!flowId) return;
    const res = await fetch(`http://localhost:8080/flows/${flowId}/start`, { method: "POST" });
    setResult(await res.text());
  }

  function addStep() {
    setSteps([...steps, { message: "", delayMinutes: 0 }]);
  }

  function updateStep(i: number, field: string, value: string | number) {
    const updated = [...steps];
    updated[i] = { ...updated[i], [field]: value };
    setSteps(updated);
  }

  return (
    <div className="max-w-2xl">
      <h1 className="text-2xl font-bold mb-6">Fluxos</h1>

      <div className="bg-black p-4 rounded shadow mb-6 flex flex-col gap-3">
        <h2 className="font-semibold">1. Criar fluxo</h2>
        <input placeholder="Nome do fluxo" value={flowName} onChange={e => setFlowName(e.target.value)} className="border p-2 rounded" />
        <button onClick={handleCreateFlow} className="bg-green-600 text-white px-4 py-2 rounded">Criar fluxo</button>
      </div>

      {flowId && (
        <>
          <div className="bg-black p-4 rounded shadow mb-6 flex flex-col gap-3">
            <h2 className="font-semibold">2. Etapas do fluxo</h2>
            {steps.map((step, i) => (
              <div key={i} className="flex gap-2 items-center">
                <span className="text-sm text-gray-500 w-6">{i + 1}.</span>
                <input placeholder="Mensagem" value={step.message} onChange={e => updateStep(i, "message", e.target.value)} className="border p-2 rounded flex-1" />
                <input type="number" placeholder="Delay (min)" value={step.delayMinutes} onChange={e => updateStep(i, "delayMinutes", +e.target.value)} className="border p-2 rounded w-28" />
              </div>
            ))}
            <button onClick={addStep} className="text-green-600 text-sm">+ Adicionar etapa</button>
            <button onClick={handleSaveSteps} className="bg-green-600 text-white px-4 py-2 rounded">Salvar etapas</button>
          </div>

          <div className="bg-black p-4 rounded shadow mb-6">
            <h2 className="font-semibold mb-3">3. Iniciar fluxo</h2>
            <button onClick={handleStart} className="bg-green-600 text-white px-4 py-2 rounded">Iniciar para todos os contatos</button>
          </div>
        </>
      )}

      {result && <p className="text-green-700">{result}</p>}
    </div>
  );
}
