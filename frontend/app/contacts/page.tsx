"use client";
import { useState, useEffect } from "react";

type Contact = { id: number; name: string; phone: string };

export default function ContactsPage() {
  const [file, setFile] = useState<File | null>(null);
  const [result, setResult] = useState("");
  const [loading, setLoading] = useState(false);
  const [contacts, setContacts] = useState<Contact[]>([]);

  async function load() {
    const res = await fetch("http://localhost:8080/contacts");
    setContacts(await res.json());
  }

  useEffect(() => { load(); }, []);

  async function handleImport() {
    if (!file) return;
    setLoading(true);
    try {
      const form = new FormData();
      form.append("file", file);
      const res = await fetch("http://localhost:8080/contacts/import", {
        method: "POST",
        body: form,
      });
      const text = await res.text();
      setResult(text);
      load();
    } catch (e) {
      setResult("Erro ao importar. Verifique se o backend está rodando.");
    }
    setLoading(false);
  }

  return (
    <div className="max-w-2xl">
      <h1 className="text-2xl font-bold mb-6">Contatos</h1>

      <div className="bg-black p-4 rounded shadow mb-6 flex flex-col gap-3">
        <h2 className="font-semibold">Importar CSV</h2>
        <p className="text-gray-500 text-sm">Formato: <code>nome,telefone</code></p>
        <input type="file" accept=".csv" onChange={(e) => setFile(e.target.files?.[0] ?? null)} />
        <button
          onClick={handleImport}
          disabled={!file || loading}
          className="bg-green-600 text-white px-4 py-2 rounded disabled:opacity-50"
        >
          {loading ? "Importando..." : "Importar"}
        </button>
        {result && <p className="text-green-700 text-sm">{result}</p>}
      </div>

      <div className="bg-black rounded shadow overflow-hidden">
        <table className="w-full text-sm">
          <thead className="bg-black-100">
            <tr>
              <th className="text-left p-3">Nome</th>
              <th className="text-left p-3">Telefone</th>
            </tr>
          </thead>
          <tbody>
            {contacts.length === 0 && (
              <tr><td colSpan={2} className="p-3 text-gray-400">Nenhum contato cadastrado.</td></tr>
            )}
            {contacts.map(c => (
              <tr key={c.id} className="border-t">
                <td className="p-3">{c.name}</td>
                <td className="p-3">{c.phone}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
