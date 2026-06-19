import type { Metadata } from "next";
import { Geist } from "next/font/google";
import "./globals.css";
import Link from "next/link";

const geist = Geist({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "WhatsApp Flow",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="pt" className={geist.className}>
      <body className="min-h-screen bg-gray-50">
        <nav className="bg-green-600 text-white px-6 py-4 flex gap-6">
          <Link href="/" className="font-bold text-lg">WhatsApp Flow</Link>
          <Link href="/contacts" className="hover:underline">Contatos</Link>
          <Link href="/campaigns" className="hover:underline">Campanhas</Link>
          <Link href="/flows" className="hover:underline">Fluxos</Link>
        </nav>
        <main className="p-6">{children}</main>
      </body>
    </html>
  );
}
