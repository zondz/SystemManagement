import java.io.Serializable;
import java.util.ArrayList;

public class Classroom implements Serializable{
	// 0001
	private String id;
	// "4C-18"
	private String classroomName;
	// 1801040203
	private String monitorId;
	private  ArrayList<Student> studentList;
	
	public Classroom() {
		
	}
	public Classroom(String id, String classroomName ) throws WrongInputValueException {
		if(validateId(id)) {
			this.id=id;
		}
		else {
			throw new WrongInputValueException("id khởi tạo lớp sai: "+id);
		}
		if(validateClassroomName(classroomName)) {
			this.classroomName=classroomName;
		}
		else {
			System.out.println("WORKING");
			throw new WrongInputValueException("tên lớp khởi tạo sai : " +classroomName);
		}
	
		studentList = new  ArrayList<Student>();
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) throws WrongInputValueException {
		if(validateId(id)) {
			this.id = id;
		}
		else {
			throw new WrongInputValueException("đặt id lớp sai : "+id);
		}

	}
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) throws WrongInputValueException {
		if(validateClassroomName(classroomName)) {
			this.classroomName = classroomName;
		}
		else {
			throw new WrongInputValueException("đặt tên lớp sai : "+classroomName);
		}
		
	}
	public String getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(String monitorId) {
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
		return "Classroom [id=" + id + ", classroomName=" + classroomName + ", monitorId=" + monitorId
				+ ", studentList=" + studentList + "]";
	}
	
	public void addStudent(Student std) {
		this.studentList.add(std);
		
	}
	
	private boolean validateId(String id) {
		if(id==null||id.length()!=4) {
			return false;
		}
		return true;
	}
	private boolean validateClassroomName(String className) {
		if(className==null||className.length()<4) {
			return false;
		}
		return true;
	}
	

	
	
	
}
