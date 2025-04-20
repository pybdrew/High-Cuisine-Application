-- MySQL dump 10.13  Distrib 8.0.41, for macos15 (arm64)
--
-- Host: 127.0.0.1    Database: menu_management
-- ------------------------------------------------------
-- Server version	8.0.40

-- Ensure the database exists
CREATE DATABASE IF NOT EXISTS menu_management;

-- Use the database
USE menu_management;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `first_name` VARCHAR(50) DEFAULT NULL,
  `last_name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES 
(1,'admin','Password!','admin','Admin','User')
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTS`
--

DROP TABLE IF EXISTS `PRODUCTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCTS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `DESCRIPTION` VARCHAR(500) NOT NULL,
  `IMAGE_URL` VARCHAR(255) NOT NULL,
  `TYPE` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTS`
--

LOCK TABLES `PRODUCTS` WRITE;
/*!40000 ALTER TABLE `PRODUCTS` DISABLE KEYS */;
INSERT INTO `PRODUCTS` VALUES 
(1,'Cappuccino','Rich espresso with steamed milk and foam','https://images.unsplash.com/photo-1541167760496-1628856ab772?auto=format&fit=crop&w=800&q=80','Drink'),
(2,'Iced Coffee','The perfect chill in every sip. Served in a clear glass with ice.','https://images.unsplash.com/photo-1461023058943-07fcbe16d735?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Drink'),
(3,'Espresso','Bold and intense—pure coffee energy in a cup.','https://images.unsplash.com/photo-1583165278997-0250ea5d72e2?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Drink'),
(4,'Mocha','Chocolate meets espresso in this sweet classic.','https://plus.unsplash.com/premium_photo-1668970851336-6c81cc888ba7?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Drink'),
(5,'Flat White','Smooth espresso with velvety steamed milk.','https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?auto=format&fit=crop&w=800&q=80','Drink'),
(6,'Macchiato','Espresso topped with a dollop of foam.','https://images.unsplash.com/photo-1563731649913-fab41907b535?q=80&w=1960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Drink'),
(7,'Cold Brew','Slow-steeped coffee served over ice.','https://plus.unsplash.com/premium_photo-1671559020928-dde18021036f?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Drink'),
(8,'Nitro Coffee','Infused with nitrogen for a creamy experience.','https://images.unsplash.com/photo-1649882453742-c93c481edb77?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Drink'),
(9,'Turkey Club','Smoked turkey with crispy bacon, lettuce, tomato, and mayo.','https://plus.unsplash.com/premium_photo-1664472757995-3260cd26e477?q=80&w=1961&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Sandwich'),
(10,'BLT','Crispy bacon, lettuce, tomato, and a dash of black pepper mayo.','https://images.unsplash.com/photo-1619096252214-ef06c45683e3?q=80&w=1925&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Sandwich'),
(11,'Grilled Cheese','Golden, melty cheddar cheese on toasted sourdough.','https://images.unsplash.com/photo-1528736235302-52922df5c122?q=80&w=1908&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Sandwich'),
(12,'Ham & Swiss','Black forest ham and melted Swiss with mustard on rye.','https://images.unsplash.com/photo-1481070414801-51fd732d7184?q=80&w=1924&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Sandwich'),
(13,'Veggie Delight','Fresh cucumbers, spinach, tomatoes, and hummus in a wrap.','https://plus.unsplash.com/premium_photo-1671559021919-19d9610c8cad?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Sandwich'),
(14,'Chicken Caesar Wrap','Grilled chicken, romaine, parmesan, and Caesar dressing.','https://plus.unsplash.com/premium_photo-1700677185925-81d4d3edc860?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D','Sandwich');
/*!40000 ALTER TABLE `PRODUCTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MENUS`
--

DROP TABLE IF EXISTS `MENUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MENUS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `DESCRIPTION` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Optional: Seed sample menu data
--

LOCK TABLES `MENUS` WRITE;
/*!40000 ALTER TABLE `MENUS` DISABLE KEYS */;
INSERT INTO `MENUS` (`ID`, `NAME`, `DESCRIPTION`) VALUES
-- ID, NAME, DESCRIPTION, IMAGE_URL, TYPE
(1, 'Drinks', 'Warm up with our curated selection of hot and cold beverages.', 'https://images.unsplash.com/photo-1509042239860-f550ce710b93?auto=format&fit=crop&w=800&q=80', 'drink'),
(2, 'Sandwiches', 'Explore our handcrafted sandwiches made with the freshest ingredients.', 'https://images.unsplash.com/photo-1586190848861-99aa4a171e90?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80', 'sandwich');

/*!40000 ALTER TABLE `MENUS` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Combined dump completed on 2025-04-20
