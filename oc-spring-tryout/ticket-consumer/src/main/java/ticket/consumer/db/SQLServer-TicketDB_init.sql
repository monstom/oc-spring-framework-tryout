



USE ticket_webapp;



CREATE TABLE dbo.Employee (
	id 			INT NOT NULL IDENTITY(1,1),
	firstname 	NVARCHAR(100) NOT NULL,
	lastname 	NVARCHAR(100) NOT NULL,
	CONSTRAINT EmployeeUNIQ_fullname 
		UNIQUE (firstname,lastname),
	PRIMARY KEY (id)
);



INSERT INTO dbo.Employee (firstname,lastname) VALUES ('Steve','Jobs'),('Elon','Musk'),('Michael','Jordan'),('Nike','Company&Co'),('Thomas','Boullé');



CREATE TABLE dbo.Project (
	id 				INT NOT NULL IDENTITY(1,1),
	title 			NVARCHAR(100) NOT NULL,
	creationDate 	DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	closed 			BIT NOT NULL DEFAULT 0,
	manager 		INT NOT NULL,
	UNIQUE (title),
	CONSTRAINT ProjectFK_userID
		FOREIGN KEY (manager) REFERENCES Employee(id)
			ON DELETE NO ACTION,
	PRIMARY KEY (id)
);



INSERT INTO dbo.Project (title,creationDate,closed,manager) VALUES 
						('Developpement du permier iPhone','2007-01-01 00:00:00',1,1),
						('Developpement de une puce intra_cérébrale','2016-09-10 21:00:00',0,2),
						('Developpement modele Air Jordan 1','1984-10-17 21:00:00',1,3),
						('R&D branding autour du modele Air Jordan','2001-02-15 18:00:00',1,4),
						('Developpement de une webapp de suivi de projet','2022-03-25 14:53:37',0,5);



CREATE TABLE dbo.Version (
	project_id		INT NOT NULL,
	label			NVARCHAR(30) NOT NULL,
	CONSTRAINT VersionFK_projectID
		FOREIGN KEY (project_id) REFERENCES Project(id)
			ON DELETE CASCADE,
	INDEX versionLabel_idx (label ASC),
	PRIMARY KEY (project_id,label)
);



INSERT INTO dbo.Version VALUES (1,'1.0.18'),(1,'2.1.46'),(1,'4.1.1-RELEASE'),(1,'3.0.1-SNAPSHOT'),(1,'4.4.1-RELEASE'),(1,'3.5.1-FIXED'),
							   (2,'1.6'),(2,'2.4.1-SNAPSHOT'),(2,'4.15.2-TEST'),(2,'6.13.4-TEST'),(2,'9.9.05-BETA'),
							   (3,'1.4.6'),(3,'1.12.6'),(3,'1.7.5-FIXED'),(3,'1.8.19-RELEASE'),
							   (4,'1.1.7'),(4,'1.2.3'),(4,'1.3.4'),(4,'1.6.1'),
							   (5,'1.0.1'),(5,'1.0.5-LICENSE'),(5,'1.2.1'),(5,'1.2.2'),(5,'1.2.4');



CREATE TABLE dbo.Statut (
	id			INT NOT NULL IDENTITY(1,1),
	label		NVARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);



INSERT INTO dbo.Statut (label) VALUES ('OPEN'),('CLOSED'),('PULL_REQUEST'),('PEER_REVIEW'),('RESOLVED'),('COMMENTING'),('REQUIRE_MANAGER'),('RESEARCH_ISSUE');



CREATE TABLE dbo.Ticket (
	id				INT NOT NULL IDENTITY(1,1),
	title			NVARCHAR(300) NOT NULL,
	creationDate	DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	description		NVARCHAR(1000) DEFAULT NULL,
	statut_id		INT NOT NULL,
	author_id		INT NOT NULL,
	project_id		INT NOT NULL,
	UNIQUE (title),
	CONSTRAINT TicketFK_statutID
		FOREIGN KEY (statut_id) REFERENCES Statut(id)
			ON DELETE NO ACTION,
	CONSTRAINT TicketFK_userID
		FOREIGN KEY (author_id) REFERENCES Employee(id)
			ON DELETE NO ACTION,
	CONSTRAINT TicketFK_projectID
		FOREIGN KEY (project_id) REFERENCES Project(id)
			ON DELETE NO ACTION,
	PRIMARY KEY (id)
);



INSERT INTO dbo.Ticket (title,creationDate,description,statut_id,author_id,project_id) VALUES
					   ('Pull request on ticket webapp project','2022-03-25 14:53:37','Pull request requie the project manager to validate the development of the functionnalities by making peer review for the testing of them',3,5,5),
					   ('Peer code review and testing for pull request','2022-03-25 14:53:37','Peer coding review in order to validate the code written by the dev asking for a pull request',3,5,5);


				   
