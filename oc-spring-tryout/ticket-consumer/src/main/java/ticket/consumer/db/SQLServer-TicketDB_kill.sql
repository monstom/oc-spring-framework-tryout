


USE ticket_webapp;


ALTER TABLE dbo.BugVersionAssociation
	DROP CONSTRAINT BugVersionAssoFK_bugID;
ALTER TABLE dbo.BugVersionAssociation 
	DROP CONSTRAINT BugVersionAssoFK_version;
DROP TABLE IF EXISTS dbo.BugVersionAssociation;

ALTER TABLE dbo.Bug
	DROP CONSTRAINT BugFK_ticketID;
ALTER TABLE dbo.Bug
	DROP CONSTRAINT BugFK_severityID;
DROP TABLE IF EXISTS dbo.Bug;

DROP TABLE IF EXISTS dbo.Severity;

ALTER TABLE dbo.Evolution
	DROP CONSTRAINT EvolutionFK_ticketID;
DROP TABLE IF EXISTS dbo.Evolution;

ALTER TABLE dbo.HistoryStatut 
	DROP CONSTRAINT StatutHistFK_ticketID;
ALTER TABLE dbo.HistoryStatut
	DROP CONSTRAINT StatutHistFK_statutID;
ALTER TABLE dbo.HistoryStatut 
	DROP CONSTRAINT StatutHistFK_commentID;
ALTER TABLE dbo.HistoryStatut 
	DROP CONSTRAINT StatutHistFK_userID;
DROP TABLE IF EXISTS dbo.HistoryStatut;

ALTER TABLE dbo.Comment
	DROP CONSTRAINT CommentFK_ticketID;
ALTER TABLE dbo.Comment
	DROP CONSTRAINT CommentFK_userID;
DROP TABLE IF EXISTS dbo.Comment;

ALTER TABLE dbo.TicketAssociation
	DROP CONSTRAINT TicketAssoFK_ticket1ID;
ALTER TABLE dbo.TicketAssociation 
	DROP CONSTRAINT TicketAssoFK_ticket2ID;
DROP TABLE IF EXISTS dbo.TicketAssociation;

ALTER TABLE dbo.Ticket 
	DROP CONSTRAINT TicketFK_statutID;
ALTER TABLE dbo.Ticket 
	DROP CONSTRAINT TicketFK_userID;
ALTER TABLE dbo.Ticket 
	DROP CONSTRAINT TicketFK_projectID;
DROP TABLE IF EXISTS dbo.Ticket;

DROP TABLE IF EXISTS Statut;

ALTER TABLE dbo.Version
	DROP CONSTRAINT VersionFK_projectID;
DROP TABLE IF EXISTS dbo.Version;

ALTER TABLE dbo.Project 
	DROP CONSTRAINT ProjectFK_userID;
DROP TABLE IF EXISTS dbo.Project;

DROP TABLE IF EXISTS dbo.Employee;
