-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 24 Sty 2018, 23:01
-- Wersja serwera: 5.5.58-0ubuntu0.14.04.1
-- Wersja PHP: 5.5.9-1ubuntu4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `Szpital2`
--

DELIMITER $$
--
-- Procedury
--
CREATE DEFINER=`szpital`@`%` PROCEDURE `ABC`()
BEGIN
      DECLARE a INT Default 0 ;
      simple_loop: LOOP
			SET a = a + 1;
         insert RezerwacjeSali (id_sali, id_rezerwujacego, termin, informacja) values ( ELT(0.5 + RAND() * 3, 1, 2, 3),ELT(0.5 + RAND() * 4, 1, 2, 3, 4),DATE_ADD(sysdate(), INTERVAL (FLOOR(RAND() * 23) +1) DAY),'rezerwacja');
         IF a=300 THEN
            LEAVE simple_loop;
         END IF;
   END LOOP simple_loop;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Badania`
--

CREATE TABLE IF NOT EXISTS `Badania` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=6 ;

--
-- Zrzut danych tabeli `Badania`
--

INSERT INTO `Badania` (`id`, `nazwa`) VALUES
(1, 'Wymaz z nosa'),
(2, 'Wymaz z oka'),
(3, 'gastroskopia'),
(4, 'badanie krwi'),
(5, 'badanie moczu');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `BadaniaPacjentow`
--

CREATE TABLE IF NOT EXISTS `BadaniaPacjentow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_badania` int(11) NOT NULL,
  `id_pacjenta` int(11) NOT NULL,
  `data` date NOT NULL,
  `wynik` varchar(450) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pacjenta` (`id_pacjenta`),
  KEY `id_badania` (`id_badania`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=50888 ;

--
-- Zrzut danych tabeli `BadaniaPacjentow`
--

INSERT INTO `BadaniaPacjentow` (`id`, `id_badania`, `id_pacjenta`, `data`, `wynik`) VALUES
(1, 1, 1, '2017-11-11', 'nieznane'),
(2, 3, 1, '2017-11-27', 'nieznane'),
(3, 4, 17, '2017-11-08', 'pozytywny'),
(4, 4, 19, '2017-11-14', 'negatywny'),
(5, 1, 15, '2017-11-30', 'nieznane '),
(6, 1, 1, '2017-11-30', 'poz'),
(10, 1, 1, '2017-11-30', 'poz'),
(11, 2, 2, '1970-01-01', 'sdvesdcx'),
(16, 2, 2, '1970-01-01', 'xzxx'),
(17, 3, 2, '1970-01-01', 'asdsd'),
(18, 3, 3, '1970-01-01', 'hhh'),
(20, 3, 3, '2017-12-29', 'Testdata - mod2'),
(21, 3, 3, '2017-12-29', 'edit2'),
(22, 2, 22, '2017-12-10', 'wynik'),
(24, 2, 6, '2017-12-10', 'wynik'),
(26, 1, 5, '2017-12-10', 'wynik'),
(27, 2, 3, '2017-12-10', 'wynik'),
(28, 2, 3, '2017-12-10', 'wynik'),
(31, 1, 1, '2017-11-30', 'poz'),
(33, 4, 11, '2017-12-10', 'wynik'),
(34, 3, 6, '2017-12-10', 'wynik'),
(36, 3, 1, '2017-12-10', 'wynik'),
(38, 5, 4, '2017-12-10', 'wynik'),
(39, 5, 6, '2017-12-10', 'wynik'),
(40, 5, 4, '2017-12-10', 'wynik'),
(50873, 4, 3, '2017-12-20', 'fghfgh'),
(50874, 3, 3, '2017-12-14', 'tttttttt'),
(50875, 3, 3, '2017-12-21', 'spr'),
(50876, 5, 5, '2017-11-27', 'dddddddddd'),
(50877, 5, 5, '2017-12-30', 'dasd'),
(50878, 5, 3, '2016-12-14', 'b'),
(50879, 2, 3, '2017-12-29', 'zxc'),
(50880, 2, 7, '2017-12-27', 'sasdasad'),
(50881, 4, 3, '2017-11-29', 'dd'),
(50882, 1, 3, '2017-12-22', 'nos'),
(50883, 3, 3, '2017-12-19', 'hgfgh'),
(50884, 1, 3, '2017-12-07', 'l2'),
(50885, 5, 3, '2017-12-20', 'dziala'),
(50886, 3, 2, '2018-01-19', 'fff'),
(50887, 4, 2, '2018-02-09', 'ddddd');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Dystrybutorzy`
--

CREATE TABLE IF NOT EXISTS `Dystrybutorzy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `mail` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `telefon` varchar(12) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `Dystrybutorzy`
--

INSERT INTO `Dystrybutorzy` (`id`, `nazwa`, `mail`, `telefon`) VALUES
(1, 'LekPol', 'lekpol@mail.com', '123123123'),
(2, 'Leki Kowalski', 'lek@mail.com', '123222222');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `DyzuryLekarzy`
--

CREATE TABLE IF NOT EXISTS `DyzuryLekarzy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_lekarza` int(11) NOT NULL,
  `data_od` datetime DEFAULT NULL,
  `data_do` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_lekarza` (`id_lekarza`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=20 ;

--
-- Zrzut danych tabeli `DyzuryLekarzy`
--

INSERT INTO `DyzuryLekarzy` (`id`, `id_lekarza`, `data_od`, `data_do`) VALUES
(1, 1, '2018-01-18 00:00:00', '2018-01-18 08:00:00'),
(2, 1, '2018-01-22 00:00:00', '2018-01-22 08:00:00'),
(3, 5, '2018-01-18 16:00:00', '2018-01-19 00:00:00'),
(12, 1, '2018-01-24 00:00:00', '2018-01-24 08:00:00'),
(16, 5, '2018-01-24 16:00:00', '2018-01-25 00:00:00'),
(17, 5, '2018-01-28 00:00:00', '2018-01-28 08:00:00'),
(18, 5, '2018-01-31 16:00:00', '2018-02-01 00:00:00'),
(19, 1, '2018-01-30 00:00:00', '2018-01-30 08:00:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `DyzuryPielegniarek`
--

CREATE TABLE IF NOT EXISTS `DyzuryPielegniarek` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pielegniarki` int(11) NOT NULL,
  `data_od` datetime DEFAULT NULL,
  `data_do` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pielegniarki` (`id_pielegniarki`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=34 ;

--
-- Zrzut danych tabeli `DyzuryPielegniarek`
--

INSERT INTO `DyzuryPielegniarek` (`id`, `id_pielegniarki`, `data_od`, `data_do`) VALUES
(17, 5, '2018-01-24 00:00:00', '2018-01-24 08:00:00'),
(18, 6, '2018-01-24 00:00:00', '2018-01-24 08:00:00'),
(25, 7, '2018-01-24 16:00:00', '2018-01-25 00:00:00'),
(26, 8, '2018-01-24 16:00:00', '2018-01-25 00:00:00'),
(27, 5, '2018-01-28 00:00:00', '2018-01-28 08:00:00'),
(28, 6, '2018-01-28 00:00:00', '2018-01-28 08:00:00'),
(29, 7, '2018-01-31 16:00:00', '2018-02-01 00:00:00'),
(30, 8, '2018-01-31 16:00:00', '2018-02-01 00:00:00'),
(31, 5, '2018-01-30 00:00:00', '2018-01-30 08:00:00'),
(32, 6, '2018-01-30 00:00:00', '2018-01-30 08:00:00'),
(33, 13, '2018-01-25 00:00:00', '2018-01-25 08:00:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Konta`
--

CREATE TABLE IF NOT EXISTS `Konta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `haslo` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `pesel` varchar(11) COLLATE utf8_polish_ci NOT NULL,
  `id_stanowiska` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_stanowiska` (`id_stanowiska`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=10 ;

--
-- Zrzut danych tabeli `Konta`
--

INSERT INTO `Konta` (`id`, `haslo`, `pesel`, `id_stanowiska`) VALUES
(1, 'haslo', '00010155555', 0),
(2, 'haslo1', '00020211111', 1),
(3, 'haslo2', '00010133333', 2),
(4, 'haslo3', '00556688888', 1),
(8, 'haslo', '33333333333', 3),
(9, 'haslo', '44444444444', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Lekarze`
--

CREATE TABLE IF NOT EXISTS `Lekarze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `pesel` varchar(11) COLLATE utf8_polish_ci NOT NULL,
  `id_stanowiska` int(11) NOT NULL,
  `id_oddzialu` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_stanowiska` (`id_stanowiska`),
  KEY `id_oddzialu` (`id_oddzialu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=13 ;

--
-- Zrzut danych tabeli `Lekarze`
--

INSERT INTO `Lekarze` (`id`, `imie`, `nazwisko`, `pesel`, `id_stanowiska`, `id_oddzialu`) VALUES
(0, '  ', 'nieznany', ' ', 0, 0),
(1, 'John', 'Dolittle', '00020211111', 1, 1),
(2, 'Stephen', 'Strange', '00020244444', 1, 2),
(3, 'Bartek', 'Stefanowski', '00556688888', 1, 3),
(4, 'Zbigniew', 'Nowacki', '74859612345', 1, 1),
(5, 'Michał', 'Nowak', '33333333333', 3, 1),
(6, 'Jerzy', 'Gorzki', '77859612345', 1, 1),
(7, 'Henryk', 'Wielki', '23459612345', 1, 2),
(8, 'Wilhelm', 'Zdobywca', '41859612345', 1, 2),
(9, 'Ludwik', 'Luksemburg', '43856712345', 3, 2),
(10, 'Grzegorz', 'Franz', '62547812345', 1, 3),
(11, 'Tomasz', 'Werski', '75869412345', 1, 3),
(12, 'Mariusz', 'Dort', '44678512345', 3, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Leki`
--

CREATE TABLE IF NOT EXISTS `Leki` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `opis` varchar(300) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `id_producenta` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_producenta` (`id_producenta`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `Leki`
--

INSERT INTO `Leki` (`id`, `nazwa`, `opis`, `id_producenta`) VALUES
(1, 'lek1', 'opis', 1),
(2, 'lek2', 'opis', 2),
(3, 'lek3', 'oppis', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `LekiPacjentow`
--

CREATE TABLE IF NOT EXISTS `LekiPacjentow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_leku` int(11) NOT NULL,
  `id_pacjenta` int(11) NOT NULL,
  `od_termin` date NOT NULL,
  `do_temin` date DEFAULT NULL,
  `dawkowanie` varchar(450) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pacjenta` (`id_pacjenta`),
  KEY `id_leku` (`id_leku`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=616 ;

--
-- Zrzut danych tabeli `LekiPacjentow`
--

INSERT INTO `LekiPacjentow` (`id`, `id_leku`, `id_pacjenta`, `od_termin`, `do_temin`, `dawkowanie`) VALUES
(1, 1, 3, '2017-11-26', '2017-11-30', '1 tabletka dziennie'),
(2, 3, 11, '2017-11-30', '2017-12-28', '2 tabletki dziennie'),
(3, 3, 17, '2017-11-09', '2017-12-23', '2 tabletki przed posiłkiem'),
(4, 2, 1, '2017-12-12', '2017-12-15', '1 tebletka dziennie'),
(5, 1, 2, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(6, 3, 5, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(7, 1, 4, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(8, 3, 4, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(9, 2, 4, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(10, 3, 6, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(11, 2, 5, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(12, 1, 3, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(13, 3, 4, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(14, 2, 6, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(15, 2, 1, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(16, 2, 6, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(17, 3, 5, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(18, 2, 5, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(19, 3, 3, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(20, 3, 2, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(21, 3, 3, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(22, 2, 6, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(23, 3, 4, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(24, 2, 3, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(25, 2, 2, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(26, 3, 5, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(27, 3, 4, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(28, 3, 1, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(30, 3, 6, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(31, 3, 5, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(32, 2, 6, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(33, 2, 5, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(34, 1, 3, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(36, 1, 3, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(37, 3, 2, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(38, 2, 3, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(39, 3, 5, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(40, 2, 6, '2017-12-10', '2017-12-20', '1 tab dziennie'),
(608, 1, 1, '2018-01-10', '2018-01-10', 'ffff'),
(609, 1, 1, '2018-01-10', '2018-01-10', 'uuuu'),
(610, 1, 15, '2018-01-11', '2018-01-11', 'www'),
(611, 1, 7, '2018-01-10', '2018-01-10', 'dfsdf');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Lozka`
--

CREATE TABLE IF NOT EXISTS `Lozka` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_Sali` int(11) NOT NULL,
  `wolne` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_Sali` (`id_Sali`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=24 ;

--
-- Zrzut danych tabeli `Lozka`
--

INSERT INTO `Lozka` (`id`, `id_Sali`, `wolne`) VALUES
(0, 0, 1),
(1, 1, 0),
(2, 1, 0),
(3, 2, 0),
(4, 2, 0),
(5, 3, 0),
(6, 4, 1),
(7, 5, 0),
(8, 5, 0),
(9, 5, 0),
(10, 6, 0),
(11, 7, 0),
(12, 7, 1),
(13, 8, 0),
(14, 8, 0),
(15, 9, 0),
(16, 10, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Oddzialy`
--

CREATE TABLE IF NOT EXISTS `Oddzialy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) CHARACTER SET latin1 NOT NULL,
  `l_mijesc` int(11) NOT NULL,
  `l_wolnych_miejsc` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=5 ;

--
-- Zrzut danych tabeli `Oddzialy`
--

INSERT INTO `Oddzialy` (`id`, `nazwa`, `l_mijesc`, `l_wolnych_miejsc`) VALUES
(0, 'nieznany', 0, 0),
(1, 'SOR', 20, 20),
(2, 'OIOM', 30, 30),
(3, 'Anestezjologia', 50, 50);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Pacjenci`
--

CREATE TABLE IF NOT EXISTS `Pacjenci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(50) CHARACTER SET ucs2 COLLATE ucs2_polish_ci NOT NULL,
  `nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `pesel` varchar(11) COLLATE utf8_polish_ci NOT NULL,
  `id_lekarza` int(11) NOT NULL,
  `id_oddzialu` int(11) NOT NULL,
  `gr_krwi` enum('A rh+','A rh-','B rh+','B rh-','AB rh+','AB rh-','0 rh+','0 rh-','nieznana') COLLATE utf8_polish_ci DEFAULT NULL,
  `nr_sali` int(11) DEFAULT NULL,
  `nr_lozka` int(11) DEFAULT NULL,
  `email` varchar(60) COLLATE utf8_polish_ci DEFAULT 'test.mail.projekt.szpital@gmail.com',
  PRIMARY KEY (`id`),
  KEY `id_lekarza` (`id_lekarza`),
  KEY `id_oddzialu` (`id_oddzialu`),
  KEY `nr_sali` (`nr_sali`),
  KEY `nr_lozka` (`nr_lozka`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=34 ;

--
-- Zrzut danych tabeli `Pacjenci`
--

INSERT INTO `Pacjenci` (`id`, `imie`, `nazwisko`, `pesel`, `id_lekarza`, `id_oddzialu`, `gr_krwi`, `nr_sali`, `nr_lozka`, `email`) VALUES
(1, 'Janusz', 'Tracz', '63010188888', 2, 2, 'A rh-', 5, 7, 'test.mail.projekt.szpital@gmail.com'),
(2, 'Karolina', 'Kowalska', '78030399999', 1, 1, '0 rh-', 2, 3, 'test.mail.projekt.szpital@gmail.com'),
(3, 'Arkadiusz', 'Glowacki', '81050544444', 1, 1, 'AB rh-', 2, 4, 'test.mail.projekt.szpital@gmail.com'),
(4, 'Michal', 'Aniol', '00112233333', 2, 2, 'B rh-', 6, 10, 'test.mail.projekt.szpital@gmail.com'),
(5, 'Ala', 'Kowalska', '74859612301', 3, 3, 'B rh-', 8, 13, 'patryk.szmekel@gmail.com'),
(6, 'Henryk', 'Wielki', '96857412355', 3, 3, 'B rh-', 8, 14, 'test.mail.projekt.szpital@gmail.com'),
(7, 'Juan', 'Nowakł', '94121102122', 1, 1, '0 rh+', 1, 2, 'test.mail.projekt.szpital@gmail.com'),
(11, 'Jan', 'Kowalski', '56451200033', 3, 3, 'B rh-', 10, 16, 'test.mail.projekt.szpital@gmail.com'),
(15, 'Aleksander', 'Maly', '45127896302', 1, 1, 'AB rh+', 1, 1, 'test.mail.projekt.szpital@gmail.com'),
(17, 'Patryk', 'Michalak', '97121309033', 1, 1, 'AB rh-', 3, 5, 'test.mail.projekt.szpital@gmail.com'),
(19, 'Michał', 'Nowa', '12345678901', 2, 3, 'AB rh+', 9, 15, 'test.mail.projekt.szpital@gmail.com'),
(21, 'asd', 'asd', '12312312312', 2, 2, 'B rh-', 5, 9, 'test.mail.projekt.szpital@gmail.com'),
(22, 'Roberto', 'Carlos', '75231489652', 0, 0, 'nieznana', 0, 0, 'test.mail.projekt.szpital@gmail.com'),
(23, 'Aleksandra', 'Nowacka', '75864212345', 0, 0, 'nieznana', 0, 0, 'test.mail.projekt.szpital@gmail.com'),
(24, 'Klaudia', 'Hoffman', '85967412345', 0, 0, 'nieznana', 0, 0, 'test.mail.projekt.szpital@gmail.com'),
(26, 'd', 'dd', '12312312312', 1, 2, 'B rh-', 5, 8, 'test.mail.projekt.szpital@gmail.com'),
(27, 'w', 'ww', '12312312312', 3, 2, 'B rh-', 7, 11, 'test.mail.projekt.szpital@gmail.com'),
(29, 'Michał', 'Kowalski', '98767876823', 5, 1, 'A rh-', 0, 0, 'test.mail.projekt.szpital@gmail.com'),
(32, 'Edward', 'Gorzyński', '85749612345', 6, 1, 'B rh+', 0, 0, 'test.mail.projekt.szpital@gmail.com'),
(33, 'Eld', 'Elddd', '12345678900', 0, 0, 'A rh+', 0, 0, '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Personel`
--

CREATE TABLE IF NOT EXISTS `Personel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `pesel` varchar(11) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `id_stanowiska` int(11) NOT NULL,
  `id_oddzialu` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_stanowiska` (`id_stanowiska`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci AUTO_INCREMENT=14 ;

--
-- Zrzut danych tabeli `Personel`
--

INSERT INTO `Personel` (`id`, `imie`, `nazwisko`, `pesel`, `id_stanowiska`, `id_oddzialu`) VALUES
(1, 'Jan', 'Kowalski', '00010155555', 0, 0),
(3, 'Adam', 'Stefanowski', '00010133333', 2, 0),
(5, 'Joanna', 'Kowalska', '85749612345', 4, 1),
(6, 'Anna', 'Nowak', '85967412300', 4, 1),
(7, 'Nina', 'Śliwka', '85967454321', 4, 1),
(8, 'Magdalena', 'Wolska', '74859612345', 4, 1),
(9, 'Katarzyna', 'Pomidor', '34567812345', 4, 2),
(10, 'Jolanta', 'Rzepecka', '68759412345', 4, 2),
(11, 'Lucjana', 'Kazimierczak', '82974612345', 4, 2),
(12, 'Wiktoria', 'Kostecka', '67859412345', 4, 2),
(13, 'Monika', 'Nowak', '44444444444', 4, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Producenci`
--

CREATE TABLE IF NOT EXISTS `Producenci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `kraj` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `id_dystrybutora` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_dystrybutora` (`id_dystrybutora`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `Producenci`
--

INSERT INTO `Producenci` (`id`, `nazwa`, `kraj`, `id_dystrybutora`) VALUES
(1, 'lek1', 'Polska', 1),
(2, 'lek2', 'Polska', 1),
(3, 'lek3', 'Niemcy', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `RezerwacjeSali`
--

CREATE TABLE IF NOT EXISTS `RezerwacjeSali` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_sali` int(11) NOT NULL,
  `id_rezerwujacego` int(11) NOT NULL,
  `termin` datetime NOT NULL,
  `informacja` varchar(450) COLLATE utf8_polish_ci DEFAULT ' ',
  PRIMARY KEY (`id`),
  KEY `id_sali` (`id_sali`),
  KEY `id_rezerwujacego` (`id_rezerwujacego`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=338 ;

--
-- Zrzut danych tabeli `RezerwacjeSali`
--

INSERT INTO `RezerwacjeSali` (`id`, `id_sali`, `id_rezerwujacego`, `termin`, `informacja`) VALUES
(1, 1, 3, '2017-11-26 11:00:00', 'wyrostek'),
(2, 2, 2, '2017-11-26 18:00:00', 'zdjęcie gipsu'),
(3, 1, 4, '2017-11-27 08:00:00', 'koronarografia'),
(4, 3, 2, '2017-11-27 10:00:00', 'wszczepienie bypassów'),
(5, 2, 4, '2017-11-26 14:00:00', 'zdjęcie szwów'),
(6, 3, 1, '2017-12-12 08:00:00', 'przeszczep nerki'),
(7, 1, 1, '2017-12-20 10:00:00', 'amputacja nogi'),
(309, 3, 1, '2017-12-12 07:00:00', ''),
(315, 2, 1, '2017-12-21 07:00:00', ' '),
(316, 2, 1, '2017-12-21 11:00:00', ' '),
(317, 3, 1, '2017-12-21 04:00:00', ' '),
(318, 3, 1, '2017-12-21 05:00:00', ' '),
(319, 3, 1, '2017-12-21 06:00:00', ' '),
(320, 3, 1, '2017-12-21 08:00:00', ' '),
(321, 3, 1, '2017-12-21 09:00:00', ' '),
(327, 1, 1, '2017-12-14 16:00:00', ' '),
(328, 1, 1, '2017-12-28 04:00:00', ' '),
(330, 1, 1, '2018-01-02 03:00:00', ' '),
(331, 1, 1, '2018-01-02 04:00:00', ' '),
(332, 1, 1, '2018-01-02 05:00:00', ' '),
(333, 1, 1, '2018-01-03 04:00:00', ' '),
(334, 1, 1, '2018-01-11 02:00:00', ' '),
(335, 3, 1, '2018-01-12 02:00:00', ' '),
(336, 1, 1, '2018-01-24 02:00:00', ' '),
(337, 1, 1, '2018-01-24 04:00:00', ' ');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Sale`
--

CREATE TABLE IF NOT EXISTS `Sale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `id_oddzialu` int(11) NOT NULL,
  `opis` varchar(300) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_oddzialu` (`id_oddzialu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `Sale`
--

INSERT INTO `Sale` (`id`, `nazwa`, `id_oddzialu`, `opis`) VALUES
(1, 'Sala 01', 1, 'Sala operacyjna'),
(2, 'Sala 11', 2, 'sala zabiegowa'),
(3, 'Sala 02', 1, 'Sala operacyjna');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `SaleSzpitalne`
--

CREATE TABLE IF NOT EXISTS `SaleSzpitalne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `id_Oddzialu` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_Oddzialu` (`id_Oddzialu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=12 ;

--
-- Zrzut danych tabeli `SaleSzpitalne`
--

INSERT INTO `SaleSzpitalne` (`id`, `nazwa`, `id_Oddzialu`) VALUES
(0, 'nieznana', 0),
(1, '100', 1),
(2, '101', 1),
(3, '102', 1),
(4, '103', 1),
(5, '200', 2),
(6, '201', 2),
(7, '202', 2),
(8, '300', 3),
(9, '301', 3),
(10, '302', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Stanowiska`
--

CREATE TABLE IF NOT EXISTS `Stanowiska` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=5 ;

--
-- Zrzut danych tabeli `Stanowiska`
--

INSERT INTO `Stanowiska` (`id`, `nazwa`) VALUES
(0, 'nieznane'),
(1, 'lekarz'),
(2, 'rejestracja'),
(3, 'ordynator'),
(4, 'pielegniarka');

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `BadaniaPacjentow`
--
ALTER TABLE `BadaniaPacjentow`
  ADD CONSTRAINT `BadaniaPacjentow_ibfk_1` FOREIGN KEY (`id_pacjenta`) REFERENCES `Pacjenci` (`id`),
  ADD CONSTRAINT `BadaniaPacjentow_ibfk_2` FOREIGN KEY (`id_badania`) REFERENCES `Badania` (`id`);

--
-- Ograniczenia dla tabeli `DyzuryLekarzy`
--
ALTER TABLE `DyzuryLekarzy`
  ADD CONSTRAINT `DyzuryLekarzy_ibfk_1` FOREIGN KEY (`id_lekarza`) REFERENCES `Lekarze` (`id`);

--
-- Ograniczenia dla tabeli `DyzuryPielegniarek`
--
ALTER TABLE `DyzuryPielegniarek`
  ADD CONSTRAINT `DyzuryPielegniarek_ibfk_1` FOREIGN KEY (`id_pielegniarki`) REFERENCES `Personel` (`id`);

--
-- Ograniczenia dla tabeli `Konta`
--
ALTER TABLE `Konta`
  ADD CONSTRAINT `Konta_ibfk_1` FOREIGN KEY (`id_stanowiska`) REFERENCES `Stanowiska` (`id`);

--
-- Ograniczenia dla tabeli `Lekarze`
--
ALTER TABLE `Lekarze`
  ADD CONSTRAINT `Lekarze_ibfk_1` FOREIGN KEY (`id_stanowiska`) REFERENCES `Stanowiska` (`id`),
  ADD CONSTRAINT `Lekarze_ibfk_2` FOREIGN KEY (`id_oddzialu`) REFERENCES `Oddzialy` (`id`);

--
-- Ograniczenia dla tabeli `Leki`
--
ALTER TABLE `Leki`
  ADD CONSTRAINT `Leki_ibfk_1` FOREIGN KEY (`id_producenta`) REFERENCES `Producenci` (`id`);

--
-- Ograniczenia dla tabeli `LekiPacjentow`
--
ALTER TABLE `LekiPacjentow`
  ADD CONSTRAINT `LekiPacjentow_ibfk_1` FOREIGN KEY (`id_pacjenta`) REFERENCES `Pacjenci` (`id`),
  ADD CONSTRAINT `LekiPacjentow_ibfk_2` FOREIGN KEY (`id_leku`) REFERENCES `Leki` (`id`);

--
-- Ograniczenia dla tabeli `Lozka`
--
ALTER TABLE `Lozka`
  ADD CONSTRAINT `Lozka_ibfk_1` FOREIGN KEY (`id_Sali`) REFERENCES `SaleSzpitalne` (`id`);

--
-- Ograniczenia dla tabeli `Pacjenci`
--
ALTER TABLE `Pacjenci`
  ADD CONSTRAINT `Pacjenci_ibfk_1` FOREIGN KEY (`id_lekarza`) REFERENCES `Lekarze` (`id`),
  ADD CONSTRAINT `Pacjenci_ibfk_2` FOREIGN KEY (`id_oddzialu`) REFERENCES `Oddzialy` (`id`),
  ADD CONSTRAINT `Pacjenci_ibfk_3` FOREIGN KEY (`nr_sali`) REFERENCES `SaleSzpitalne` (`id`),
  ADD CONSTRAINT `Pacjenci_ibfk_4` FOREIGN KEY (`nr_lozka`) REFERENCES `Lozka` (`id`);

--
-- Ograniczenia dla tabeli `Personel`
--
ALTER TABLE `Personel`
  ADD CONSTRAINT `Personel_ibfk_1` FOREIGN KEY (`id_stanowiska`) REFERENCES `Stanowiska` (`id`);

--
-- Ograniczenia dla tabeli `Producenci`
--
ALTER TABLE `Producenci`
  ADD CONSTRAINT `Producenci_ibfk_1` FOREIGN KEY (`id_dystrybutora`) REFERENCES `Dystrybutorzy` (`id`);

--
-- Ograniczenia dla tabeli `RezerwacjeSali`
--
ALTER TABLE `RezerwacjeSali`
  ADD CONSTRAINT `RezerwacjeSali_ibfk_1` FOREIGN KEY (`id_sali`) REFERENCES `Sale` (`id`),
  ADD CONSTRAINT `RezerwacjeSali_ibfk_2` FOREIGN KEY (`id_rezerwujacego`) REFERENCES `Konta` (`id`);

--
-- Ograniczenia dla tabeli `Sale`
--
ALTER TABLE `Sale`
  ADD CONSTRAINT `Sale_ibfk_1` FOREIGN KEY (`id_oddzialu`) REFERENCES `Oddzialy` (`id`);

--
-- Ograniczenia dla tabeli `SaleSzpitalne`
--
ALTER TABLE `SaleSzpitalne`
  ADD CONSTRAINT `SaleSzpitalne_ibfk_1` FOREIGN KEY (`id_Oddzialu`) REFERENCES `Oddzialy` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
