package hgu.alinew.model;

public class ChapelerInfo {
	private String teamInfo;
	private String studentId;
	private String attendance;

	public ChapelerInfo(String teamProfessorName, String studentId, String attendance) {
		super();
		this.teamInfo = teamProfessorName;
		this.studentId = studentId;
		this.attendance = attendance;
	}

	public String getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(String teamInfo) {
		this.teamInfo = teamInfo;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
}