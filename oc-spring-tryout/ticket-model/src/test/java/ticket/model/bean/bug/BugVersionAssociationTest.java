package ticket.model.bean.bug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("BugVersionAssociationClass_UniteTesting")
public class BugVersionAssociationTest {
	
	private final int version_label_length = 30;
	
	private BugVersionAssociation first_association;
	
	
	@BeforeEach
	public void init_BugVersionAssociation() {
		System.out.println("BugVersionAssociationTest - Appel avant chaque test");
		first_association = new BugVersionAssociation();
	}
	
	@AfterEach
	public void end_BugVersionAssociation() {
		first_association = null;
		System.out.println("BugVersionAssociationTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_BugVersionAssociationTest() {
		System.out.println("BugVersionAssociationTest - Début du test de classe BugVersionAssociation");
	}
	
	@AfterAll 
	public static void end_BugVersionAssociationTest() {
		System.out.println("BugVersionAssociationTest - Fin du test de classe BugVersionAssociation");
	}
	
	
	@ParameterizedTest(name = "Ces clés primaires de lassociation : bugID et versionID ({0},{1}) ne doivent pas être négatives ou nulles !")
	@CsvSource({ "10, 5", "0, 6", "-3, 10", "70, -9", "15, 0", "-1, 0", "0, -9", "-7, -14", "0, 0" })
	@Tag("BugVersionAssociation-foreignKeys_BugVersion_invalidValue")
	public void invalidValueOf_ForeignKeys_BugID_VersionID(int arg1, int arg2) {
		// Arrange
		
		// Act
		first_association.setBugVersionAssociation_bugID(arg1);
		first_association.setBugVersionAssociation_versionID(arg2);
		
		// Assert
		assertTrue(first_association.getBugVersionAssociation_bugID() > 0
				&& first_association.getBugVersionAssociation_versionID() > 0 );
	}
	
	@ParameterizedTest(name = "Cette clé étrangère de lassociation : libéllé ({0}) ne doit pas dépasser 30 caractères !")
	@ValueSource(strings = { "Développement Java", "1.0.1-SNAPSHOT", "3.1.5-RELEASE",
							 "bzeuivbziueviubeziuvbueivbezivbiuezbvui" 
						   })
	@Tag("BugVersionAssociation-foreignKey_versionlabel_invalidLength")
	public void invalidLengthOf_VersionLabel(String arg1) {
		// Arrange
		
		// Act
		first_association.setBugVersionAssociation_versionLabel(arg1);;
		
		// Assert
		assertTrue( first_association.getBugVersionAssociation_versionLabel().length() < this.version_label_length );
	}
	
	
	@ParameterizedTest(name = "Cette clé étrangère de lassociation : libéllé ({0}) ne doit pas être vide ou nulle !")
	@ValueSource(strings = { "1.0.1-SNAPSHOT", "3.1.5-RELEASE", " " })
	@Tag("BugVersionAssociation-foreignKey_versionlabel_emptyness")
	public void isEmpty_ForeignKey_VersionLabel(String arg1) {
		// Arrange
		
		// Act
		first_association.setBugVersionAssociation_versionLabel(arg1);
		
		// Assert
		assertFalse( first_association.getBugVersionAssociation_versionLabel().isEmpty() 
				  || first_association.getBugVersionAssociation_versionLabel().isBlank() );
	}

}
