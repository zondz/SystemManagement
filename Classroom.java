import java.util.ArrayList;

public class Classroom {
	private int id;
	private String classroomName;
	private int monitorId;
	private  ArrayList<Student> studentList;
	
	public Classroom() {
		
	}
	public Classroom(int id, String classroomName ) {
		this.id=id;
		this.classroomName=classroomName;
		studentList = new  ArrayList<Student>();
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	public int getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}
	
	public ArrayList<Student> getStudentList() {
		return this.studentList;
	}
	
	public int NumberOfStudent() {
		return this.studentList.size();
	}
	@Override
	public String toString() {
		return "Classroom={Id: "+this.id+" classroomName: "+this.classroomName +"monitorId: "+this.monitorId  ;
	}
	
	public void addStudent(Student std) {
		this.studentList.add(std);
		
	}
	
	
	
}
