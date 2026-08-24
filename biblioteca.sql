--
-- PostgreSQL database dump
--

\restrict nTj3GRI7zKU4fjUhKgfRAgWgxyEPcaWb0iWilWCSt5I0C3GbOLURcSe3xRjZFtq

-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

-- Started on 2026-08-24 11:31:12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 24626)
-- Name: autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autor (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    nacionalidade character varying(50)
);


ALTER TABLE public.autor OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24625)
-- Name: autor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.autor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.autor_id_seq OWNER TO postgres;

--
-- TOC entry 5098 (class 0 OID 0)
-- Dependencies: 225
-- Name: autor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.autor_id_seq OWNED BY public.autor.id;


--
-- TOC entry 228 (class 1259 OID 24635)
-- Name: editora; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.editora (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    cidade character varying(50)
);


ALTER TABLE public.editora OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24634)
-- Name: editora_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.editora_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.editora_id_seq OWNER TO postgres;

--
-- TOC entry 5099 (class 0 OID 0)
-- Dependencies: 227
-- Name: editora_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.editora_id_seq OWNED BY public.editora.id;


--
-- TOC entry 233 (class 1259 OID 24670)
-- Name: emprestimo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emprestimo (
    id integer NOT NULL,
    leitor_id integer NOT NULL,
    livro_id integer NOT NULL,
    funcionario_id integer NOT NULL,
    data_emprestimo date DEFAULT CURRENT_DATE,
    data_devolucao date,
    status character varying(20) DEFAULT 'EMPRESTADO'::character varying
);


ALTER TABLE public.emprestimo OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 24669)
-- Name: emprestimo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprestimo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.emprestimo_id_seq OWNER TO postgres;

--
-- TOC entry 5100 (class 0 OID 0)
-- Dependencies: 232
-- Name: emprestimo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.emprestimo_id_seq OWNED BY public.emprestimo.id;


--
-- TOC entry 222 (class 1259 OID 24592)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
    id integer NOT NULL,
    pessoa_id integer NOT NULL,
    cargo character varying(50),
    salario numeric(10,2)
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24591)
-- Name: funcionario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.funcionario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.funcionario_id_seq OWNER TO postgres;

--
-- TOC entry 5101 (class 0 OID 0)
-- Dependencies: 221
-- Name: funcionario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.funcionario_id_seq OWNED BY public.funcionario.id;


--
-- TOC entry 224 (class 1259 OID 24608)
-- Name: leitor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.leitor (
    id integer NOT NULL,
    pessoa_id integer NOT NULL,
    matricula character varying(20),
    data_cadastro date
);


ALTER TABLE public.leitor OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24607)
-- Name: leitor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.leitor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.leitor_id_seq OWNER TO postgres;

--
-- TOC entry 5102 (class 0 OID 0)
-- Dependencies: 223
-- Name: leitor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.leitor_id_seq OWNED BY public.leitor.id;


--
-- TOC entry 230 (class 1259 OID 24644)
-- Name: livro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livro (
    codigo integer NOT NULL,
    titulo character varying(200) NOT NULL,
    autor_id integer NOT NULL,
    editora_id integer NOT NULL,
    ano_publicacao integer,
    categoria character varying(50),
    disponibilidade boolean DEFAULT true
);


ALTER TABLE public.livro OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24643)
-- Name: livro_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livro_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livro_codigo_seq OWNER TO postgres;

--
-- TOC entry 5103 (class 0 OID 0)
-- Dependencies: 229
-- Name: livro_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livro_codigo_seq OWNED BY public.livro.codigo;


--
-- TOC entry 220 (class 1259 OID 24581)
-- Name: pessoa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    cpf character varying(14),
    telefone character varying(20),
    email character varying(100)
);


ALTER TABLE public.pessoa OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24580)
-- Name: pessoa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pessoa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pessoa_id_seq OWNER TO postgres;

--
-- TOC entry 5104 (class 0 OID 0)
-- Dependencies: 219
-- Name: pessoa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pessoa_id_seq OWNED BY public.pessoa.id;


