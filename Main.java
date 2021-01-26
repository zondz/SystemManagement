import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
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
 * Chức năng chính
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
 * 4.2 Chọn ra lớp trưởng lớp học
 * 
 * Yêu cầu : ghi thông tin sinh viên , lớp học ra file
 * + file sinh viên gôm : id sinh viên , tên sinh viên , mã lớp 
 * + file lớp học gồm : id , tên lớp , mã sinh viên lớp trưởng 
 * 
 * notes : id trong từng file phải là duy nhất
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
	 * show menu
	 * 
	 * @param args
	 */
	public static void main(String[]args) {
		
	idMap.put(1801040203,new Student());
		System.out.println(idMap.size());
//		showStudents();
//		showClassList();;
		try {
			
			// tao lop
			createNewClass();
//			idClassMap.put(10, cls);
			// tao sinh vien
			Student std = createNewStudent();
			Student std1 = createNewStudent();
//			modifySudent();
			addStudentToClass();
			selectMonitor();
//			modifyClass();
//			removeClass();
//			deleteStudentFromClass();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		
//	File file = new File("Student-1.txt");
//	if(file.exists()) {
//		System.out.print("FileExist");
//		file.delete();
//	}
	}

/**
 *  1. Hiển thị danh sách sinh viên
 */
public static void showStudents() {
	// kiểm tra có sinh viên không 
	if(idMap.isEmpty()) {
		System.out.println("Không có sinh viên !");
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
	 System.out.println("Không có lớp học nào !");
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
public static Student createNewStudent() throws WrongInputValueException {
//	ArrayList<Student> allReturnedStudent= showStudents(classroomList);
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập id của sinh viên mới : ");
	int id = sc.nextInt();
	if(idMap.containsKey(id)) {
		throw new WrongInputValueException("Id của sinh viên mới sai:" +id);
	}
	sc.nextLine();
	System.out.println("Nhập tên sinh viên mới : ");
	String name = sc.nextLine();
	Student newStudent = new Student(id,name);
	idMap.put(id,newStudent);
	
	
	
	// create new file for new Student 
	
//	FileOutputStream fos;
	try {
		FileOutputStream fos = new FileOutputStream("Student-"+id+".txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(newStudent);
		oos.close();
		fos.close();
		FileInputStream fis  = new FileInputStream("Student-"+id+".txt");
		ObjectInputStream obs = new ObjectInputStream(fis);
		System.out.println("Read from file");
		Student student = (Student) obs.readObject();
		System.out.println(student);
		obs.close();
		fis.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	return newStudent;
}

/**
 * 3.1.2 Sửa sinh viên
 * @throws WrongInputValueException 
 */
public static void modifySudent() throws WrongInputValueException {
	System.out.println("Start modifyStudent");
	showStudents();
	System.out.println("Nhập id sinh viên cần sửa : ");
	Scanner sc = new Scanner(System.in);	
	int modifyId = sc.nextInt();
	sc.nextLine();
	if(!idMap.containsKey(modifyId)) {
		
		throw new WrongInputValueException("id không tồn tại : "+modifyId);
		
	};
	
	Student modifyStudent = idMap.get(modifyId);
	// nhập thông tin mới
	System.out.println("nhập tên mới : ");
	String newStudentName = sc.nextLine();
	System.out.println("nhập tên lớp học mới : ");
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
	// Không tồn tại lớp học tên vậy
	if(check==false) {
		throw new WrongInputValueException("Không tồn tại lớp học tên :" +newStudentClassname);
	}
		
	
	modifyStudent.setName(newStudentName);
	modifyStudent.setClassName(newStudentClassname);
		cls.addStudent(modifyStudent);
		
	Iterator<Student> iter1 = cls.getStudentList().iterator();
	while(iter1.hasNext()) {
		System.out.println(iter1.next());
	}
	
	// find that existing file and update
	String fileName = "Student-"+modifyId+".txt";
	try {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(modifyStudent);
		oos.close();
		fos.close();
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		System.out.println("Read from file");
		Student student = (Student) ois.readObject();
		System.out.println(student);
		ois.close();
		fis.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
/**
 * 3.1.3 Xóa sinh viên
 * @throws WrongInputValueException 
 */
public static void deleteStudent() throws WrongInputValueException {
	System.out.println("START DeleteStudent");
	showStudents();
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập id sinh viên để xóa : ");
	int deleteId = sc.nextInt();
	sc.nextLine();
	if(!idMap.containsKey(deleteId)) {
		throw new WrongInputValueException("Id sinh viên không tồn tại: "+deleteId);
	}
	Student deleteStudent = idMap.get(deleteId);
	idMap.remove(deleteId);
	
	Set<Integer> keySet = idClassMap.keySet();

	Iterator<Integer> iter  = keySet.iterator();

	Classroom cls=null;
	// students trong từng  class
	List<Student> students=null;
	
	boolean check = false;
	
	while(iter.hasNext()) {

		cls = idClassMap.get(iter.next());
	
		students = cls.getStudentList();

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
		System.out.println("Xóa xong");
	}
	
	for(int i=0;i<students.size();i++) {
//		System.out.println("Numstudents.size());
		System.out.println(students.get(i));
	}

	System.out.println("DONE deletingStudents");
	System.out.println("Start deleting files");
	String fileName = "Student-"+deleteId+".txt";
	File file = new File (fileName);
	if(file.exists()) {
		file.delete();
	}

	
}

/**
 * 3.2 Thêm sinh viên vào class
 * @throws WrongInputValueException 
 */
public static void addStudentToClass() throws WrongInputValueException {
	// kiá»ƒm tra cÃ³ class nÃ o khÃ´ng 
	if(idClassMap.isEmpty()) {
		System.out.println("Không có bất kì lớp học nào !");
		return;
	}
	
	showStudents();
	// nháº­p id sinh viÃªn
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập id sinh viên để thêm vào lớp:");
	int id = sc.nextInt();
	sc.nextLine();
	
	if(!idMap.containsKey(id)) {
		throw new WrongInputValueException("Không tìm thấy id sinh viên: "+ id);
	}
	// láº¥y ra sinh viÃªn
	Student std =idMap.get(id);
	// chá»�n class Ä‘á»ƒ add 
	System.out.println("nhập tên lớp  :");
	showClassList();
	String className = sc.nextLine();
	//  kiá»ƒm tra class Ä‘Ã³ tá»“n táº¡i khÃ´ng 
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
	// khÃ´ng cÃ³ class tÃªn nhÆ° váº­y
	if(check==false) {
		throw new WrongInputValueException("Lớp không tồn tại : " + className);
	}
	// kiá»ƒm tra student trong class chÆ°a 
	List<Student> students = cls.getStudentList();
	// student da trong class
	for(int i =0;i<students.size();i++) {
		if(students.get(i).getId()==std.getId()) {
			System.out.println("Học sinh đã có sẵn trong lớp ");
			return;
		}
	}
	// student chua trong class

	std.setClassName(cls.getClassroomName());
	String fileName = "Student-"+id+".txt";
	FileOutputStream fos;
	try {
		fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(std);
		oos.close();
		fos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// need to modify the class file also ?
	cls.getStudentList().add(std);
	
	for(int i = 0;i<students.size();i++) {
		System.out.println(students.get(i));
	}
	
	
	System.out.println("ADD CLASS XONGGGGGGG");

}
/**
 * 3.3 XÃ³a student khá»�i class 
 * @throws WrongInputValueException 
 */
public static void deleteStudentFromClass() throws WrongInputValueException {
	System.out.println("Start deleting student from class");
	if(idClassMap.isEmpty()) {
		System.out.println("Không có lớp nào tồn tại!");
		return;
	}
	// show ra cÃ¡c lá»›p Ä‘á»ƒ chá»�n lá»›p
	showClassList();
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập tên lớp :");
	String className = sc.nextLine();
	List<Student> students=null;
	boolean check = false;
	Classroom cls=null;
	
	// kiá»ƒm tra cÃ³ lá»›p Ä‘Ã³ khÃ´ng
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
	// in ra há»�c sinh trong lá»›p
	if(check == true) {
	System.out.println("Học sinh trong lớp "+ className + " :");
	for(Student std : students) {
		System.out.println(std);
	}
	}
	else {
		throw new WrongInputValueException("tên class không tồn tại: "+className);
	}
	System.out.println("Nhập id sinh viên để xóa khỏi lớp:");
	int id = sc.nextInt();
	sc.nextLine();
	// check xem há»�c há»�c sinh nÃ y cÃ³ trong lá»›p khÃ´ng
	boolean inClass = false;
	for(int i =0;i<students.size();i++) {
		if(students.get(i).getId()==id) {
			inClass=true;
			break;
		};
	}
	if(inClass==false) {
		System.out.println("id sinh viên không có trong lớp : "+id);
		return;
	}
	
	// check co phai monitor hay khong -> SAIIIIIIIIIIii
//	boolean monitor =false;
//	if(cls.getMonitorId()==id) {
//		monitor = true;
//	}
//	
	// lÃ  lá»›p trÆ°á»Ÿng , cáº§n chá»‰nh file classroom
	if(cls.getMonitorId()==id) {
		
		cls.setMonitorId(0);
		// Classroom file : Classroom-classroomId
		File file = new File("Classroom-"+cls.getId()+".txt");
		if(file.exists()) {
			file.delete();
		}
		else {
			
//			 + file lá»›p há»�c gá»“m : id lá»›p há»�c , tÃªn lá»›p há»�c , mÃ£ sinh viÃªn cá»§a lá»›p trÆ°á»Ÿng
//			String inputToClassFile ="Class id : " +String.valueOf(cls.getId())+" \n" +cls.getClassroomName()+" \n " +String.valueOf(cls.getMonitorId());
			String[] inputToClassFile = {"class id: " + String.valueOf(cls.getId()),"class name: "+cls.getClassroomName(),"monitor id: "+String.valueOf(cls.getMonitorId())};
			try {
				FileWriter fr = new FileWriter("Classroom-"+cls.getId()+".txt");
				for(int i =0 ;i<inputToClassFile.length;i++) {
					System.out.println(inputToClassFile[i]);
					fr.write(inputToClassFile[i]);
					fr.write("\n");
				}
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	students.remove(idMap.get(id));
	idMap.remove(id);
	System.out.println("Các học sinh trong lớp "+ className + " Sau khi xóa:");
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
	System.out.println("Chọn chức năng :");
	System.out.println("* 4.1.1 Thêm mới lớp học");
	System.out.println("4.1.2 sửa lớp học");
	System.out.println("4.1.3 Xóa lớp học");
	System.out.println("4.2 Chọn lớp trưởng của lớp học");

	
}
/**
 * 4.1.1 Thêm mới lớp học
 * @throws WrongInputValueException 
 */
 public static void createNewClass() throws WrongInputValueException {
	 System.out.println("START CreateNewClass");
	 Scanner sc = new Scanner(System.in);
	 /**
	  * 	private int id;
	private String classroomName;
	private int monitorId;
	private  ArrayList<Student> studentList;
	
	  */
	 System.out.println("input new class id : ");
	 int newClassId = sc.nextInt();
	 sc.nextLine();
	 if(idClassMap.containsKey(newClassId)) {
		 throw new WrongInputValueException("id lớp đã tồn tại: " +newClassId);
	 }
	 // chá»‰ cÃ³ 1 id tÆ°Æ¡ng Ä‘Æ°Æ¡ng má»™t lá»›p 
	 System.out.println("Nhập mới tên lớp học :");
	 String newClassName = sc.nextLine();
	 Set<Integer> keySet = idClassMap.keySet();
	 Iterator<Integer> iter = keySet.iterator();
	 while(iter.hasNext()) {
		 int i = iter.next();
		 if(idClassMap.get(i).getClassroomName().equals(newClassName)) {
			throw new WrongInputValueException("Tên lớp học đã tồn tại : " + newClassName);
		 }
	 }
	 
	 Classroom cls = new Classroom(newClassId, newClassName);
	 idClassMap.put(newClassId, cls);
	String[] inputToClassFile = {"class id: " + String.valueOf(newClassId),"class name: "+newClassName,"monitor id: "+String.valueOf(cls.getMonitorId())};
	try {
		FileWriter fr = new FileWriter("Classroom-"+cls.getId()+".txt");
		for(int i =0 ;i<inputToClassFile.length;i++) {
			System.out.println(inputToClassFile[i]);
			fr.write(inputToClassFile[i]);
			fr.write("\n");
		}
		fr.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	System.out.println("create new class xonggg");
	 showClassList();
	 
	 
 }
 /**
  * 4.1.2 sửa lớp học
 * @throws WrongInputValueException 
  */
 public static void modifyClass() throws WrongInputValueException {
	 System.out.println("Start modifyClass");
	 if(idClassMap.isEmpty()) {
		 System.out.println("Không có lớp để sửa!");
		 return;
	 }
	 showClassList();
	 Scanner sc = new Scanner(System.in);
	 System.out.println("Nhập id lớp để sửa: ");
	 int modifyId = sc.nextInt();
	 sc.nextLine();
	 if(!idClassMap.containsKey(modifyId)) {
		 throw new WrongInputValueException("id lớp không tồn tại : "+modifyId);
	 }
	 System.out.println("Nhập tên lớp mới để sửa: ");
	 String newClassName = sc.nextLine();
	 Set<Integer> keySet = idClassMap.keySet();
	 Iterator<Integer> iter = keySet.iterator();
	 while(iter.hasNext()) {
		 int i = iter.next();
		 if(idClassMap.get(i).getClassroomName().equals(newClassName)) {
			throw new WrongInputValueException("Tên lớp mới đã tồn tại : " + newClassName);
		 }
	 }
	 
	 // check monitor infor 

	Classroom classToModify = idClassMap.get(modifyId);
	classToModify.setClassroomName(newClassName);
	List<Student> students = classToModify.getStudentList();
	if(students.size()==0) {
		System.out.println("Lớp "+newClassName+" Không có học sinh");
		return;
	}
	System.out.println("Chọn lớp trưởng bằng id học sinh  :");
	for(int i = 0; i<students.size();i++) {
		System.out.println(students.get(i));
	}
	int newMonitorId = sc.nextInt();
	sc.nextLine();
	
	boolean check = false;
//	int newMonitorIndex;
	Student newMonitor = null;
	for(int i =0; i<students.size();i++) {
		newMonitor = students.get(i);
		if(newMonitor.getId()== newMonitorId) {
			check = true;
			break;
		}
	}
	if(check == true) {
		classToModify.setMonitorId(newMonitorId);
		String[] inputToClassFile = {"class id: " + String.valueOf(classToModify.getId()),"class name: "+classToModify.getClassroomName(),"monitor id: "+String.valueOf(classToModify.getMonitorId())};
		try {
			FileWriter fr = new FileWriter("Classroom-"+classToModify.getId()+".txt");
			for(int i =0 ;i<inputToClassFile.length;i++) {
				System.out.println(inputToClassFile[i]);
				fr.write(inputToClassFile[i]);
				fr.write("\n");
			}
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	else {
		throw new WrongInputValueException("id học sinh không đúng : "+newMonitorId);
	}
	
	
	System.out.println("MODIFY CLASS XONGGGGGGGGg");
	
	
//	 Classroom cls = idClassMap.get(modifyId);
//	 cls.setClassroomName(newClassName);
//	 cls.set
//	 
//	 
	 
	 
 }
 /**
  * 4.1.3 Xóa lớp học
 * @throws WrongInputValueException 
  */
 public static void removeClass() throws WrongInputValueException {
	 System.out.println("Start removeClass");
	 Scanner sc = new Scanner(System.in);
	 System.out.println("Chọn id lớp cần xóa :");
	 showClassList();
	 int removeClassId = sc.nextInt();
	 if(!idClassMap.containsKey(removeClassId)) {
		throw new WrongInputValueException("id lớp không tồn tại : "+removeClassId);
	 }
	 Classroom classToRemove = idClassMap.get(removeClassId);
	 List<Student> students = classToRemove.getStudentList();
	for(Student s : students) {
		s.setClassName(null);
	}
	
	 idClassMap.remove(removeClassId);
	 File file = new File("Classroom-"+classToRemove.getId()+".txt");
	 if(file.exists()) {
		 file.delete();
	 }
	 System.out.println("Xóa xong!");
	 Set<Integer> keySet=idClassMap.keySet();
	 Iterator<Integer> iter = keySet.iterator();
	 System.out.println("Các lớp học sau khi xóa :");
	 if(idClassMap.size()==0) {
		 System.out.println("Không còn lớp nào!");
	 }
	 while(iter.hasNext()) {
		 System.out.println(idClassMap.get(iter.next()));
	 }
	 showStudents();
	 
 }
 /**
  * 4.2 Chọn lớp trưởng của lớp học
 * @throws WrongInputValueException 
  */
 public static void selectMonitor() throws WrongInputValueException {
	 
	 // chá»�n lá»›p há»�c 
	 System.out.println("Start selectMonitor:");
	 Scanner sc = new Scanner(System.in);
	 System.out.println("Chọn id lớp để chọn lớp trưởng :");
	 showClassList();
	 int classId = sc.nextInt();
	 sc.nextLine();
	 if(!idClassMap.containsKey(classId)) {
			throw new WrongInputValueException("id lớp không tồn tại : "+ classId);
		 }
	 Classroom classToChooseMonitor = idClassMap.get(classId);
	 List<Student> students= classToChooseMonitor.getStudentList();
	 if(students.size()==0) {
		 System.out.println("Lớp "+classToChooseMonitor.getClassroomName()+" Không có học sinh để chọn làm lớp trường ");
		 return;
	 }
	 System.out.println("Chọn lớp trưởng mới bằng id  :");
		for(int i = 0; i<students.size();i++) {
			System.out.println(students.get(i));
		}
		
		// get studnet id to be monitor 
		int newMonitorId = sc.nextInt();
		sc.nextLine();
		
		boolean check = false;
//		int newMonitorIndex;
		Student newMonitor = null;
		for(int i =0; i<students.size();i++) {
			newMonitor = students.get(i);
			if(newMonitor.getId()== newMonitorId) {
				check = true;
				break;
			}
		}
		if(check == true) {
			classToChooseMonitor.setMonitorId(newMonitorId);
			// cập nhật file
			String[] inputToClassFile = {"class id: " + String.valueOf(classToChooseMonitor.getId()),"class name: "+classToChooseMonitor.getClassroomName(),"monitor id: "+String.valueOf(classToChooseMonitor.getMonitorId())};
			try {
				FileWriter fr = new FileWriter("Classroom-"+classToChooseMonitor.getId()+".txt");
				for(int i =0 ;i<inputToClassFile.length;i++) {
					System.out.println(inputToClassFile[i]);
					fr.write(inputToClassFile[i]);
					fr.write("\n");
				}
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		else {
			throw new WrongInputValueException("id không có trong lớp : "+newMonitorId);
		}
		
	System.out.println("Chọn lớp trưởng xong");
	System.out.println("Lớp sau khi chọn lớp trưởng: "+classToChooseMonitor);
	 
 }
 
}
