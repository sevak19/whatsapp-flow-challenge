# WhatsApp Flow Challenge

Sistema de disparo automático de mensagens via WhatsApp com suporte a campanhas e fluxos sequenciais.

## Tecnologias

- **Backend:** Java 21 + Spring Boot 4.1
- **Banco de dados:** PostgreSQL 17 (Docker)
- **Migrations:** Flyway
- **Agendamento:** Spring Scheduler
- **Frontend:** Next.js 16 + Tailwind CSS
- **Envio:** WPPConnect (mock por padrão)

## Como rodar

### Pré-requisitos

- Java 21
- Docker
- Node.js 18+

### 1. Banco de dados

```bash
cd backend
docker compose up -d
```

### 2. Backend

```bash
cd backend
./mvnw spring-boot:run
```

Sobe na porta `8080`.

### 3. Frontend

```bash
cd frontend
npm install
npm run dev
```

Sobe na porta `3000`.

---

## Funcionalidades

### Contatos
- Importação via CSV com colunas `nome,telefone`
- Listagem de contatos cadastrados

### Campanhas
- Criar campanha com mensagem e delay mín/máx em segundos
- Disparar campanha para todos os contatos
- Cada contato recebe a mensagem com delay acumulado sequencial

### Fluxos
- Criar fluxo com etapas sequenciais
- Cada etapa tem mensagem e delay em minutos
- Iniciar fluxo para todos os contatos
- Cada contato executa o fluxo de forma independente

---

## Arquitetura
Controller → Service → send_queue (banco) → QueueScheduler → WppConnectService

O envio nunca ocorre direto no controller. Tudo passa pela fila persistida no banco, processada pelo scheduler a cada 1 segundo.

O fluxo funciona de forma similar com `flow_executions` e `FlowScheduler`.

---

## Banco de dados

| Tabela | Descrição |
|--------|-----------|
| `contacts` | Contatos importados |
| `campaigns` | Campanhas de disparo |
| `send_queue` | Fila de mensagens pendentes |
| `flows` | Fluxos de mensagens |
| `flow_steps` | Etapas de cada fluxo |
| `flow_executions` | Execução individual por contato |

---

## WPPConnect

Por padrão o sistema roda em modo mock — os envios são logados no console.

Para ativar o envio real, configure o `application.yaml`:

```yaml
wppconnect:
  url: http://localhost:21465
  token: seu-token
  session: minha-sessao
```