import java.util.ArrayList;
import java.util.Arrays;


/**
 * Bài tập
 * Chương trình in ra console
 * Chức năng chính :
 * 1. Hiển thị danh sách sinh viên
 * 2. Hiển thị danh sách lớp học
 * 3. Quản lý sinh viên
 * 3.1.1 Thêm sinh viên
 * 3.1.2 Sửa sinh viên
 * 3.1.3 Xóa sinh viên
 * 3.2 Thêm sinh viên vào trong lớp học
 * 3.3 Xóa sinh viên khỏi lớp học
 * 4. Quản lý lớp học
 * 4.1.1 Thêm lớp học 
 * 4.1.2 Sửa lớp học
 * 4.1.3 Xóa lớp học
 * 4.2 Chọn ra lớp trưởng của lớp học
 * 
 * Yêu cầu : ghi thông tin sinh viên , lớp học ra file
 * + file sinh viên gồm : id sinh viên, tên sinh viên , mã lớp 
 * + file lớp học gồm :id lớp học , tên lớp học , mã sinh viên của lớp trưởng
 * 
 * notes : id của file phải là duy nhất
 * @author Thanh
 *
 */
public class Main {
	
private static ArrayList<Classroom> classroomList = new ArrayList<Classroom>(); 

	/**
	 * Thực hiện show menu , các chức năng của chương trình
	 * Gọi các function tương ứng
	 * @param args
	 */
	public static void main(String[]args) {
	Classroom cl1 = new Classroom(1,"4c-18");
	Student st1 = new Student(1,"Thanh","4C-18");
	Student st2 = new Student(2,"Tuan Anh","4C-18");
	cl1.addStudent(st1);
	
	Classroom cl2 = new Classroom(2,"4c-18");
	cl2.addStudent(st2);
	classroomList.add(cl1);
	classroomList.add(cl2);
	System.out.println("Working");
	showStudents(classroomList);
	showClassList(classroomList);	
	}

/**
 *  1. Hiển thị danh sách sinh viên
 */
public static void showStudents(ArrayList<Classroom> classroomList) {
	// kiểm tra có sinh viên không
	if(classroomList.size()==0) {
		System.out.print("Không có student");
		return;
	}
	// cần sử dụng file để truy cập thông tin lớp, sinh viên, không dùng biến ?
	
	ArrayList<Student> getStudentReturnList;
	for(int i=0;i<classroomList.size();i++) {
		if(classroomList.get(i).NumberOfStudent()==0) {
			continue;
		}
		getStudentReturnList = classroomList.get(i).getStudentList();
		
		for(int j=0;j<getStudentReturnList.size();j++) {
			System.out.println(getStudentReturnList.get(j).toString());
		}
	}
	
	
}
/**
 *  2. Hiển thị danh sách lớp học
 */
public static void showClassList(ArrayList<Classroom> classroomList) {
 if(classroomList.size()==0) {
	 System.out.println("Không có lớp nào ");
	 return;
 }
	for(Classroom cl : classroomList) {
		System.out.println("class name: "+ cl.getClassroomName());
	}
	
}

//public static boolean checkIfExistStudent(ArrayList<Classroom> classroomList) {
//	if(classroomList.size()==0) return false;
//	boolean check=false;
//	for(Classroom cl : classroomList) {
//		if(cl.getStudentList().size()!=0) {
//			check=true;
//		}
//	}
//	return check;
//}


/**
 * 3. Quản lý sinh viên
 * 
 */
public static void studentManagement() {
	
}

/**
 * 3.1.1 Thêm sinh viên
 */
public static void createNewStudent() {}

/**
 * 3.1.2 Sửa sinh viên
 */
public static void modifySudent() {}
/**
 * 3.1.3 Xóa sinh viên
 */
public static void deleteStudent() {}

/**
 * 3.2 Thêm student vào class
 */
public static void addStudentToClass() {
	
}
/**
 * 3.3 Xóa student khỏi class
 */
public static void deleteStudentFromClass() {
	
}
/**
 * 4. Quản lý lớp học
 * 
 */
public void manageClasses() {
	
}
/**
 * 4.1.1 Thêm lớp học mới
 */
 public static void createNewClass() {
	 
 }
 /**
  * 4.1.2 Sửa lớp học
  */
 public static void modifyClass() {
	 
 }
 /**
  * 4.1.3 Xóa lớp học
  */
 public static void removeClass() {}
 /**
  * Chọn ra lớp trưởng lớp học
  */
 public static void selectMonitor() {}
 
}
