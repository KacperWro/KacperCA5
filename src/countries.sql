DROP DATABASE IF EXISTS countries_info;
CREATE DATABASE countries_info;

DROP TABLE IF EXISTS `countries`;

CREATE TABLE `countries` (
	`countryID` int(5) NOT NULL,
	`continent` varchar(50),
	`countryName` varchar(50),
	`capital` varchar(50),
	`population` int(12),
	`areaSqKm` float(20),
	`popDensitySqKm` float(20),
	PRIMARY KEY (countryID));

	ALTER TABLE `countries`
      MODIFY `countryID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
	
INSERT INTO `countries` (`countryID`, `continent`, `countryName`, `capital`, `population`, `areaSqKm`, `popDensitySqKm`) VALUES
(1, "Europe", "Ireland","Dublin",5011500,70273,71.3),
(2, "Europe", "United Kingdom","London",67081000,242495,270.7),
(3, "Europe","Iceland","Reykjavik",366425,103000,3.5),
(4, "Europe","Luxembourg","Luxembourg City",633622,2586.4,242),
(5, "Europe","Liechtenstein","Vaduz",38896,160,237),
(6, "North America", "Mexico", "Mexico City", 126014024,1972550,61),
(7, "Europe", "Denmark","Copenhagen",5873420,42933 ,137.65),
(8, "Europe","Poland","Warsaw",38179800,312696 ,123),
(9, "Europe","Germany","Berlin", 83190556,357022 ,232),
(10, "North America", "United States of America","Washington",331893745,9833520,33.6),
(11, "Europe","France","Paris",67413000,	643801,116),
(12, "North America", "Canada", "Ottawa", 38436447,9984670,4.2);
