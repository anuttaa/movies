--
-- PostgreSQL database dump
--

\restrict p65sq32CHyMaRmiHqcgsoHV8XXUfORj63tqb6xRaONEErZ1584GwuKCMuG86lBz
\unrestrict p65sq32CHyMaRmiHqcgsoHV8XXUfORj63tqb6xRaONEErZ1584GwuKCMuG86lBz

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

-- Started on 2025-12-19 01:51:55

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 17367)
-- Name: actors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actors (
    actor_id integer NOT NULL,
    full_name character varying(255) NOT NULL,
    birthday date,
    biography character varying(255)
);


ALTER TABLE public.actors OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17366)
-- Name: actors_actor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.actors_actor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.actors_actor_id_seq OWNER TO postgres;

--
-- TOC entry 5124 (class 0 OID 0)
-- Dependencies: 225
-- Name: actors_actor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.actors_actor_id_seq OWNED BY public.actors.actor_id;


--
-- TOC entry 230 (class 1259 OID 17413)
-- Name: actors_awards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actors_awards (
    actor_id integer NOT NULL,
    award_id integer NOT NULL
);


ALTER TABLE public.actors_awards OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 17356)
-- Name: awards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.awards (
    award_id integer NOT NULL,
    award_name character varying(255) NOT NULL,
    award_type character varying(255),
    award_description character varying(255),
    year date
);


ALTER TABLE public.awards OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17355)
-- Name: awards_award_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.awards_award_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.awards_award_id_seq OWNER TO postgres;

--
-- TOC entry 5125 (class 0 OID 0)
-- Dependencies: 223
-- Name: awards_award_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.awards_award_id_seq OWNED BY public.awards.award_id;


--
-- TOC entry 239 (class 1259 OID 17521)
-- Name: chats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chats (
    chat_id integer NOT NULL,
    chat_name character varying(255) NOT NULL
);


ALTER TABLE public.chats OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 17520)
-- Name: chats_chat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.chats_chat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.chats_chat_id_seq OWNER TO postgres;

--
-- TOC entry 5126 (class 0 OID 0)
-- Dependencies: 238
-- Name: chats_chat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.chats_chat_id_seq OWNED BY public.chats.chat_id;


--
-- TOC entry 242 (class 1259 OID 17554)
-- Name: chats_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chats_users (
    chat_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.chats_users OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 17572)
-- Name: confirmation_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.confirmation_token (
    id integer NOT NULL,
    token character varying(255) NOT NULL,
    user_id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    expires_at timestamp without time zone NOT NULL,
    confirmed_at timestamp without time zone
);


ALTER TABLE public.confirmation_token OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 17571)
-- Name: confirmation_token_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.confirmation_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.confirmation_token_id_seq OWNER TO postgres;

--
-- TOC entry 5127 (class 0 OID 0)
-- Dependencies: 243
-- Name: confirmation_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.confirmation_token_id_seq OWNED BY public.confirmation_token.id;


--
-- TOC entry 222 (class 1259 OID 17345)
-- Name: directors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.directors (
    director_id integer NOT NULL,
    director_full_name character varying(255) NOT NULL
);


ALTER TABLE public.directors OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 17430)
-- Name: directors_awards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.directors_awards (
    director_id integer NOT NULL,
    award_id integer NOT NULL
);


ALTER TABLE public.directors_awards OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17344)
-- Name: directors_director_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.directors_director_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.directors_director_id_seq OWNER TO postgres;

--
-- TOC entry 5128 (class 0 OID 0)
-- Dependencies: 221
-- Name: directors_director_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.directors_director_id_seq OWNED BY public.directors.director_id;


--
-- TOC entry 236 (class 1259 OID 17489)
-- Name: favourites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favourites (
    favourites_id integer NOT NULL,
    movie_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.favourites OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 17488)
-- Name: favourites_favourites_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.favourites_favourites_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.favourites_favourites_id_seq OWNER TO postgres;

