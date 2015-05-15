--
-- PostgreSQL database dump
--

--Lines 20,26,31,45 were manually commented so that the script could be executed in pgAdmin3 QueryTool
--Setup:First manually create a database called androidcampaigns in pgAdmin3 and rename the default public schema
--to androidcampaigns then execute these queries in QueryTool so that springboot can connect to the database.

-- Dumped from database version 9.4.1
-- Dumped by pg_dump version 9.4.1
-- Started on 2015-05-14 12:07:33

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--DROP DATABASE androidcampaigns;
--
-- TOC entry 2063 (class 1262 OID 16419)
-- Name: androidcampaigns; Type: DATABASE; Schema: -; Owner: androidcampaigns
--

--CREATE DATABASE androidcampaigns WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Greek_Greece.1253' LC_CTYPE = 'Greek_Greece.1253';


ALTER DATABASE androidcampaigns OWNER TO androidcampaigns;

--\connect androidcampaigns

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: androidcampaigns; Type: SCHEMA; Schema: -; Owner: postgres
--

--CREATE SCHEMA androidcampaigns;


ALTER SCHEMA androidcampaigns OWNER TO postgres;

--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA androidcampaigns; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA androidcampaigns IS 'standard public schema';


--
-- TOC entry 182 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 182
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = androidcampaigns, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 16440)
-- Name: campaign; Type: TABLE; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

CREATE TABLE campaign (
    id long NOT NULL,
    name character varying(100) DEFAULT 'Some Campaign'::character varying,
    "fromTime" timestamp with time zone NOT NULL,
    "toTime" timestamp with time zone NOT NULL,
    "experimentsUsedId" integer NOT NULL,
    status integer DEFAULT 0 NOT NULL,
    measuments json,
    area polygon NOT NULL
);


ALTER TABLE campaign OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16451)
-- Name: experiments; Type: TABLE; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

CREATE TABLE experiments (
    id integer NOT NULL,
    name character varying(256) NOT NULL,
    "contextType" character varying(256) NOT NULL,
    "sensorDependencies" text NOT NULL,
    "fromTime" timestamp with time zone,
    "toTime" timestamp with time zone,
    status character varying(256) NOT NULL,
    "userID" integer,
    url character varying(256) NOT NULL,
    filename character varying(256) NOT NULL,
    description text NOT NULL,
    "timestamp" bigint NOT NULL
);


ALTER TABLE experiments OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16449)
-- Name: experiments_id_seq; Type: SEQUENCE; Schema: androidcampaigns; Owner: postgres
--

CREATE SEQUENCE experiments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE experiments_id_seq OWNER TO postgres;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 174
-- Name: experiments_id_seq; Type: SEQUENCE OWNED BY; Schema: androidcampaigns; Owner: postgres
--

ALTER SEQUENCE experiments_id_seq OWNED BY experiments.id;


--
-- TOC entry 177 (class 1259 OID 16462)
-- Name: plugins; Type: TABLE; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

CREATE TABLE plugins (
    id integer NOT NULL,
    name character varying(256) NOT NULL,
    "contextType" character varying(256) NOT NULL,
    "runtimeFactoryClass" character varying(256) NOT NULL,
    description character varying(256) NOT NULL,
    "installUrl" character varying(256) NOT NULL,
    filename character varying(256) NOT NULL
);


ALTER TABLE plugins OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16460)
-- Name: plugins_id_seq; Type: SEQUENCE; Schema: androidcampaigns; Owner: postgres
--

CREATE SEQUENCE plugins_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE plugins_id_seq OWNER TO postgres;

--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 176
-- Name: plugins_id_seq; Type: SEQUENCE OWNED BY; Schema: androidcampaigns; Owner: postgres
--

ALTER SEQUENCE plugins_id_seq OWNED BY plugins.id;


--
-- TOC entry 179 (class 1259 OID 16473)
-- Name: results; Type: TABLE; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

CREATE TABLE results (
    id integer NOT NULL,
    "experimentID" integer NOT NULL,
    "deviceID" integer NOT NULL,
    message text NOT NULL
);


ALTER TABLE results OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16471)
-- Name: results_id_seq; Type: SEQUENCE; Schema: androidcampaigns; Owner: postgres
--

CREATE SEQUENCE results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE results_id_seq OWNER TO postgres;

--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 178
-- Name: results_id_seq; Type: SEQUENCE OWNED BY; Schema: androidcampaigns; Owner: postgres
--

ALTER SEQUENCE results_id_seq OWNED BY results.id;


--
-- TOC entry 181 (class 1259 OID 16484)
-- Name: smartphones; Type: TABLE; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

