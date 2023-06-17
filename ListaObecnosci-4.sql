-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Cze 17, 2023 at 03:35 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ListaObecnosci`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `period`
--

CREATE TABLE `period` (
  `PeriodId` int(11) NOT NULL,
  `Date` date NOT NULL,
  `StartTime` time NOT NULL,
  `EndTime` time NOT NULL,
  `GroupId` int(11) NOT NULL,
  `SubjectId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `period`
--

INSERT INTO `period` (`PeriodId`, `Date`, `StartTime`, `EndTime`, `GroupId`, `SubjectId`) VALUES
(17, '2023-06-14', '01:00:00', '02:00:00', 2, 3),
(18, '2023-06-18', '11:00:00', '12:00:00', 2, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `presence`
--

CREATE TABLE `presence` (
  `presenceId` int(11) NOT NULL,
  `StudentIndex` varchar(6) NOT NULL,
  `PeriodId` int(11) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `presence`
--

INSERT INTO `presence` (`presenceId`, `StudentIndex`, `PeriodId`, `Status`) VALUES
(25, '696969', 17, 'Obecny'),
(35, '969696', 17, 'Nieobecny'),
(37, '765432', 17, 'UNKNOWN'),
(39, '696969', 18, 'Obecny'),
(40, '765432', 18, 'Nieobecny'),
(41, '969696', 18, 'Obecny');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `student`
--

CREATE TABLE `student` (
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `StudentIndex` varchar(6) NOT NULL,
  `GroupId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`FirstName`, `LastName`, `StudentIndex`, `GroupId`) VALUES
('Adam', 'Ludwiczak', '123456', NULL),
('Tomek', 'Pagacz', '696969', 2),
('Maciej', 'Kowalski', '765432', 2),
('Wojtek', 'Stachecki', '969696', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `studentgroup`
--

CREATE TABLE `studentgroup` (
  `GroupId` int(11) NOT NULL,
  `GroupName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `studentgroup`
--

INSERT INTO `studentgroup` (`GroupId`, `GroupName`) VALUES
(2, 'Grupa 1');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subject`
--

CREATE TABLE `subject` (
  `SubjectId` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SubjectId`, `Name`) VALUES
(3, 'matma');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `period`
--
ALTER TABLE `period`
  ADD PRIMARY KEY (`PeriodId`);

--
-- Indeksy dla tabeli `presence`
--
ALTER TABLE `presence`
  ADD PRIMARY KEY (`presenceId`);

--
-- Indeksy dla tabeli `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`StudentIndex`);

--
-- Indeksy dla tabeli `studentgroup`
--
ALTER TABLE `studentgroup`
  ADD PRIMARY KEY (`GroupId`);

--
-- Indeksy dla tabeli `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`SubjectId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `period`
--
ALTER TABLE `period`
  MODIFY `PeriodId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `presence`
--
ALTER TABLE `presence`
  MODIFY `presenceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `studentgroup`
--
ALTER TABLE `studentgroup`
  MODIFY `GroupId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `SubjectId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