--
-- TOC entry 5129 (class 0 OID 0)
-- Dependencies: 235
-- Name: favourites_favourites_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.favourites_favourites_id_seq OWNED BY public.favourites.favourites_id;


--
-- TOC entry 241 (class 1259 OID 17532)
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    message_id integer NOT NULL,
    sender_id integer NOT NULL,
    receiver_chat_id integer,
    message character varying(255) NOT NULL,
    date timestamp without time zone NOT NULL
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 17531)
-- Name: messages_message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.messages_message_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.messages_message_id_seq OWNER TO postgres;

--
-- TOC entry 5130 (class 0 OID 0)
-- Dependencies: 240
-- Name: messages_message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messages_message_id_seq OWNED BY public.messages.message_id;


--
-- TOC entry 237 (class 1259 OID 17508)
-- Name: movie_genres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie_genres (
    movie_id integer NOT NULL,
    genre character varying(255) NOT NULL
);


ALTER TABLE public.movie_genres OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 17378)
-- Name: movies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movies (
    movie_id integer NOT NULL,
    title character varying(255) NOT NULL,
    description character varying(255),
    rate double precision,
    poster character varying(255),
    director character varying(255) NOT NULL,
    duration character varying(255) NOT NULL,
    release_year integer NOT NULL
);


ALTER TABLE public.movies OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 17447)
-- Name: movies_actors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movies_actors (
    movie_id integer NOT NULL,
    actor_id integer NOT NULL,
    role character varying(255)
);


ALTER TABLE public.movies_actors OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 17396)
-- Name: movies_awards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movies_awards (
    movie_id integer NOT NULL,
    award_id integer NOT NULL
);


ALTER TABLE public.movies_awards OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 17377)
-- Name: movies_movie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movies_movie_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.movies_movie_id_seq OWNER TO postgres;

--
-- TOC entry 5131 (class 0 OID 0)
-- Dependencies: 227
-- Name: movies_movie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movies_movie_id_seq OWNED BY public.movies.movie_id;


--
-- TOC entry 234 (class 1259 OID 17465)
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review (
    review_id integer NOT NULL,
    user_id integer NOT NULL,
    movie_id integer NOT NULL,
    rating double precision,
    review_text character varying(255),
    created_at date DEFAULT now() NOT NULL
);


ALTER TABLE public.review OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 17464)
-- Name: review_review_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.review_review_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.review_review_id_seq OWNER TO postgres;

--
-- TOC entry 5132 (class 0 OID 0)
-- Dependencies: 233
-- Name: review_review_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.review_review_id_seq OWNED BY public.review.review_id;


--
-- TOC entry 220 (class 1259 OID 17326)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    name character varying(255) NOT NULL,
    surname character varying(255) NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255),
    role character varying(255) DEFAULT 'GUEST'::character varying NOT NULL,
    is_email_verified boolean DEFAULT false NOT NULL,
    avatar character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17325)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 5133 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 4883 (class 2604 OID 17370)
-- Name: actors actor_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors ALTER COLUMN actor_id SET DEFAULT nextval('public.actors_actor_id_seq'::regclass);


--
-- TOC entry 4882 (class 2604 OID 17359)
-- Name: awards award_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.awards ALTER COLUMN award_id SET DEFAULT nextval('public.awards_award_id_seq'::regclass);


--
-- TOC entry 4888 (class 2604 OID 17524)
-- Name: chats chat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chats ALTER COLUMN chat_id SET DEFAULT nextval('public.chats_chat_id_seq'::regclass);


--
-- TOC entry 4890 (class 2604 OID 17575)
-- Name: confirmation_token id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token ALTER COLUMN id SET DEFAULT nextval('public.confirmation_token_id_seq'::regclass);


--
-- TOC entry 4881 (class 2604 OID 17348)
-- Name: directors director_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.directors ALTER COLUMN director_id SET DEFAULT nextval('public.directors_director_id_seq'::regclass);


