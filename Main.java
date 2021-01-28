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

 * 
 * Quản lý sinh viên 
 * 1 Hiển thị danh sách sinh viên
 * 2 Thêm sinh viên
 * 3. Sửa sinh viên
 * 4 Xóa sinh viên

 * Quản lý lớp học 
 * 1 Hiển thị danh sách lớp học
 * 2 Thêm lớp học
 * 3 Sửa lớp học
 * 4 Xóa lớp học
 * 5 Chọn ra lớp trưởng lớp học
 * 6 Thêm sinh viên vào trong lớp học
 * 7 Xóa sinh viên khỏi lớp học
 * Yêu cầu : ghi thông tin sinh viên , lớp học ra file
 * + file sinh viên gôm : id sinh viên , tên sinh viên , mã lớp 
 * + file lớp học gồm : id , tên lớp , mã sinh viên lớp trưởng 
 * 
 * notes : id trong từng file phải là duy nhất
 * @author Thanh
 *
 */
public class Main {

/**
 * HashMap
 */
	static HashMap<String,Student> idMap = new HashMap<>();	
	static HashMap<String,Classroom> idClassMap = new HashMap<>();
	/**
	 * show menu
	 * 
	 * @param args
	 */
	public static void main(String[]args) {
		while(true) {
		System.out.println("----------------EDUCATION MANAGEMENT SYSTEM------------------------");
		System.out.println("Chọn chức năng : ");

		System.out.println("1 : Manage student");
		System.out.println("2 : Manage Class");
		System.out.println("X. Thoát");
		Scanner sc = new Scanner(System.in);
		String answer = sc.nextLine();
		switch(answer) {
		case "1" : studentManagement();
		break;
		case "2" : 	manageClasses();
		break;
		
		case "X" : 
			System.out.println("Goodbye !!");
			return;
		
		}
		}

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
	
	Set<String> keySet = idMap.keySet();
//	String[] keys = new String[keySet.size()];
	Iterator<String> iter = keySet.iterator();
	while(iter.hasNext()) {
		System.out.println(idMap.get(iter.next()));
	}

}


/**
 *  2. Hiển thị danh sách lớp học
 */
public static void showClassList() {
	
 if(idClassMap.size()==0) {
	 System.out.println("Không có lớp học nào !");
	 return;
 }
 Set<String> keySet = idClassMap.keySet(); 
	Iterator<String> iter = keySet.iterator();
	Classroom cls= null;
	while(iter.hasNext()) {
		cls = idClassMap.get(iter.next());
		System.out.println("CLass id : "+cls.getId()+" || Class name : "+cls.getClassroomName());
	}
	
}

/**
 * 3. Quản lý sinh viên
 * 
 */
public static void studentManagement() {
	Scanner sc = new Scanner(System.in);
	boolean cont = true;
	while(cont) {
	System.out.println("Chọn chức năng để quản lý sinh viên : ");
	System.out.println("1 : Hiển thị danh sách sinh viên");
	System.out.println("2 : Thêm sinh viên");
	System.out.println("3 : Sửa sinh viên");
	System.out.println("4 : Xóa sinh viên");
	System.out.println("X : Thoát quản lý sinh viên");
	String choice = sc.nextLine();
	switch(choice) {
	case "1" : showStudents();
	break;
	case "2" : try {
			createNewStudent();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	break;
	case "3" : try {
			modifySudent();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	break;
	case "4" : try {
			deleteStudent();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	break;

	case "X"  : 
	return;
	}
	}
		
}

/**
 * 3.1.1 Thêm sinh viên
 * @throws WrongInputValueException 
 * @throws IOException 
 * @throws Exception 
 */
public static void createNewStudent() throws WrongInputValueException {
//	ArrayList<Student> allReturnedStudent= showStudents(classroomList);
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập id của sinh viên mới (ex : 1801040203) : ");
	String newStudentId = sc.nextLine();
	// function chỉ kiểm tra xem có trùng lặp không thôi , còn điều kiện các kiểu phải tự check
	if(idMap.containsKey(newStudentId)) {
		throw new WrongInputValueException("Id của sinh viên mới bị trùng : " +newStudentId);
	}
	
	System.out.println("Nhập tên sinh viên mới (số kí tự tối thiểu >= 1) : ");
	String name = sc.nextLine();
	Student newStudent = new Student(newStudentId,name);
//	System.out.println("Không đến được đây");
	idMap.put(newStudentId,newStudent);
	
	
	
	// create new file for new Student 

	try {
		FileOutputStream fos = new FileOutputStream("Student-"+newStudent.getId()+".txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(newStudent);
		oos.close();
		fos.close();

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	System.out.println("Tạo sinh viên thành công");
}

/**
 * 3.1.2 Sửa sinh viên
 * @throws WrongInputValueException 
 */
public static void modifySudent() throws WrongInputValueException {

	System.out.println("Bắt đầu modifyStudent...");
	if(idMap.isEmpty()) {
		System.out.println("Không có sinh viên đê sửa !!");
		return;
	}
	showStudents();
	System.out.println("Nhập id sinh viên cần sửa : ");
	Scanner sc = new Scanner(System.in);	
	String modifyStudentId = sc.nextLine();
	if(!idMap.containsKey(modifyStudentId)) {
		
		throw new WrongInputValueException("id không tồn tại : "+modifyStudentId);
		
	};
	
	Student modifyStudent = idMap.get(modifyStudentId);
	// nhập thông tin mới
	System.out.println("nhập tên mới : ");
	String newStudentName = sc.nextLine();
	// sửa 
	
	
	modifyStudent.setName(newStudentName);
	System.out.println("Sửa tên sinh viên xong : "+ modifyStudent);
	
	System.out.println("Cho Sửa lớp học của sinh viên bằng nhập Y/N ");
	String answer = sc.nextLine();
	// cho sửa lớp học
	if(answer.equals("Y")) {
		
		
		if(idClassMap.isEmpty()) {
			System.out.println("Không có lớp để chọn ");
		}
		else {
			showClassList();
			System.out.println("nhập id lớp học mới : ");

			String newStudentClassId = sc.nextLine();
			// check if class room exist ?
			Set<String> keySet = idClassMap.keySet();
			Iterator<String> iter = keySet.iterator();
			boolean check=false;
			Classroom cls=null;
			List<Student> students = null;

			if(keySet.contains(newStudentClassId)) {
				check = true;
				cls=idClassMap.get(newStudentClassId);
				students = cls.getStudentList();
//				List<Student> students =null;
			}
			// Không tồn tại lớp học tên vậy
			if(check==false) {
				throw new WrongInputValueException("Không tồn tại id lớp học :" +newStudentClassId);
			}

			// nốt logic ở đây nữa
			
			
			// kiểm tra xem student này đã có trong lớp nào chưa -> xóa student này khỏi student list đó
			if(modifyStudent.getClassName()!=null) {
//				boolean checkHasClass = false;
				Classroom cl ;
				Set<String> classIdSet = idClassMap.keySet();
				Iterator<String> classIdIter = classIdSet.iterator();
				while(classIdIter.hasNext()) {
					 cl = idClassMap.get(classIdIter.next());
					if(cl.getClassroomName().equals(modifyStudent.getClassName())) {
						// compare student to remove by id
						cl.getStudentList().remove(modifyStudent);
						
					
					}
				} 
				
			}
			// nếu chưa có -> thêm vào lớp
			students.add(modifyStudent);
			
			modifyStudent.setClassName(cls.getClassroomName());
			
		}
		
	}
	
	// find that existing file and update
	String fileName = "Student-"+modifyStudent.getId()+".txt";
	try {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(modifyStudent);
		oos.close();
		fos.close();

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Chỉnh sủa học sinh xong!");
	
}
/**
 * 3.1.3 Xóa sinh viên
 * @throws WrongInputValueException 
 */
public static void deleteStudent() throws WrongInputValueException {
	System.out.println("Bắt đầu xóa sinh viên...");
	showStudents();
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập id sinh viên để xóa : ");
	String deleteStudentId = sc.nextLine();
	if(!idMap.containsKey(deleteStudentId)) {
		throw new WrongInputValueException("Id sinh viên không tồn tại: "+deleteStudentId);
	}
	Student deleteStudent = idMap.get(deleteStudentId);
	idMap.remove(deleteStudentId);
	
	Set<String> keySet = idClassMap.keySet();

	Iterator<String> iter  = keySet.iterator();

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
				idMap.remove(s.getId());
				check = true;
				break;
			}
		
		}
		if(check == true) {
			break;
		}		
		
	}


	System.out.println("Start deleting file");
	String fileName = "Student-"+deleteStudentId+".txt";
	File file = new File (fileName);
	if(file.exists()) {
		file.delete();
	}
	System.out.println("Xóa học sinh xong");	

	
}

/**
 * 3.2 Thêm sinh viên vào class
 * @throws WrongInputValueException 
 */
public static void addStudentToClass() throws WrongInputValueException {
	if(idClassMap.isEmpty()) {
		System.out.println("Không có bất kì lớp học nào !");
		return;
	}
	// show tất cả student ?? -> chỉ show những student chưa có class - > className  = null
	List<Student> students = new ArrayList<Student>();
	Set<String> studentKeySet = idMap.keySet();
	Iterator<String> iter = studentKeySet.iterator();
	Student s =null;
	while(iter.hasNext()) {
		s = idMap.get(iter.next());
		if(s.getClassName()==null) {
			students.add(s);
		}
	}
	if(students.size()==0) {
		System.out.println("Không có sinh viên hợp lệ để thêm vào lớp !!");
		return;
	}
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập id sinh viên để thêm vào lớp:");
	// show sinh viên để chọn 
	for(int i = 0;i<students.size();i++) {
		System.out.println(students.get(i));
	}
	
	String id = sc.nextLine();
	
	// check xem student này có nằm trong list cho phép không 
	boolean checkInList = false;
	for(int i = 0;i<students.size();i++) {
		// nếu nằm trong list className = null
		if(students.get(i).getId().equals(id)) {
			checkInList = true;
			break;
		}
	}
	
	if(!checkInList) {
		throw new WrongInputValueException("Không tìm thấy id sinh viên: "+ id);
	}
	
	// lấy sinh viên
	Student std =idMap.get(id);
	
	System.out.println("nhập id lớp  :");
	showClassList();
	String classToAddStudentId = sc.nextLine();
	
	//Kiểm tra có tồn tại lớp không
	Set<String> keySet = idClassMap.keySet();
	Iterator<String> classListIter = keySet.iterator();
	boolean check=false;
	Classroom cls=null;
	List<Student> studentsInCls=null;
	while(classListIter.hasNext()) {
		cls = idClassMap.get(classListIter.next());
		studentsInCls = cls.getStudentList();
		if(cls.getId().equals(classToAddStudentId)) {
			check= true;
			break;
		}
	}
	
	// Không tồn tại lớp với tên như vậy
	if(check==false) {
		throw new WrongInputValueException("id lớp không tồn tại : " + classToAddStudentId);
	}
	
	std.setClassName(cls.getClassroomName());
	// Không cần chỉnh class file vì class file chỉ có monitor id
	cls.getStudentList().add(std);
	// cập nhật file student
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
	
	System.out.println("Học sinh trong lớp sau khi thêm :");
	for(int i = 0;i<studentsInCls.size();i++) { // modify -------------------------------------
		System.out.println(studentsInCls.get(i));
	}
	
	
	System.out.println("Thêm vào lớp xong");

}
/**
 * 3.3 Xóa student khỏi class
 * @throws WrongInputValueException 
 */
public static void deleteStudentFromClass() throws WrongInputValueException {
	System.out.println("Start deleting student from class");
	if(idClassMap.isEmpty()) {
		System.out.println("Không có lớp nào tồn tại!");
		return;
	}
	// show class để xóa student
	showClassList();
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập id lớp : ");
	String classToDeleteStudentId = sc.nextLine();
	List<Student> students=null;
	boolean check = false;
	Classroom cls=null;
	
	// Kiểm tra có lớp không
	Set<String> keySet = idClassMap.keySet();
	Iterator<String> iter = keySet.iterator();
	while(iter.hasNext()) {
		cls = idClassMap.get(iter.next());
		students=cls.getStudentList();
		if(cls.getId().equals(classToDeleteStudentId)) {
			check = true;
			break;
		}
		
	}
	// in ra học sinh trong lớp
	if(check == true) {
		// không có học sinh trong lớp
		if(cls.getStudentList().size()==0) {
			System.out.println("Không có học sinh trong lớp");
			return;
		}
	System.out.println("Học sinh trong lớp "+ cls.getClassroomName() + " :");
	for(Student std : students) {
		System.out.println("Student id :"+std.getId() +" || Student name : "+std.getName());
	}
	}
	else {
		throw new WrongInputValueException("id lớp không tồn tại: "+classToDeleteStudentId);
	}
	
	System.out.println("Nhập id sinh viên để xóa khỏi lớp:");
	String DeleteStudentid = sc.nextLine();
	
	// check xem student có trong lớp đó không ?
	boolean inClass = false;
	for(int i =0;i<students.size();i++) {
		if(students.get(i).getId().equals(DeleteStudentid)) {
			inClass=true;
			break;
		};
	}
	if(inClass==false) {
		throw new WrongInputValueException("id sinh viên không có trong lớp : "+DeleteStudentid);
	}
	
	// là lớp trưởng -> chỉnh classroom file
	if(cls.getMonitorId()==DeleteStudentid) {
		
		cls.setMonitorId(null);
		// Classroom file : Classroom-classroomId
		File file = new File("Classroom-"+cls.getId()+".txt");
		if(file.exists()) {
			file.delete();
		}
		else {
			

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
	// chỉnh sửa student class thành null
	Student s = idMap.get(DeleteStudentid);
	s.setClassName(null);
	students.remove(idMap.get(DeleteStudentid));
//	idMap.remove(DeleteStudentid);

	System.out.println("Xóa học sinh khỏi lớp xong ");
	
}
/**
 * 4. Quản lý lớp học
 * 
 */
public static void manageClasses() {
	Scanner sc = new Scanner(System.in);
	while(true) {
	System.out.println("Chọn chức năng quản lý lớp học :");
	System.out.println("1: Hiển thị danh sách lớp học");
	System.out.println("2: Thêm mới lớp học");
	System.out.println("3: sửa lớp học");
	System.out.println("4: Xóa lớp học");
	System.out.println("5: Chọn lớp trưởng của lớp học");
	System.out.println("6: Thêm sinh viên vào lớp học");
	System.out.println("7: Xóa sinh viên khỏi lớp học");
	System.out.println("X : Thoát quản lý lớp học!");
	
	String answer = sc.nextLine();
	switch(answer){
	case "1" : showClassList();
	break;
	case "2" : try {
			createNewClass();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	break;
	case "3" : try {
			modifyClass();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	break;
	case "4" : try {
			removeClass();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	break;
	case "5" : try {
			selectMonitor();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	break;
	case "6" : try {
			addStudentToClass();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	break;
	case "7" : try {
			deleteStudentFromClass();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	break;
	case "X" : return;
	}
	}
	
}
/**
 * 4.1.1 Thêm mới lớp học
 * @throws WrongInputValueException 
 */
 public static void createNewClass() throws WrongInputValueException {
	 System.out.println("Bắt đầu tạo lớp mới ..");
	 Scanner sc = new Scanner(System.in);

	 System.out.println("nhập Id của lớp mới  (ex : 0001) : ");
	 String newClassId = sc.nextLine();
	 
	 if(idClassMap.containsKey(newClassId)) {
		 throw new WrongInputValueException("id lớp đã tồn tại: " +newClassId);
		
	 }
	 System.out.println("Nhập mới tên lớp học (số kí tự tối thiểu >=4 ):");
	 String newClassName = sc.nextLine();

	 // class id 
	 Classroom cls = new Classroom(newClassId, newClassName);
	 idClassMap.put(newClassId, cls);
	String[] inputToClassFile = {"class id: " + String.valueOf(newClassId),"class name: "+newClassName,"monitor id: "+String.valueOf(cls.getMonitorId())};
	try {
		FileWriter fr = new FileWriter("Classroom-"+cls.getId()+".txt");
		for(int i =0 ;i<inputToClassFile.length;i++) {
//			System.out.println(inputToClassFile[i]);
			fr.write(inputToClassFile[i]);
			fr.write("\n");
		}
		fr.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	System.out.println("Tạo lớp mới xong");
//	 showClassList();
	 
	 
 }
 /**
  * 4.1.2 sửa lớp học
 * @throws WrongInputValueException 
  */
 public static void modifyClass() throws WrongInputValueException {
	 System.out.println("Bắt đầu sửa lớp");
	 if(idClassMap.isEmpty()) {
		 System.out.println("Không có lớp để sửa!");
		 return;
	 }
	 showClassList();
	 Scanner sc = new Scanner(System.in);
	 System.out.println("Nhập id lớp để sửa: ");
	 String modifyClassId = sc.nextLine();
	 
	 if(!idClassMap.containsKey(modifyClassId)) {
		throw new WrongInputValueException("id lớp không tồn tại : "+modifyClassId);
	 }
	 System.out.println("Nhập tên lớp mới để sửa: ");
	 String newClassName = sc.nextLine();
	 
	 // kiểm tra tên lớp có tồn tại không -> không được trùng với các tên lớp khác
	 Set<String> keySet = idClassMap.keySet();
	 Iterator<String> iter = keySet.iterator();
	 while(iter.hasNext()) {
		 String i = iter.next();
		 if(idClassMap.get(i).getClassroomName().equals(newClassName)) {
		throw new WrongInputValueException("Tên lớp mới đã tồn tại : " + newClassName);
		 }
	 }
	 
	 // check monitor infor 
	Classroom classToModify = idClassMap.get(modifyClassId);
	
	// thay đổi tên lớp học
	classToModify.setClassroomName(newClassName);
	
	// thêm if có muốn thay đổi id lớp trưởng không 
	
	List<Student> students = classToModify.getStudentList();
	if(students.size()==0) {
		System.out.println("Lớp "+newClassName+" Không có học sinh");
		return;
	}
	System.out.println("Chọn lớp trưởng bằng id học sinh  :");
	for(int i = 0; i<students.size();i++) {
		System.out.println(students.get(i));
	}
	String newMonitorId = sc.nextLine();

	// kiểm tra xem id nhập có tồn tại trong lớp không
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
	
	
	System.out.println("Sửa lớp học xong");
	
 }
 /**
  * 4.1.3 Xóa lớp học
 * @throws WrongInputValueException 
  */
 public static void removeClass() throws WrongInputValueException {
	 System.out.println("Start removeClass");
	 Scanner sc = new Scanner(System.in);
	 showClassList();
	 System.out.println("Chọn id lớp cần xóa :");
	
	 String removeClassId = sc.nextLine();
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
	 Set<String> keySet=idClassMap.keySet();
	 Iterator<String> iter = keySet.iterator();
	 System.out.println("Các lớp học sau khi xóa :");
	 if(idClassMap.size()==0) {
		 System.out.println("Không còn lớp nào!");
//		 System.out.println("Kiểm tra các học sinh xem còn lớp không : ");
//		 showStudents();
		 return;
	 }
	 
	 while(iter.hasNext()) {
		 Classroom cls = idClassMap.get(iter.next());
		 System.out.println("Class id : "+cls.getId()+" || Class name : "+cls.getClassroomName());
	 }
//	 showStudents();
	 
 }
 /**
  * 4.2 Chọn lớp trưởng của lớp học
 * @throws WrongInputValueException 
  */
 public static void selectMonitor() throws WrongInputValueException {
	 
	 System.out.println("Bắt đầu chọn lớp trưởng :");
	 Scanner sc = new Scanner(System.in);
	 
	 
	 if(idClassMap.isEmpty()) {
		 System.out.println("không có lớp phù hợp!");
		 return;
	 }
	 
	 showClassList();
	 System.out.println("Chọn id lớp để chọn lớp trưởng :");
	 
	 String classId = sc.nextLine();
	 
	 // kiểm tra lớp tồn tại không
	 if(!idClassMap.containsKey(classId)) {
			throw new WrongInputValueException("id lớp không tồn tại : "+ classId);
			
		 }
	 // lấy lớp ra 
	 Classroom classToChooseMonitor = idClassMap.get(classId);
	 
	 // không có hs
	 List<Student> students= classToChooseMonitor.getStudentList();
	 if(students.size()==0) {
		 System.out.println("Lớp "+classToChooseMonitor.getClassroomName()+" Không có học sinh để chọn làm lớp trường ");
		 return;
	 }
	 
	 // có học sinh
	 System.out.println("Chọn lớp trưởng mới bằng id  :");
		for(int i = 0; i<students.size();i++) {
			System.out.println(students.get(i));
		}
		
		// nhập id của hs để làm lớp trưởng
		String newMonitorId = sc.nextLine();
		
		// kiểm tra id hs tồn tại trong lớp không 
		boolean check = false;
//		int newMonitorIndex;
		Student newMonitor = null;
		for(int i =0; i<students.size();i++) {
			newMonitor = students.get(i);
			if(newMonitor.getId().equals(newMonitorId)) {
				check = true;
				break;
			}
		} // tồn tại
		if(check == true) {
			classToChooseMonitor.setMonitorId(newMonitorId);
			// cập nhật file
			String[] inputToClassFile = {"class id: " + String.valueOf(classToChooseMonitor.getId()),"class name: "+classToChooseMonitor.getClassroomName(),"monitor id: "+String.valueOf(classToChooseMonitor.getMonitorId())};
			try {
				FileWriter fr = new FileWriter("Classroom-"+classToChooseMonitor.getId()+".txt");
				for(int i =0 ;i<inputToClassFile.length;i++) {
//					System.out.println(inputToClassFile[i]);
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
//	System.out.println("Lớp sau khi chọn lớp trưởng: "+classToChooseMonitor);
	 
 }
 
}
