package no.uib.fma008.info233.v15.oblig2.io;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import no.uib.fma008.info233.v15.oblig2.models.Activity;
import no.uib.fma008.info233.v15.oblig2.util.Utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

/**
 * This class is responsible for parsing the document with an recursive method,
 * finding the right nodes and add them to nodeList. It also generate the
 * activityList using the Activity class constructor.
 * 
 * @author fma008
 * @version 1.0
 *
 */
public class Parser implements ParserInterface, Serializable {

	private static final long serialVersionUID = -3814961194301800598L;
	private String emne;
	private Document doc;
	private transient List<Node> nodeList;
	private List<Activity> activityList;
	private List<String> dateStringList;

	/**
	 * method for initializing the emne to get, parsing, the document and lists
	 */
	public void docToLists() {
		try {
			String emne = getEmne();
			nodeList = new ArrayList<Node>();
			activityList = new ArrayList<Activity>();
			dateStringList = new ArrayList<String>();
			doc = Jsoup.connect(
					"http://rom.app.uib.no/ukesoversikt/?entry=emne&input="
							+ emne).get();

			nodesToList(doc, doc.parentNode(), nodeList);
			dateStringList.remove(0); // first child is not a day and date
			addActivities();
			sortActivityList();
			setActivityDayAndDate();
			printActivityList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method for sorting the activities in activityList based on Monday to
	 * Friday perspective
	 */
	public void sortActivityList() {
		Collections.sort(activityList, new Comparator<Activity>() {
			public int compare(Activity a1, Activity a2) {

				return a1.getDay().compareTo(a2.getDay());
			}
		});
	}

	/**
	 * method for printing strings in dateStringList
	 */
	public void printDateList() {
		if (nodeList.size() > 0)
			for (String n : dateStringList) {
				System.out.println(n);
			}
		else {
			System.out.println("No datetimes in list");
		}
	}

	/**
	 * method for printing activities in activityList
	 */
	public void printActivityList() {
		if (activityList.size() > 0)
			for (Activity a : activityList) {
				System.out.println(a.toString());
			}
		else {
			System.out.println("No activities in list");
		}
	}

	/**
	 * method for retrieving activities in activityList as String
	 * 
	 * @return activities
	 */
	public String printActivityStrings() {
		String activities = "";
		if (!activityList.isEmpty()) {
			for (Activity a : activityList) {
				activities += "\n" + a.toString() + "\n";
			}
		} else {
			activities = "\n" + " No activities found for this subject!";
		}
		return activities;
	}

	/**
	 * method for adding activities to activityList pushing them through the
	 * Activity constructor
	 */
	public void addActivities() {
		for (Node n : nodeList) {
			if (n != null) {
				activityList.add(new Activity(n));
			}
		}
	}

	/**
	 * method for setting number of day (1-5) in week for activities
	 */
	public void setActivityDayAndDate() {
		for (Activity a : activityList) {
			if (a.getDay() != null) {
				int position = Utilities.toInt(a.getDay());
				a.setDay(getDateStringList().get(position - 1));
			}
		}
	}

	/**
	 * Recursive parser
	 *
	 * @param node
	 *            the root node of the part of the document to be parsed
	 * @param parent
	 *            the parent is needed to add the content in the node without
	 *            children
	 * @param nodeList
	 *            the nodeList to add the nodes to
	 */
	public List<Node> nodesToList(Node node, Node parent, List<Node> nodeList) {

		for (Node n : node.childNodes()) {
			if (n.attr("class").equals("week-header")) {
				String date = n.childNodes().get(0) + "";
				dateStringList.add(date);
			}
			if (n.attr("class").equals("week-data")) {
				nodeList.add(n);
				n.attr("rowspan", ((n.siblingIndex()) / 2 + ""));
			} else {
				nodesToList(n, parent, nodeList);
			}
		}
		return nodeList;
	}

	/**
	 * @return the doc
	 */
	public Document getDoc() {
		return doc;
	}

	/**
	 * @return the nodeList
	 */
	public List<Node> getNodeList() {
		return nodeList;
	}

	/**
	 * @return the activityList
	 */
	public List<Activity> getActivityList() {
		return activityList;
	}

	/**
	 * @return the dateStringList
	 */
	public List<String> getDateStringList() {
		return dateStringList;
	}

	/**
	 * @return the emne
	 */
	public String getEmne() {
		return emne;
	}

	/**
	 * @param emne
	 *            the emne to set
	 */
	public void setEmne(String emne) {
		this.emne = emne;
	}

}