--
-- TOC entry 4887 (class 2604 OID 17492)
-- Name: favourites favourites_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favourites ALTER COLUMN favourites_id SET DEFAULT nextval('public.favourites_favourites_id_seq'::regclass);


--
-- TOC entry 4889 (class 2604 OID 17535)
-- Name: messages message_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages ALTER COLUMN message_id SET DEFAULT nextval('public.messages_message_id_seq'::regclass);


--
-- TOC entry 4884 (class 2604 OID 17381)
-- Name: movies movie_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies ALTER COLUMN movie_id SET DEFAULT nextval('public.movies_movie_id_seq'::regclass);


--
-- TOC entry 4885 (class 2604 OID 17468)
-- Name: review review_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review ALTER COLUMN review_id SET DEFAULT nextval('public.review_review_id_seq'::regclass);


--
-- TOC entry 4878 (class 2604 OID 17329)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 5100 (class 0 OID 17367)
-- Dependencies: 226
-- Data for Name: actors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actors (actor_id, full_name, birthday, biography) FROM stdin;
1	Leonardo DiCaprio	1974-11-11	American actor
2	Joseph Gordon-Levitt	1981-02-17	American actor
3	Morgan Freeman	1937-06-02	Хорошая!!
\.


--
-- TOC entry 5104 (class 0 OID 17413)
-- Dependencies: 230
-- Data for Name: actors_awards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actors_awards (actor_id, award_id) FROM stdin;
3	4
1	1
1	5
\.


--
-- TOC entry 5098 (class 0 OID 17356)
-- Dependencies: 224
-- Data for Name: awards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.awards (award_id, award_name, award_type, award_description, year) FROM stdin;
1	Oscar	Academy	Academy Awards	2000-01-01
2	Golden Globe	Golden	Golden Globe Awards	2005-01-01
3	BAFTA	British	British Academy Film Awards	2010-01-01
4	Оскар	Academy	Лучшая женская роль	2000-01-01
5	Оскар	Academy	Лучшая мужская роль	2025-12-01
\.


--
-- TOC entry 5113 (class 0 OID 17521)
-- Dependencies: 239
-- Data for Name: chats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chats (chat_id, chat_name) FROM stdin;
1	General
\.


--
-- TOC entry 5116 (class 0 OID 17554)
-- Dependencies: 242
-- Data for Name: chats_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chats_users (chat_id, user_id) FROM stdin;
1	1
\.


--
-- TOC entry 5118 (class 0 OID 17572)
-- Dependencies: 244
-- Data for Name: confirmation_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.confirmation_token (id, token, user_id, created_at, expires_at, confirmed_at) FROM stdin;
3	75b43347-de5c-4286-a986-d87a5fae4cad	8	2025-12-16 01:36:34.958301	2025-12-17 01:36:34.958301	\N
4	6b305dcb-384e-4251-a4d5-6075fd07dfc3	9	2025-12-16 17:02:33.686314	2025-12-17 17:02:33.686314	\N
\.


--
-- TOC entry 5096 (class 0 OID 17345)
-- Dependencies: 222
-- Data for Name: directors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.directors (director_id, director_full_name) FROM stdin;
1	Christopher Nolan
2	Peter Jackson
3	Frank Darabont
6	Leonardo
7	Cristofer Nolan
8	Кристофер
5	Regisser
\.


--
-- TOC entry 5105 (class 0 OID 17430)
-- Dependencies: 231
-- Data for Name: directors_awards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.directors_awards (director_id, award_id) FROM stdin;
1	3
\.


--
-- TOC entry 5110 (class 0 OID 17489)
-- Dependencies: 236
-- Data for Name: favourites; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.favourites (favourites_id, movie_id, user_id) FROM stdin;
1	1	1
4	1	9
5	6	9
\.


