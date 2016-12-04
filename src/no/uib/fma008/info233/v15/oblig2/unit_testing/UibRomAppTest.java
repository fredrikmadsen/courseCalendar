package no.uib.fma008.info233.v15.oblig2.unit_testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import no.uib.fma008.info233.v15.oblig2.io.Parser;
import no.uib.fma008.info233.v15.oblig2.models.Activity;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is responsible for testing the parser and activity class, two of
 * the important classes used in the UibRomApp application. Contains various
 * tests that evaluate the state of the object, if something is true and/or
 * false.
 * 
 * @author fma008
 * @version 1.0
 *
 */
public class UibRomAppTest {

	private Parser parser;
	private Activity activity;

	@Before
	public void setUp() throws Exception {
		this.parser = new Parser();

	}

	/**
	 * method test parsing with valid subject string as setEmne parameter
	 */
	@Test
	public void testParsingSucess() {
		parser.setEmne("info233");
		assertFalse(this.parser.getEmne().isEmpty());
		parser.docToLists();
		assertTrue(this.parser.getDoc().childNodeSize() > 0);
	}

	/**
	 * method test if list is filled
	 */
	@Test
	public void testParsingSucessFillLists() {
		parser.setEmne("info132");
		parser.docToLists();
		assertFalse(this.parser.getNodeList().isEmpty());
		assertTrue(this.parser.getDateStringList().size() > 0);
		assertTrue(!this.parser.getActivityList().isEmpty());
	}

	/**
	 * method test with non-subject string as setEmne parameter
	 */
	@Test
	public void testParsingListIsEmpty() {
		parser.setEmne("cookie");
		parser.docToLists();
		assertTrue(this.parser.getNodeList().isEmpty());
		assertTrue(this.parser.getDateStringList().size() > 0); // http://rom.app.uib.no/ukesoversikt/?entry=emne&input=cookie,
																// date is still
																// available
		assertTrue(this.parser.getActivityList().isEmpty());
	}

	/**
	 * method test that activities are available in list with valid subject
	 * string
	 */
	@Test
	public void testActivitySucess() {
		parser.setEmne("info233");
		parser.docToLists();
		activity = parser.getActivityList().get(0);
		assertTrue(activity != null);
		assertTrue(activity.getDay() != null);
	}

	/**
	 * method test no available activities due to non-subject string parameter
	 * for setEmne
	 */
	@Test
	public void testActivityEmpty() {
		parser.setEmne("betterCallSaul");
		parser.docToLists();
		assertTrue(parser.getActivityList().size() == 0);
	}

	/**
	 * method test that toString is correct for the activity object
	 */
	@Test
	public void testActivityToString() {
		parser.setEmne("info102");
		parser.docToLists();
		activity = parser.getActivityList().get(0);
		assertTrue(activity.toString().startsWith(
				" Type: " + activity.getType()));
		assertTrue(activity.getBeginTime() instanceof Calendar);

	}
}