CREATE TABLE dbo.TicketAssociation (
	ticket1_id		INT NOT NULL,
	ticket2_id		INT NOT NULL,
	CONSTRAINT TicketAssoFK_ticket1ID
		FOREIGN KEY (ticket1_id) REFERENCES Ticket(id)
			ON DELETE NO ACTION,
	CONSTRAINT TicketAssoFK_ticket2ID
		FOREIGN KEY (ticket2_id) REFERENCES Ticket(id)
			ON DELETE NO ACTION,
	PRIMARY KEY (ticket1_id,ticket2_id)
);



INSERT INTO dbo.TicketAssociation VALUES (1,2);



CREATE TABLE dbo.Comment (
	id 				INT NOT NULL IDENTITY(1,1),
	description		NVARCHAR(1000) NOT NULL,
	author_id 		INT NOT NULL,
	ticket_id 		INT NOT NULL,
	CONSTRAINT CommentFK_userID
		FOREIGN KEY (author_id) REFERENCES Employee(id)
			ON DELETE NO ACTION,
	CONSTRAINT CommentFK_ticketID
		FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
			ON DELETE NO ACTION,
	PRIMARY KEY (id)
);



INSERT INTO dbo.Comment (description,author_id,ticket_id) VALUES 
						('Pull request: require the project manager validation',5,1),
						('Pull request: accepted and validated by project manager',5,1),
						('Peer review: failed. Found error during testing',5,2),
						('Peer review: successed. No major issues found',5,2);



CREATE TABLE dbo.HistoryStatut (
	ticket_id		INT NOT NULL,
	statut_id		INT NOT NULL,
	creationDate	DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	comment_id		INT DEFAULT NULL,
	author_id		INT NOT NULL,
	CONSTRAINT StatutHistFK_ticketID
		FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
			ON DELETE CASCADE,
	CONSTRAINT StatutHistFK_statutID
		FOREIGN KEY (statut_id) REFERENCES Statut(id)
			ON DELETE CASCADE,
	CONSTRAINT StatutHistFK_commentID
		FOREIGN KEY (comment_id) REFERENCES Comment(id)
			ON DELETE SET NULL,
	CONSTRAINT StatutHistFK_userID
		FOREIGN KEY (author_id) REFERENCES Employee(id)
			ON DELETE NO ACTION,
	PRIMARY KEY (ticket_id,statut_id)
);



INSERT INTO dbo.HistoryStatut VALUES (1,1,'2022-03-25 14:53:38',NULL,5),(1,3,'2022-03-25 14:53:38',1,5),(1,7,'2022-03-25 14:53:38',NULL,5),(1,5,'2022-03-25 14:53:38',2,5),(1,2,'2022-03-25 14:53:38',NULL,5),
								 	 (2,1,'2022-03-25 14:53:38',NULL,5),(2,4,'2022-03-25 14:53:38',3,5),(2,6,'2022-03-25 14:53:38',NULL,5),(2,8,'2022-03-25 14:53:38',NULL,5),(2,5,'2022-03-25 14:53:38',4,5),(2,2,'2022-03-25 14:53:38',NULL,5);



CREATE TABLE dbo.Evolution (
	ticket_id		INT NOT NULL,
	priority		INT NOT NULL,
	CONSTRAINT EvolutionRANGE_priority
		CHECK (priority BETWEEN 1 AND 10),
	CONSTRAINT EvolutionFK_ticketID
		FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
			ON DELETE CASCADE,
	PRIMARY KEY (ticket_id)
);



INSERT INTO dbo.Evolution VALUES (1,8),(2,5);



CREATE TABLE dbo.Severity (
	id			INT NOT NULL IDENTITY(1,1),
	level 		INT NOT NULL,
	label		NVARCHAR(100) NOT NULL,
	CONSTRAINT SeverityCHK_level
		CHECK (level BETWEEN 1 AND 5),
	PRIMARY KEY (id)
);



INSERT INTO dbo.Severity (level,label) VALUES (1,'NONE'),(2,'MINOR'),(3,'MEDIUM'),(4,'MAJOR'),(5,'CRITICAL');



CREATE TABLE dbo.Bug (
	ticket_id		INT NOT NULL,
	severity_id		INT NOT NULL,
	CONSTRAINT BugFK_ticketID
		FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
			ON DELETE CASCADE,
	CONSTRAINT BugFK_severityID
		FOREIGN KEY (severity_id) REFERENCES Severity(id)
			ON DELETE NO ACTION,
	PRIMARY KEY (ticket_id)
);



INSERT INTO dbo.Bug VALUES (2,4);



CREATE TABLE dbo.BugVersionAssociation (
	bug_id			INT NOT NULL,
	version_id		INT NOT NULL,
	version_label	NVARCHAR(30) NOT NULL,
	CONSTRAINT BugVersionAssoFK_bugID
		FOREIGN KEY (Bug_id) REFERENCES Bug(ticket_id)
			ON DELETE CASCADE,
	CONSTRAINT BugVersionAssoFK_version
		FOREIGN KEY (version_id,version_label) REFERENCES Version(project_id,label)
			ON DELETE CASCADE,
	PRIMARY KEY (bug_id,version_id,version_label)
);



INSERT INTO dbo.BugVersionAssociation VALUES (2,5,'1.2.1');


