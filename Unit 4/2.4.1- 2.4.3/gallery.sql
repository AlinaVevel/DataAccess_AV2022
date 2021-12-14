--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

-- Started on 2021-12-10 19:11:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "Gallery";
--
-- TOC entry 3345 (class 1262 OID 24772)
-- Name: Gallery; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "Gallery" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


ALTER DATABASE "Gallery" OWNER TO postgres;

\connect "Gallery"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 850 (class 1247 OID 24822)
-- Name: posint; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.posint AS double precision
	CONSTRAINT posint_check CHECK ((VALUE > (0)::double precision));


ALTER DOMAIN public.posint OWNER TO postgres;

--
-- TOC entry 841 (class 1247 OID 24807)
-- Name: dimensionstype; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.dimensionstype AS (
	width public.posint,
	height public.posint
);


ALTER TYPE public.dimensionstype OWNER TO postgres;

--
-- TOC entry 835 (class 1247 OID 24791)
-- Name: materialtypes; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.materialtypes AS ENUM (
    'iron',
    'bronze',
    'marbie'
);


ALTER TYPE public.materialtypes OWNER TO postgres;

--
-- TOC entry 838 (class 1247 OID 24798)
-- Name: paintingtypes; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.paintingtypes AS ENUM (
    'oilPainting',
    'waterColor',
    'pastel'
);


ALTER TYPE public.paintingtypes OWNER TO postgres;

--
-- TOC entry 829 (class 1247 OID 24779)
-- Name: styles; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.styles AS ENUM (
    'grecoRoman',
    'neoClassic',
    'Cubism'
);


ALTER TYPE public.styles OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 24785)
-- Name: artwork; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artwork (
    code integer NOT NULL,
    title character varying(100),
    dated date NOT NULL,
    styles public.styles,
    codeauthor character varying(20)
);


ALTER TABLE public.artwork OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24773)
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.author (
    code character varying(20) NOT NULL,
    name character varying(50),
    nationality character varying(50)
);


ALTER TABLE public.author OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 24808)
-- Name: painting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.painting (
    type public.paintingtypes,
    dimensions public.dimensionstype
)
INHERITS (public.artwork);


ALTER TABLE public.painting OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 24813)
-- Name: sculpture; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sculpture (
    material public.materialtypes,
    weight public.posint
)
INHERITS (public.artwork);


ALTER TABLE public.sculpture OWNER TO postgres;

--
-- TOC entry 3337 (class 0 OID 24785)
-- Dependencies: 210
-- Data for Name: artwork; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3336 (class 0 OID 24773)
-- Dependencies: 209
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3338 (class 0 OID 24808)
-- Dependencies: 212
-- Data for Name: painting; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3339 (class 0 OID 24813)
-- Dependencies: 213
-- Data for Name: sculpture; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3195 (class 2606 OID 24789)
-- Name: artwork artwork_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artwork
    ADD CONSTRAINT artwork_pkey PRIMARY KEY (code);


--
-- TOC entry 3193 (class 2606 OID 24777)
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (code);


--
-- TOC entry 3196 (class 2606 OID 24816)
-- Name: artwork artwork_codeauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artwork
    ADD CONSTRAINT artwork_codeauthor_fkey FOREIGN KEY (codeauthor) REFERENCES public.author(code);


-- Completed on 2021-12-10 19:11:07

--
-- PostgreSQL database dump complete
--