--
-- TOC entry 5115 (class 0 OID 17532)
-- Dependencies: 241
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (message_id, sender_id, receiver_chat_id, message, date) FROM stdin;
1	1	1	Welcome to the chat!	2025-12-14 23:40:28.24432
\.


--
-- TOC entry 5111 (class 0 OID 17508)
-- Dependencies: 237
-- Data for Name: movie_genres; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movie_genres (movie_id, genre) FROM stdin;
2	DRAMA
6	FANTASY
1	ACTION
1	SCIFI
\.


--
-- TOC entry 5102 (class 0 OID 17378)
-- Dependencies: 228
-- Data for Name: movies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movies (movie_id, title, description, rate, poster, director, duration, release_year) FROM stdin;
2	The Shawshank Redemption	Hope is a good thing.	10	https://example.com/shawshank.jpg	Frank Darabont	2h 22m	1994
6	The Lord of the Rings: The Fellowship of the Ring		8.8	\N	Cristofer Nolan	2h 28m	2001
1	Inception	A mind-bending heist within dreams.	8.8	\N	Christopher Nolan	2h 28m	2010
\.


--
-- TOC entry 5106 (class 0 OID 17447)
-- Dependencies: 232
-- Data for Name: movies_actors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movies_actors (movie_id, actor_id, role) FROM stdin;
1	1	Cobb
1	2	Arthur
2	3	Red
\.


--
-- TOC entry 5103 (class 0 OID 17396)
-- Dependencies: 229
-- Data for Name: movies_awards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movies_awards (movie_id, award_id) FROM stdin;
1	3
2	1
\.


--
-- TOC entry 5108 (class 0 OID 17465)
-- Dependencies: 234
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.review (review_id, user_id, movie_id, rating, review_text, created_at) FROM stdin;
1	1	1	9	Amazing concept	2025-12-14
2	2	2	10	Masterpiece	2025-12-14
\.


--
-- TOC entry 5094 (class 0 OID 17326)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, name, surname, login, password, role, is_email_verified, avatar) FROM stdin;
2	Nikita	Petrov	nikifor@example.com	password	GUEST	f	\N
1	Ivan	Ivanov	pavel@example.com	password	ADMIN	f	avatars/pavel_✨FOLLOW✨SAVE FOR MORE.jpg
8	Anna	Турейко	anuttaatur@gmail.com	$2a$10$S7mdOeuVBs2.Cs3BKcESqOsPlEMIQjLkdkO7ZVgWubaTTjVwiY7fC	USER	f	avatars/anuttaatur_✨FOLLOW✨SAVE FOR MORE.jpg
9	Anna	Турейко	annatyreiko@gmail.com	$2a$10$M25hNo5lhOeOnk7s2XkIk.PDG46RQ49PYBhNAxiVtxmQrOskcqAlS	USER	f	\N
\.


--
-- TOC entry 5134 (class 0 OID 0)
-- Dependencies: 225
-- Name: actors_actor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.actors_actor_id_seq', 4, true);


--
-- TOC entry 5135 (class 0 OID 0)
-- Dependencies: 223
-- Name: awards_award_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.awards_award_id_seq', 5, true);


--
-- TOC entry 5136 (class 0 OID 0)
-- Dependencies: 238
-- Name: chats_chat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.chats_chat_id_seq', 1, true);


--
-- TOC entry 5137 (class 0 OID 0)
-- Dependencies: 243
-- Name: confirmation_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.confirmation_token_id_seq', 4, true);


--
-- TOC entry 5138 (class 0 OID 0)
-- Dependencies: 221
-- Name: directors_director_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.directors_director_id_seq', 8, true);


--
-- TOC entry 5139 (class 0 OID 0)
-- Dependencies: 235
-- Name: favourites_favourites_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.favourites_favourites_id_seq', 5, true);


--
-- TOC entry 5140 (class 0 OID 0)
-- Dependencies: 240
-- Name: messages_message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messages_message_id_seq', 1, true);


