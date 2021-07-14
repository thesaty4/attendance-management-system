-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2021 at 07:42 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `attendance_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance_info`
--

CREATE TABLE `attendance_info` (
  `roll_no` int(11) DEFAULT NULL,
  `date` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `iss_present` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `attendance_info`
--

INSERT INTO `attendance_info` (`roll_no`, `date`, `month`, `year`, `iss_present`) VALUES
(1, 12, 4, 2021, 1),
(2, 12, 4, 2021, 1),
(1, 13, 4, 2021, 1),
(2, 13, 4, 2021, 1);

-- --------------------------------------------------------

--
-- Table structure for table `course_info`
--

CREATE TABLE `course_info` (
  `course_name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `c_date` date DEFAULT current_timestamp(),
  `c_time` time DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_info`
--

INSERT INTO `course_info` (`course_name`, `description`, `c_date`, `c_time`) VALUES
('A-Level', 'certification', '2021-04-12', '08:13:44'),
('B-Level', 'master course', '2021-04-12', '07:47:09'),
('O-Level', 'certification', '2021-04-12', '07:45:12');

-- --------------------------------------------------------

--
-- Table structure for table `login_info`
--

CREATE TABLE `login_info` (
  `usr_name` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `mobile` varchar(14) DEFAULT NULL,
  `passwd` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `student_info`
--

CREATE TABLE `student_info` (
  `roll_no` int(11) NOT NULL,
  `stu_name` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `father` varchar(50) DEFAULT NULL,
  `course_name` varchar(50) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `regi_date` date DEFAULT current_timestamp(),
  `regi_time` time DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_info`
--

INSERT INTO `student_info` (`roll_no`, `stu_name`, `gender`, `father`, `course_name`, `mobile`, `address`, `state`, `regi_date`, `regi_time`) VALUES
(1, 'satya mishra', 'male', 'hariram mishra', 'a-level', '1234567890', 'gkp', 'uttar pradesh', '2021-04-12', '09:07:06'),
(2, 'amisha sharma', 'female', 'sharma ji', 'a-level', '1234567890', 'gkp', 'uttar pradesh', '2021-04-12', '09:07:51');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance_info`
--
ALTER TABLE `attendance_info`
  ADD KEY `roll_no` (`roll_no`);

--
-- Indexes for table `course_info`
--
ALTER TABLE `course_info`
  ADD PRIMARY KEY (`course_name`);

--
-- Indexes for table `login_info`
--
ALTER TABLE `login_info`
  ADD PRIMARY KEY (`usr_name`);

--
-- Indexes for table `student_info`
--
ALTER TABLE `student_info`
  ADD PRIMARY KEY (`roll_no`),
  ADD KEY `course_name` (`course_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student_info`
--
ALTER TABLE `student_info`
  MODIFY `roll_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance_info`
--
ALTER TABLE `attendance_info`
  ADD CONSTRAINT `attendance_info_ibfk_1` FOREIGN KEY (`roll_no`) REFERENCES `student_info` (`roll_no`);

--
-- Constraints for table `student_info`
--
ALTER TABLE `student_info`
  ADD CONSTRAINT `student_info_ibfk_1` FOREIGN KEY (`course_name`) REFERENCES `course_info` (`course_name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
