PGDMP  .                    }            todolist    14.17 (Homebrew)    16.0     P           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            Q           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            R           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            S           1262    16385    todolist    DATABASE     j   CREATE DATABASE todolist WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE todolist;
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                halidazainon    false            T           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   halidazainon    false    4            �            1259    16396    tasks    TABLE     �   CREATE TABLE public.tasks (
    id integer NOT NULL,
    description character varying(255) NOT NULL,
    completed boolean DEFAULT false,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.tasks;
       public         heap    postgres    false    4            �            1259    16395    tasks_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tasks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.tasks_id_seq;
       public          postgres    false    210    4            U           0    0    tasks_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.tasks_id_seq OWNED BY public.tasks.id;
          public          postgres    false    209            �           2604    16399    tasks id    DEFAULT     d   ALTER TABLE ONLY public.tasks ALTER COLUMN id SET DEFAULT nextval('public.tasks_id_seq'::regclass);
 7   ALTER TABLE public.tasks ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    210    210            M          0    16396    tasks 
   TABLE DATA           I   COPY public.tasks (id, description, completed, created_date) FROM stdin;
    public          postgres    false    210   a       V           0    0    tasks_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.tasks_id_seq', 21, true);
          public          postgres    false    209            �           2606    16403    tasks tasks_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.tasks DROP CONSTRAINT tasks_pkey;
       public            postgres    false    210            M      x������ � �     