--
-- TOC entry 234 (class 1259 OID 24682)
-- Name: vw_emprestimos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_emprestimos AS
 SELECT emp.id,
    p.nome AS leitor,
    l.titulo,
    pf.nome AS funcionario,
    emp.data_emprestimo,
    emp.data_devolucao,
    emp.status
   FROM (((((public.emprestimo emp
     JOIN public.leitor le ON ((emp.leitor_id = le.id)))
     JOIN public.pessoa p ON ((le.pessoa_id = p.id)))
     JOIN public.livro l ON ((emp.livro_id = l.codigo)))
     JOIN public.funcionario f ON ((emp.funcionario_id = f.id)))
     JOIN public.pessoa pf ON ((f.pessoa_id = pf.id)));


ALTER VIEW public.vw_emprestimos OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 24665)
-- Name: vw_livros; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_livros AS
 SELECT l.codigo,
    l.titulo,
    a.nome AS autor,
    e.nome AS editora,
    l.ano_publicacao,
    l.categoria,
    l.disponibilidade
   FROM ((public.livro l
     JOIN public.autor a ON ((l.autor_id = a.id)))
     JOIN public.editora e ON ((l.editora_id = e.id)));


ALTER VIEW public.vw_livros OWNER TO postgres;

--
-- TOC entry 4897 (class 2604 OID 24629)
-- Name: autor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor ALTER COLUMN id SET DEFAULT nextval('public.autor_id_seq'::regclass);


--
-- TOC entry 4898 (class 2604 OID 24638)
-- Name: editora id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editora ALTER COLUMN id SET DEFAULT nextval('public.editora_id_seq'::regclass);


--
-- TOC entry 4901 (class 2604 OID 24673)
-- Name: emprestimo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprestimo ALTER COLUMN id SET DEFAULT nextval('public.emprestimo_id_seq'::regclass);


--
-- TOC entry 4895 (class 2604 OID 24595)
-- Name: funcionario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id SET DEFAULT nextval('public.funcionario_id_seq'::regclass);


--
-- TOC entry 4896 (class 2604 OID 24611)
-- Name: leitor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leitor ALTER COLUMN id SET DEFAULT nextval('public.leitor_id_seq'::regclass);


--
-- TOC entry 4899 (class 2604 OID 24647)
-- Name: livro codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro ALTER COLUMN codigo SET DEFAULT nextval('public.livro_codigo_seq'::regclass);


--
-- TOC entry 4894 (class 2604 OID 24584)
-- Name: pessoa id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa ALTER COLUMN id SET DEFAULT nextval('public.pessoa_id_seq'::regclass);


--
-- TOC entry 5086 (class 0 OID 24626)
-- Dependencies: 226
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.autor (id, nome, nacionalidade) FROM stdin;
\.


--
-- TOC entry 5088 (class 0 OID 24635)
-- Dependencies: 228
-- Data for Name: editora; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.editora (id, nome, cidade) FROM stdin;
\.


--
-- TOC entry 5092 (class 0 OID 24670)
-- Dependencies: 233
-- Data for Name: emprestimo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emprestimo (id, leitor_id, livro_id, funcionario_id, data_emprestimo, data_devolucao, status) FROM stdin;
\.


--
-- TOC entry 5082 (class 0 OID 24592)
-- Dependencies: 222
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionario (id, pessoa_id, cargo, salario) FROM stdin;
\.


--
-- TOC entry 5084 (class 0 OID 24608)
-- Dependencies: 224
-- Data for Name: leitor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.leitor (id, pessoa_id, matricula, data_cadastro) FROM stdin;
\.


--
-- TOC entry 5090 (class 0 OID 24644)
-- Dependencies: 230
-- Data for Name: livro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.livro (codigo, titulo, autor_id, editora_id, ano_publicacao, categoria, disponibilidade) FROM stdin;
\.


--
-- TOC entry 5080 (class 0 OID 24581)
-- Dependencies: 220
-- Data for Name: pessoa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pessoa (id, nome, cpf, telefone, email) FROM stdin;
\.


