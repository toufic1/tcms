-- One-time setup of the LOCAL development database.
-- Run it as the PostgreSQL admin user ("postgres"), from the tcms folder:
--   "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -h localhost -f db/local-setup.sql
--
-- Why a separate "tcms" user instead of using "postgres"?
--   Least privilege: the app only gets rights on its own database. If the app is ever
--   hacked, the attacker can't touch other databases or create new admin users.
--
-- The password "tcms" is ONLY for your laptop (it's the default in application.properties).
-- On the VPS we will use a strong password passed in through an environment variable.

CREATE ROLE tcms WITH LOGIN PASSWORD 'tcms';

CREATE DATABASE tcms OWNER tcms;
