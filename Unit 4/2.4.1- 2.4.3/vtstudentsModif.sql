--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

-- Started on 2021-12-10 19:59:39

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

DROP DATABASE "VTInstitute";
--
-- TOC entry 3380 (class 1262 OID 24582)
-- Name: VTInstitute; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "VTInstitute" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


ALTER DATABASE "VTInstitute" OWNER TO postgres;

\connect "VTInstitute"

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
-- TOC entry 855 (class 1247 OID 24850)
-- Name: coursesname; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.coursesname AS ENUM (
    'DAM',
    'DAW',
    'Finances',
    'RRHH',
    'ASIR',
    'SMR'
);


ALTER TYPE public.coursesname OWNER TO postgres;

--
-- TOC entry 858 (class 1247 OID 24867)
-- Name: hours; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.hours AS double precision
	CONSTRAINT hours_check CHECK ((VALUE > (0)::double precision));


ALTER DOMAIN public.hours OWNER TO postgres;

--
-- TOC entry 865 (class 1247 OID 24926)
-- Name: iscardcheck; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.iscardcheck AS (
	letters character varying(2),
	numbers integer
);


ALTER TYPE public.iscardcheck OWNER TO postgres;

--
-- TOC entry 851 (class 1247 OID 24830)
-- Name: notas; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.notas AS double precision
	CONSTRAINT notas_check CHECK (((VALUE >= (0)::double precision) AND (VALUE <= (10)::double precision)));


ALTER DOMAIN public.notas OWNER TO postgres;

--
-- TOC entry 862 (class 1247 OID 24888)
-- Name: subjectsname; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.subjectsname AS ENUM (
    'DATA ACCES',
    'INTERFACE DESIGN',
    'PROCESS AND THREADS',
    'MILTIMEDIA',
    'FOL2',
    'ENGLISH2',
    'Mathematic',
    'FOL',
    'HTML/CSS',
    'DATA BASE',
    'PROGRAMMING',
    'ENGLISH1'
);


ALTER TYPE public.subjectsname OWNER TO postgres;

--
-- TOC entry 232 (class 1255 OID 24727)
-- Name: enrollstudent(character varying, integer); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.enrollstudent(IN idstudent character varying, IN idcourse integer)
    LANGUAGE plpgsql
    AS $$
DECLARE 
codeEroll INTEGER;
mysubject subjects;
BEGIN
	INSERT INTO enrollment(code,student, course) VALUES (default,idStudent, idCourse)
	returning "code" into codeEroll;
	FOR mysubject IN (SELECT * from subjects where course_id = idCourse)
	LOOP
		INSERT INTO scores(enrollmentid, subjectid,score) VALUES(codeEroll, mysubject.code, 0);
	END LOOP;
	
END;
$$;


ALTER PROCEDURE public.enrollstudent(IN idstudent character varying, IN idcourse integer) OWNER TO postgres;

