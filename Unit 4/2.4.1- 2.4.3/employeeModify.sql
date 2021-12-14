--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

-- Started on 2021-12-14 16:22:52

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

DROP DATABASE employees;
--
-- TOC entry 3334 (class 1262 OID 24595)
-- Name: employees; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE employees WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


ALTER DATABASE employees OWNER TO postgres;

\connect employees

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
-- TOC entry 832 (class 1247 OID 24948)
-- Name: idcheck; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.idcheck AS integer
	CONSTRAINT idcheck_check CHECK (((VALUE)::text ~~ '7___'::text));


ALTER DOMAIN public.idcheck OWNER TO postgres;

--
-- TOC entry 842 (class 1247 OID 24978)
-- Name: namedepartments; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.namedepartments AS ENUM (
    'ACCOUNTING',
    'RESEARCH',
    'SALES',
    'OPERATIONS'
);


ALTER TYPE public.namedepartments OWNER TO postgres;

--
-- TOC entry 836 (class 1247 OID 24957)
-- Name: namejobs; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.namejobs AS ENUM (
    'CLERK',
    'SALESMAN',
    'MANAGER',
    'ANALYST',
    'PRESIDENT'
);


ALTER TYPE public.namejobs OWNER TO postgres;

--
-- TOC entry 839 (class 1247 OID 24968)
-- Name: namelocation; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.namelocation AS ENUM (
    'NEW YORK',
    'DALLAS',
    'CHICAGO',
    'BOSTON'
);


ALTER TYPE public.namelocation OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 24601)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    empno public.idcheck NOT NULL,
    ename character varying(10),
    deptno integer,
    job public.namejobs
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 212 (class 1255 OID 24631)
-- Name: listofemployee(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.listofemployee(jobt character varying) RETURNS SETOF public.employee
    LANGUAGE plpgsql
    AS $$
declare
enames employee;
begin
for enames in
select * from employee
where job = jobT
loop return next enames;
end loop;
end;
$$;


ALTER FUNCTION public.listofemployee(jobt character varying) OWNER TO postgres;

--
-- TOC entry 211 (class 1255 OID 24632)
-- Name: listofemployeeindepart(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.listofemployeeindepart(department integer) RETURNS SETOF public.employee
    LANGUAGE plpgsql
    AS $$
declare
enames employee;
begin
for enames in
select * from employee
where deptno = department
loop return next enames;
end loop;
end;
$$;


ALTER FUNCTION public.listofemployeeindepart(department integer) OWNER TO postgres;

--
-- TOC entry 213 (class 1255 OID 24633)
-- Name: listofemployeewithpattern(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.listofemployeewithpattern(pattern character varying) RETURNS SETOF public.employee
    LANGUAGE plpgsql
    AS $$
declare
enames employee;
begin
for enames in
select * from employee
where ename like pattern

loop return next enames;
end loop;
end;
$$;


ALTER FUNCTION public.listofemployeewithpattern(pattern character varying) OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24596)
-- Name: dept; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dept (
    deptno integer NOT NULL,
    dname public.namedepartments,
    loc public.namelocation
);


ALTER TABLE public.dept OWNER TO postgres;

--
-- TOC entry 3327 (class 0 OID 24596)
-- Dependencies: 209
-- Data for Name: dept; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dept (deptno, dname, loc) VALUES (10, NULL, NULL);
INSERT INTO public.dept (deptno, dname, loc) VALUES (20, NULL, NULL);
INSERT INTO public.dept (deptno, dname, loc) VALUES (30, NULL, NULL);
INSERT INTO public.dept (deptno, dname, loc) VALUES (40, NULL, NULL);
INSERT INTO public.dept (deptno, dname, loc) VALUES (80, 'SALES', 'CHICAGO');


--
-- TOC entry 3328 (class 0 OID 24601)
-- Dependencies: 210
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7369, 'SMITH', 20, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7499, 'ALLEN', 30, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7521, 'WARD', 30, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7566, 'JONES', 20, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7654, 'MARTIN', 30, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7698, 'BLAKE', 30, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7782, 'CLARK', 10, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7788, 'SCOTT', 20, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7839, 'KING', 10, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7844, 'TURNER', 30, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7876, 'ADAMS', 20, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7900, 'JAMES', 30, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7902, 'FORD', 20, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7934, 'MILLER', 10, NULL);
INSERT INTO public.employee (empno, ename, deptno, job) VALUES (7975, 'STARK', 20, 'SALESMAN');


--
-- TOC entry 3184 (class 2606 OID 24600)
-- Name: dept dept_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dept
    ADD CONSTRAINT dept_pkey PRIMARY KEY (deptno);


--
-- TOC entry 3186 (class 2606 OID 24951)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (empno);


--
-- TOC entry 3187 (class 2606 OID 24606)
-- Name: employee fk_employee_dpt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fk_employee_dpt FOREIGN KEY (deptno) REFERENCES public.dept(deptno);


-- Completed on 2021-12-14 16:22:53

--
-- PostgreSQL database dump complete
--

