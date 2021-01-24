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
			classroomList.add(cls);
			// tao sinh vien
			Student std = createNewStudent();
			Student std1 = createNewStudent();
//			cls.addStudent(std);
//			cls.addStudent(std1);

			List<Student>list =cls.getStudentList();
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
	
	
	
	
//	ArrayList<Student> allReturnedStudent = null;
//	ArrayList<Student> getStudentReturnListFromEachClass;
//	for(int i=0;i<classroomList.size();i++) {
//		if(classroomList.get(i).NumberOfStudent()==0) {
//			continue;
//		}
//		allReturnedStudent = new ArrayList<Student>();
//		getStudentReturnListFromEachClass = classroomList.get(i).getStudentList();
//		
//		for(int j=0;j<getStudentReturnListFromEachClass.size();j++) {
//			allReturnedStudent.add(getStudentReturnListFromEachClass.get(j));
////			System.out.println(getStudentReturnListFromEachClass.get(j).toString());
//		}
//	}
//	return allReturnedStudent;
//	
	
}
/**
 *  2. Hiển thị danh sách lớp học
 */
public static void showClassList() {
 if(classroomList.size()==0) {
	 System.out.println("Không có lớp nào ");
	 return;
 }
	for(Classroom cl : classroomList) {
		System.out.println("class name: "+ cl.getClassroomName());
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
	//String.valueOf(newStudent.getId())+newStudent.getName()+String.valueOf(getIdOfClass(newStudent.getId(), classroomList));
	//getIdOfClass(newStudent.getId())!=-1
//	if(getIdOfClass(newStudent.getId())!=-1) {
//	String[] saveInfor = {String.valueOf(newStudent.getId()),newStudent.getName(),String.valueOf(getIdOfClass(newStudent.getId()))};
//	String fileName = "Student-"+newStudent.getId()+".txt";
//	File file = new File(fileName);
//	System.out.println(file.getAbsolutePath());
//	FileWriter fr=null;
//	try{file.createNewFile();
//	fr = new FileWriter(file);
//	for(int i=0;i<3;i++) {
//	fr.write("Student id:" +saveInfor[i]+ "\n");	
//	}
//	}catch(IOException e) {
//		throw e;
//	}
//	finally {
//		fr.close();
//	
//	}
//	}
	// code  mẫu IO file trên youtube
//	ObjectOutputStream oos=new ObjectOutputStream(fos);
//	oos.writeObject(newStudent);
//	fos.close();
//	oos.close();
	return newStudent;
}

//public static int getIdOfClass (int studentId) {
//	ArrayList<Student> allReturnedStudent = showStudents(classroomList);
//	int id =-1;
//	if(allReturnedStudent!=null) {
//	for(Student std : allReturnedStudent) {
//		if(std.getId()==studentId) {
//			
//			String className = std.getClassName();
//			
//			for(Classroom cls : classroomList) {
//				if(cls.getClassroomName()==className) {
//					id= cls.getId();
//					return id;
//				}
//			}
//			
//		}
//	}
//	}
//	return id;
//}

/**
 * 3.1.2 Sửa sinh viên
 * @throws WrongInputValueException 
 */
public static void modifySudent(int id) throws WrongInputValueException {
	Scanner sc = new Scanner(System.in);
	
	//find all student exist
//	showStudents();
//	System.out.println("Input student id :");
//	int studentId = sc.nextInt();
	sc.nextLine();
	
	if(!idMap.containsKey(id)) {
		
		throw new WrongInputValueException("Wrong modify input id value : "+id);
		
	};
	Student modifyStudent = idMap.get(id);
	System.out.println("input new student name : ");
	String newStudentName = sc.nextLine();
	System.out.println("input new student class name: ");
	String newStudentClassname = sc.nextLine();
	
	modifyStudent.setName(newStudentName);
	modifyStudent.setClassName(newStudentClassname);
	
	// find that existing file and update
	
}
/**
 * 3.1.3 Xóa sinh viên
 * @throws WrongInputValueException 
 */
public static void deleteStudent(int id) throws WrongInputValueException {
	if(!idMap.containsKey(id)) {
		throw new WrongInputValueException("Student id does not exist: "+id);
	}
	idMap.remove(id);
//	classroomList
	for(Classroom cls : classroomList) {
		for(int i=0;i<cls.getStudentList().size();i++) {
			Student std = cls.getStudentList().get(i);
			if(std.getId()==id) {
				cls.getStudentList().remove(std);
			}
		}
	}
	
}

/**
 * 3.2 Thêm student vào class
 * @throws WrongInputValueException 
 */
public static void addStudentToClass() throws WrongInputValueException {
	if(classroomList.size()==0) {
		System.out.println("No class room found!");
		return;
	}
	showStudents();
	Scanner sc = new Scanner(System.in);
	System.out.println("Input student id:");
	int id = sc.nextInt();
	sc.nextLine();
	System.out.println("Choose class :");
	showClassList();
	String className = sc.nextLine();
	Student std =idMap.get(id);

	for(Classroom cls : classroomList) {
		if(cls.getClassroomName().equals(className)) {
			if(cls.getStudentList().contains(std)) {
				System.out.println("Student already in class");
			return;
			}
			else {
				std.setClassName(className);
				cls.getStudentList().add(std);
				List<Student> list =cls.getStudentList();
				for(int i=0;i<list.size();i++) {
					System.out.println(list.get(i));
				}
				break;
			}
			
		}
		else {
			throw new WrongInputValueException("classroom name does not exist");
		}
		
	}
	
}
/**
 * 3.3 Xóa student khỏi class
 * @throws WrongInputValueException 
 */
public static void deleteStudentFromClass() throws WrongInputValueException {
	if(classroomList.size()==0) {
		System.out.println("No class room found!");
		return;
	}
	showClassList();
	Scanner sc = new Scanner(System.in);
	System.out.println("Choose class :");
	String className = sc.nextLine();
	List<Student> stds=null;
	boolean check = false;
	Classroom c=null;
	for(Classroom cls : classroomList) {
		if(cls.getClassroomName().equals(className)) {
			c=cls;
			stds = cls.getStudentList();
			check = true;
			break;
		}
		
	}
	if(check == true) {
	System.out.println("Students in class"+ className + ":");
	for(Student std : stds) {
		System.out.println(std);
	}
	}
	else {
		throw new WrongInputValueException("class does not exist: "+className);
	}
	System.out.println("Input student id:");
	int id = sc.nextInt();
	sc.nextLine();
	
	c.getStudentList().remove(idMap.get(id));
	System.out.println("Students in class "+ className + " after deleting:");
	for(Student std : stds) {
		System.out.println(std);
	}
	
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
