package no.uib.fma008.info233.v15.oblig2.models;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;

import no.uib.fma008.info233.v15.oblig2.util.Utilities;

import org.jsoup.nodes.Node;

/**
 * This class is responsible for setting up activity objects. Each object
 * provide useful information, the objects of this class are stored in
 * activityList in Parser class
 * 
 * @author fma008
 * @version 1.0
 *
 */
public class Activity implements ActivityInterface, Serializable {

	private static final long serialVersionUID = -4962193791986602328L;
	private transient Node node;
	private String type;
	private String building;
	private String room;
	private Calendar beginTime;
	private Calendar endTime;
	private String description;
	private String time;
	private String day;

	/**
	 * constructor
	 * 
	 * @param nodeToAdd
	 * @throws ParseException
	 */
	public Activity(Node nodeToAdd) {
		node = nodeToAdd;
		for (Node n : nodeToAdd.childNodes()) {
			if (n.childNodeSize() > 0) {
				if (n.childNode(0).attr("class").equals("activity")) {
					this.type = n.childNode(0).childNode(0) + "";
				}
				if (n.attr("class").equals("item_room")) {
					if (n.childNode(0).childNode(0) != null) {
						this.building = n.childNode(0).childNode(0) + "";
					}
					this.room = n.attr("title");
				}
				if (n.attr("class").equals("time")) {
					this.time = n.childNode(0) + "";
				}
				if (n.attr("class").equals("item_desc")) {
					this.description = n.childNode(0) + "";
				}
				if (n.attr("rowspan").equals("")) {
					this.day = n.parentNode().attr("rowspan");
				}

			}
		}
		try {
			beginTime = Utilities.toCalendarFormat(Utilities.trimString(time,
					0, 5));
			endTime = Utilities.toCalendarFormat(Utilities.trimString(time, 6,
					11));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @return the beginTime
	 */
	public Calendar getBeginTime() {
		return beginTime;
	}

	/**
	 * @return the endTime
	 */
	public Calendar getEndTime() {
		return endTime;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * 
	 * @param day
	 *            the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * toString method for representing activity object
	 * 
	 * @Override
	 * @return activity
	 */
	public String toString() {
		String activity = " Type: " + type + "\n" + " Building: " + building
				+ "\n" + " Room: " + room + "\n" + " Day/Date: " + day + "\n"
				+ " Begin time: "
				+ Utilities.checkHourTime(beginTime.get(Calendar.HOUR_OF_DAY))
				+ ":"
				+ Utilities.checkMinuteTime(beginTime.get(Calendar.MINUTE))
				+ "\n" + " End time: "
				+ Utilities.checkHourTime(endTime.get(Calendar.HOUR_OF_DAY))
				+ ":" + Utilities.checkMinuteTime(endTime.get(Calendar.MINUTE))
				+ "\n" + " Description: " + description + "\n";
		return activity;
	}
}