--
-- TOC entry 5105 (class 0 OID 0)
-- Dependencies: 225
-- Name: autor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.autor_id_seq', 1, false);


--
-- TOC entry 5106 (class 0 OID 0)
-- Dependencies: 227
-- Name: editora_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.editora_id_seq', 1, false);


--
-- TOC entry 5107 (class 0 OID 0)
-- Dependencies: 232
-- Name: emprestimo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprestimo_id_seq', 1, false);


--
-- TOC entry 5108 (class 0 OID 0)
-- Dependencies: 221
-- Name: funcionario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.funcionario_id_seq', 1, false);


--
-- TOC entry 5109 (class 0 OID 0)
-- Dependencies: 223
-- Name: leitor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.leitor_id_seq', 1, false);


--
-- TOC entry 5110 (class 0 OID 0)
-- Dependencies: 229
-- Name: livro_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livro_codigo_seq', 1, false);


--
-- TOC entry 5111 (class 0 OID 0)
-- Dependencies: 219
-- Name: pessoa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pessoa_id_seq', 1, false);


--
-- TOC entry 4919 (class 2606 OID 24633)
-- Name: autor autor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (id);


--
-- TOC entry 4921 (class 2606 OID 24642)
-- Name: editora editora_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editora
    ADD CONSTRAINT editora_pkey PRIMARY KEY (id);


--
-- TOC entry 4925 (class 2606 OID 24681)
-- Name: emprestimo emprestimo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_pkey PRIMARY KEY (id);


--
-- TOC entry 4909 (class 2606 OID 24601)
-- Name: funcionario funcionario_pessoa_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pessoa_id_key UNIQUE (pessoa_id);


--
-- TOC entry 4911 (class 2606 OID 24599)
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id);


--
-- TOC entry 4913 (class 2606 OID 24619)
-- Name: leitor leitor_matricula_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leitor
    ADD CONSTRAINT leitor_matricula_key UNIQUE (matricula);


--
-- TOC entry 4915 (class 2606 OID 24617)
-- Name: leitor leitor_pessoa_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leitor
    ADD CONSTRAINT leitor_pessoa_id_key UNIQUE (pessoa_id);


--
-- TOC entry 4917 (class 2606 OID 24615)
-- Name: leitor leitor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leitor
    ADD CONSTRAINT leitor_pkey PRIMARY KEY (id);


--
-- TOC entry 4923 (class 2606 OID 24654)
-- Name: livro livro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_pkey PRIMARY KEY (codigo);


--
-- TOC entry 4905 (class 2606 OID 24590)
-- Name: pessoa pessoa_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_cpf_key UNIQUE (cpf);


--
-- TOC entry 4907 (class 2606 OID 24588)
-- Name: pessoa pessoa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);


--
-- TOC entry 4926 (class 2606 OID 24602)
-- Name: funcionario fk_funcionario_pessoa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT fk_funcionario_pessoa FOREIGN KEY (pessoa_id) REFERENCES public.pessoa(id);


--
-- TOC entry 4927 (class 2606 OID 24620)
-- Name: leitor fk_leitor_pessoa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leitor
    ADD CONSTRAINT fk_leitor_pessoa FOREIGN KEY (pessoa_id) REFERENCES public.pessoa(id);


--
-- TOC entry 4928 (class 2606 OID 24655)
-- Name: livro fk_livro_autor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro
    ADD CONSTRAINT fk_livro_autor FOREIGN KEY (autor_id) REFERENCES public.autor(id);


--
-- TOC entry 4929 (class 2606 OID 24660)
-- Name: livro fk_livro_editora; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro
    ADD CONSTRAINT fk_livro_editora FOREIGN KEY (editora_id) REFERENCES public.editora(id);


-- Completed on 2026-08-24 11:31:13

--
-- PostgreSQL database dump complete
--

\unrestrict nTj3GRI7zKU4fjUhKgfRAgWgxyEPcaWb0iWilWCSt5I0C3GbOLURcSe3xRjZFtq