--
-- TOC entry 5141 (class 0 OID 0)
-- Dependencies: 227
-- Name: movies_movie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movies_movie_id_seq', 9, true);


--
-- TOC entry 5142 (class 0 OID 0)
-- Dependencies: 233
-- Name: review_review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.review_review_id_seq', 3, true);


--
-- TOC entry 5143 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 9, true);


--
-- TOC entry 4908 (class 2606 OID 17419)
-- Name: actors_awards actors_awards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors_awards
    ADD CONSTRAINT actors_awards_pkey PRIMARY KEY (actor_id, award_id);


--
-- TOC entry 4902 (class 2606 OID 17376)
-- Name: actors actors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors
    ADD CONSTRAINT actors_pkey PRIMARY KEY (actor_id);


--
-- TOC entry 4900 (class 2606 OID 17365)
-- Name: awards awards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.awards
    ADD CONSTRAINT awards_pkey PRIMARY KEY (award_id);


--
-- TOC entry 4920 (class 2606 OID 17530)
-- Name: chats chats_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chats
    ADD CONSTRAINT chats_pkey PRIMARY KEY (chat_id);


--
-- TOC entry 4924 (class 2606 OID 17560)
-- Name: chats_users chats_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chats_users
    ADD CONSTRAINT chats_users_pkey PRIMARY KEY (chat_id, user_id);


--
-- TOC entry 4926 (class 2606 OID 17582)
-- Name: confirmation_token confirmation_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_pkey PRIMARY KEY (id);


--
-- TOC entry 4910 (class 2606 OID 17436)
-- Name: directors_awards directors_awards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.directors_awards
    ADD CONSTRAINT directors_awards_pkey PRIMARY KEY (director_id, award_id);


--
-- TOC entry 4896 (class 2606 OID 17625)
-- Name: directors directors_director_full_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.directors
    ADD CONSTRAINT directors_director_full_name_key UNIQUE (director_full_name);


--
-- TOC entry 4898 (class 2606 OID 17352)
-- Name: directors directors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.directors
    ADD CONSTRAINT directors_pkey PRIMARY KEY (director_id);


--
-- TOC entry 4916 (class 2606 OID 17497)
-- Name: favourites favourites_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favourites
    ADD CONSTRAINT favourites_pkey PRIMARY KEY (favourites_id);


--
-- TOC entry 4922 (class 2606 OID 17543)
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (message_id);


--
-- TOC entry 4918 (class 2606 OID 17638)
-- Name: movie_genres movie_genres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_genres
    ADD CONSTRAINT movie_genres_pkey PRIMARY KEY (movie_id, genre);


--
-- TOC entry 4912 (class 2606 OID 17453)
-- Name: movies_actors movies_actors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies_actors
    ADD CONSTRAINT movies_actors_pkey PRIMARY KEY (movie_id, actor_id);


--
-- TOC entry 4906 (class 2606 OID 17402)
-- Name: movies_awards movies_awards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies_awards
    ADD CONSTRAINT movies_awards_pkey PRIMARY KEY (movie_id, award_id);


--
-- TOC entry 4904 (class 2606 OID 17390)
-- Name: movies movies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (movie_id);


--
-- TOC entry 4914 (class 2606 OID 17477)
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (review_id);


--
-- TOC entry 4892 (class 2606 OID 17682)
-- Name: users users_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);


--
-- TOC entry 4894 (class 2606 OID 17341)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4930 (class 2606 OID 17420)
-- Name: actors_awards actors_awards_actor_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors_awards
    ADD CONSTRAINT actors_awards_actor_id_fkey FOREIGN KEY (actor_id) REFERENCES public.actors(actor_id) ON DELETE CASCADE;


--
-- TOC entry 4931 (class 2606 OID 17425)
-- Name: actors_awards actors_awards_award_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors_awards
    ADD CONSTRAINT actors_awards_award_id_fkey FOREIGN KEY (award_id) REFERENCES public.awards(award_id) ON DELETE CASCADE;