--
-- TOC entry 220 (class 1255 OID 24695)
-- Name: ifuserexist(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.ifuserexist(ids character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
        
        BEGIN 
        
      		 if exists(select idcard from students where idcard like idS) then
	  			 return -1;
	   		else
	   			return 0;
        	end if;
       
        
        END;
   
$$;


ALTER FUNCTION public.ifuserexist(ids character varying) OWNER TO postgres;

--
-- TOC entry 234 (class 1255 OID 24739)
-- Name: studenscore(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.studenscore(enrollmentidstudent character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE 
myenrollid enrollment;
resultF integer;
BEGIN
	FOR myenrollid in (SELECT * from enrollment where student like enrollmentIdStudent)
	LOOP
		IF EXISTS (SELECT * FROM scores WHERE score < 5 AND enrollmentid = myenrollid.code) THEN
			resultF =  -1;
		ELSE
			resultF =  0;
		END IF;
		
		
	END LOOP;
	return resultF;
END;
$$;


ALTER FUNCTION public.studenscore(enrollmentidstudent character varying) OWNER TO postgres;

--
-- TOC entry 233 (class 1255 OID 24745)
-- Name: studentenrollsamecourse(character varying, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.studentenrollsamecourse(idstudent character varying, idcourse integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
 BEGIN 
        
      		 if exists(select course from enrollment where idStudent like student AND course = idCourse) then
	  			 return -1;
	   		else
	   			return 0;
        	end if;
       
        
        END;

$$;


ALTER FUNCTION public.studentenrollsamecourse(idstudent character varying, idcourse integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 212 (class 1259 OID 24612)
-- Name: courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.courses (
    code integer NOT NULL,
    name public.coursesname
);


ALTER TABLE public.courses OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 24611)
-- Name: courses_code_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.courses_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.courses_code_seq OWNER TO postgres;

--
-- TOC entry 3381 (class 0 OID 0)
-- Dependencies: 211
-- Name: courses_code_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.courses_code_seq OWNED BY public.courses.code;


--
-- TOC entry 215 (class 1259 OID 24640)
-- Name: enrollment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enrollment (
    code integer NOT NULL,
    course integer NOT NULL,
    student public.iscardcheck
);


ALTER TABLE public.enrollment OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 24639)
-- Name: enrollment_code_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.enrollment_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.enrollment_code_seq OWNER TO postgres;

--
-- TOC entry 3382 (class 0 OID 0)
-- Dependencies: 214
-- Name: enrollment_code_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.enrollment_code_seq OWNED BY public.enrollment.code;


--
-- TOC entry 218 (class 1259 OID 24658)
-- Name: scores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.scores (
    enrollmentid integer NOT NULL,
    subjectid integer NOT NULL,
    score public.notas
);


ALTER TABLE public.scores OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24656)
-- Name: scores_enrollmentid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.scores_enrollmentid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.scores_enrollmentid_seq OWNER TO postgres;

--
-- TOC entry 3383 (class 0 OID 0)
-- Dependencies: 216
-- Name: scores_enrollmentid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.scores_enrollmentid_seq OWNED BY public.scores.enrollmentid;


--
-- TOC entry 217 (class 1259 OID 24657)
-- Name: scores_subjectid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.scores_subjectid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.scores_subjectid_seq OWNER TO postgres;

--
-- TOC entry 3384 (class 0 OID 0)
-- Dependencies: 217
-- Name: scores_subjectid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.scores_subjectid_seq OWNED BY public.scores.subjectid;


--
-- TOC entry 213 (class 1259 OID 24634)
-- Name: students; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.students (
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    email character varying(50),
    phone character varying(50),
    idcard public.iscardcheck NOT NULL
);


ALTER TABLE public.students OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 24589)
-- Name: subjects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subjects (
    code integer NOT NULL,
    year integer,
    hourse public.hours,
    course_id integer,
    name public.subjectsname
);


ALTER TABLE public.subjects OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24588)
-- Name: subjects_code_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subjects_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subjects_code_seq OWNER TO postgres;

--
-- TOC entry 3385 (class 0 OID 0)
-- Dependencies: 209
-- Name: subjects_code_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subjects_code_seq OWNED BY public.subjects.code;


--
-- TOC entry 3207 (class 2604 OID 24615)
-- Name: courses code; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN code SET DEFAULT nextval('public.courses_code_seq'::regclass);


--
-- TOC entry 3208 (class 2604 OID 24643)
-- Name: enrollment code; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment ALTER COLUMN code SET DEFAULT nextval('public.enrollment_code_seq'::regclass);


--
-- TOC entry 3209 (class 2604 OID 24661)
-- Name: scores enrollmentid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores ALTER COLUMN enrollmentid SET DEFAULT nextval('public.scores_enrollmentid_seq'::regclass);


--
-- TOC entry 3210 (class 2604 OID 24662)
-- Name: scores subjectid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores ALTER COLUMN subjectid SET DEFAULT nextval('public.scores_subjectid_seq'::regclass);


--
-- TOC entry 3206 (class 2604 OID 24592)
-- Name: subjects code; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subjects ALTER COLUMN code SET DEFAULT nextval('public.subjects_code_seq'::regclass);


--
-- TOC entry 3368 (class 0 OID 24612)
-- Dependencies: 212
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.courses (code, name) VALUES (3, NULL);
INSERT INTO public.courses (code, name) VALUES (4, NULL);
INSERT INTO public.courses (code, name) VALUES (50, NULL);
INSERT INTO public.courses (code, name) VALUES (60, NULL);
INSERT INTO public.courses (code, name) VALUES (9, NULL);
INSERT INTO public.courses (code, name) VALUES (10, NULL);


--
-- TOC entry 3371 (class 0 OID 24640)
-- Dependencies: 215
-- Data for Name: enrollment; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3374 (class 0 OID 24658)
-- Dependencies: 218
-- Data for Name: scores; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3369 (class 0 OID 24634)
-- Dependencies: 213
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3366 (class 0 OID 24589)
-- Dependencies: 210
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.subjects (code, year, hourse, course_id, name) VALUES (30, 1, 60, 3, NULL);
INSERT INTO public.subjects (code, year, hourse, course_id, name) VALUES (40, 1, 90, 4, NULL);
INSERT INTO public.subjects (code, year, hourse, course_id, name) VALUES (10, 1, 40, 50, NULL);
INSERT INTO public.subjects (code, year, hourse, course_id, name) VALUES (20, 1, 60, 50, NULL);
INSERT INTO public.subjects (code, year, hourse, course_id, name) VALUES (50, 1, 60, 50, NULL);
INSERT INTO public.subjects (code, year, hourse, course_id, name) VALUES (60, 1, 60, 60, NULL);
INSERT INTO public.subjects (code, year, hourse, course_id, name) VALUES (34, 1, 60, 9, NULL);
INSERT INTO public.subjects (code, year, hourse, course_id, name) VALUES (66, 1, 90, 10, NULL);


--
-- TOC entry 3386 (class 0 OID 0)
-- Dependencies: 211
-- Name: courses_code_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.courses_code_seq', 6, true);


--
-- TOC entry 3387 (class 0 OID 0)
-- Dependencies: 214
-- Name: enrollment_code_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.enrollment_code_seq', 27, true);


--
-- TOC entry 3388 (class 0 OID 0)
-- Dependencies: 216
-- Name: scores_enrollmentid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.scores_enrollmentid_seq', 1, false);


--
-- TOC entry 3389 (class 0 OID 0)
-- Dependencies: 217
-- Name: scores_subjectid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.scores_subjectid_seq', 1, false);


--
-- TOC entry 3390 (class 0 OID 0)
-- Dependencies: 209
-- Name: subjects_code_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subjects_code_seq', 15, true);


--
-- TOC entry 3214 (class 2606 OID 24617)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (code);


--
-- TOC entry 3218 (class 2606 OID 24645)
-- Name: enrollment enrollment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_pkey PRIMARY KEY (code);


--
-- TOC entry 3216 (class 2606 OID 24932)
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (idcard);


--
-- TOC entry 3220 (class 2606 OID 24678)
-- Name: scores subjectid_enrollmentid_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores
    ADD CONSTRAINT subjectid_enrollmentid_pk PRIMARY KEY (subjectid, enrollmentid);


--
-- TOC entry 3212 (class 2606 OID 24594)
-- Name: subjects subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (code);


--
-- TOC entry 3222 (class 2606 OID 24651)
-- Name: enrollment enrollment_course_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_course_fkey FOREIGN KEY (course) REFERENCES public.courses(code);


--
-- TOC entry 3221 (class 2606 OID 24623)
-- Name: subjects fk_course_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES public.courses(code);


--
-- TOC entry 3224 (class 2606 OID 24663)
-- Name: scores scores_enrollmentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores
    ADD CONSTRAINT scores_enrollmentid_fkey FOREIGN KEY (enrollmentid) REFERENCES public.enrollment(code);


--
-- TOC entry 3225 (class 2606 OID 24668)
-- Name: scores scores_subjectid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores
    ADD CONSTRAINT scores_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subjects(code);


--
-- TOC entry 3223 (class 2606 OID 24942)
-- Name: enrollment student_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT student_fkey FOREIGN KEY (student) REFERENCES public.students(idcard) NOT VALID;


-- Completed on 2021-12-10 19:59:39

--
-- PostgreSQL database dump complete
--

