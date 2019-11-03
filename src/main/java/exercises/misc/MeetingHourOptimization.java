package exercises.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingHourOptimization {

  /**
   * Meeting hour optimization.
   *
   * <p>1) Given a list of meeting objects (e.g. {name: 'MeetingName', hours: numHours}) and an
   * integer haveHours denoting how many hours of one's schedule alloted to meetings, write a
   * function that optimizes the total number of meetings in one's day
   *
   * <p>2) Using the same input, write a function that optimizes the total number of hours one
   * spends in meetings
   *
   * @param args
   */
  public static void main(String[] args) {
    List<Meeting> meetings = new ArrayList<>();
    meetings.add(new Meeting("Morning meeting", 5));
    meetings.add(new Meeting("Useless meeting", 3));
    meetings.add(new Meeting("Boring meeting", 2));

    System.out.println(optimizeForTotalNoOfMeetings(meetings, 8));
    System.out.println(optimizeForTotalNoOfMeetings(meetings, 3));
    System.out.println(optimizeForTotalNoOfMeetings(meetings, 5));
    System.out.println(optimizeForTotalNoOfMeetings(meetings, 0)); // fuck meetings!

    System.out.println("=========");

    List<Meeting> moreMeetings = new ArrayList<>();
    moreMeetings.add(new Meeting("standup", 1));
    moreMeetings.add(new Meeting("conf meeting", 3));
    moreMeetings.add(new Meeting("HR meeting", 1));
    moreMeetings.add(new Meeting("mobbing", 7));

    System.out.println(optimizeForTotalNoOfHoursSpentInMeetings(moreMeetings, 6));
    System.out.println(optimizeForTotalNoOfHoursSpentInMeetings(moreMeetings, 7));
    System.out.println(optimizeForTotalNoOfHoursSpentInMeetings(moreMeetings, 8));
  }

  // optimizing for the total number of meetings in one's day
  private static List<Meeting> optimizeForTotalNoOfMeetings(
      List<Meeting> meetings, int noOfHoursAvailable) {
    // sort meetings by duration ascending
    List<Meeting> meetingsCopy = new ArrayList<>(meetings);

    Collections.sort(
        meetingsCopy,
        (m1, m2) -> {
          if (m1.hours == m2.hours) {
            return 0;
          } else if (m1.hours > m2.hours) {
            return 1;
          } else {
            return -1;
          }
        });

    List<Meeting> meetingsOptimized = new ArrayList<>();
    for (int i = 0; i < meetingsCopy.size(); i++) {
      if (meetingsCopy.get(i).hours <= noOfHoursAvailable) {
        meetingsOptimized.add(meetingsCopy.get(i));
        noOfHoursAvailable -= meetingsCopy.get(i).hours;
      }
    }

    return meetingsOptimized;
  }

  // optimize for total number of hours spent attending meetings
  private static List<Meeting> optimizeForTotalNoOfHoursSpentInMeetings(
      List<Meeting> meetings, int hoursAvailable) {
    // create matrix containing max number of hours that can be spent in meetings for all values
    // of available hours up to given value
    int[][] hoursSpentInMeetings = new int[hoursAvailable + 1][meetings.size()];

    // prefill first column of matrix
    for (int i = 1; i <= hoursAvailable; i++) {
      if (meetings.get(0).hours <= i) {
        hoursSpentInMeetings[i][0] = meetings.get(0).hours;
      }
    }

    // find if there is a meeting lasting one hour
    int indexOfMeetingLastingOneHour = -1;
    for (int i = 0; i < meetings.size(); i++) {
      if (meetings.get(i).hours == 1) {
        indexOfMeetingLastingOneHour = i;
        break;
      }
    }

    if (indexOfMeetingLastingOneHour != -1) {
      // prefill first row of matrix (if applicable)
      for (int i = indexOfMeetingLastingOneHour; i < meetings.size(); i++) {
        hoursSpentInMeetings[1][i] = 1;
      }
    }

    // now fill the rest of the matrix
    for (int i = 2; i <= hoursAvailable; i++) {
      for (int j = 1; j < meetings.size(); j++) {

        Meeting currMeeting = meetings.get(j);

        if (currMeeting.hours > i) {
          // this meeting lasts more than the hours available, so cannot be attended
          hoursSpentInMeetings[i][j] = hoursSpentInMeetings[i][j - 1];
          continue;
        }

        hoursSpentInMeetings[i][j] =
            Math.max(
                // total hours spent in meetings if I attend the current meeting
                meetings.get(j).hours + hoursSpentInMeetings[i - meetings.get(j).hours][j - 1],
                // total hours spent in meetings if I don't attend the current meeting
                hoursSpentInMeetings[i][j - 1]);
      }
    }

    // now we need to derive the meetings to attend from the matrix
    // start from bottom right corner of matrix
    List<Meeting> meetingsToAttend = new ArrayList<>();

    int row = hoursAvailable;
    int col = meetings.size() - 1;

    int hoursCounter = hoursSpentInMeetings[row][col];
    while (hoursCounter > 0) {
      if (meetings.get(col).hours > hoursSpentInMeetings[row][col]) {
        // we did not attend the meeting, so look to the left of it
        col--;
      } else {
        // we did attend this meeting, so add it to final list
        meetingsToAttend.add(meetings.get(col));
        hoursCounter -= meetings.get(col).hours;
        row -= meetings.get(col).hours;
        col--;
      }
    }

    return meetingsToAttend;
  }

  private static class Meeting {
    public final String name;
    public final int hours;

    private Meeting(final String name, final int hours) {
      this.name = name;
      this.hours = hours;
    }

    @Override
    public String toString() {
      return "Meeting{" + "name='" + name + '\'' + ", hours=" + hours + '}';
    }
  }
}
