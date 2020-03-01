package hgu.alinew.model;

public class ChapelerInfo {
	private String teamInfo;
	private String studentId;
	private String studentName;

	public ChapelerInfo(String teamProfessorName, String studentId, String studentName) {
		super();
		this.teamInfo = teamProfessorName;
		this.studentId = studentId;
		this.studentName = studentName;
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

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
