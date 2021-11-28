--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

-- Started on 2021-11-16 15:19:01

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
-- TOC entry 231 (class 1255 OID 24727)
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
-- TOC entry 232 (class 1255 OID 24738)
-- Name: getstudentenrollment(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.getstudentenrollment(idstudent character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE 
mycode enrollment;
BEGIN
	
		IF EXISTS (SELECT 1 FROM enrollment WHERE student = idStudent) THEN
			RETURN mycode.code;
		ELSE
			RETURN -1;
		END IF;
	
END;
$$;




ALTER FUNCTION public.ifuserexist(ids character varying) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 24589)
-- Name: subjects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subjects (
    code integer NOT NULL,
    name character varying(50),
    year integer,
    hourse integer,
    course_id integer
);


ALTER TABLE public.subjects OWNER TO postgres;

--
-- TOC entry 235 (class 1255 OID 24756)
-- Name: report(character varying); Type: FUNCTION; Schema: public; Owner: postgres
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
-- TOC entry 234 (class 1255 OID 24740)
-- Name: studenscorenotfinish(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.studenscorenotfinish(idstudent character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE 
myenrollid enrollment;
BEGIN
	FOR myenrollid in (SELECT code from enrollment where student like enrollmentIdStudent)
	LOOP
		IF EXISTS (SELECT 1 FROM scores LEFT JOIN enrollment ON scores.enrolmentid = enrollment.code AND enrollment.student = idStudent where score < 5) THEN
			RETURN -1;
		ELSE
			RETURN 0;
		END IF;
		
	END LOOP;
END;
$$;



ALTER FUNCTION public.studentenrollsamecourse(idstudent character varying, idcourse integer) OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 24612)
-- Name: courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.courses (
    code integer NOT NULL,
    name character varying(100) NOT NULL
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
-- TOC entry 3365 (class 0 OID 0)
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
    student character varying(50) NOT NULL,
    course integer NOT NULL
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
-- TOC entry 3366 (class 0 OID 0)
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
    score integer
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
-- TOC entry 3367 (class 0 OID 0)
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
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 217
-- Name: scores_subjectid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.scores_subjectid_seq OWNED BY public.scores.subjectid;


--
-- TOC entry 213 (class 1259 OID 24634)
-- Name: students; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.students (
    idcard character varying(20) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    phone character varying(50)
);


ALTER TABLE public.students OWNER TO postgres;

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
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 209
-- Name: subjects_code_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subjects_code_seq OWNED BY public.subjects.code;


--
-- TOC entry 3192 (class 2604 OID 24615)
-- Name: courses code; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN code SET DEFAULT nextval('public.courses_code_seq'::regclass);


--
-- TOC entry 3193 (class 2604 OID 24643)
-- Name: enrollment code; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment ALTER COLUMN code SET DEFAULT nextval('public.enrollment_code_seq'::regclass);


--
-- TOC entry 3194 (class 2604 OID 24661)
-- Name: scores enrollmentid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores ALTER COLUMN enrollmentid SET DEFAULT nextval('public.scores_enrollmentid_seq'::regclass);


--
-- TOC entry 3195 (class 2604 OID 24662)
-- Name: scores subjectid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores ALTER COLUMN subjectid SET DEFAULT nextval('public.scores_subjectid_seq'::regclass);


--
-- TOC entry 3191 (class 2604 OID 24592)
-- Name: subjects code; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subjects ALTER COLUMN code SET DEFAULT nextval('public.subjects_code_seq'::regclass);


--
-- TOC entry 3353 (class 0 OID 24612)
-- Dependencies: 212
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.courses (code, name) FROM stdin;
4	Multiplatform app
5	Webpage app development
6	Finance report
\.


--
-- TOC entry 3356 (class 0 OID 24640)
-- Dependencies: 215
-- Data for Name: enrollment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.enrollment (code, student, course) FROM stdin;
13	123456789A	4
14	111111111S	5
\.


--
-- TOC entry 3359 (class 0 OID 24658)
-- Dependencies: 218
-- Data for Name: scores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.scores (enrollmentid, subjectid, score) FROM stdin;
13	10	0
13	11	0
13	12	0
13	13	0
13	14	0
13	15	0
\.


--
-- TOC entry 3354 (class 0 OID 24634)
-- Dependencies: 213
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.students (idcard, firstname, lastname, email, phone) FROM stdin;
123456789A	Marina	Lopez	marinaLopez@gmail.com	89273901
111111111S	Lina	Martinez	lm@gmail.com	89323901
111111112R	Malina	Romero	mr@gmail.com	89323901
112641112R	Luzifer	Morgernstern	lm@gmail.com	89323901
\.


--
-- TOC entry 3351 (class 0 OID 24589)
-- Dependencies: 210
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subjects (code, name, year, hourse, course_id) FROM stdin;
10	FOL	2021	3	4
11	SGE	2021	6	4
12	Design Interface	2021	6	4
13	Data Base Access	2021	6	4
14	Threads and Process	2021	3	4
15	Android and Unity	2021	5	4
\.


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 211
-- Name: courses_code_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.courses_code_seq', 6, true);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 214
-- Name: enrollment_code_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.enrollment_code_seq', 14, true);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 216
-- Name: scores_enrollmentid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.scores_enrollmentid_seq', 1, false);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 217
-- Name: scores_subjectid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.scores_subjectid_seq', 1, false);


--
-- TOC entry 3374 (class 0 OID 0)
-- Dependencies: 209
-- Name: subjects_code_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subjects_code_seq', 15, true);


--
-- TOC entry 3199 (class 2606 OID 24617)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (code);


--
-- TOC entry 3203 (class 2606 OID 24645)
-- Name: enrollment enrollment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_pkey PRIMARY KEY (code);


--
-- TOC entry 3201 (class 2606 OID 24638)
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (idcard);


--
-- TOC entry 3205 (class 2606 OID 24678)
-- Name: scores subjectid_enrollmentid_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores
    ADD CONSTRAINT subjectid_enrollmentid_pk PRIMARY KEY (subjectid, enrollmentid);


--
-- TOC entry 3197 (class 2606 OID 24594)
-- Name: subjects subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (code);


--
-- TOC entry 3208 (class 2606 OID 24651)
-- Name: enrollment enrollment_course_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_course_fkey FOREIGN KEY (course) REFERENCES public.courses(code);


--
-- TOC entry 3207 (class 2606 OID 24646)
-- Name: enrollment enrollment_student_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_student_fkey FOREIGN KEY (student) REFERENCES public.students(idcard);


--
-- TOC entry 3206 (class 2606 OID 24623)
-- Name: subjects fk_course_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES public.courses(code);


--
-- TOC entry 3209 (class 2606 OID 24663)
-- Name: scores scores_enrollmentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores
    ADD CONSTRAINT scores_enrollmentid_fkey FOREIGN KEY (enrollmentid) REFERENCES public.enrollment(code);


--
-- TOC entry 3210 (class 2606 OID 24668)
-- Name: scores scores_subjectid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores
    ADD CONSTRAINT scores_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subjects(code);


-- Completed on 2021-11-16 15:19:01

--
-- PostgreSQL database dump complete
--