--
-- TOC entry 4943 (class 2606 OID 17561)
-- Name: chats_users chats_users_chat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chats_users
    ADD CONSTRAINT chats_users_chat_id_fkey FOREIGN KEY (chat_id) REFERENCES public.chats(chat_id) ON DELETE CASCADE;


--
-- TOC entry 4944 (class 2606 OID 17566)
-- Name: chats_users chats_users_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chats_users
    ADD CONSTRAINT chats_users_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- TOC entry 4945 (class 2606 OID 17583)
-- Name: confirmation_token confirmation_token_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- TOC entry 4932 (class 2606 OID 17442)
-- Name: directors_awards directors_awards_award_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.directors_awards
    ADD CONSTRAINT directors_awards_award_id_fkey FOREIGN KEY (award_id) REFERENCES public.awards(award_id) ON DELETE CASCADE;


--
-- TOC entry 4933 (class 2606 OID 17437)
-- Name: directors_awards directors_awards_director_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.directors_awards
    ADD CONSTRAINT directors_awards_director_id_fkey FOREIGN KEY (director_id) REFERENCES public.directors(director_id) ON DELETE CASCADE;


--
-- TOC entry 4938 (class 2606 OID 17498)
-- Name: favourites favourites_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favourites
    ADD CONSTRAINT favourites_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(movie_id) ON DELETE CASCADE;


--
-- TOC entry 4939 (class 2606 OID 17503)
-- Name: favourites favourites_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favourites
    ADD CONSTRAINT favourites_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- TOC entry 4941 (class 2606 OID 17549)
-- Name: messages messages_receiver_chat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_receiver_chat_id_fkey FOREIGN KEY (receiver_chat_id) REFERENCES public.chats(chat_id) ON DELETE CASCADE;


--
-- TOC entry 4942 (class 2606 OID 17544)
-- Name: messages messages_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- TOC entry 4940 (class 2606 OID 17515)
-- Name: movie_genres movie_genres_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_genres
    ADD CONSTRAINT movie_genres_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(movie_id) ON DELETE CASCADE;


--
-- TOC entry 4934 (class 2606 OID 17459)
-- Name: movies_actors movies_actors_actor_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies_actors
    ADD CONSTRAINT movies_actors_actor_id_fkey FOREIGN KEY (actor_id) REFERENCES public.actors(actor_id) ON DELETE CASCADE;


--
-- TOC entry 4935 (class 2606 OID 17454)
-- Name: movies_actors movies_actors_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies_actors
    ADD CONSTRAINT movies_actors_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(movie_id) ON DELETE CASCADE;


--
-- TOC entry 4928 (class 2606 OID 17408)
-- Name: movies_awards movies_awards_award_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies_awards
    ADD CONSTRAINT movies_awards_award_id_fkey FOREIGN KEY (award_id) REFERENCES public.awards(award_id) ON DELETE CASCADE;


--
-- TOC entry 4929 (class 2606 OID 17403)
-- Name: movies_awards movies_awards_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies_awards
    ADD CONSTRAINT movies_awards_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(movie_id) ON DELETE CASCADE;


--
-- TOC entry 4927 (class 2606 OID 17656)
-- Name: movies movies_director_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_director_fkey FOREIGN KEY (director) REFERENCES public.directors(director_full_name);


--
-- TOC entry 4936 (class 2606 OID 17483)
-- Name: review review_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(movie_id) ON DELETE CASCADE;


--
-- TOC entry 4937 (class 2606 OID 17478)
-- Name: review review_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


-- Completed on 2025-12-19 01:51:57

--
-- PostgreSQL database dump complete
--

\unrestrict p65sq32CHyMaRmiHqcgsoHV8XXUfORj63tqb6xRaONEErZ1584GwuKCMuG86lBz