CREATE TABLE smartphones (
    id integer NOT NULL,
    "phoneID" integer NOT NULL,
    "deviceType" character varying(256) DEFAULT 'Generic Android Device'::character varying NOT NULL,
    "sensorRules" text
);


ALTER TABLE smartphones OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16482)
-- Name: smartphones_id_seq; Type: SEQUENCE; Schema: androidcampaigns; Owner: postgres
--

CREATE SEQUENCE smartphones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE smartphones_id_seq OWNER TO postgres;

--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 180
-- Name: smartphones_id_seq; Type: SEQUENCE OWNED BY; Schema: androidcampaigns; Owner: postgres
--

ALTER SEQUENCE smartphones_id_seq OWNED BY smartphones.id;


--
-- TOC entry 172 (class 1259 OID 16420)
-- Name: user; Type: TABLE; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

CREATE TABLE "user" (
    id bigint NOT NULL,
    is_admin integer DEFAULT 0,
    role integer DEFAULT 1,
    username character varying(45) DEFAULT NULL::character varying,
    password character varying(145) DEFAULT NULL::character varying,
    email character varying(128) DEFAULT NULL::character varying,
    firstname character varying(45) DEFAULT NULL::character varying,
    lastname character varying(45) DEFAULT NULL::character varying,
    points integer DEFAULT 0 NOT NULL,
    cooldown integer DEFAULT 0 NOT NULL
);


ALTER TABLE "user" OWNER TO postgres;

--
-- TOC entry 1923 (class 2604 OID 16454)
-- Name: id; Type: DEFAULT; Schema: androidcampaigns; Owner: postgres
--

ALTER TABLE ONLY experiments ALTER COLUMN id SET DEFAULT nextval('experiments_id_seq'::regclass);


--
-- TOC entry 1924 (class 2604 OID 16465)
-- Name: id; Type: DEFAULT; Schema: androidcampaigns; Owner: postgres
--

ALTER TABLE ONLY plugins ALTER COLUMN id SET DEFAULT nextval('plugins_id_seq'::regclass);


--
-- TOC entry 1925 (class 2604 OID 16476)
-- Name: id; Type: DEFAULT; Schema: androidcampaigns; Owner: postgres
--

ALTER TABLE ONLY results ALTER COLUMN id SET DEFAULT nextval('results_id_seq'::regclass);


--
-- TOC entry 1926 (class 2604 OID 16487)
-- Name: id; Type: DEFAULT; Schema: androidcampaigns; Owner: postgres
--

ALTER TABLE ONLY smartphones ALTER COLUMN id SET DEFAULT nextval('smartphones_id_seq'::regclass);


--
-- TOC entry 2050 (class 0 OID 16440)
-- Dependencies: 173
-- Data for Name: campaign; Type: TABLE DATA; Schema: androidcampaigns; Owner: postgres
--



--
-- TOC entry 2052 (class 0 OID 16451)
-- Dependencies: 175
-- Data for Name: experiments; Type: TABLE DATA; Schema: androidcampaigns; Owner: postgres
--

INSERT INTO experiments (id, name, "contextType", "sensorDependencies", "fromTime", "toTime", status, "userID", url, filename, description, "timestamp") VALUES (1, 'Experiment1', 'org.ambientdynamix.contextplugins.ExperimentPlugin', 'org.ambientdynamix.contextplugins.GpsPlugin', '2000-12-01 00:00:00', '2000-12-01 00:00:00', 'some', 0, 'http://83.212.110.88:8080/dynamixRepository/org.ambientdynamix.contextplugins.ExperimentPlugin_0.9.54.jar', 'org.ambientdynamix.contextplugins.ExperimentPlugin_0.9.54.jar', 'just a sample expexperiment', 0);


--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 174
-- Name: experiments_id_seq; Type: SEQUENCE SET; Schema: androidcampaigns; Owner: postgres
--

SELECT pg_catalog.setval('experiments_id_seq', 1, false);


--
-- TOC entry 2054 (class 0 OID 16462)
-- Dependencies: 177
-- Data for Name: plugins; Type: TABLE DATA; Schema: androidcampaigns; Owner: postgres
--

INSERT INTO plugins (id, name, "contextType", "runtimeFactoryClass", description, "installUrl", filename) VALUES (1, 'plugs.xml', 'plugs.xml', 'plugs.xml', 'plugs.xml', 'http://83.212.110.88:8080/dynamixRepository/plugs.xml', 'plugs.xml');
INSERT INTO plugins (id, name, "contextType", "runtimeFactoryClass", description, "installUrl", filename) VALUES (6, 'GpsPlugin', 'org.ambientdynamix.contextplugins.GpsPlugin', 'org.ambientdynamix.contextplugins.GpsPlugin.PluginFactory', 'GpsPlugin', 'http://83.212.110.88:8080/dynamixRepository/org.ambientdynamix.contextplugins.GpsPlugin_0.9.54.jar', 'org.ambientdynamix.contextplugins.GpsPlugin_0.9.54.jar');


