import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


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
//private static ArrayList<String> fileNameList = new ArrayList<String>();
//private static ArrayList<Integer> usedId= new ArrayList<Integer>();
/**
 * HashMap
 */
	static HashMap<Integer,Student> idMap = new HashMap<>();	
	static HashMap<Integer,Classroom> idClassMap = new HashMap<>();
	/**
	 * Thực hiện show menu , các chức năng của chương trình
	 * Gọi các function tương ứng
	 * @param args
	 */
	public static void main(String[]args) {
		
		idMap.put(1801040203,new Student());
		System.out.println(idMap.size());
//		showStudents();
//		showClassList();;
		try {
			
			// tao lop
			Classroom cls = new Classroom(2,"4C");
			idClassMap.put(2, cls);
			// tao sinh vien
			Student std = createNewStudent();
			Student std1 = createNewStudent();
//			cls.addStudent(std);
//			cls.addStudent(std1);

//			List<Student>list =cls.getStudentList();
//			cls.addStudent(std);
//			System.out.println(createNewStudent());
			
//			modifySudent();
//			System.out.println("Student trong lop trc khi xoa");
//			showStudents();
//			for(int i =0;i<cls.NumberOfStudent();i++) {
//				System.out.println(list.get(i));
//			}
//			System.out.println("Student sau khi xoa");
//			deleteStudent(std.getId());
//			for(int i =0;i<cls.NumberOfStudent();i++) {
//				System.out.println(list.get(i));
//			}
//			
//			showStudents();
			addStudentToClass();
			deleteStudent();
			showStudents();
//			deleteStudentFromClass();
//			showStudents();
//			showClassList();
//			modifySudent();
			deleteStudentFromClass();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

/**
 *  1. Hiển thị danh sách sinh viên
 */
public static void showStudents() {
	// kiểm tra có sinh viên hay không
	if(idMap.isEmpty()) {
		System.out.println("Không có sinh viên");
		return ;
	}
	
	Set<Integer> keySet = idMap.keySet();
	int[] keys = new int[keySet.size()];
	Iterator<Integer> iter = keySet.iterator();
	int index = 0;
	while(iter.hasNext()) {
		keys[index]=iter.next();
		index++;
	}
	for(int i=0;i<keySet.size();i++) {
		System.out.println(idMap.get(keys[i]));
		
	}
	System.out.println("Show student XONGGG");

	
}
/**
 *  2. Hiển thị danh sách lớp học
 */
public static void showClassList() {
 if(idClassMap.size()==0) {
	 System.out.println("Không có lớp nào ");
	 return;
 }
 Set<Integer> keySet = idClassMap.keySet(); 
	Iterator<Integer> iter = keySet.iterator();
	while(iter.hasNext()) {
		System.out.println(idClassMap.get(iter.next()));
	}
	
}

/**
 * 3. Quản lý sinh viên
 * 
 */
public static void studentManagement() {
	
}

/**
 * 3.1.1 Thêm sinh viên 
 * @throws WrongInputValueException 
 * @throws IOException 
 * @throws Exception 
 */
public static Student createNewStudent() throws WrongInputValueException, IOException  {
//	ArrayList<Student> allReturnedStudent= showStudents(classroomList);
	Scanner sc = new Scanner(System.in);
	System.out.println("Insert new Student id: ");
	int id = sc.nextInt();
	if(idMap.containsKey(id)) {
		throw new WrongInputValueException("Wrong value for id:" +id);
	}
	sc.nextLine();
	System.out.println("Insert new student name: ");
	String name = sc.nextLine();
	Student newStudent = new Student(id,name);
	idMap.put(id,newStudent);

	return newStudent;
}

/**
 * 3.1.2 Sửa sinh viên
 * @throws WrongInputValueException 
 */
public static void modifySudent() throws WrongInputValueException {
	showStudents();
	System.out.println("Please input student id : ");
	Scanner sc = new Scanner(System.in);	
	int modifyId = sc.nextInt();
	sc.nextLine();
	if(!idMap.containsKey(modifyId)) {
		
		throw new WrongInputValueException("Wrong modify input id value : "+modifyId);
		
	};
	// lấy được sinh viên cần sửa trong hash map
	Student modifyStudent = idMap.get(modifyId);
	// nhập thông tin mới 
	System.out.println("input new student name : ");
	String newStudentName = sc.nextLine();
	System.out.println("input new student class name: ");
	String newStudentClassname = sc.nextLine();
	// check if class room exist ?
	Set<Integer> keySet = idClassMap.keySet();
	Iterator<Integer> iter = keySet.iterator();
	boolean check=false;
	Classroom cls=null;
	while(iter.hasNext()) {
		cls = idClassMap.get(iter.next());
		if(cls.getClassroomName().equals(newStudentClassname)) {
			check= true;
			break;
		}
	}
	// không có class tên như vậy
	if(check==false) {
		throw new WrongInputValueException("Lớp không tồn tại :" +newStudentClassname);
	}
		
	
	modifyStudent.setName(newStudentName);
	modifyStudent.setClassName(newStudentClassname);
	
	cls.addStudent(modifyStudent);
	Iterator iter1 = cls.getStudentList().iterator();
	while(iter1.hasNext()) {
		System.out.println(iter1.next());
	}
	
	// find that existing file and update
	
}
/**
 * 3.1.3 Xóa sinh viên // fix
 * @throws WrongInputValueException 
 */
public static void deleteStudent() throws WrongInputValueException {
	System.out.println("START DeleteStudent");
	showStudents();
	Scanner sc = new Scanner(System.in);
	System.out.println("input id to delete : ");
	int deleteId = sc.nextInt();
	sc.nextLine();
	if(!idMap.containsKey(deleteId)) {
		throw new WrongInputValueException("Student id does not exist: "+deleteId);
	}
	Student deleteStudent = idMap.get(deleteId);
	idMap.remove(deleteId);
	// xóa trong class 
	// trả về set
	Set<Integer> keySet = idClassMap.keySet();
	// lặp qua set
	Iterator<Integer> iter  = keySet.iterator();
	// cls từng cls
	Classroom cls=null;
	// student trong từng class
	List<Student> students=null;
	// không tồn tại học sinh
	boolean check = false;
	// còn có id của class tiếp thep
	while(iter.hasNext()) {
//		 lấy đc class tiếp theo
		cls = idClassMap.get(iter.next());
		// lấy được studentList tiếp theo
		students = cls.getStudentList();
		// kiểm tra trong mỗi students của class
		for(Student s : students) {
//			
			if(s.equals(deleteStudent)) {
				students.remove(s);
				check = true;
				break;
			}
		
		}
		if(check == true) {
			break;
		}
		System.out.println("Done delete");
	}
	
	for(int i=0;i<students.size();i++) {
		System.out.println(students.size());
		System.out.println(students.get(i));
	}
//	classroomList
//	for(Classroom cls : classroomList) {
//		for(int i=0;i<cls.getStudentList().size();i++) {
//			Student std = cls.getStudentList().get(i);
//			if(std.getId()==id) {
//				cls.getStudentList().remove(std);
//			}
//		}
//	}
	System.out.println("DONE deletingStudents");
}

/**
 * 3.2 Thêm student vào class
 * @throws WrongInputValueException 
 */
public static void addStudentToClass() throws WrongInputValueException {
	// kiểm tra có class nào không 
	if(idClassMap.isEmpty()) {
		System.out.println("No class room found!");
		return;
	}
	// nếu có -> show sinh viên để add vào lớp
	showStudents();
	// nhập id sinh viên
	Scanner sc = new Scanner(System.in);
	System.out.println("Input student id to add:");
	int id = sc.nextInt();
	sc.nextLine();
	// kiểm tra xem id hợp lệ không 
	if(!idMap.containsKey(id)) {
		throw new WrongInputValueException("Không tìm thấy sinh viên : "+ id);
	}
	// lấy ra sinh viên
	Student std =idMap.get(id);
	// chọn class để add 
	System.out.println("input class name  :");
	showClassList();
	String className = sc.nextLine();
	//  kiểm tra class đó tồn tại không 
	Set<Integer> keySet = idClassMap.keySet();
	Iterator<Integer> iter = keySet.iterator();
	boolean check=false;
	Classroom cls=null;
	while(iter.hasNext()) {
		cls = idClassMap.get(iter.next());
		if(cls.getClassroomName().equals(className)) {
			check= true;
			break;
		}
	}
	// không có class tên như vậy
	if(check==false) {
		throw new WrongInputValueException("Lớp không tồn tại : " + className);
	}
	// kiểm tra student trong class chưa 
	List<Student> students = cls.getStudentList();
	// student da trong class
	for(int i =0;i<students.size();i++) {
		if(students.get(i).getId()==std.getId()) {
			System.out.println("Student already in class ");
			return;
		}
	}
	// student chua trong class

	std.setClassName(cls.getClassroomName());
	cls.getStudentList().add(std);
	
	for(int i = 0;i<students.size();i++) {
		System.out.println(students.get(i));
	}
	
	
	System.out.println("ADD CLASS XONGGGGGGG");
	
	
	
//	for(Classroom cls : classroomList) {
//		if(cls.getClassroomName().equals(className)) {
//			if(cls.getStudentList().contains(std)) {
//				System.out.println("Student already in class");
//			return;
//			}
//			else {
//				std.setClassName(className);
//				cls.getStudentList().add(std);
//				List<Student> list =cls.getStudentList();
//				for(int i=0;i<list.size();i++) {
//					System.out.println(list.get(i));
//				}
//				break;
//			}
//			
//		}
//		else {
//			throw new WrongInputValueException("classroom name does not exist");
//		}
//		
//	}
//	
}
/**
 * 3.3 Xóa student khỏi class fix
 * @throws WrongInputValueException 
 */
public static void deleteStudentFromClass() throws WrongInputValueException {
	System.out.println("Start deleting student from class");
	if(idClassMap.isEmpty()) {
		System.out.println("No class room found!");
		return;
	}
	showClassList();
	Scanner sc = new Scanner(System.in);
	System.out.println("Choose class name :");
	String className = sc.nextLine();
	List<Student> students=null;
	boolean check = false;
	Classroom cls=null;
	
	Set<Integer> keySet = idClassMap.keySet();
	Iterator<Integer> iter = keySet.iterator();
	while(iter.hasNext()) {
		cls = idClassMap.get(iter.next());
		students=cls.getStudentList();
		if(cls.getClassroomName().equals(className)) {
			check = true;
			break;
		}
		
	}
	
//	for(Classroom cls : classroomList) {
//		if(cls.getClassroomName().equals(className)) {
//			c=cls;
//			stds = cls.getStudentList();
//			check = true;
//			break;
//		}
//		
//	}
	if(check == true) {
	System.out.println("Students in class "+ className + " :");
	for(Student std : students) {
		System.out.println(std);
	}
	}
	else {
		throw new WrongInputValueException("class name does not exist: "+className);
	}
	System.out.println("Input student id:");
	int id = sc.nextInt();
	sc.nextLine();
	// check dieu kien nua 
	if(!idMap.containsKey(id)) {
		throw new WrongInputValueException("Không tìm thấy sinh viên : "+ id);
	}
	students.remove(idMap.get(id));
	System.out.println("Students in class "+ className + " after deleting:");
	for(Student student : students) {
		System.out.println(student);
	}
	System.out.println("DELETE FROM CLASS XONG ");
	
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
