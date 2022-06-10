



USE ticket_webapp;



CREATE TABLE IF NOT EXISTS Employee (
	id 			INT UNSIGNED NOT NULL AUTO_INCREMENT,
	firstname 	VARCHAR(100) NOT NULL,
	lastname 	VARCHAR(100) NOT NULL,
	CONSTRAINT EmployeeUNIQ_fullname 
		UNIQUE (firstname,lastname),
	PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Employee (firstname,lastname) VALUES ("Steve","Jobs"),("Elon","Musk"),("Michael","Jordan"),("Nike","Company&Co"),("Thomas","Boullé");



CREATE TABLE IF NOT EXISTS Project (
	id 				INT UNSIGNED NOT NULL AUTO_INCREMENT,
	title 			VARCHAR(100) NOT NULL,
	creationDate 	TIMESTAMP NOT NULL DEFAULT NOW(),
	closed 			BOOLEAN NOT NULL DEFAULT FALSE,
	manager 		INT UNSIGNED NOT NULL,
	UNIQUE (title),
	CONSTRAINT ProjectFK_employeeID
		FOREIGN KEY (manager) REFERENCES Employee(id)
			ON DELETE RESTRICT,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Project (title,creationDate,closed,manager) VALUES 
					("Developpement du permier iPhone","2007-01-01 00:00:00",TRUE,1),
					("Developpement d'une puce intra_cérébrale","2016-09-10 21:00:00",FALSE,2),
					("Developpement modele Air Jordan 1","1984-10-17 21:00:00",TRUE,3),
					("R&D branding autour du modele Air Jordan","2001-02-15 18:00:00",TRUE,4),
					("Developpement d'une webapp de suivi de projet","2022-03-25 14:53:37",FALSE,5);



CREATE TABLE IF NOT EXISTS Version (
	project_id		INT UNSIGNED NOT NULL,
	label			VARCHAR(30) NOT NULL,
	CONSTRAINT VersionFK_projectID
		FOREIGN KEY (project_id) REFERENCES Project(id)
			ON DELETE CASCADE,
	INDEX versionLabel_idx (label ASC),
	PRIMARY KEY (project_id,label)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Version VALUES (1,"1.0.18"),(1,"2.1.46"),(1,"4.1.1-RELEASE"),(1,"3.0.1-SNAPSHOT"),(1,"4.4.1-RELEASE"),(1,"3.5.1-FIXED"),
						   (2,"1.6"),(2,"2.4.1-SNAPSHOT"),(2,"4.15.2-TEST"),(2,"6.13.4-TEST"),(2,"9.9.05-BETA"),
						   (3,"1.4.6"),(3,"1.12.6"),(3,"1.7.5-FIXED"),(3,"1.8.19-RELEASE"),
						   (4,"1.1.7"),(4,"1.2.3"),(4,"1.3.4"),(4,"1.6.1"),
						   (5,"1.0.1"),(5,"1.0.5-LICENSE"),(5,"1.2.1"),(5,"1.2.2"),(5,"1.2.4");



CREATE TABLE IF NOT EXISTS Statut (
	id			INT UNSIGNED NOT NULL AUTO_INCREMENT,
	label		VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Statut (label) VALUES ("OPEN"),("CLOSED"),("PULL_REQUEST"),("PEER_REVIEW"),("RESOLVED"),("COMMENTING"),("REQUIRE_MANAGER"),("RESEARCH_ISSUE");



CREATE TABLE IF NOT EXISTS Ticket (
	id				INT UNSIGNED NOT NULL AUTO_INCREMENT,
	title			VARCHAR(300) NOT NULL,
	creationDate	TIMESTAMP NOT NULL DEFAULT NOW(),
	description		VARCHAR(1000) DEFAULT NULL,
	statut_id		INT UNSIGNED NOT NULL,
	author_id		INT UNSIGNED NOT NULL,
	project_id		INT UNSIGNED NOT NULL,
	CONSTRAINT TicketFK_statutID
		FOREIGN KEY (statut_id) REFERENCES Statut(id)
			ON DELETE RESTRICT,
	CONSTRAINT TicketFK_employeeID
		FOREIGN KEY (author_id) REFERENCES Employee(id)
			ON DELETE RESTRICT,
	CONSTRAINT TicketFK_projectID
		FOREIGN KEY (project_id) REFERENCES Project(id)
			ON DELETE RESTRICT,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Ticket (title,creationDate,description,statut_id,author_id,project_id) VALUES
				   ("Pull request on ticket webapp project","2022-03-25 14:53:37","Pull request requie the project manager to validate the development of the functionnalities by making peer review for the testing of them",3,5,5),
				   ("Peer code review and testing for pull request","2022-03-25 14:53:37","Peer coding review in order to validate the code written by the dev asking for a pull request",3,5,5);


				   
CREATE TABLE IF NOT EXISTS TicketAssociation (
	ticket1_id		INT UNSIGNED NOT NULL,
	ticket2_id		INT UNSIGNED NOT NULL,
	CONSTRAINT TicketAssoFK_ticket1ID
		FOREIGN KEY (ticket1_id) REFERENCES Ticket(id)
			ON DELETE CASCADE,
	CONSTRAINT TicketAssoFK_ticket2ID
		FOREIGN KEY (ticket2_id) REFERENCES Ticket(id)
			ON DELETE CASCADE,
	PRIMARY KEY (ticket1_id,ticket2_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO TicketAssociation VALUES (1,2);



CREATE TABLE IF NOT EXISTS Comment (
	id 				INT UNSIGNED NOT NULL AUTO_INCREMENT,
	description		VARCHAR(1000) NOT NULL,
	author_id 		INT UNSIGNED NOT NULL,
	ticket_id 		INT UNSIGNED NOT NULL,
	CONSTRAINT CommentFK_employeeID
		FOREIGN KEY (author_id) REFERENCES Employee(id)
			ON DELETE RESTRICT,
	CONSTRAINT CommentFK_ticketID
		FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
			ON DELETE RESTRICT,
	PRIMARY KEY(id)
) ENGINE=InnoDB  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Comment (description,author_id,ticket_id) VALUES 
					("Pull request: require the project's manager validation",5,1),
					("Pull request: accepted and validated by project's manager",5,1),
					("Peer review: failed. Found error during testing",5,2),
					("Peer review: successed. No major issues found",5,2);



CREATE TABLE IF NOT EXISTS HistoryStatut (
	ticket_id		INT UNSIGNED NOT NULL,
	statut_id		INT UNSIGNED NOT NULL,
	creationDate	TIMESTAMP NOT NULL DEFAULT NOW(),
	comment_id		INT UNSIGNED DEFAULT NULL,
	employee_id			INT UNSIGNED NOT NULL,
	CONSTRAINT StatutHistFK_ticketID
		FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
			ON DELETE CASCADE,
	CONSTRAINT StatutHistFK_statutID
		FOREIGN KEY (statut_id) REFERENCES Statut(id)
			ON DELETE CASCADE,
	CONSTRAINT StatutHistFK_commentID
		FOREIGN KEY (comment_id) REFERENCES Comment(id)
			ON DELETE SET NULL,
	CONSTRAINT StatutHistFK_employeeID
		FOREIGN KEY (employee_id) REFERENCES Employee(id)
			ON DELETE RESTRICT,
	PRIMARY KEY (ticket_id,statut_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO HistoryStatut VALUES (1,1,"2022-03-25 14:53:38",NULL,5),(1,3,"2022-03-25 14:53:38",1,5),(1,7,"2022-03-25 14:53:38",NULL,5),(1,5,"2022-03-25 14:53:38",2,5),(1,2,"2022-03-25 14:53:38",NULL,5),
								 (2,1,"2022-03-25 14:53:38",NULL,5),(2,4,"2022-03-25 14:53:38",3,5),(2,6,"2022-03-25 14:53:38",NULL,5),(2,8,"2022-03-25 14:53:38",NULL,5),(2,5,"2022-03-25 14:53:38",4,5),(2,2,"2022-03-25 14:53:38",NULL,5);



CREATE TABLE IF NOT EXISTS Evolution (
	ticket_id		INT UNSIGNED NOT NULL,
	priority		INT UNSIGNED NOT NULL,
	CONSTRAINT EvolutionRANGE_priority
		CHECK (priority BETWEEN 1 AND 10),
	CONSTRAINT EvolutionFK_ticketID
		FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
			ON DELETE CASCADE,
	PRIMARY KEY (ticket_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Evolution VALUES (1,8),(2,5);



CREATE TABLE IF NOT EXISTS Severity (
	id			INT UNSIGNED NOT NULL AUTO_INCREMENT,
	level 		INT UNSIGNED NOT NULL,
	label		VARCHAR(100) NOT NULL,
	CONSTRAINT range_level
		CHECK (level BETWEEN 1 AND 5),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Severity (level,label) VALUES (1,"NONE"),(2,"MINOR"),(3,"MEDIUM"),(4,"MAJOR"),(5,"CRITICAL");



CREATE TABLE IF NOT EXISTS Bug (
	ticket_id		INT UNSIGNED NOT NULL,
	severity_id		INT UNSIGNED NOT NULL,
	CONSTRAINT BugFK_ticketID
		FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
			ON DELETE CASCADE,
	CONSTRAINT BugFK_severityID
		FOREIGN KEY (severity_id) REFERENCES Severity(id)
			ON DELETE RESTRICT,
	PRIMARY KEY (ticket_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO Bug VALUES (2,4);



CREATE TABLE IF NOT EXISTS BugVersionAssociation (
	bug_id			INT UNSIGNED NOT NULL,
	version_id		INT UNSIGNED NOT NULL,
	version_label	VARCHAR(30) NOT NULL,
	CONSTRAINT BugVersionAssoFK_bugID
		FOREIGN KEY (Bug_id) REFERENCES Bug(ticket_id)
			ON DELETE CASCADE,
	CONSTRAINT BugVersionAssoFK_versionID
		FOREIGN KEY (version_id) REFERENCES Version(project_id)
			ON DELETE CASCADE,
	CONSTRAINT BugVersionAssoFK_versionLabel
		FOREIGN KEY (version_label) REFERENCES Version(label)
			ON DELETE CASCADE,
	PRIMARY KEY (bug_id,version_id,version_label)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO BugVersionAssociation VALUES (2,5,"1.2.1");