--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 176
-- Name: plugins_id_seq; Type: SEQUENCE SET; Schema: androidcampaigns; Owner: postgres
--

SELECT pg_catalog.setval('plugins_id_seq', 1, false);


--
-- TOC entry 2056 (class 0 OID 16473)
-- Dependencies: 179
-- Data for Name: results; Type: TABLE DATA; Schema: androidcampaigns; Owner: postgres
--



--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 178
-- Name: results_id_seq; Type: SEQUENCE SET; Schema: androidcampaigns; Owner: postgres
--

SELECT pg_catalog.setval('results_id_seq', 1, false);


--
-- TOC entry 2058 (class 0 OID 16484)
-- Dependencies: 181
-- Data for Name: smartphones; Type: TABLE DATA; Schema: androidcampaigns; Owner: postgres
--

INSERT INTO smartphones (id, "phoneID", "deviceType", "sensorRules") VALUES (1, 1, 'Generic Android Device', 'org.ambientdynamix.contextplugins.batteryLevelPlugin,org.ambientdynamix.contextplugins.batteryTemperaturePlugin,org.ambientdynamix.contextplugins.GpsPlugin,org.ambientdynamix.contextplugins.WifiScanPlugin');


--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 180
-- Name: smartphones_id_seq; Type: SEQUENCE SET; Schema: androidcampaigns; Owner: postgres
--

SELECT pg_catalog.setval('smartphones_id_seq', 1, false);


--
-- TOC entry 2049 (class 0 OID 16420)
-- Dependencies: 172
-- Data for Name: user; Type: TABLE DATA; Schema: androidcampaigns; Owner: postgres
--

INSERT INTO "user" (id, is_admin, role, username, password, email, firstname, lastname, points, cooldown) VALUES (1, 1, 0, 'admin', '$2a$10$dnrU46nko2wRUUfN8GsLI.QOjHQVKNTAEGJtQPGmc2//kn27.awpW', 'admin', 'admin', 'admin', 0, 0);
INSERT INTO "user" (id, is_admin, role, username, password, email, firstname, lastname, points, cooldown) VALUES (2, 0, 1, 'test', '$2a$10$dnrU46nko2wRUUfN8GsLI.QOjHQVKNTAEGJtQPGmc2//kn27.awpW', 'test', 'test', 'test', 0, 0);
INSERT INTO "user" (id, is_admin, role, username, password, email, firstname, lastname, points, cooldown) VALUES (3, 0, 1, 'android', 'android', 'android', 'android', 'android', 0, 0);


--
-- TOC entry 1931 (class 2606 OID 16448)
-- Name: campaign_pmkey; Type: CONSTRAINT; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY campaign
    ADD CONSTRAINT campaign_pmkey PRIMARY KEY (id);


--
-- TOC entry 1933 (class 2606 OID 16459)
-- Name: experiments_pmkey; Type: CONSTRAINT; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY experiments
    ADD CONSTRAINT experiments_pmkey PRIMARY KEY (id);


--
-- TOC entry 1935 (class 2606 OID 16470)
-- Name: plugins_pmkey; Type: CONSTRAINT; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY plugins
    ADD CONSTRAINT plugins_pmkey PRIMARY KEY (id);


--
-- TOC entry 1937 (class 2606 OID 16481)
-- Name: results_pmkey; Type: CONSTRAINT; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY results
    ADD CONSTRAINT results_pmkey PRIMARY KEY (id);


--
-- TOC entry 1939 (class 2606 OID 16493)
-- Name: smartphones_pmkey; Type: CONSTRAINT; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY smartphones
    ADD CONSTRAINT smartphones_pmkey PRIMARY KEY (id);


--
-- TOC entry 1929 (class 2606 OID 16433)
-- Name: user_pmkey; Type: CONSTRAINT; Schema: androidcampaigns; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pmkey PRIMARY KEY (id);


--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 6
-- Name: androidcampaigns; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA androidcampaigns FROM PUBLIC;
REVOKE ALL ON SCHEMA androidcampaigns FROM postgres;
GRANT ALL ON SCHEMA androidcampaigns TO postgres;
GRANT ALL ON SCHEMA androidcampaigns TO PUBLIC;


-- Completed on 2015-05-14 12:07:34

--
-- PostgreSQL database dump complete
--

